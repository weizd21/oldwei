package top.oldwei.websocket.entity;

import top.oldwei.mybatis.entity.BaseEntity;


/**
 * @Author:weizd
 * @Date:20-4-3
 *
 * 单聊消息
 *
 *
 */
public class Message extends BaseEntity {


    private String sendUserId;

    private String receiverUserId;

    private String msgType;

    private String content;


}
