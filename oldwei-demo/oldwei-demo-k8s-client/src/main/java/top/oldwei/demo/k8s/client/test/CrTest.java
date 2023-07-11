package top.oldwei.demo.k8s.client.test;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.base.CustomResourceDefinitionContext;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: weizd
 * @time: 2020/12/15 3:26 下午
 */
@Slf4j
public class CrTest {

    /**
     *
     * http://152.136.218.90:31380/seldon/default/iris-model-01/api/v1.0/doc/
     *
     *
     *
     * curl -X POST http://152.136.218.90:31380/seldon/default/iris-model-01/api/v1.0/predictions \
     *      -H 'Content-Type: application/json' \
     *      -d '{ "data": { "ndarray": [[1,2,3,4]] } }'
     *
     *
     * curl -X POST http://<ingress>/seldon/seldon/iris-model/api/v1.0/predictions \
     *     -H 'Content-Type: application/json' \
     *     -d '{ "data": { "ndarray": [[1,2,3,4]] } }'
     *
     *
     */

    public static CustomResourceDefinitionContext vsContext = new CustomResourceDefinitionContext.Builder()
            .withGroup("networking.istio.io")
            .withPlural("virtualservices")
            .withScope("Namespaced")
//            .withVersion("v1alpha3")
            .withVersion("v1beta1")
            .withName("virtualservices.networking.istio.io")
            .build();



    public static void main(String[] args) throws Exception{

        KubernetesClient client = GetClient.getClientWithToken(null,null);
//        KubernetesClient client = GetClient.getClientWithCA(null);

        // Creating Custom Resources Now:
        CustomResourceDefinitionContext crdContext = new CustomResourceDefinitionContext.Builder()
                .withGroup("machinelearning.seldon.io")
                .withPlural("seldondeployments")
                .withScope("Namespaced")
                .withVersion("v1")
                .withName("seldondeployments.machinelearning.seldon.io")
                .build();

        String namespace = "default";
        namespace = "weizd";



        list(client,vsContext,"default");

        Map<String,Object> vs = client.customResource(vsContext).load(CrTest.class.getResourceAsStream("/VirtualService.yaml"));

        log.info("yaml: {}",vs);

        client.customResource(vsContext).create("test",vs);


//        createByYaml(client,crdContext,namespace);

//        createByObject(client,crdContext,namespace);

//        list(client,crdContext,namespace);
    }




    public static void createByObject(KubernetesClient client,CustomResourceDefinitionContext context,String namespace)throws Exception{
        // Load from Yaml
        Map<String, Object> seldonDeployment = client.customResource(context)
                .load(CrTest.class.getResourceAsStream("/seldon/deployment.yaml"));
        // Create Custom Resource
        client.customResource(context).create(namespace, seldonDeployment);

//        client.customResource(crdContext).create(CrTest.class.getResourceAsStream("/seldon/deployment.yaml"));
//        log.info("Created Custom Resource successfully too");

    }


    public static void createByYaml(KubernetesClient client,CustomResourceDefinitionContext context,String namespace)throws Exception{
        // Load from Yaml
        Map<String, Object> seldonDeployment = client.customResource(context)
                .load(CrTest.class.getResourceAsStream("/seldon/deployment.yaml"));
        // Create Custom Resource
        client.customResource(context).create(namespace, seldonDeployment);

//        client.customResource(crdContext).create(CrTest.class.getResourceAsStream("/seldon/deployment.yaml"));
//        log.info("Created Custom Resource successfully too");

    }

    public static void list(KubernetesClient client,CustomResourceDefinitionContext context,String namespace){
        // Listing all custom resources in given namespace:
        Map<String, Object> list = client.customResource(context).list(namespace);
        List<Map<String, Object>> items = (List<Map<String, Object>>) list.get("items");
        log.info("namespace [{}] ,cr size: [{}]",namespace,items.size());
        for(Map<String, Object> customResource : items) {
            Map<String, Object> metadata = (Map<String, Object>) customResource.get("metadata");
            log.info(metadata.get("name").toString());
        }

    }




}
