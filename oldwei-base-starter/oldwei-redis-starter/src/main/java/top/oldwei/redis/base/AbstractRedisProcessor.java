package top.oldwei.redis.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @Author:weizd
 * @Date:20-2-27
 */
@Slf4j
public abstract class AbstractRedisProcessor implements RedisCommand {

    /**
     * 序列化
     */
    protected static byte[] serialize(Object object) {
        ObjectOutputStream objectOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            log.error("etcRedis serialize Object error: ", e);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            } catch (IOException e) {
                log.error("etcRedis outputStream close error: ", e);
            }
        }
        return null;
    }

    /**
     * 反序列化
     *
     * @param bytes
     * @return
     */
    protected static Object unSerialize(byte[] bytes) {
        ObjectInputStream objectInputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return objectInputStream.readObject();
        } catch (Exception e) {
            log.error("etcRedis unSerialize Object error: ", e);
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
            } catch (IOException e) {
                log.error("etcRedis inputStream close error: ", e);
            }
        }
        return null;
    }


}
