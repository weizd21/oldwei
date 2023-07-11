package top.oldwei.demo.k8s.client.info;

import io.fabric8.kubernetes.api.model.Secret;
import io.fabric8.kubernetes.api.model.SecretBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.extern.slf4j.Slf4j;
import top.oldwei.demo.k8s.client.test.GetClient;

import java.util.Base64;

/**
 * @description:
 * @author: weizd
 * @time: 2021/6/3 9:08 下午
 */
@Slf4j
public class GetService {


    public static void main(String[] args) throws Exception{
        KubernetesClient client = GetClient.getClientWithConfig(null);

//        client.services().inNamespace("dev").list().getItems().forEach(service -> {
//            log.info("{}",service.getMetadata().getName());
//            log.info("{}",service.getStatus().);
//        });

        client.apps().deployments().inNamespace("default").list().getItems().forEach(deployment -> {
            log.info("{} replicas: [{}]",deployment.getMetadata().getName(),deployment.getStatus().getAvailableReplicas());
        });




    }


}
