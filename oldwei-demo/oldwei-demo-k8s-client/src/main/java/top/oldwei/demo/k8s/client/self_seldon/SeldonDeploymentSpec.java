//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package top.oldwei.demo.k8s.client.self_seldon;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer.None;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import lombok.Data;
import okhttp3.Protocol;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(Include.NON_NULL)
//@JsonPropertyOrder({"apiVersion", "kind", "metadata", "minReadySeconds", "paused", "progressDeadlineSeconds", "replicas", "revisionHistoryLimit", "selector", "strategy", "template"})
@JsonDeserialize(
        using = None.class
)
@Data
public class SeldonDeploymentSpec implements KubernetesResource {

    /**
     *
     *
     */
    @JsonProperty("name")
    private String name;
    /**
     *
     *
     */
    @JsonProperty("predictors")
    private PredictorSpec[] predictors;
    /**
     *
     *
     */
    @JsonProperty("oauthKey")
    private String oauthKey;
    /**
     *
     *
     */
    @JsonProperty("oauthSecret")
    private String oauthSecret;
    /**
     *
     *
     */
    @JsonProperty("annotations")
    private Map<String, String> annotations;
    /**
     *
     *
     */
    @JsonProperty("protocol")
    private Protocol protocol;
    /**
     *
     *
     */
    @JsonProperty("transport")
    private String transport;
    /**
     *
     *
     */
    @JsonProperty("replicas")
    private Integer replicas;

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
