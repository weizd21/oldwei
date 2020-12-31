package top.oldwei.demo.k8s.client.info;

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
public class GetNodeMetricsInfo {


    public static void main(String[] args) throws Exception{

        KubernetesClient client = GetClient.getClientWithConfig(null);

        NodeMetricsList nodeMetricList = client.top().nodes().metrics();
        nodeMetricList.getItems().forEach(nodeMetrics -> {
            log.info("{}",nodeMetrics);
        });
        log.info("node size: [{}]",client.nodes().list().getItems().size());









    }
}
