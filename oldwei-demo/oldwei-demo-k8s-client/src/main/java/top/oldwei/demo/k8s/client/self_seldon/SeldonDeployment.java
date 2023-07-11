package top.oldwei.demo.k8s.client.self_seldon;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer.None;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.client.CustomResource;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "spec", "status"})
@JsonDeserialize(
    using = None.class
)
@Data
@Version("v1")
@Group("machinelearning.seldon.io")
public class SeldonDeployment extends CustomResource<SeldonDeploymentSpec,SeldonDeploymentStatus> implements Namespaced {

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
