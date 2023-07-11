import io.fabric8.istio.api.networking.v1beta1.VirtualService;
import io.fabric8.istio.client.DefaultIstioClient;
import io.fabric8.istio.client.IstioClient;
import io.fabric8.kubernetes.api.model.ListOptions;
import io.fabric8.kubernetes.api.model.ListOptionsBuilder;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.utils.Serialization;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: weizd
 * @time: 2022/2/11 6:12 下午
 */
@Slf4j
public class IstioMain {

    public static void main(String[] args) throws Exception{
        String masterUrl = "https://49.233.51.77:6443";
        String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6InpyaG5PamtEaDdNdTdYcTRoOV94SmJHYWJYYUtNYTJsb1V1amVWOTlDSDgifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlLXN5c3RlbSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJhZG1pbi10b2tlbi13bmJocyIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50Lm5hbWUiOiJhZG1pbiIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6ImNlNjM5YWM4LWQ5MWEtNDkzMC04ZDUzLWE5OWVkMDBjMmY0NiIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDprdWJlLXN5c3RlbTphZG1pbiJ9.RkhCg8MeeLIXbAOUPT2szhu41MMVNq2PCh8bEzcTVrMvWwQyjVkJ29AVDxfkVFeaekvDAZNcmCcSuTgGCtMB4nX9TRc4DeDu4ti5cRtrd2z-2ZkDzPPU5JmmtlB-HZ0-COClCvxuKSjRf15RmHeKuOd3DgtSkdubpFjH0IIqRGRWvBhfLUgLYO6hgTIfzB5gzHIgPMbJ7sA0lnqwayxjdFjSMuf30qEwK-wEt6vF7Ecgez1P5hDXsE_-NsOzcUUkfQHoDp9QxdkT75Ey9ZTa38faIak0r0zzUzTT8zeKXHGJ6VR9TjjRqVrDIxLEP8taQl7oiDO4gceHss11Uh1QMA";

        Config config = new ConfigBuilder()
                .withMasterUrl(masterUrl)
                .withOauthToken(token)
                .withTrustCerts(true)
                .build();

        IstioClient client = new DefaultIstioClient(config);

//        for (Gateway item : client.v1beta1().gateways().list().getItems()) {
//            System.out.println(item.getMetadata().getName());
//        }

        for(VirtualService item: client.v1beta1().virtualServices().list().getItems()){
            log.info("{}", Serialization.asJson(item));
        }


        ListOptions listOptions = new ListOptionsBuilder()
                .withLimit(5L)
//                .withContinue()
                .build();
        client.v1beta1().virtualServices().list(listOptions).getItems().forEach(virtualService -> {
            System.out.println(virtualService.getMetadata().getName());
        });



    }


//    public static void main(String args[]) {
//        String str = "default你好";
//        String pattern = "[a-z0-9]([-a-z0-9]*[a-z0-9])?(\\.[a-z0-9]([-a-z0-9]*[a-z0-9])?)*";
//
//        Pattern r = Pattern.compile(pattern);
//        Matcher m = r.matcher(str);
//        System.out.println(m.matches());
//    }


}
