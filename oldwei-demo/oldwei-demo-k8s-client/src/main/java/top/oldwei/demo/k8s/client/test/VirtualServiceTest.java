package top.oldwei.demo.k8s.client.test;

import cn.hutool.json.JSONUtil;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.ResourceQuota;
import io.fabric8.kubernetes.client.KubernetesClient;

/**
 * @description:
 * @author: weizd
 * @time: 2021/7/30 12:16 上午
 */
public class VirtualServiceTest {


    public static void main(String[] args) throws Exception{



        KubernetesClient client = GetClient.getClientWithToken(null,null);

        //
//        client.services().inNamespace("default").list().getItems().forEach(e -> {
//            System.out.println(JSONUtil.toJsonStr(e));
//        });


        client.services().inNamespace("default").withoutLabel("seldon.io/model").list().getItems().forEach(e -> {
            System.out.println(JSONUtil.toJsonStr(e));
        });




    }
}
