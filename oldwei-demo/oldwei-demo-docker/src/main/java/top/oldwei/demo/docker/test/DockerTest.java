package top.oldwei.demo.docker.test;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.AuthConfig;
import com.github.dockerjava.api.model.PushResponseItem;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.PushImageResultCallback;

/**
 * @description:
 * @author: weizd
 * @time: 2021/2/3 11:22 上午
 */
public class DockerTest {

    private static String DOCKER_URL = "localhost:8099";

    private static String HARBOR_LOGIN_ADDRESS = "localhost:8099";
    private static String HARBOR_USERNAME = "admin";
    private static String HARBOR_PASSWORD = "Harbor12345";

    public static void main(String[] args) {

        AuthConfig autoConfig = new AuthConfig().withRegistryAddress(HARBOR_LOGIN_ADDRESS).withUsername(HARBOR_USERNAME).withPassword(HARBOR_PASSWORD);

        DockerClient dockerClient = DockerClientBuilder.getInstance(DOCKER_URL).build();

        //push至镜像仓库
        PushImageResultCallback pushImageResultCallback = new PushImageResultCallback() {
            @Override
            public void onNext(PushResponseItem item) {
                super.onNext(item);
            }
            @Override
            public void onComplete() {
                super.onComplete();
            }
        };

        String imageName = "";
        dockerClient.pushImageCmd(imageName).withAuthConfig(autoConfig).exec(pushImageResultCallback).awaitSuccess();




    }









}
