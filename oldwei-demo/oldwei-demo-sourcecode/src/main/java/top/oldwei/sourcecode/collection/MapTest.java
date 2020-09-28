package top.oldwei.sourcecode.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:weizd
 * @Date:20-6-19
 */
public class MapTest {


    public static void main(String[] args) {


        Map<String,String> stringMap = new HashMap<>();

        for(int i=0;i<20;i++){
            stringMap.put("key_"+i,"value_"+i);
        }
        System.out.println(" over------");
    }
}
