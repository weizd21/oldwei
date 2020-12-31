package top.oldwei.demo.k8s.client.info;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.VersionInfo;
import lombok.extern.slf4j.Slf4j;
import top.oldwei.demo.k8s.client.test.GetClient;

/**
 * @description:
 * @author: weizd
 * @time: 2020/12/16 4:35 下午
 */
@Slf4j
public class GetVersionInfo {


    public static void main(String[] args) throws Exception{
        KubernetesClient client = GetClient.getClientWithToken(null,null);
        getVersionInfo(client);
    }




    public static void getVersionInfo(KubernetesClient client){
        VersionInfo versionInfo = client.getVersion();

        log.info("Version details of this Kubernetes cluster :-");
        log.info("Major        : {}", versionInfo.getMajor());
        log.info("Minor        : {}", versionInfo.getMinor());
        log.info("GitVersion   : {}", versionInfo.getGitVersion());
        log.info("BuildDate    : {}", versionInfo.getBuildDate());
        log.info("GitTreeState : {}", versionInfo.getGitTreeState());
        log.info("Platform     : {}", versionInfo.getPlatform());
        log.info("GitVersion   : {}", versionInfo.getGitVersion());
        log.info("GoVersion    : {}", versionInfo.getGoVersion());
        log.info("GitCommit    : {}", versionInfo.getGitCommit());
    }
}
