package top.oldwei.demo.k8s.client.deployment;

import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.utils.Serialization;
import top.oldwei.demo.k8s.client.test.GetClient;

/**
 * @description:
 * @author: weizd
 * @time: 2021/6/9 3:32 下午
 */
public class DeploymentTest {


    public static void main(String[] args) throws Exception{

        KubernetesClient client = GetClient.getClientWithConfig(null);
        Deployment deployment = client.apps().deployments().inNamespace("dev").withName("model-gateway").get();
        System.out.println(Serialization.asJson(deployment));
        System.out.println(client.apps().deployments().inNamespace("dev").withName("model-gateway").isReady());
        System.out.println(client.apps().deployments().inNamespace("marui").withName("nginx").isReady());
        System.out.println(client.apps().deployments().inNamespace("cattle-system").withName("ranche").isReady());
        System.out.println(Serialization.asJson(client.apps().deployments().inNamespace("cattle-system").withName("ranche").get()));
    }

}
