package top.oldwei.demo.k8s.client.istio.model.virtualservice;

import io.fabric8.kubernetes.api.builder.Fluent;

/**
 * @Classname VirtualServiceSpecHttpRouteDestinationFluent
 * @Description TODO
 * @Date 2020/10/20 0020 16:53
 * @Create by llarao
 */
public interface SpecHttpRouteDestinationFluent<A extends SpecHttpRouteDestinationFluent<A>> extends Fluent<A> {
    String getHost();
    A withHost(String host);
    String getSubset();
    A withSubset(String subset);

    SpecHttpRouteDestinationPort getPort();
    SpecHttpRouteDestinationPort buildPort();
    A withPort(SpecHttpRouteDestinationPort item);
    Boolean hasPort();
    DestinationPortNested<A> withNewPort();
    DestinationPortNested<A> withNewPortLike(SpecHttpRouteDestinationPort item);
    DestinationPortNested<A> editPort();
    DestinationPortNested<A> editOrNewPort();
    DestinationPortNested<A> editOrNewPortLike(SpecHttpRouteDestinationPort item);


    public interface DestinationPortNested<N> extends io.fabric8.kubernetes.api.builder.Nested<N>, SpecHttpRouteDestinationPortFluent<DestinationPortNested<N>> {
        public N and();    public N endPort();
    }
}
