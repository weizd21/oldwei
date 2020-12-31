package top.oldwei.demo.k8s.client.info;

import io.fabric8.kubernetes.api.model.EventList;
import io.fabric8.kubernetes.client.KubernetesClient;
import top.oldwei.demo.k8s.client.test.GetClient;

/**
 * @description:
 * @author: weizd
 * @time: 2020/12/25 2:23 下午
 */
public class EventInfo {

    public static void main(String[] args) throws Exception{


        KubernetesClient client = GetClient.getClientWithConfig(null);

        EventList eventList = client.v1().events().list();
        eventList.getItems().forEach(e -> System.out.println(e.getMessage()));
    }
}
