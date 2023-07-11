package top.oldwei.demo.k8s.client.self_seldon;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.api.model.ResourceRequirements;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@Data
public class PredictorSpec implements KubernetesResource {

    @JsonProperty("name")
    private String name;

    @JsonProperty("graph")
    private PredictiveUnit graph;

    @JsonProperty("componentSpecs")
    private SeldonPodSpec[] componentSpecs;

    @JsonProperty("replicas")
    private Integer replicas;

    @JsonProperty("annotations")
    private Map<String, String> annotations;

    @JsonProperty("engineResources")
    private ResourceRequirements engineResources;

    @JsonProperty("labels")
    private Map<String, String> labels;

    @JsonProperty("svcOrchSpec")
    private SvcOrchSpec svcOrchSpec;

    @JsonProperty("traffic")
    private Integer traffic;

    @JsonProperty("explainer")
    private Explainer explainer;

    @JsonProperty("shadow")
    private Boolean shadow;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
