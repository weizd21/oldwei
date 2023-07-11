package top.oldwei.demo.k8s.client.self_seldon;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.EnvVar;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.api.model.ResourceRequirements;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@Data
public class SvcOrchSpec implements KubernetesResource {

    @JsonProperty("resources")
    private ResourceRequirements resources;

    @JsonProperty("env")
    private EnvVar[] env;

    @JsonProperty("replicas")
    private Integer replicas;

}
