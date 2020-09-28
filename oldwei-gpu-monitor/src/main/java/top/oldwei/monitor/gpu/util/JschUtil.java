package top.oldwei.monitor.gpu.util;

import com.google.common.collect.Lists;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * @Author:weizd
 * @Date:20-3-18
 */
@Slf4j
public class JschUtil {


    /**
     * 获取连接
     * @param user
     * @param password
     * @param host
     * @param post
     * @param priKeyPath
     * @param usePassword
     * @return
     * @throws Exception
     */
    private static Session connect(String user,String password,String host,int post,String priKeyPath,boolean usePassword) throws Exception {
        JSch  jsch = new JSch();
        if(!usePassword){
            // 配置私钥地址
            jsch.addIdentity(priKeyPath);
        }
        Session session = jsch.getSession(user, host, post);
        if (session == null) {
            throw new Exception("session is null");
        }
        if(usePassword){
            session.setPassword(password);
        }
        java.util.Properties config = new java.util.Properties();
        //第一次登陆
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        try {
            session.connect(30000);
        } catch (Exception e) {
            throw new Exception("连接远程端口无效或用户名密码错误");
        }
        return session;
    }


    /**
     * 密码远程执行命令
     * @param command
     * @param user
     * @param passwd
     * @param host
     * @param port
     * @return
     * @throws Exception
     */
    public static String execCmdWithPassword(String command, String user, String passwd, String host, int port) throws Exception {
        return execCmd(command,user,passwd,host,port,null,true);
    }

    /**
     * 免密远程执行命令
     * @param command
     * @param user
     * @param priKeyPath
     * @param host
     * @param port
     * @return
     * @throws Exception
     */
    public static String execCmdWithPriKeyPath(String command, String user, String priKeyPath, String host, int port) throws Exception {
        return execCmd(command,user,null,host,port,priKeyPath,false);
    }


    private static String execCmd(String command, String user, String passwd, String host, int port,String priKeyPath,boolean usePassword) throws Exception {
        log.info("execCmd params : command=[{}],user=[{}],passwd=[{}],host=[{}],port=[{}],priKeyPath=[{}],usePassword=[{}]",command,user,passwd,host,port,priKeyPath,usePassword);
        Session session= connect(user, passwd,host,port,priKeyPath,usePassword);
        BufferedReader reader = null;
        Channel channel = null;
        StringBuffer sb = new StringBuffer();
        try {
            channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);

            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);

            channel.connect();
            InputStream in = channel.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in));
            String buf = null;
            //返回数据
            while ((buf = reader.readLine()) != null) {
                sb.append(buf).append("\n");
            }
            log.info("execCom result:[{}]",sb.toString());
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSchException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            channel.disconnect();
            session.disconnect();
        }
        return null;
    }


}
