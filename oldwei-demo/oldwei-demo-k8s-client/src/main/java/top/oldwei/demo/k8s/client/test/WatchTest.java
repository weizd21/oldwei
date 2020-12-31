package top.oldwei.demo.k8s.client.test;

import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watcher;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: weizd
 * @time: 2020/12/12 12:51 下午
 */
@Slf4j
public class WatchTest {


    public static void main(String[] args) {

        KubernetesClient client;

        try {
            client = GetClient.getClientWithConfig(null);

            client.pods().watch(new Watcher<Pod>() {
                @Override
                public void eventReceived(Action action, Pod pod) {
//                    log.info("event received action :[{}],resource: [{}]",action.name(),pod.getMetadata());
                    log.info("1-------------->");
                }

                @Override
                public void onClose(KubernetesClientException e) {
//                    log.info("resource close: [{}]",e);

                }
            });

            log.info("===========================分割线==========================");

            client.namespaces().watch(new Watcher<Namespace>() {
                @Override
                public void eventReceived(Action action, Namespace namespace) {
//                    log.info("namespace: [{}]",namespace.getMetadata());
                    log.info("2-------------->");
                }

                @Override
                public void onClose(KubernetesClientException e) {
//                    log.info("close namespace watch: [{}]",e);
                }
            });



//            client.events().inAnyNamespace().watch(new Watcher<Event>() {
//
//                @Override
//                public void eventReceived(Watcher.Action action, Event resource) {
//                    System.out.println("event " + action.name() + " " + resource.toString());
//                }
//
//                @Override
//                public void onClose(KubernetesClientException cause) {
//                    System.out.println("Watcher close due to " + cause);
//                }
//
//            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("end --------------------------------- ");

    }


}
