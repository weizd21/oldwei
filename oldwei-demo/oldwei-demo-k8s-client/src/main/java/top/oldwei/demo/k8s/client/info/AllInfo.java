package top.oldwei.demo.k8s.client.info;

import com.google.common.collect.Lists;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.ReplicaSet;
import io.fabric8.kubernetes.api.model.metrics.v1beta1.*;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.VersionInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import top.oldwei.demo.k8s.client.test.GetClient;
import top.oldwei.demo.k8s.client.util.Str2NumUtil;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: weizd
 * @time: 2020/12/16 10:11 下午
 */
@Slf4j
public class AllInfo {





    public static void main(String[] args) throws Exception{
        long start = System.currentTimeMillis();
//        KubernetesClient client = GetClient.getClientWithConfig(null);
        KubernetesClient client = GetClient.getClientWithToken(null,null);


        List<String> apiPath = Lists.newArrayList();

//        apiPath.addAll(client.apps().rootPaths().getPaths());
        apiPath.addAll(client.rootPaths().getPaths());



        log.info("rootPath : [{}]",client.apps().rootPaths().getPaths());

        client.resourceQuotas().list().getItems().forEach(resourceQuota -> {
            log.info("{}",resourceQuota);
        });

        clusterInfo(client);

        nodeInfo(client);
        podInfo(client);
        namespaceInfo(client);
        ingressInfo(client);
        persistentVolumeInfo(client);
        deploymentInfo(client);
        statefulSetInfo(client);
        jobInfo(client);
        daemonSetInfo(client);
        serviceInfo(client);

        replicaSetInfo(client);
        replicaControllerInfo(client);
        log.info("get all info take :[{} ms]",System.currentTimeMillis() - start);
    }

    public static void clusterInfo(KubernetesClient client){

        client.rootPaths().getPaths().forEach(path -> {
            if(path.contains("management")){
//                log.info(path);
            }
        });

        client.resourceQuotas().list().getItems().forEach(resourceQuota -> {
//            log.info("{}",resourceQuota);
        });

//        client.v1().

        VersionInfo versionInfo = client.getVersion();

        log.info("{}",versionInfo);

    }

    public static void nodeInfo(KubernetesClient client){

        Integer totalNode = 0;
        Integer totalPod = 0;
        Integer totalCpu = 0;
        Long totalMemory = 0L;


        NodeList nodeList = client.nodes().list();
        for(Node node:nodeList.getItems()){
//            log.info("node :{}",node);
            totalNode++;
            totalPod = totalPod + Integer.valueOf(node.getStatus().getAllocatable().get("pods").toString());
            totalCpu = totalCpu + Integer.valueOf(node.getStatus().getAllocatable().get("cpu").toString());
            totalMemory = totalMemory + Str2NumUtil.getMemoryValue(node.getStatus().getAllocatable().get("memory").toString());
        }
        log.info("node size: [{}]",totalNode);
        log.info("total memory size: [{}]",totalMemory);
        log.info("total cpu num: [{}]",totalCpu);
        log.info("total pod num: [{}]",totalPod);


        long totalNodeUseMemory = 0;
        long totalNodeUseCpu = 0;
        NodeMetricsList nodeMetricList = client.top().nodes().metrics();
        for(NodeMetrics nodeMetrics: nodeMetricList.getItems()){
//            log.info("nodeMetric {}",nodeMetrics);
            totalNodeUseCpu = totalNodeUseCpu + Str2NumUtil.getCpuValue(nodeMetrics.getUsage().get("cpu").toString());
            totalNodeUseMemory = totalNodeUseMemory = totalNodeUseMemory + Str2NumUtil.getMemoryValue(nodeMetrics.getUsage().get("memory").toString());
        }

        log.info("totalNodeUseMemory :[{}]",totalNodeUseMemory);
        log.info("totalNodeUseCpu :[{}]",totalNodeUseCpu);
    }

    public static void podInfo(KubernetesClient client){
        Integer totalUsePod = 0;
        PodList podList = client.pods().list();
        for(Pod pod:podList.getItems()){
//            log.info("pod :{}",pod);
            totalUsePod++;
        }

        long totalUseCpu = 0;
        long totalUseMemory = 0;

        PodMetricsList podMetricsList = client.top().pods().metrics();
        for(PodMetrics podMetrics: podMetricsList.getItems()){
//            log.info("podMetric: {}",podMetrics);
            for(ContainerMetrics containerMetrics: podMetrics.getContainers()){
                totalUseMemory = totalUseMemory + Str2NumUtil.getMemoryValue(containerMetrics.getUsage().get("memory").toString());
                totalUseCpu = totalUseCpu + Str2NumUtil.getCpuValue(containerMetrics.getUsage().get("cpu").toString());
            }
        }

        log.info("totalUsePod :[{}]",totalUsePod);
        log.info("totalPodUseMemory :[{}]",totalUseMemory);
        log.info("totalPodUseCpu :[{}]",totalUseCpu);
    }




