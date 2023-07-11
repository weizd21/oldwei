package top.oldwei.demo.k8s.client.test;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONString;
import cn.hutool.json.JSONUtil;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.Quantity;
import io.fabric8.kubernetes.api.model.ResourceQuota;
import io.fabric8.kubernetes.api.model.ResourceQuotaList;
import io.fabric8.kubernetes.api.model.ResourceQuotaStatus;
import io.fabric8.kubernetes.client.KubernetesClient;

import java.util.List;

/**
 * @description:
 * @author: weizd
 * @time: 2021/7/29 11:46 下午
 */
public class NamespaceTest {


    public static void main(String[] args) throws Exception{



        KubernetesClient client = GetClient.getClientWithToken(null,null);

        Namespace namespace = client.namespaces().withName("test").get();

        System.out.println(namespace);

//        ResourceQuota resourceQuota = client.resourceQuotas().inNamespace("default").list().getItems().get(0);
        List<ResourceQuota> list   = client.resourceQuotas().inNamespace("test").list().getItems();
        System.out.println(list.size());
//        System.out.println(list);
//        System.out.println(JSONUtil.toJsonStr(resourceQuota));
//        System.out.println(JSONUtil.toJsonStr(resourceQuota.getStatus().getHard()));
//        System.out.println(JSONUtil.toJsonStr(resourceQuota.getStatus().getUsed()));
//        System.out.println(JSONUtil.toJsonStr(resourceQuota.getStatus()));

        System.out.println(JSONUtil.toJsonStr(list.get(0)));


        System.out.println(client.namespaces().withName("default2").isReady());


        System.out.println(list.get(0).getStatus().getHard().getOrDefault("limits.cpu", Quantity.parse("0")).getAmount());
        System.out.println(list.get(0).getStatus().getHard().getOrDefault("limits.cpu", Quantity.parse("0")).getFormat());
        System.out.println(list.get(0).getStatus().getHard().getOrDefault("limits.memory", Quantity.parse("0")).getAmount());
        System.out.println(list.get(0).getStatus().getHard().getOrDefault("limits.memory", Quantity.parse("0")).getFormat());
        System.out.println(list.get(0).getStatus().getHard().get("limits.memory").toString());
        System.out.println(list.get(0).getStatus().getHard().get("request.nvidia.com/gpu"));
        System.out.println(list.get(0).getStatus().getHard().get("request.nvidia.com/gpu"));

        System.out.println(list.get(0).getStatus().getUsed().get("request.nvidia.com/gpu"));
        System.out.println(list.get(0).getStatus().getUsed().get("request.nvidia.com/gpu"));

    }
}
