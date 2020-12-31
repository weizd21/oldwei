package top.oldwei.demo.k8s.client.test;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.io.resource.ResourceUtil;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @description:
 * @author: weizd
 * @time: 2020/12/14 2:41 下午
 */
@Slf4j
public class GetClient {

    /**
     * 通过ca证书进行连接
     * @param masterUrl
     * @return
     * @throws Exception
     */
    public static KubernetesClient getClientWithCA(String masterUrl) throws Exception{
        masterUrl = "https://152.136.218.90:6443";
        Config config = new ConfigBuilder()
                .withMasterUrl(masterUrl)
                .withTrustCerts(true)
                .withCaCertData("LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUN3akNDQWFxZ0F3SUJBZ0lCQURBTkJna3Foa2lHOXcwQkFRc0ZBREFTTVJBd0RnWURWUVFERXdkcmRXSmwKTFdOaE1CNFhEVEl3TVRBeU1UQXdNRFV5TWxvWERUTXdNVEF4T1RBd01EVXlNbG93RWpFUU1BNEdBMVVFQXhNSAphM1ZpWlMxallUQ0NBU0l3RFFZSktvWklodmNOQVFFQkJRQURnZ0VQQURDQ0FRb0NnZ0VCQUo1cVpac1pJMnkzCm0xNU5wMW55TFRkSjZmbG9CS3ZtTGl3WWhKbXBVVk1ERjRiMjUxRExSWlQvMkZRc2EyMHFXNGF6SGJFeWFqbHAKY2pVRzRYZnRTRVV3eVA0YnBHSThGVWMrMWdhUmtBbzdtVG9FNU9qVGhnR3d4ZVdEVFdhTlR2L0tuNG1UUkk1QQpaL3NXQ0Y5NzNPQkxZL1dYVGt1ZUh6WW9oelRvV3NlR3N1NEhRN2ZUa3BwajZJcnlEUlRTbkptallkSmhubVkrCm1KVHhhblFwTWJoaitYTUpFNlNmUlhrbVhKTi9RNjVtYWJBbHFCcDRRekhFdW1OVFpST2xsL1lKNnEyTktod0YKR3lEMXkvYld0cU9Oc201VjJBUTFmNWMyTGxINDFnUXF4eU5lYXFJNi9FUWFuS2FKbUxndGhwa3NOaFhRRjIvcQpXYnFYbzBKUEEvTUNBd0VBQWFNak1DRXdEZ1lEVlIwUEFRSC9CQVFEQWdLa01BOEdBMVVkRXdFQi93UUZNQU1CCkFmOHdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBSGhFa29lQ2t6M1Z1OWRSNEpOWXdBWi9mS1cvL09WbVZKbmUKL0lhVEhTb0Rua1lZdVl2WENlTXJlTElUd2lCbzJQOVFOV0FMNlltZExWUDZhbi9hN1ZPeFZRaXJhZHZBRGpNeAptbFFlZk5WQkJCR0dKOUkzNnRKeXM4czhtdGN6ckJpRWdybjkwUlI3VGlCdUs0VUU5bjhJRW1nWFZjZmU2WEk5CmdLV1RBYWZlQzVVY2ZBcjhlYmlCa3lxYUxkTnljRC9RbjJqd2k2c3BFTmVqYjE0dkUyWm9GYXRtUHkvUTVPdlQKTGJIRkY2bDlBWEdCV05QYlZvVGNkVVJaUDA5c0E5eVl4WnpmUmllRC9ySFFvL05SRGlhWFFOckVuWDdzR3VKOQpNek95UW1tdTVyZ2dEUXZUOHRoeVRyMXRoQ1E3cWk0OFZGNVJ3cURtdjJ6M2Y4ZmJGNTA9Ci0tLS0tRU5EIENFUlRJRklDQVRFLS0tLS0K")
                .withClientCertData("LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUM2VENDQWRHZ0F3SUJBZ0lJSGYwbDQ4WDRxL3N3RFFZSktvWklodmNOQVFFTEJRQXdFakVRTUE0R0ExVUUKQXhNSGEzVmlaUzFqWVRBZUZ3MHlNREV3TWpFd01EQTFNakphRncwek1ERXdNVGt3TURBMU1qTmFNQzR4RnpBVgpCZ05WQkFvVERuTjVjM1JsYlRwdFlYTjBaWEp6TVJNd0VRWURWUVFERXdwcmRXSmxMV0ZrYldsdU1JSUJJakFOCkJna3Foa2lHOXcwQkFRRUZBQU9DQVE4QU1JSUJDZ0tDQVFFQTBJTXNHWkRNYTAySGZSRWFQVkVxWlM4ZWpWcFMKMzRVR1M0L2V2N1AyV2dYbnNiT2JxUTZibFFid0o1c2EzQ0dZNUhIUGpmQk4zS3h5TnF0SkVFVW5QWm9kWHJ5RwpyaUNZZnRsbW5TVXpiZ1BsbUtxQWpheHE3bU1HbWV3NS9za2RQa09Zb3Y1M1ppckpMTndvbk5zRmNoczRnc1FRCmVVS1RvWVRHMkdvbTBLNDlVT3ppTGJERWwrbXBwU25HaHJxcFNsNFpnSXZrTXVkSjZUWTBQMk04U2N6Q1RHYlIKT1lUckhmZk9icldVc0hrOG42TDByU0NWbzE4MksydGZFbDlNbFhtSHBUQktjSWRhOXJDNU9YWE9oQWNvVlhGOQpicUpWUTIwQ3Z1Zk5HRXhTNE1ObmRmZnQweXk0Rzlxcm92c3FsVXRqMVBvWktlODV6cE9zWG1yS2J3SURBUUFCCm95Y3dKVEFPQmdOVkhROEJBZjhFQkFNQ0JhQXdFd1lEVlIwbEJBd3dDZ1lJS3dZQkJRVUhBd0l3RFFZSktvWkkKaHZjTkFRRUxCUUFEZ2dFQkFGNHVKSkN1aDFub1JmMktheUJtOE1yMVJkWXFmcFdvVHJ4QmJ0cmgvaEE3OVVLSQpYNHRKeHRmNXJ2RXVWK1I4ZEk1VnNua08zM0lsSzh5bCt4M0ZwZVZZQzZqeE1ZbWh6bDFNcW1iMFVNWHNPa2R4ClJ3UHZRUTdnbkp2MThxbnJRQWI4dmZCa1hqT3ZEaGJlRnd2OWJ4Z3VnYkp1N0JsaUNRV3RPaU5hTk9FTFVOdzIKZFRSdnk0a1VGdG5Yd1dJMFBCYU5jVVRsUUpzOXcyb0dFeEt5Zm5IR3VNcDYxTk16S01XNzZLeDFaaHdmdUdnVQpGZzIvS3NOYUV5V2hpamtMQldnbjg1cno1eURaR3dGNWozTUk4eFN4T3R2ekNIY0Rqd1RZVDhZYy9EUTFDQ3FECkk4TFFWMStDWmYvQ0l6MFNBK0c1eTVzZ0J6L0VxSHBuNnB0MlVSbz0KLS0tLS1FTkQgQ0VSVElGSUNBVEUtLS0tLQo")
                .withClientKeyData("LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFb3dJQkFBS0NBUUVBMElNc0daRE1hMDJIZlJFYVBWRXFaUzhlalZwUzM0VUdTNC9ldjdQMldnWG5zYk9iCnFRNmJsUWJ3SjVzYTNDR1k1SEhQamZCTjNLeHlOcXRKRUVVblBab2RYcnlHcmlDWWZ0bG1uU1V6YmdQbG1LcUEKamF4cTdtTUdtZXc1L3NrZFBrT1lvdjUzWmlySkxOd29uTnNGY2hzNGdzUVFlVUtUb1lURzJHb20wSzQ5VU96aQpMYkRFbCttcHBTbkdocnFwU2w0WmdJdmtNdWRKNlRZMFAyTThTY3pDVEdiUk9ZVHJIZmZPYnJXVXNIazhuNkwwCnJTQ1ZvMTgySzJ0ZkVsOU1sWG1IcFRCS2NJZGE5ckM1T1hYT2hBY29WWEY5YnFKVlEyMEN2dWZOR0V4UzRNTm4KZGZmdDB5eTRHOXFyb3ZzcWxVdGoxUG9aS2U4NXpwT3NYbXJLYndJREFRQUJBb0lCQUF2ZTI5emN2SGFEWkdvLwpWZ0lWbzljODFuMXZuZll4cVlrVnVMV3JOaVFyMytQaStNS29IVDFmdHI1YWV5MzdENnc1dW5vM0t3ZWNIYWgxCkdlUytPeHlyc0c1YlIwT1VnWG4vUUhORDBlaTZlaEMxN0Rta2pqWGlGMWNkOXJORm1jK1lvNURyOGJvMFIxMngKYnZKM0dabEVhWFF3VzB1NytaR2hjSHlUbG9BY3dPc3daRzVqYUsySGxleit0TThCVnFvU0NzT2lYdzRpdXpKaQpjOTV0ZkE0a0Y5N3NRNDBuVmFmcDZycytiWTMvMGMvcnUvM3VPRjZTQUlaZEw2YlJGaHkrcTBhZEhBazF5Ym51CkJVSnlDdXFZN2NEVy9lS2VweW5uQ3FHZGQ3ZUFWZ0ZrL2YvTzJueVVWaUFGY25HaTBVYmFONmJ4ZFoveld6SEgKb2g2dTRURUNnWUVBMXhURzNKak1STjJBTzB2b2VOL290cUdselI5aWwrUnJMU000cVdCdmUvMHhGMlh0M29VdgpNUUpyaTA0MmRNVlV1Qm41MlNUNDlSRnVzc2p1ZVpuS1RnNHdxV3MzOHRCWTl1WG1TeFl6S1AvTSthK2VlN0hWCnJuL0FOdmk3bFNhZDBxL1BTWTA0L2s0WDJWTktobTcxbVlSK0tFUEUxTkVLNlZRaHJsYkt2VmtDZ1lFQStDNTUKRWpqQktIUjV6SEtaV1ZYN2UzS3NCeG51TElEY3hHUGxYM0krS2lEU2E4WWxWK0hENzBSRDk1SlJHU285L0l2ZAo4bEZSUVdxWkY1RUhPTVREQ2hFMjQ4UEtNRnM4Wkg4ajBsbnMycFIvM3N2c05RVGdTanZ4R1dhRWdKdlhiY0NoCjBqM0U0b1drZ0JJM01ITkVKaDVhZVpWaXliU1AxeGNhaEJTWDVRY0NnWUVBenBQMEZMamxxVmNkN05ZUnFOZFcKaXVOamZrcWd4Y2JzY2VRY2JnSmowYkR6ejhkazJvOE5Mb05taTl4SlRZcWl2bGlrc3IxaUtPL2ZaUFM4cUNyZwpObXhHck5LQVlFVmd6WE11NS91Z2s4WnROM3hJT2dJcWZIblRRbmxRb2diL0d2Y2xuejEyK2pwRGljY0lFMnJMCmxmRzl3aXFsQ01ybk1naE9lVVovWDdFQ2dZQjNFVVRBdXozZ2ZORWFoemlQSmJNN2JLNmYwWTcxQnZYanV0R0cKMHphRDI2OVlTWDROQmFHTkgwRkVlNHd3K0pVNG5wbmk4YVI4TC9vMjFqdVN2c09jQnRwQXQrUS9BdWFjaUdyVgpOcE50NHFUZWJSTnI5R1R1K0ljWldwZmpZK0VkSkxmK04raEhqVHA0djBSL0dDM3Y0OHdnWE0xQ1hFQk9QeVZYCmpORzY3UUtCZ0JPYzR5VWN6WmdzMElUWisraTFqMnBQalQrSWd6ZXFIaFVXWXRVdnJVaWhuSzMxSVhGMWVqaEsKckU4eTZBMXpXS1dnVDBMTnJXcmZoZ04rS3NWSGwwbkJzeXpKRFJPWnVBZ2JxZDZGNkF5SDdENkxqdlducmRwdApXTFFPaXR0b3l0SGg2eHdjdlZrNjQ2Z3VOSjNxNU9TZVhBVzlnVUtBTkhQY2U2VTh4RTg3Ci0tLS0tRU5EIFJTQSBQUklWQVRFIEtFWS0tLS0tCg")
                .build();
        KubernetesClient client = new DefaultKubernetesClient(config);
        return client;
    }


