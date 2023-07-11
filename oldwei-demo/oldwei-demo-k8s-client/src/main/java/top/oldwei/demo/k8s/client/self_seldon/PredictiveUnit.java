package top.oldwei.demo.k8s.client.self_seldon;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer.None;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(
        using = None.class
)
@Data
public class PredictiveUnit implements KubernetesResource {

    @JsonProperty("name")
    private String name;

    @JsonProperty("children")
    private PredictiveUnit[] children;

    @JsonProperty("type")
    private String type;

    @JsonProperty("implementation")
    private String implementation;

    @JsonProperty("methods")
    private String[] methods;

    @JsonProperty("endpoint")
    private Endpoint endpoint;

    @JsonProperty("parameters")
    private Parameter[] parameters;

    @JsonProperty("modelUri")
    private String modelUri;

    @JsonProperty("serviceAccountName")
    private String serviceAccountName;

    @JsonProperty("envSecretRefName")
    private String envSecretRefName;

    @JsonProperty("logger")
    private Logger logger;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap();


    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
