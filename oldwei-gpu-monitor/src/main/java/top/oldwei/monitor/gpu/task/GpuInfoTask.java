package top.oldwei.monitor.gpu.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import top.oldwei.monitor.gpu.util.ExecUtil;
import top.oldwei.monitor.gpu.web.DeviceService;

/**
 * @Author:weizd
 * @Date:20-7-30
 */
@Slf4j
@EnableScheduling
@Configuration
public class GpuInfoTask {

    @Autowired
    private DeviceService deviceService;

    @Scheduled(fixedDelay = 15)
    public void getGPUInfo(){
        try {
            String memCmd = "free";
            String memRes= ExecUtil.execCmd(memCmd);

            log.info("{}",deviceService.getMemoryInfo(memRes));

        }catch (Exception e){

        }
    }
}