    /**
     * 通过token进行连接
     * @param masterUrl
     * @param token
     * @return
     * @throws Exception
     */
    public static KubernetesClient getClientWithToken(String masterUrl,String token) throws Exception{

        masterUrl = "https://152.136.218.90:6443";
        token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjlRRDdEZG1TSExhR05uU0tJaXBnUHNId01CMFhQREtFSm9tdGRTbnZqYkkifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlLXN5c3RlbSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJhZG1pbnRlc3QtdG9rZW4tNnJrbTQiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoiYWRtaW50ZXN0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQudWlkIjoiNzEwZWU1MWItOTc2Ni00ZTY5LTkyYzgtZTRkMDgzNmUwZmJiIiwic3ViIjoic3lzdGVtOnNlcnZpY2VhY2NvdW50Omt1YmUtc3lzdGVtOmFkbWludGVzdCJ9.CUQSK95L_ElvELveFD3Y9hEvtsXPGOZ4VsWd81xTb-OQ_jxarKjFitZPoMBryoj8avxvbRTA4RIeOaOzL_LdQQVZ6fJNY3YK1aDFbVKc4NvBIJFz6UIhio77PPs3a0pAdkTLwKaxtloJtUmc5JbB-LVkXbIvY-4Ua0YqFO3dewLhTKcES71y9gI_ABQy7IQPwxkgTGlWb2xOvW1FV8V1yIxegeUeYXfqSiBOzflxgd5YFYSHpmmkxiXLzfjS6ghc5-m_CPQuwGovu-z45TLkgvX37Bf_ZopX5F10PzbVF-h_k6DmQGIhTWdL-W0qE9FmZWKg59bFsz3SHWOJlIshqg";

        masterUrl = "https://49.233.51.77:6443";
        token = "eyJhbGciOiJSUzI1NiIsImtpZCI6InpyaG5PamtEaDdNdTdYcTRoOV94SmJHYWJYYUtNYTJsb1V1amVWOTlDSDgifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlLXN5c3RlbSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJhZG1pbi10b2tlbi13bmJocyIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50Lm5hbWUiOiJhZG1pbiIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6ImNlNjM5YWM4LWQ5MWEtNDkzMC04ZDUzLWE5OWVkMDBjMmY0NiIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDprdWJlLXN5c3RlbTphZG1pbiJ9.RkhCg8MeeLIXbAOUPT2szhu41MMVNq2PCh8bEzcTVrMvWwQyjVkJ29AVDxfkVFeaekvDAZNcmCcSuTgGCtMB4nX9TRc4DeDu4ti5cRtrd2z-2ZkDzPPU5JmmtlB-HZ0-COClCvxuKSjRf15RmHeKuOd3DgtSkdubpFjH0IIqRGRWvBhfLUgLYO6hgTIfzB5gzHIgPMbJ7sA0lnqwayxjdFjSMuf30qEwK-wEt6vF7Ecgez1P5hDXsE_-NsOzcUUkfQHoDp9QxdkT75Ey9ZTa38faIak0r0zzUzTT8zeKXHGJ6VR9TjjRqVrDIxLEP8taQl7oiDO4gceHss11Uh1QMA";

        Config config = new ConfigBuilder()
                .withMasterUrl(masterUrl)
                .withOauthToken(token)
                .withTrustCerts(true)
                .build();
        KubernetesClient client = new DefaultKubernetesClient(config);
        return client;
    }


