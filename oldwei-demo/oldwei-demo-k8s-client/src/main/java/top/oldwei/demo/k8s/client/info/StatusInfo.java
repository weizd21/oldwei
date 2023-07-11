package top.oldwei.demo.k8s.client.info;

import io.fabric8.kubernetes.api.model.Cluster;
import io.fabric8.kubernetes.api.model.EventList;
import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.extern.slf4j.Slf4j;
import top.oldwei.demo.k8s.client.test.GetClient;

/**
 * @description:
 * @author: weizd
 * @time: 2020/12/25 3:22 下午
 */
@Slf4j
public class StatusInfo {

    public static void main(String[] args) throws Exception{

        KubernetesClient client = GetClient.getClientWithConfig(null);

//        EventList eventList = client.v1().events().list();
//        eventList.getItems().forEach(e -> System.out.println(e.getMessage()));
//

        client.nodes();
        client.namespaces();
        client.pods();
        client.secrets().list().getItems().forEach(secret -> {
            log.info("{}",secret);
        });

        client.storage();
        client.serviceAccounts();





//        client.settings().podPresets().list().getItems().forEach(podPreset -> {
//            log.info("{}",podPreset);
//        });

        client.services();

        client.apps().replicaSets();
        client.apps().statefulSets();
        client.apps().deployments();
        client.apps().daemonSets();

        client.v1().events();


        client.apiextensions().v1().customResourceDefinitions();
        client.apiextensions().v1beta1().customResourceDefinitions();

        client.extensions().ingresses();

        client.network().ingress();
        client.network().ingresses();



    }
}
