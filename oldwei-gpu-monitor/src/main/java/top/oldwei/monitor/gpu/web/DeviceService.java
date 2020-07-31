package top.oldwei.monitor.gpu.web;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.oldwei.monitor.gpu.dto.CpuInfoDTO;
import top.oldwei.monitor.gpu.dto.GpuInfoDTO;
import top.oldwei.monitor.gpu.dto.MemInfo;
import top.oldwei.monitor.gpu.dto.MemoryInfoDTO;
import top.oldwei.monitor.gpu.dto.NetInfoDTO;
import top.oldwei.monitor.gpu.dto.NetItemInfo;
import top.oldwei.monitor.gpu.dto.SwapInfo;

import java.util.List;
import java.util.StringTokenizer;

/**
 * @Author:weizd
 * @Date:20-3-19
 *
 * 获取设备信息
 */
@Slf4j
@Component
public class DeviceService {


    /**
     * free
     *
     * 根据命令获取 内存信息
     * @param bashResult
     * @return
     */
    public MemoryInfoDTO getMemoryInfo(String bashResult){
        MemoryInfoDTO memoryInfoDTO = new MemoryInfoDTO();
        StringTokenizer stringTokenizer = new StringTokenizer(bashResult,"\n");
        stringTokenizer.nextToken();
        memoryInfoDTO.setMemInfo(getMemInfo(stringTokenizer.nextToken()));
        memoryInfoDTO.setSwapInfo(getSwapInfo(stringTokenizer.nextToken()));

        log.debug(JSONObject.toJSON(memoryInfoDTO).toString());
        return memoryInfoDTO;
    }

    private MemInfo getMemInfo(String lineStr){
        StringTokenizer stringTokenizer = new StringTokenizer(lineStr);
        stringTokenizer.nextToken();

        MemInfo memInfo = new MemInfo();
        memInfo.setTotal(stringTokenizer.nextToken());
        memInfo.setUsed(stringTokenizer.nextToken());
        memInfo.setFree(stringTokenizer.nextToken());
        memInfo.setShared(stringTokenizer.nextToken());
        memInfo.setBuffCache(stringTokenizer.nextToken());
        memInfo.setAvailable(stringTokenizer.nextToken());
        return memInfo;
    }

    private SwapInfo getSwapInfo(String lineStr){
        StringTokenizer stringTokenizer = new StringTokenizer(lineStr);
        stringTokenizer.nextToken();

        SwapInfo swapInfo = new SwapInfo();
        swapInfo.setTotal(stringTokenizer.nextToken());
        swapInfo.setUsed(stringTokenizer.nextToken());
        swapInfo.setFree(stringTokenizer.nextToken());
        return swapInfo;
    }



    /**
     * cat /proc/cpuinfo
     *
     * 根据 命令的执行结果 获取 cpu信息
     * @param bashResult
     * @return
     */
    public List<CpuInfoDTO> getCpuInfo(String bashResult){
        List<CpuInfoDTO> cpuInfoDTOS = Lists.newArrayList();
        CpuInfoDTO cpuInfoDTO = null;
        String [] cpuinfo = bashResult.split("\n\n");
        for(String cpuinfoItem:cpuinfo){
            StringTokenizer stringTokenizer = new StringTokenizer(cpuinfoItem,"\n",false);
            cpuInfoDTO = new CpuInfoDTO();

            cpuInfoDTO.setProcessor(getValue(stringTokenizer.nextToken()));
            cpuInfoDTO.setVendorId(getValue(stringTokenizer.nextToken()));
            cpuInfoDTO.setCpuFamily(getValue(stringTokenizer.nextToken()));
            cpuInfoDTO.setModel(getValue(stringTokenizer.nextToken()));
            cpuInfoDTO.setModelName(getValue(stringTokenizer.nextToken()));

            cpuInfoDTO.setStepping(getValue(stringTokenizer.nextToken()));
            cpuInfoDTO.setMicrocode(getValue(stringTokenizer.nextToken()));
            cpuInfoDTO.setCpuMHz(getValue(stringTokenizer.nextToken()));
            cpuInfoDTO.setCacheSize(getValue(stringTokenizer.nextToken()));
            cpuInfoDTO.setPhysicalId(getValue(stringTokenizer.nextToken()));

            cpuInfoDTO.setSiblings(getValue(stringTokenizer.nextToken()));
            cpuInfoDTO.setCoreId(getValue(stringTokenizer.nextToken()));
            cpuInfoDTO.setCpuCores(getValue(stringTokenizer.nextToken()));
            cpuInfoDTO.setApicid(getValue(stringTokenizer.nextToken()));
            cpuInfoDTO.setInitialApicid(getValue(stringTokenizer.nextToken()));

            cpuInfoDTO.setFpu(getValue(stringTokenizer.nextToken()));
            cpuInfoDTO.setFpuException(getValue(stringTokenizer.nextToken()));
            cpuInfoDTO.setCpuidLevel(getValue(stringTokenizer.nextToken()));
            cpuInfoDTO.setWp(getValue(stringTokenizer.nextToken()));
            cpuInfoDTO.setFlags(getValue(stringTokenizer.nextToken()));

            cpuInfoDTO.setBugs(getValue(stringTokenizer.nextToken()));
            cpuInfoDTO.setBogomips(getValue(stringTokenizer.nextToken()));
            cpuInfoDTO.setClflushSize(getValue(stringTokenizer.nextToken()));
            cpuInfoDTO.setCacheAlignment(getValue(stringTokenizer.nextToken()));
            cpuInfoDTO.setAddressSizes(getValue(stringTokenizer.nextToken()));

            cpuInfoDTO.setPowerManagement(getValue(stringTokenizer.nextToken()));

            cpuInfoDTOS.add(cpuInfoDTO);
        }
        log.debug(JSONObject.toJSON(cpuInfoDTOS).toString());
        return cpuInfoDTOS;
    }

