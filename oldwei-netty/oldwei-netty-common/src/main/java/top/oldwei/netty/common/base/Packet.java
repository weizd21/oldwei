package top.oldwei.netty.common.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:weizd
 * @Date:20-5-12
 */
@Data
public abstract class Packet implements Serializable {

    /**
     * 版本号
     */
    private Byte version = 1;


    /**
     * 具体 数据的标识
     * 定义的指令
     * 代表 传输数据的类型
     * @return
     */
    public abstract Byte getCommand();


}
