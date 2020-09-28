package top.oldwei.netty.common.constant;

/**
 * @Author:weizd
 * @Date:19-10-28
 */
public interface Command {

    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;

    Byte CREATE_GROUP_REQUEST = 5;

    Byte CREATE_GROUP_RESPONSE = 6;

    Byte JOIN_GROUP_REQUEST = 7;

    Byte JOIN_GROUP_RESPONSE = 8;

    Byte SEND_GROUP_MESSAGE_REQUEST = 9;

    Byte SEND_GROUP_MESSAGE_RESPONSE = 10;


    /**
     * 文件传输
     */
    Byte FILE_TRANSFER = 11;
    /**
     * 文件传输中 请求
     */
    Byte FILE_TRANSFER_REQUEST = 12;
    /**
     *  文件传输中 响应
     */
    Byte FILE_TRANSFER_RESPONSE = 13;

    /**
     * 开始文件传输 请求
     */
    Byte START_FILE_TRANSFER_REQUEST = 14;

    /**
     * 开始文件夹传输 请求
     */
    Byte START_FOLDER_TRANSFER_REQUEST = 15;



    String LOGIN = "login";

    String SEND_MESSAGE = "sendMsg";

    String CREATE_GROUP = "createGroup";

    String JOIN_GROUP = "joinGroup";

    String SEND_CROUP_MSG = "sendGroupMsg";


}
