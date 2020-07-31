package top.oldwei.monitor.gpu.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author:weizd
 * @Date:20-3-19
 *  执行本地命令的工具类
 */
@Slf4j
public class ExecUtil {


    /**
     * 本地执行命令 获取结果
     * @param command
     * @return
     * @throws Exception
     */
    public static String execCmd(String command){
        log.debug("execCmd:{}",command);
        StringBuffer sb = new StringBuffer();
        BufferedReader bufferedReader = null;
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"sh","-c",command});

            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String buf = null;
            //返回数据
            while ((buf = bufferedReader.readLine()) != null) {
                sb.append(buf).append("\n");
            }
            log.debug("execUtil result:【{}】",sb.toString());
            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }




}
