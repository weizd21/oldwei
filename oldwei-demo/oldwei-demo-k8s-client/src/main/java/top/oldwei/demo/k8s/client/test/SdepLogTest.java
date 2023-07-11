package top.oldwei.demo.k8s.client.test;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.WatcherException;
import lombok.extern.slf4j.Slf4j;
import top.oldwei.demo.k8s.client.self_seldon.SeldonDeployment;

import java.util.Arrays;

/**
 * @description:
 * @author: weizd
 * @time: 2021/7/10 3:58 下午
 */
@Slf4j
public class SdepLogTest {

    public static void main(String[] args) throws Exception{

        KubernetesClient client = GetClient.getClientWithToken(null,null);

        client.customResources(SeldonDeployment.class).inAnyNamespace().watch(new Watcher<SeldonDeployment>() {
            @Override
            public void eventReceived(Action action, SeldonDeployment seldonDeployment) {


            }

            @Override
            public void onClose(WatcherException e) {

            }
        });


    }
}
