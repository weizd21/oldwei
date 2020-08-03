package top.oldwei.monitor.gpu.task;

import cn.hutool.core.date.SystemClock;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import top.oldwei.monitor.gpu.dto.GpuInfoDTO;
import top.oldwei.monitor.gpu.dto.MemoryInfoDTO;
import top.oldwei.monitor.gpu.util.ExecUtil;
import top.oldwei.monitor.gpu.util.JschUtil;
import top.oldwei.monitor.gpu.web.DeviceService;

import java.util.Collection;
import java.util.List;

/**
 * @Author:weizd
 * @Date:20-7-30
 */
@Slf4j
@EnableScheduling
@Configuration
public class GpuInfoTask {

    private static final String gpu200Path = "/home/weizd/Desktop/20200420-20200425/id_rsa";

    @Autowired
    private DeviceService deviceService;


    @Autowired
    private RedissonClient redissonClient;


//    @Scheduled(fixedDelay = 5*1000)
    public void setGPUInfo(){
        try {
            String memCmd = "free";
            String memRes= ExecUtil.execCmd(memCmd);

//            log.info("{}",deviceService.getMemoryInfo(memRes));

//            String cpuCmd = "cat /proc/cpuinfo";
//            String cpuRes = ExecUtil.execCmd(cpuCmd);
//            log.info("{}",deviceService.getCpuInfo(cpuRes));
//
//            String netCmd = "cat /proc/net/dev";
//            String netRes = ExecUtil.execCmd(netCmd);
//            log.info("{}",deviceService.getNetInfo(netCmd));

            RScoredSortedSet<String> sortedSet = redissonClient.getScoredSortedSet("device_localhost");
            MemoryInfoDTO memoryInfoDTO = deviceService.getMemoryInfo(memRes);
            log.info("SET {}",memoryInfoDTO);
            sortedSet.add(-(SystemClock.now()), JSONObject.toJSON(memoryInfoDTO).toString());



            String gpuCmd = "nvidia-smi --query-gpu=index,uuid,name,utilization.gpu,utilization.memory,memory.total,memory.used,memory.free,temperature.gpu,temperature.memory --format=csv,noheader,nounits";

            String gpuRes = JschUtil.execCmdWithPriKeyPath(gpuCmd,"dataexa",gpu200Path,"192.168.1.200",22);

            List<GpuInfoDTO> list  = deviceService.getGpuInfo(gpuRes);

            log.info("{}",list);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    @Scheduled(fixedDelay = 15*1000)
    public void getGPUInfo(){
        try {
            RScoredSortedSet<String> sortedSet = redissonClient.getScoredSortedSet("device_localhost");

//            MemoryInfoDTO memoryInfoDTO = JSONObject.parseObject(sortedSet.first(),MemoryInfoDTO.class);
//            log.info("{}",memoryInfoDTO);
//            log.info("size: {}",sortedSet.size());

            Collection<String> topList = sortedSet.valueRange(0,5);



            for(String s :topList){
                log.info("GET {}",JSONObject.parseObject(s,MemoryInfoDTO.class));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }




}
