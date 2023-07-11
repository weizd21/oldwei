package top.oldwei.demo.k8s.client.self_seldon;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.Container;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@Data
public class Explainer {

    @JsonProperty("type")
    private String type;

    @JsonProperty("modelUri")
    private String modelUri;

    @JsonProperty("serviceAccountName")
    private String serviceAccountName;

    @JsonProperty("containerSpec")
    private Container containerSpec;

    @JsonProperty("config")
    private Map<String, String> config;

    @JsonProperty("endpoint")
    private Endpoint endpoint;

    @JsonProperty("envSecretRefName")
    private String envSecretRefName;

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
