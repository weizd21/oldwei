package top.oldwei.demo.k8s.client.istio.model.serviceentry;

import io.fabric8.kubernetes.api.builder.Fluent;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ObjectMetaFluent;

/**
 * @Classname VirtualServiceFluent
 * @Description TODO
 * @Date 2020/10/17 0017 10:21
 * @Create by llarao
 */
public interface ServiceEntryFluent<A extends ServiceEntryFluent<A>> extends Fluent<A> {

    public String getApiVersion();
    public A withApiVersion(String apiVersion);
    public Boolean hasApiVersion();
    public A withNewApiVersion(String arg1);
    public A withNewApiVersion(StringBuilder arg1);
    public A withNewApiVersion(StringBuffer arg1);
    public String getKind();
    public A withKind(String kind);
    public Boolean hasKind();
    A withNewKind(String arg1);
    public A withNewKind(StringBuilder arg1);
    public A withNewKind(StringBuffer arg1);

    /**
     * This method has been deprecated, please use method buildMetadata instead.
     * @return The buildable object.
     */
    public ObjectMeta getMetadata();
    public ObjectMeta buildMetadata();
    public A withMetadata(ObjectMeta metadata);
    public Boolean hasMetadata();
    public MetadataNested<A> withNewMetadata();
    public MetadataNested<A> withNewMetadataLike(ObjectMeta item);
    public MetadataNested<A> editMetadata();
    public MetadataNested<A> editOrNewMetadata();
    public MetadataNested<A> editOrNewMetadataLike(ObjectMeta item);

    /**
     * This method has been deprecated, please use method buildSpec instead.
     * @return The buildable object.
     */
    public ServiceEntrySpec getSpec();
    public ServiceEntrySpec buildSpec();
    public A withSpec(ServiceEntrySpec spec);
    public Boolean hasSpec();
    public SpecNested<A> withNewSpec();
    public SpecNested<A> withNewSpecLike(ServiceEntrySpec item);
    public SpecNested<A> editSpec();
    public SpecNested<A> editOrNewSpec();
    public SpecNested<A> editOrNewSpecLike(ServiceEntrySpec item);


    public interface MetadataNested<N> extends io.fabric8.kubernetes.api.builder.Nested<N>,ObjectMetaFluent<MetadataNested<N>>{


        @Override
        public N and();    public N endMetadata();
    }
    public interface SpecNested<N> extends io.fabric8.kubernetes.api.builder.Nested<N>, ServiceEntrySpecFluent<SpecNested<N>> {
        @Override
        public N and();    public N endSpec();
    }


}
