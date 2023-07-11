package top.oldwei.demo.k8s.client.test;

import io.fabric8.kubernetes.api.model.RootPaths;
import io.fabric8.kubernetes.api.model.apiextensions.v1beta1.CustomResourceDefinition;
import io.fabric8.kubernetes.api.model.apiextensions.v1beta1.CustomResourceDefinitionList;
import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @description:
 * @author: weizd
 * @time: 2020/12/15 11:47 上午
 */
@Slf4j
public class CrdTest {

    private static boolean logRootPaths = false;


    public static void main(String[] args) throws Exception{

//        KubernetesClient client = GetClient.getClientWithConfig(null);
        KubernetesClient client = GetClient.getClientWithToken(null,null);


        if (logRootPaths) {
            RootPaths rootPaths = client.rootPaths();
            if (rootPaths != null) {
                List<String> paths = rootPaths.getPaths();
                if (paths != null) {
                    System.out.println("Supported API Paths:");
                    for (String path : paths) {
                        System.out.println("    " + path);
                    }
                    System.out.println();
                }
            }
        }

        load(client);
    }





    public static void load(KubernetesClient client){
        log.info("Listing all current Custom Resource Definitions :");
//        CustomResourceDefinitionList crdList = client.customResourceDefinitions().list();
//        List<CustomResourceDefinition> crdsItems = crdList.getItems();
//        log.info("Found CRD(s) num: [{}]",crdsItems.size());
//        crdsItems.forEach(crd -> {
//            if(crd.getMetadata().getName().contains("seldon")){
//                log.info("name: [{}], selfLink: [{}]",crd.getMetadata().getName(),crd.getMetadata().getSelfLink());
//            }
//        });

//        // Creating a custom resource from yaml
//        CustomResourceDefinition aCustomResourceDefinition = client.customResourceDefinitions().load(CrdTest.class.getResourceAsStream("/demo/crd.yml")).get();
//        log.info("Creating CRD...");
//        client.customResourceDefinitions().create(aCustomResourceDefinition);
//
//        log.info("Updated Custom Resource Definitions: ");
//        client.customResourceDefinitions().list().getItems().forEach(crd -> log.info(crd.getMetadata().getName()));


    }




}
