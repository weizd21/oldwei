import io.fabric8.istio.client.IstioClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.utils.Serialization;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: weizd
 * @time: 2022/3/24 9:41 下午
 */
@Slf4j
public class NodeTest {
    public static void main(String[] args) {

        KubernetesClient client = ClientUtil.getClient();

        client.nodes().list().getItems().forEach(node -> {
            log.info("{}", Serialization.asJson(node));
        });




    }
}
