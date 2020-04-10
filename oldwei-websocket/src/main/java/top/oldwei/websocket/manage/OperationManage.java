package top.oldwei.websocket.manage;

import com.google.common.collect.Maps;
import top.oldwei.websocket.constant.OperateConstant;
import top.oldwei.websocket.strategy.JoinGroupOperate;
import top.oldwei.websocket.strategy.Operate;

import java.util.Map;

/**
 * @Author:weizd
 * @Date:20-3-24
 */
public class OperationManage {


    private static Map<String, Operate> operateMap = Maps.newConcurrentMap();

    static {
        operateMap.put(OperateConstant.JOIN_GROUP,new JoinGroupOperate());
    }



}
