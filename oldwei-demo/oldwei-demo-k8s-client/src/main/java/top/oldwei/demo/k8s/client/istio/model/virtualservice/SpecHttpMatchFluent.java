package top.oldwei.demo.k8s.client.istio.model.virtualservice;

import io.fabric8.kubernetes.api.builder.Fluent;

import java.util.Map;

/**
 * @Classname VirtualServiceSpecHttpMatchFluent
 * @Description TODO
 * @Date 2020/10/20 0020 16:00
 * @Create by llarao
 */
public interface SpecHttpMatchFluent<A extends SpecHttpMatchFluent<A>> extends Fluent<A> {

    Map<String, SpecHttpMatchCondition> getHeaders();
    Map<String, SpecHttpMatchCondition> buildHeaders();
    SpecHttpMatchCondition buildHeader(String key);
    A withHeaders(Map<String, SpecHttpMatchCondition> item);
    Boolean hasHeaders();
    SpecHttpMatchHeaderConditionNested<A> addNewHeader(String key);
    SpecHttpMatchHeaderConditionNested<A> addNewHeadersLike(String key , SpecHttpMatchCondition item);
    SpecHttpMatchHeaderConditionNested<A> editHeaders(String key);
    A addNewHeaders(String key ,SpecHttpMatchCondition item);
    A removeFromHeaders(String... keys);


    /*URI相关操作*/
    SpecHttpMatchCondition getUri();
    SpecHttpMatchCondition buildUri();
    A withUri(SpecHttpMatchCondition items);
    Boolean hasUri();
    SpecHttpMatchUriConditionNested<A> withNewUri();
    SpecHttpMatchUriConditionNested<A> withNewUriLike(SpecHttpMatchCondition item);
    SpecHttpMatchUriConditionNested<A> editUris();
    SpecHttpMatchUriConditionNested<A> editOrNewUri();
    SpecHttpMatchUriConditionNested<A> editOrNewUriLike(SpecHttpMatchCondition item);

    /*Scheme相关操作*/
    SpecHttpMatchCondition getScheme();
    SpecHttpMatchCondition buildScheme();
    A withScheme(SpecHttpMatchCondition items);
    Boolean hasScheme();
    SpecHttpMatchSchemeConditionNested<A> withNewScheme();
    SpecHttpMatchSchemeConditionNested<A> withNewSchemeLike(SpecHttpMatchCondition item);
    SpecHttpMatchSchemeConditionNested<A> editSchemes();
    SpecHttpMatchSchemeConditionNested<A> editOrNewScheme();
    SpecHttpMatchSchemeConditionNested<A> editOrNewSchemeLike(SpecHttpMatchCondition item);

    /*Methods相关操作*/
    SpecHttpMatchCondition getMethods();
    SpecHttpMatchCondition buildMethods();
    A withMethods(SpecHttpMatchCondition items);
    Boolean hasMethods();
    SpecHttpMatchMethodsConditionNested<A> withNewMethods();
    SpecHttpMatchMethodsConditionNested<A> withNewMethodsLike(SpecHttpMatchCondition item);
    SpecHttpMatchMethodsConditionNested<A> editMethods();
    SpecHttpMatchMethodsConditionNested<A> editOrNewMethods();
    SpecHttpMatchMethodsConditionNested<A> editOrNewMethodsLike(SpecHttpMatchCondition item);


    /*SourceLabels相关配置*/
    Map<String, String> getSourceLabels();
    Map<String, String> buildSourceLabels();
    A withSourceLabels(Map<String, String> sourceLabels);
    Boolean hasSourceLabels();
    A setNewSourceLabel(String key,String value);
    A addAllToSourceLabels(Map<String, String> sourceLabels);
    A removeFromSourceLabel(String... items);


    public interface SpecHttpMatchConditionNested<N> extends io.fabric8.kubernetes.api.builder.Nested<N>, SpecHttpMatchConditionFluent<SpecHttpMatchConditionNested<N>> {
        @Override
        public N and();    public N endCondition();
    }

    public interface SpecHttpMatchHeaderConditionNested<N> extends io.fabric8.kubernetes.api.builder.Nested<N>, SpecHttpMatchConditionFluent<SpecHttpMatchHeaderConditionNested<N>> {
        @Override
        public N and();    public N endHeader();
    }

    public interface SpecHttpMatchUriConditionNested<N> extends io.fabric8.kubernetes.api.builder.Nested<N>, SpecHttpMatchConditionFluent<SpecHttpMatchUriConditionNested<N>> {
        @Override
        public N and();    public N endUri();
    }

    public interface SpecHttpMatchSchemeConditionNested<N> extends io.fabric8.kubernetes.api.builder.Nested<N>, SpecHttpMatchConditionFluent<SpecHttpMatchSchemeConditionNested<N>> {
        @Override
        public N and();    public N endScheme();
    }

    public interface SpecHttpMatchMethodsConditionNested<N> extends io.fabric8.kubernetes.api.builder.Nested<N>, SpecHttpMatchConditionFluent<SpecHttpMatchMethodsConditionNested<N>> {
        @Override
        public N and();    public N endMethod();
    }


}
