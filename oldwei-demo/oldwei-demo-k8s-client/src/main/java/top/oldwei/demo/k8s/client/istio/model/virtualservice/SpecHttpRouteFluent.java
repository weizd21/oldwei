package top.oldwei.demo.k8s.client.istio.model.virtualservice;

import io.fabric8.kubernetes.api.builder.Fluent;

/**
 * @Classname VirtualServiceSpecFluent
 * @Description TODO
 * @Date 2020/10/20 0020 14:52
 * @Create by llarao
 */
public interface SpecHttpRouteFluent<A extends SpecHttpRouteFluent<A>> extends Fluent<A> {

    /*destination目标设置*/
    SpecHttpRouteDestination buildDestination();
    SpecHttpRouteDestination getDestination();
    A withDestination(SpecHttpRouteDestination items);
    Boolean hasDestination();
    SpecHttpRouteDestinationNested<A> withNewDestination();
    SpecHttpRouteDestinationNested<A> withNewDestinationLike(SpecHttpRouteDestination item);
    SpecHttpRouteDestinationNested<A> editDestination();
    SpecHttpRouteDestinationNested<A> editOrNewDestination();
    SpecHttpRouteDestinationNested<A> editOrNewDestinationLike(SpecHttpRouteDestination item);


    /*weight权重设置*/
    Integer getWeight();
    A withWeight(Integer item);


    public interface SpecHttpRouteDestinationNested<N> extends io.fabric8.kubernetes.api.builder.Nested<N>, SpecHttpRouteDestinationFluent<SpecHttpRouteDestinationNested<N>> {
        public N and();    public N endDestination();
    }
}
