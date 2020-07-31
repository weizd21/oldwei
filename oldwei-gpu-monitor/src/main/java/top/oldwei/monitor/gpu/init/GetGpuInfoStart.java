package top.oldwei.monitor.gpu.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import top.oldwei.monitor.gpu.dto.MemoryInfoDTO;
import top.oldwei.monitor.gpu.util.ExecUtil;
import top.oldwei.monitor.gpu.util.JschUtil;
import top.oldwei.monitor.gpu.web.DeviceService;

import java.util.StringTokenizer;

/**
 * @Author:weizd
 * @Date:20-3-18
 *  启动获取GPU信息的线程
 */
@Slf4j
@Component
public class GetGpuInfoStart implements CommandLineRunner {

    @Autowired
    private DeviceService deviceService;


    @Override
    public void run(String... args) throws Exception {

        log.info("---> start run getGpuInfo ");

        String sshHost = "192.168.1.200";
        int sshPort = 22;
        String sshUser = "dataexa";
        String sshPassword = "DataExa@798205268";


//        String netCmd = "cat /proc/net/dev";
//        String localNetResult = ExecUtil.execCmd(netCmd);
//        deviceService.getNetInfo(localNetResult);
//
//        String remoteNetResult = JschUtil.execCmdWithPassword(netCmd,sshUser,sshPassword,sshHost,sshPort);
//        deviceService.getNetInfo(remoteNetResult);
//
//
//
//        String gpuCmd = "nvidia-smi --query-gpu=index,uuid,name,utilization.gpu,utilization.memory,memory.total,memory.used,memory.free,temperature.gpu,temperature.memory --format=csv,noheader,nounits";
//        String gpuResult = JschUtil.execCmdWithPassword(gpuCmd,sshUser,sshPassword,sshHost,sshPort);
//        deviceService.getGpuInfo(gpuResult);


//        String cpuinfoCmd = "cat /proc/cpuinfo";
//        String localCpuinfoResult = ExecUtil.execCmd(cpuinfoCmd);
//        String remoteCpuinfoResult = JschUtil.execCmdWithPassword(cpuinfoCmd,sshUser,sshPassword,sshHost,sshPort);
//        deviceService.getCpuInfo(localCpuinfoResult);
//        deviceService.getCpuInfo(remoteCpuinfoResult);


//        String meminfoCmd = "cat /proc/meminfo";
//
//        String localMeminfoResult = ExecUtil.execCmd(meminfoCmd);
//
//        log.info(localMeminfoResult);


//        String memCmd = "free";
//
//        String memRes= ExecUtil.execCmd(memCmd);
//
//        deviceService.getMemoryInfo(memRes);



        String cmd1 = "free -m | awk 'NR==2{printf \"Memory Usage: %s/%sMB (%.2f%%)\\n\", $3,$2,$3*100/$2 }'";
        cmd1 = "df -h | awk '$NF==\"/\"{printf \"Disk Usage: %d/%dGB (%s)\\n\", $3,$2,$5}'";
        cmd1 = "top -bn1 | grep load | awk '{printf \"CPU Load: %.2f\\n\", $(NF-2)}'";

        String r1 = ExecUtil.execCmd(cmd1);

        //String r2 = JschUtil.execCmdWithPassword(cmd1,sshUser,sshPassword,sshHost,sshPort);

        log.info(r1);

        //log.info(r2);


    }










}
