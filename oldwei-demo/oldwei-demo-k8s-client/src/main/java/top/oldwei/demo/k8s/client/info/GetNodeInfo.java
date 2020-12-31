package top.oldwei.demo.k8s.client.info;

import io.fabric8.kubernetes.api.model.NodeList;
import io.fabric8.kubernetes.api.model.metrics.v1beta1.NodeMetricsList;
import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.extern.slf4j.Slf4j;
import top.oldwei.demo.k8s.client.test.GetClient;

/**
 * @description:
 * @author: weizd
 * @time: 2020/12/16 5:19 下午
 */
@Slf4j
public class GetNodeInfo {


    public static void main(String[] args) throws Exception{

        KubernetesClient client = GetClient.getClientWithConfig(null);

        NodeList nodeList = client.nodes().list();
        nodeList.getItems().forEach(node -> {
            log.info("{}",node);
        });
        log.info("node size: [{}]",nodeList.getItems().size());


    }
}
