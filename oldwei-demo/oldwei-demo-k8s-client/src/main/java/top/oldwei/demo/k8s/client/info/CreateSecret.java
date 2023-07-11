package top.oldwei.demo.k8s.client.info;

import io.fabric8.kubernetes.api.model.NodeList;
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
public class CreateSecret {


    public static void main(String[] args) throws Exception{
        KubernetesClient client = GetClient.getClientWithConfig(null);

//        Secret secret1 = new SecretBuilder()
//                .withNewMetadata().withName("secret1").addToLabels("foo", "bar").endMetadata()
//                .addToData("username", "guccifer")
//                .addToData("password", "shadowgovernment")
//                .addToData("secret1", "SECRET1")
//                .build();
//
//        Secret secret2 = new SecretBuilder()
//                .withNewMetadata().withName("secret2").addToLabels("foo", "bar").endMetadata()
//                .addToData("one", "1")
//                .build();
//
//        client.secrets().inNamespace("ns1").create(secret1);
//        client.secrets().inNamespace("ns2").create(secret2);
//

//        Secret secret3 = new SecretBuilder()
//                .withNewMetadata().withName("docker-register").endMetadata()
//                .withNewType("kubernetes.io/.dockerconfigjson")
//                .addToStringData("docker-server", "10.10.10.15:8888")
//                .addToStringData("docker-username", "admin")
//                .addToStringData("docker-password", "Harbor12345")
//                .build();
//        client.secrets().inNamespace("weizd").create(secret3);
//

        String server = "10.10.10.15:8888";
        String username = "admin";
        String password = "Harbor12345";

        client.secrets().inNamespace("weizd").create(createRegistrySecret("harbor2-secret",server,username,password));

    }

    private static Secret createRegistrySecret(String secretName,String server,String username,String password) {
        Base64.Encoder encoder = Base64.getEncoder();
        // 生成 auth 部分得 Base64 字符串
        String authStr = String.format("%s:%s", username, password);
        String enStr = encoder.encodeToString(authStr.getBytes());
        // 将相关 docker-registry 信息替换
        String dataStr = String.format("{\"auths\":{\"%s\":{\"username\":\"%s\",\"password\":\"%s\",\"auth\":\"%s\"}}}",
                server, username, password, enStr);
        // 对 JSON 字符串在进行 Base64 加密
        dataStr = encoder.encodeToString(dataStr.getBytes());

        // 创建 Secret 资源对象
        Secret registrySecret = new SecretBuilder()
                .withNewMetadata()
                .withName(secretName)
                .endMetadata()
                .addToData(".dockerconfigjson", dataStr)
                .withType("kubernetes.io/dockerconfigjson")
                .build();
        return registrySecret;
    }


}