    /**
     * 根据 key : value 字符串 获取其中的value
     * @param str
     * @return
     */
    private String getValue(String str){
        String res = "";
        if(!StrUtil.isEmpty(str) && str.contains(":")){
            res = str.substring(str.indexOf(":")+1).trim();
        }
        return res;
    }



    /**
     * nvidia-smi --query-gpu=index,uuid,name,
     *                        utilization.gpu,utilization.memory,
     *                        memory.total,memory.used,memory.free,
     *                        temperature.gpu,temperature.memory --format=csv,noheader,nounits
     *
     * 根据bash命令执行结果 获取详细GPU信息
     * @param bashResult
     * @return
     */
    public List<GpuInfoDTO> getGpuInfo(String bashResult){
        StringTokenizer stringTokenizer = new StringTokenizer(bashResult,"\n");
        List<GpuInfoDTO> gpuInfoDTOList = Lists.newArrayList();
        GpuInfoDTO gpuInfoDTO = null;
        while (stringTokenizer.hasMoreElements()){
            String element = stringTokenizer.nextToken();
            StringTokenizer stringTokenizer1 = new StringTokenizer(element,",");
            gpuInfoDTO = new GpuInfoDTO();
            gpuInfoDTO.setIndex(stringTokenizer1.nextToken().trim());
            gpuInfoDTO.setUuid(stringTokenizer1.nextToken().trim());
            gpuInfoDTO.setName(stringTokenizer1.nextToken().trim());
            gpuInfoDTO.setUtilizationGpu(stringTokenizer1.nextToken().trim());
            gpuInfoDTO.setUtilizationMemory(stringTokenizer1.nextToken().trim());
            gpuInfoDTO.setMemoryTotal(stringTokenizer1.nextToken().trim());
            gpuInfoDTO.setMemoryUsed(stringTokenizer1.nextToken().trim());
            gpuInfoDTO.setMemoryFree(stringTokenizer1.nextToken().trim());
            gpuInfoDTO.setTemperatureGpu(stringTokenizer1.nextToken().trim());
            gpuInfoDTO.setTemperatureMemory(stringTokenizer1.nextToken().trim());
            gpuInfoDTOList.add(gpuInfoDTO);
        }
        log.debug(JSONObject.toJSON(gpuInfoDTOList).toString());
        return gpuInfoDTOList;
    }



    /**
     * cat /proc/net/dev
     *
     * 从执行的命令结果 映射到 bean 中
     * @param bashResult  执行shell命令的结果
     * @return
     */
    public List<NetInfoDTO> getNetInfo(String bashResult){
        StringTokenizer stringTokenizer = new StringTokenizer(bashResult,"\n");
        List<NetInfoDTO> netInfoDTOList = Lists.newArrayList();
        NetInfoDTO netInfoDTO = null;
        while (stringTokenizer.hasMoreTokens()) {
            String str = stringTokenizer.nextToken();
            if (!str.contains("|")) {
                StringTokenizer stringTokenizer1 = new StringTokenizer(str);
                netInfoDTO = new NetInfoDTO();
                netInfoDTO.setFace(stringTokenizer1.nextToken());
                netInfoDTO.setReceive(getNetItemInfo(stringTokenizer1));
                netInfoDTO.setTransmit(getNetItemInfo(stringTokenizer1));
                netInfoDTOList.add(netInfoDTO);
            }
        }
        log.debug(JSONObject.toJSON(netInfoDTOList).toString());
        return netInfoDTOList;
    }

    /**
     * 获取 输入网络和输出网络参数
     * @param stringTokenizer
     * @return
     */
    private NetItemInfo getNetItemInfo(StringTokenizer stringTokenizer){
        NetItemInfo netItemInfo = new NetItemInfo();
        netItemInfo.setBytes(stringTokenizer.nextToken().trim());
        netItemInfo.setPackets(stringTokenizer.nextToken().trim());
        netItemInfo.setErrs(stringTokenizer.nextToken().trim());
        netItemInfo.setDrop(stringTokenizer.nextToken().trim());
        netItemInfo.setFifo(stringTokenizer.nextToken().trim());
        netItemInfo.setFrame(stringTokenizer.nextToken().trim());
        netItemInfo.setCompressed(stringTokenizer.nextToken().trim());
        netItemInfo.setMulticast(stringTokenizer.nextToken().trim());
        return netItemInfo;
    }


}
