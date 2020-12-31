package top.oldwei.demo.util;

import java.util.Random;

/**
 * @author weizd
 * @date 2020/12/2下午6:42
 */
public class RandomUtil {

    private static Random random = new Random();

    public static int get2bitInt(){
        return random.nextInt(99);
    }

    public static int getRandomInt(int max){
        return random.nextInt(max);
    }

}