    public static void namespaceInfo(KubernetesClient client){
        log.info("namespace size: [{}]", client.namespaces().list().getItems().size());
    }

    public static void ingressInfo(KubernetesClient client){
        log.info("ingress size: [{}]", client.network().ingresses().list().getItems().size());
    }
    public static void persistentVolumeInfo(KubernetesClient client){
        log.info("persistentVolume size: [{}]", client.persistentVolumes().list().getItems().size());
    }

    public static void deploymentInfo(KubernetesClient client){
        log.info("deployment size: [{}]", client.apps().deployments().list().getItems().size());

        Integer replicas = 0;

        for(Deployment deployment:client.apps().deployments().list().getItems()){
//            log.info("{}",deployment);

            replicas = replicas + deployment.getSpec().getReplicas();

            List<Container> containers = deployment.getSpec().getTemplate().getSpec().getContainers();
            for(Container container:containers){
                Map<String,Quantity> limits = container.getResources().getLimits();
                if(null != limits){
//                    log.info("limit: [cpu:{} ,memory:{}]",limits.get("cpu"),limits.get("memory"));
                }
                Map<String,Quantity> requests = container.getResources().getRequests();
                if(null != requests){
                    log.info("request: [cpu:{} ,memory:{}]",requests.get("cpu"),requests.get("memory"));
                }else if(null != limits){
                    log.info("limit: [cpu:{} ,memory:{}]",limits.get("cpu"),limits.get("memory"));
                }
            }
        }


        log.info("replicas :{}",replicas);

    }

    public static void statefulSetInfo(KubernetesClient client){
        log.info("statefulSet size: [{}]", client.apps().statefulSets().list().getItems().size());

        client.apps().statefulSets().list().getItems().forEach(statefulSet -> {
            log.info("{}",statefulSet);
        });

    }
    public static void jobInfo(KubernetesClient client){
        log.info("job size: [{}]", client.batch().jobs().list().getItems().size());
    }
    public static void daemonSetInfo(KubernetesClient client){
        log.info("daemonSet size: [{}]", client.apps().daemonSets().list().getItems().size());
    }

    public static void serviceInfo(KubernetesClient client){
        log.info("service size: [{}]", client.services().list().getItems().size());
    }



     public static void replicaSetInfo(KubernetesClient client){
        log.info("replicaSet size: [{}]", client.apps().replicaSets().list().getItems().size());
         client.apps().replicaSets().list().getItems().forEach(replicaSet -> {
//             log.info("{}",replicaSet);
         });

         Long totalRequestMemory = 0L;
         Long totalRequestCpu = 0L;

         for(ReplicaSet replicaSet:client.apps().replicaSets().list().getItems()){
             List<Container> containers = replicaSet.getSpec().getTemplate().getSpec().getContainers();
             int replicas = replicaSet.getSpec().getReplicas();
             replicas = replicaSet.getStatus().getAvailableReplicas();
             for(Container container:containers){
                 Map<String,Quantity> limits = container.getResources().getLimits();
                 if(null != limits){
//                    log.info("limit: [cpu:{} ,memory:{}]",limits.get("cpu"),limits.get("memory"));
                 }
                 Map<String,Quantity> requests = container.getResources().getRequests();
                 if(null != requests){
//                     log.info("request: [cpu:{} ,memory:{}]",requests.get("cpu"),requests.get("memory"));
                     if(!StringUtils.isEmpty(requests.get("cpu"))){
                         totalRequestCpu = totalRequestCpu + Str2NumUtil.getCpuValue(requests.get("cpu").toString())*replicas;
                     }
                     if(!StringUtils.isEmpty(requests.get("memory"))){
                         totalRequestMemory = totalRequestMemory + Str2NumUtil.getMemoryValue(requests.get("memory").toString());
                     }
                  }else if(null != limits){
                     log.info("limit: [cpu:{} ,memory:{}]",limits.get("cpu"),limits.get("memory"));
                 }
             }
         }
         log.info("totalRequestCpu: {}",totalRequestCpu);
         log.info("totalRequestMemory: {}",totalRequestMemory);


    }

     public static void replicaControllerInfo(KubernetesClient client){
        log.info("replicationController size: [{}]", client.replicationControllers().list().getItems().size());
         client.replicationControllers().list().getItems().forEach(replicationController -> {
             log.info("{}",replicationController);

         });
    }







}
