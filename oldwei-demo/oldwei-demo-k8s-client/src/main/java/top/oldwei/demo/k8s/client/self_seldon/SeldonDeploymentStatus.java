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
import io.fabric8.kubernetes.api.model.ServiceStatus;
import io.fabric8.kubernetes.api.model.apps.DeploymentStatus;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(Include.NON_NULL)
//@JsonPropertyOrder({"apiVersion", "kind", "metadata", "availableReplicas", "collisionCount", "conditions", "observedGeneration", "readyReplicas", "replicas", "unavailableReplicas", "updatedReplicas"})
@JsonDeserialize(
    using = None.class
)
@Data
public class SeldonDeploymentStatus implements KubernetesResource {

    @JsonProperty("state")
    private String state;

    @JsonProperty("description")
    private String description;

    @JsonProperty("deploymentStatus")
    private Map<String,DeploymentStatus> deploymentStatus;

    @JsonProperty("serviceStatus")
    private Map<String,ServiceStatus> serviceStatus;

    @JsonProperty("replicas")
    private Integer replicas;

    @JsonProperty("address")
    private Address address;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 1:正在运行 2：准备停止 3：停止  4.运行失败  5：发布  6：超时
     */
    private Byte status;


    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


}
