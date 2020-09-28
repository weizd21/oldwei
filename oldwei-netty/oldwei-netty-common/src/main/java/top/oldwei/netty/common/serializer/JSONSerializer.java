package top.oldwei.netty.common.serializer;

import com.alibaba.fastjson.JSONObject;
import top.oldwei.netty.common.constant.SerializerType;

/**
 * @Author:weizd
 * @Date:20-5-12
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerType() {
        return SerializerType.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSONObject.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSONObject.parseObject(bytes,clazz);
    }
}
