package top.oldwei.demo.k8s.client.seldon;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.PodSpec;
import io.fabric8.kubernetes.api.model.Quantity;
import io.fabric8.kubernetes.api.model.ResourceRequirements;
import io.fabric8.kubernetes.api.model.SecurityContext;
import io.fabric8.kubernetes.api.model.autoscaling.v2beta1.MetricSpec;
import io.fabric8.kubernetes.api.model.autoscaling.v2beta1.ResourceMetricSource;
import io.fabric8.kubernetes.client.utils.Serialization;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: weizd
 * @time: 2021/6/4 1:26 下午
 */

@Slf4j
public class SeldonDeploymentCURD {


    public static void main(String[] args) {
        create();
    }


    public static void create(){
        SeldonDeployment seldonDeployment = new SeldonDeployment();

        ObjectMeta objectMeta = new ObjectMeta();
        objectMeta.setName("deployemnt-name");
        objectMeta.setNamespace("namespace");
        seldonDeployment.setMetadata(objectMeta);

        SeldonDeploymentSpec seldonDeploymentSpec = new SeldonDeploymentSpec();
        seldonDeploymentSpec.setName("spec-name");
        Map<String,String> annotation = Maps.newHashMap();
        annotation.put("seldon.io/executor","true");
        annotation.put("seldon.io/istio-gateway","istio-system/seldon-gateway");
        seldonDeploymentSpec.setAnnotations(annotation);

        PredictorSpec predictorSpec = new PredictorSpec();

        //
        SeldonPodSpec seldonPodSpec = new SeldonPodSpec();

        SeldonHpaSpec seldonHpaSpec = new SeldonHpaSpec();
        seldonHpaSpec.setMaxReplicas(2);
        seldonHpaSpec.setMinReplicas(1);
        MetricSpec cpuMetricSpec = new MetricSpec();
        ResourceMetricSource cpuResourceMetricSource = new ResourceMetricSource();
        cpuResourceMetricSource.setName("cpu");
        cpuResourceMetricSource.setTargetAverageUtilization(50);
        cpuMetricSpec.setResource(cpuResourceMetricSource);

        MetricSpec memoryMetricSpec = new MetricSpec();
        ResourceMetricSource memoryResourceMetricSource = new ResourceMetricSource();
        memoryResourceMetricSource.setName("memory");
        memoryResourceMetricSource.setTargetAverageUtilization(50);
        memoryMetricSpec.setResource(memoryResourceMetricSource);

        seldonHpaSpec.setMetrics(new MetricSpec[]{cpuMetricSpec,memoryMetricSpec});
        seldonPodSpec.setHpaSpec(seldonHpaSpec);


        PodSpec podSpec = new PodSpec();
        List<Container> containers = Lists.newArrayList();

        Container container = new Container();
        container.setName("classifier");
        container.setImage("local.harbor.io/ai-platform/ai-models:v0.0.0");
        container.setImagePullPolicy("IfNotPresent");
        SecurityContext securityContext = new SecurityContext();
        securityContext.setRunAsUser(0L);

        ResourceRequirements resourceRequirements = new ResourceRequirements();
        Map<String, Quantity> limits = new HashMap<String, Quantity>();
        limits.put("cpu",new Quantity("1024m"));
        limits.put("memory",new Quantity("1024m"));
        limits.put("nvidia.com/gpu",new Quantity("0"));


        Map<String, Quantity> requests = new HashMap<String, Quantity>();
        requests.put("cpu",new Quantity("2024m"));
        requests.put("memory",new Quantity("2048m"));


        resourceRequirements.setLimits(limits);
        resourceRequirements.setRequests(requests);
        container.setResources(resourceRequirements);
        container.setSecurityContext(securityContext);
        containers.add(container);
        podSpec.setContainers(containers);
        seldonPodSpec.setSpec(podSpec);


        PredictiveUnit predictiveUnit = new PredictiveUnit();
        predictiveUnit.setChildren(new PredictiveUnit[]{});
        predictiveUnit.setImplementation(null);

        Endpoint endpoint = new Endpoint();
        endpoint.setType("REST");
        predictiveUnit.setEndpoint(endpoint);

        predictiveUnit.setModelUri("");
        predictiveUnit.setEnvSecretRefName("seldon-init-container-secret");
        predictiveUnit.setName("classifier");

        predictorSpec.setGraph(predictiveUnit);

        predictorSpec.setName("default");
        predictorSpec.setReplicas(1);

        predictorSpec.setComponentSpecs(new SeldonPodSpec[]{seldonPodSpec});
        seldonDeploymentSpec.setPredictors(new PredictorSpec[]{predictorSpec});

        seldonDeployment.setSpec(seldonDeploymentSpec);


        log.info("{}", Serialization.asJson(seldonDeployment));
        System.out.println(Serialization.asJson(seldonDeployment));
    }


    public static void delete(){

    }






}
