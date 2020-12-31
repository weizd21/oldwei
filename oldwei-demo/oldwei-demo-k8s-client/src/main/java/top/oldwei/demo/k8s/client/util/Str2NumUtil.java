package top.oldwei.demo.k8s.client.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @description:
 * @author: weizd
 * @time: 2020/12/17 12:51 下午
 */
@Slf4j
public class Str2NumUtil {


    public static void main(String[] args) {
        String cpuStr = "1000";
        System.out.println(getCpuValue(cpuStr));


        System.out.println(Integer.MAX_VALUE);

        System.out.println(Long.MAX_VALUE);

        System.out.println(new BigDecimal(Long.MAX_VALUE).divide(new BigDecimal(Math.pow(2,50)),2, RoundingMode.HALF_UP));

        String memoryStr = "98732548Mi";
        System.out.println(getMemoryValue(memoryStr));


    }


    /**
     * 统一为 n单位
     *
     * @param cpu
     * @return
     */
    public static Long getCpuValue(String cpu){
        log.info("cpu :{}",cpu);
        if(StringUtils.isEmpty(cpu)){
            return 0L;
        }
        BigDecimal value ;
        if (cpu.endsWith("n")) {
            value = new BigDecimal(cpu.replace("n", ""));
        } else if (cpu.endsWith("m")) {
            value = new BigDecimal(cpu.replace("m", "")).multiply(new BigDecimal(Math.pow(10,6)));
        } else {
            value = new BigDecimal(cpu.trim()).multiply(new BigDecimal(Math.pow(10,9)));
        }
        return value.longValue();
    }

    /**
     * 统一返回 Ki
     * @param memory
     * @return
     */
    public static Long getMemoryValue(String memory){
        if(StringUtils.isEmpty(memory)){
            return 0L;
        }
        BigDecimal value ;
        if (memory.endsWith("Ki")) {
            value = new BigDecimal(memory.replace("Ki", ""));
        } else if (memory.endsWith("Mi")) {
            value = new BigDecimal(memory.replace("Mi", "")).multiply(new BigDecimal(Math.pow(2,10)));
        } else if (memory.endsWith("Gi")) {
            value = new BigDecimal(memory.replace("Gi", "")).multiply(new BigDecimal(Math.pow(2,20)));
        } else if (memory.endsWith("Ti")) {
            value = new BigDecimal(memory.replace("Ti", "")).multiply(new BigDecimal(Math.pow(2,30)));
        } else if (memory.endsWith("Pi")) {
            value = new BigDecimal(memory.replace("Pi", "")).multiply(new BigDecimal(Math.pow(2,40)));
        } else if (memory.endsWith("Ei")) {
            value = new BigDecimal(memory.replace("Ei", "")).multiply(new BigDecimal(Math.pow(2,50)));
        } else {
            value = new BigDecimal(memory);
        }
        return value.longValue();
    }


//    public static Integer getCpuValue(String cpu){
//        Integer value = "";
//        if (cpu.endsWith("n")) {
//            value = Integer.valueOf(cpu.replace("n", ""));
//        } else if (cpu.endsWith("m")) {
//            value = Integer.valueOf(cpu.replace("m", ""));
//        } else {
//            value = Integer.valueOf(cpu) * ;
//        }
//        return Integer.valueOf(value);
//    }


//    public static Integer getMemoryValue(String memory){
//        String value = "";
//        if (memory.endsWith("Ki")) {
//            value = memory.replace("Ki", "");
//        } else if (memory.endsWith("Mi")) {
//            value = memory.replace("Mi", "");
//        } else if (memory.endsWith("Gi")) {
//            value = memory.replace("Gi", "");
//        } else if (memory.endsWith("Ti")) {
//            value = memory.replace("Ti", "");
//        } else if (memory.endsWith("Pi")) {
//            value = memory.replace("Pi", "");
//        } else if (memory.endsWith("Ei")) {
//            value = memory.replace("Ei", "");
//        } else {
//            value = memory;
//        }
//        return Integer.valueOf(value);
//    }


    public static double parseMemory(String memory) {
        if (StringUtils.isEmpty(memory)) {
            return 0d;
        }
        memory = memory.replaceAll("i", "");
        double ret;
        if (memory.endsWith("n")) {
            ret = Double.parseDouble(memory.replace("n", "")) * Math.pow(10, -9);
        } else if (memory.endsWith("u")) {
            ret = Double.parseDouble(memory.replace("u", "")) * Math.pow(10, -6);
        } else if (memory.endsWith("m")) {
            ret = Double.parseDouble(memory.replace("m", "")) * Math.pow(10, -3);
        } else if (memory.endsWith("k")) {
            ret = Double.parseDouble(memory.replace("k", "")) * Math.pow(10, 3);
        } else if (memory.endsWith("K")) {
            ret = Double.parseDouble(memory.replace("K", "")) * Math.pow(10, 3);
        } else if (memory.endsWith("M")) {
            ret = Double.parseDouble(memory.replace("M", "")) * Math.pow(10, 6);
        } else if (memory.endsWith("G")) {
            ret = Double.parseDouble(memory.replace("G", "")) * Math.pow(10, 9);
        } else if (memory.endsWith("T")) {
            ret = Double.parseDouble(memory.replace("T", "")) * Math.pow(10, 12);
        } else if (memory.endsWith("P")) {
            ret = Double.parseDouble(memory.replace("P", "")) * Math.pow(10, 15);
        } else if (memory.endsWith("E")) {
            ret = Double.parseDouble(memory.replace("E", "")) * Math.pow(10, 18);
        } else if (memory.endsWith("Ki")) {
            ret = Double.parseDouble(memory.replace("Ki", "")) * Math.pow(2, 10);
        } else if (memory.endsWith("Mi")) {
            ret = Double.parseDouble(memory.replace("Mi", "")) * Math.pow(2, 20);
        } else if (memory.endsWith("Gi")) {
            ret = Double.parseDouble(memory.replace("Gi", "")) * Math.pow(2, 30);
        } else if (memory.endsWith("Ti")) {
            ret = Double.parseDouble(memory.replace("Ti", "")) * Math.pow(2, 40);
        } else if (memory.endsWith("Pi")) {
            ret = Double.parseDouble(memory.replace("Pi", "")) * Math.pow(2, 50);
        } else if (memory.endsWith("Ei")) {
            ret = Double.parseDouble(memory.replace("Ei", "")) * Math.pow(2, 60);
        } else {
            ret = Double.parseDouble(memory);
        }
        return ret;
    }
}
