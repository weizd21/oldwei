package top.oldwei.websocket.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import top.oldwei.websocket.constant.WebSocketConstant;
import top.oldwei.websocket.pojo.SSHConnectInfo;
import top.oldwei.websocket.pojo.WebSSHData;
import top.oldwei.websocket.service.WebSSHService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

/**
 * @Author:weizd
 * @Date:20-3-20
 */
@Slf4j
@Service
public class WebSSHServiceImpl implements WebSSHService {

    private static Map<String,SSHConnectInfo> sessionMap = Maps.newConcurrentMap();


    @Override
    public void initConnection(WebSocketSession webSocketSession) {
        JSch jSch = new JSch();
        SSHConnectInfo sshConnectInfo = new SSHConnectInfo();
        sshConnectInfo.setJSch(jSch);
        sshConnectInfo.setWebSocketSession(webSocketSession);
        String uuid = String.valueOf(webSocketSession.getAttributes().get(WebSocketConstant.USER_UUID_KEY));
        sessionMap.put(uuid,sshConnectInfo);
        log.info("【{}】初始化连接成功。。。",uuid);
    }

    @Override
    public void dealMessage(WebSocketSession webSocketSession,String message){
        WebSSHData webSSHData = JSONObject.parseObject(message,WebSSHData.class);

        String uuid =String.valueOf(webSocketSession.getAttributes().get(WebSocketConstant.USER_UUID_KEY));

        if(WebSocketConstant.WEBSSH_OPERATE_CONNECT.equals(webSSHData.getOperate())){
            // 建立连接
            SSHConnectInfo sshConnectInfo = sessionMap.get(uuid);
            new Thread(()->{
                try{
                    connect(sshConnectInfo,webSSHData,webSocketSession);
                }catch (Exception e){
                    e.printStackTrace();
                    close(webSocketSession);
                }


            }).start();

        }else if(WebSocketConstant.WEBSSH_OPERATE_COMMAND.equals(webSSHData.getOperate())){
            String command = webSSHData.getCommand();

            SSHConnectInfo sshConnectInfo = sessionMap.get(uuid);
            if(sshConnectInfo != null){
                try {
                    transCommand(sshConnectInfo.getChannel(),command);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }





        }else {
            log.error("暂不支持的操作方式");
        }


    }

    private void connect(SSHConnectInfo sshConnectInfo,WebSSHData webSSHData,WebSocketSession webSocketSession) throws JSchException, IOException{
        Session session = null;
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        //获取jsch的会话
        session = sshConnectInfo.getJSch().getSession(webSSHData.getUsername(), webSSHData.getHost(), webSSHData.getPort());
        session.setConfig(config);
        //设置密码
        session.setPassword(webSSHData.getPassword());
        //连接  超时时间30s
        session.connect(30000);

        //开启shell通道
        Channel channel = session.openChannel("shell");

        //通道连接 超时时间3s
        channel.connect(3000);

        //设置channel
        sshConnectInfo.setChannel(channel);

//        //转发消息
//        transToSSH(channel, "\r");

        //读取终端返回的信息流
        InputStream inputStream = channel.getInputStream();
        try {
            //循环读取
            byte[] buffer = new byte[1024];
            int i = 0;
            //如果没有数据来，线程会一直阻塞在这个地方等待数据。
            while ((i = inputStream.read(buffer)) != -1) {
                sendMessage(webSocketSession, Arrays.copyOfRange(buffer, 0, i));
            }

        } finally {
            //断开连接后关闭会话
            session.disconnect();
            channel.disconnect();
            if (inputStream != null) {
                inputStream.close();
            }
        }

    }

    private void transCommand(Channel channel,String command)throws IOException{
        if (channel != null) {
            OutputStream outputStream = channel.getOutputStream();
            outputStream.write(command.getBytes());
            outputStream.flush();
        }
    }

    @Override
    public void sendMessage(WebSocketSession webSocketSession, byte[] bytes) throws IOException {
        webSocketSession.sendMessage(new TextMessage(bytes));
    }

    @Override
    public void close(WebSocketSession webSocketSession) {
        String userId = String.valueOf(webSocketSession.getAttributes().get(WebSocketConstant.USER_UUID_KEY));
        SSHConnectInfo sshConnectInfo = (SSHConnectInfo) sessionMap.get(userId);
        if (sshConnectInfo != null) {
            //断开连接
            if (sshConnectInfo.getChannel() != null) {
                sshConnectInfo.getChannel().disconnect();
            }
            //map中移除
            sessionMap.remove(userId);
        }
    }
}
