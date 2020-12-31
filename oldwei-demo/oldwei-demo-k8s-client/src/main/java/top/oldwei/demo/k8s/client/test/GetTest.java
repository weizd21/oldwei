package top.oldwei.demo.k8s.client.test;

import io.fabric8.kubernetes.api.model.ListOptionsBuilder;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceList;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: weizd
 * @time: 2020/12/12 12:56 下午
 */
@Slf4j
public class GetTest {


    public void getInfo(KubernetesClient client){

//        log.info("命名空间列表： {}",client.namespaces().list());

        NamespaceList namespaceList = client.namespaces().list();
        for (Namespace namespace:namespaceList.getItems()){
            log.info("namespace metadata: [{}]",namespace.getMetadata());
        }



        log.info(" {}",client.namespaces().withLabel("this", "works").list());


        System.out.println(
                client.pods().withLabel("this", "works").list()
        );

        System.out.println(
                client.pods().inNamespace("test").withLabel("this", "works").list()
        );

        System.out.println(
                client.pods().inNamespace("test").withName("testing").get()
        );

        /*
         * 	The continue option should be set when retrieving more results from the server.
         * 	Since this value is server defined, clients may only use the continue value from
         * 	a previous query result with identical query parameters (except for the value of
         * 	continue) and the server may reject a continue value it does not recognize.
         */
        PodList podList = client.pods().inNamespace("myproject").list(5, null);
        podList.getItems().forEach((obj) -> { System.out.println(obj.getMetadata().getName()); });

        podList = client.pods().inNamespace("myproject").list(5, podList.getMetadata().getContinue());
        podList.getItems().forEach((obj) -> { System.out.println(obj.getMetadata().getName()); });

        Integer services = client.services().inNamespace("myproject").list(1, null).getItems().size();

        client.services().inNamespace("myproject").list(new ListOptionsBuilder().withLimit(1L).withContinue(null).build());
        System.out.println(services);




    }





}
