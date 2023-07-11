package top.oldwei.demo.k8s.client.self_seldon;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Endpoint {

    @JsonProperty("service_host")
    private String serviceHost;

    @JsonProperty("service_port")
    private Integer servicePort;

    private String type;

    private Integer grpcPort;

    private Integer httpPort;

}