    /**
     * 读取配置文件进行连接
     * @param configPath
     * @return
     * @throws Exception
     */
    public static KubernetesClient getClientWithConfig(String configPath) throws Exception{
//        InputStream inputStream = new ClassPathResource(configPath).getStream();
//        Config config = Config.fromKubeconfig(IoUtil.read(inputStream,Charset.defaultCharset()));
        if(configPath == null){
            configPath = "classpath:kubectl/config-token.yml";
            configPath = "classpath:kubectl/config-ca-77.yml";
        }
        Config config = Config.fromKubeconfig(ResourceUtil.readStr(configPath,Charset.defaultCharset()));
        KubernetesClient client = new DefaultKubernetesClient(config);
        return client;
    }


    public static void main(String[] args) throws Exception{
//        String configPath = "classpath:kubectl/config-ca.yml";
//        KubernetesClient client = getClientWithConfig(configPath);
//        String configContent = ResourceUtil.readStr(configPath,Charset.defaultCharset());

        String masterUrl = "https://152.136.218.90:6443";
        String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjlRRDdEZG1TSExhR05uU0tJaXBnUHNId01CMFhQREtFSm9tdGRTbnZqYkkifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlLXN5c3RlbSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJhZG1pbnRlc3QtdG9rZW4tNnJrbTQiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoiYWRtaW50ZXN0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQudWlkIjoiNzEwZWU1MWItOTc2Ni00ZTY5LTkyYzgtZTRkMDgzNmUwZmJiIiwic3ViIjoic3lzdGVtOnNlcnZpY2VhY2NvdW50Omt1YmUtc3lzdGVtOmFkbWludGVzdCJ9.CUQSK95L_ElvELveFD3Y9hEvtsXPGOZ4VsWd81xTb-OQ_jxarKjFitZPoMBryoj8avxvbRTA4RIeOaOzL_LdQQVZ6fJNY3YK1aDFbVKc4NvBIJFz6UIhio77PPs3a0pAdkTLwKaxtloJtUmc5JbB-LVkXbIvY-4Ua0YqFO3dewLhTKcES71y9gI_ABQy7IQPwxkgTGlWb2xOvW1FV8V1yIxegeUeYXfqSiBOzflxgd5YFYSHpmmkxiXLzfjS6ghc5-m_CPQuwGovu-z45TLkgvX37Bf_ZopX5F10PzbVF-h_k6DmQGIhTWdL-W0qE9FmZWKg59bFsz3SHWOJlIshqg";


        KubernetesClient client = getClientWithToken(masterUrl,token);

        log.info("namespace size: [{}]",client.namespaces().list().getItems().size());
        log.info("oauth token is :[{}]",client.getConfiguration().getOauthToken());
    }



}
