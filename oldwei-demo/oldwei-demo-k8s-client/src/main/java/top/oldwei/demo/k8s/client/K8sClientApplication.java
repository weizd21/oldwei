package top.oldwei.demo.k8s.client;

import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.oldwei.demo.k8s.client.test.ConnectTest;
import top.oldwei.demo.k8s.client.test.GetClient;

/**
 * @description:
 * @author: weizd
 * @time: 2020/12/11 11:22 下午
 */
@SpringBootApplication
@Slf4j
public class K8sClientApplication {


    public static void main(String[] args) {
        SpringApplication.run(K8sClientApplication.class,args);


        log.info("1------------------------------------");
        try {
            KubernetesClient client = GetClient.getClientWithConfig(null);
            log.info("namespace size: [{}]",client.namespaces().list().getItems().size());

        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("2------------------------------------");



        new Thread(()->{
//            ConnectTest.list();
        }).start();


        System.out.println("end ----------------------");
    }




}
