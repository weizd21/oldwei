package top.oldwei.netty.common.packet;

import lombok.Data;
import top.oldwei.netty.common.base.Packet;
import top.oldwei.netty.common.constant.Command;

/**
 * @Author:weizd
 * @Date:19-10-28
 */
@Data
public class LoginRequestPacket extends Packet {

    private Long userId;

    private String userName;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
