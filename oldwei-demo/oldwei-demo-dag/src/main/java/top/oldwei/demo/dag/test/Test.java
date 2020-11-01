package top.oldwei.demo.dag.test;

import com.alibaba.fastjson.JSONObject;
import top.oldwei.demo.dag.entity.Dag;
import top.oldwei.demo.dag.entity.ExecutorPlan;

public class Test {


    public static void main(String[] args) {


        Dag dag = JSONObject.parseObject(dagStr,Dag.class);

        ExecutorPlan.execute(dag);

    }


    private static String str = "{\n" +
            "\t\"edges\": [\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 0,\n" +
            "\t\t\t\"targetNodeId\": 1,\n" +
            "\t\t\t\"id\": 2,\n" +
            "\t\t\t\"sourceNodeId\": 0,\n" +
            "\t\t\t\"sourceOutputIndex\": 0,\n" +
            "\t\t\t\"type\": \"active\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 0,\n" +
            "\t\t\t\"targetNodeId\": 4,\n" +
            "\t\t\t\"id\": 5,\n" +
            "\t\t\t\"sourceNodeId\": 3,\n" +
            "\t\t\t\"sourceOutputIndex\": 0,\n" +
            "\t\t\t\"type\": \"active\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 0,\n" +
            "\t\t\t\"targetNodeId\": 7,\n" +
            "\t\t\t\"id\": 8,\n" +
            "\t\t\t\"sourceNodeId\": 6,\n" +
            "\t\t\t\"sourceOutputIndex\": 0,\n" +
            "\t\t\t\"type\": \"active\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 0,\n" +
            "\t\t\t\"targetNodeId\": 12,\n" +
            "\t\t\t\"id\": 15,\n" +
            "\t\t\t\"sourceNodeId\": 9,\n" +
            "\t\t\t\"sourceOutputIndex\": 0,\n" +
            "\t\t\t\"type\": \"active\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 1,\n" +
            "\t\t\t\"targetNodeId\": 12,\n" +
            "\t\t\t\"id\": 16,\n" +
            "\t\t\t\"sourceNodeId\": 1,\n" +
            "\t\t\t\"sourceOutputIndex\": 0,\n" +
            "\t\t\t\"type\": \"active\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 0,\n" +
            "\t\t\t\"targetNodeId\": 13,\n" +
            "\t\t\t\"id\": 17,\n" +
            "\t\t\t\"sourceNodeId\": 10,\n" +
            "\t\t\t\"sourceOutputIndex\": 0,\n" +
            "\t\t\t\"type\": \"active\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 1,\n" +
            "\t\t\t\"targetNodeId\": 13,\n" +
            "\t\t\t\"id\": 18,\n" +
            "\t\t\t\"sourceNodeId\": 4,\n" +
            "\t\t\t\"sourceOutputIndex\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 0,\n" +
            "\t\t\t\"targetNodeId\": 14,\n" +
            "\t\t\t\"id\": 19,\n" +
            "\t\t\t\"sourceNodeId\": 11,\n" +
            "\t\t\t\"sourceOutputIndex\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 1,\n" +
            "\t\t\t\"targetNodeId\": 14,\n" +
            "\t\t\t\"id\": 20,\n" +
            "\t\t\t\"sourceNodeId\": 7,\n" +
            "\t\t\t\"sourceOutputIndex\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 0,\n" +
            "\t\t\t\"targetNodeId\": 21,\n" +
            "\t\t\t\"id\": 23,\n" +
            "\t\t\t\"sourceNodeId\": 12,\n" +
            "\t\t\t\"sourceOutputIndex\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 1,\n" +
            "\t\t\t\"targetNodeId\": 21,\n" +
            "\t\t\t\"id\": 24,\n" +
            "\t\t\t\"sourceNodeId\": 13,\n" +
            "\t\t\t\"sourceOutputIndex\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 2,\n" +
            "\t\t\t\"targetNodeId\": 21,\n" +
            "\t\t\t\"id\": 25,\n" +
            "\t\t\t\"sourceNodeId\": 14,\n" +
            "\t\t\t\"sourceOutputIndex\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 0,\n" +
            "\t\t\t\"targetNodeId\": 22,\n" +
            "\t\t\t\"id\": 26,\n" +
            "\t\t\t\"sourceNodeId\": 21,\n" +
            "\t\t\t\"sourceOutputIndex\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 0,\n" +
            "\t\t\t\"targetNodeId\": 28,\n" +
            "\t\t\t\"id\": 29,\n" +
            "\t\t\t\"sourceNodeId\": 27,\n" +
            "\t\t\t\"sourceOutputIndex\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 0,\n" +
            "\t\t\t\"targetNodeId\": 31,\n" +
            "\t\t\t\"id\": 32,\n" +
            "\t\t\t\"sourceNodeId\": 30,\n" +
            "\t\t\t\"sourceOutputIndex\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 0,\n" +
            "\t\t\t\"targetNodeId\": 34,\n" +
            "\t\t\t\"id\": 35,\n" +
            "\t\t\t\"sourceNodeId\": 33,\n" +
            "\t\t\t\"sourceOutputIndex\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 0,\n" +
            "\t\t\t\"targetNodeId\": 37,\n" +
            "\t\t\t\"id\": 38,\n" +
            "\t\t\t\"sourceNodeId\": 36,\n" +
            "\t\t\t\"sourceOutputIndex\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 0,\n" +
            "\t\t\t\"targetNodeId\": 44,\n" +
            "\t\t\t\"id\": 49,\n" +
            "\t\t\t\"sourceNodeId\": 40,\n" +
            "\t\t\t\"sourceOutputIndex\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 1,\n" +
            "\t\t\t\"targetNodeId\": 44,\n" +
            "\t\t\t\"id\": 50,\n" +
            "\t\t\t\"sourceNodeId\": 37,\n" +
            "\t\t\t\"sourceOutputIndex\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 0,\n" +
            "\t\t\t\"targetNodeId\": 45,\n" +
            "\t\t\t\"id\": 51,\n" +
            "\t\t\t\"sourceNodeId\": 41,\n" +
            "\t\t\t\"sourceOutputIndex\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 1,\n" +
            "\t\t\t\"targetNodeId\": 45,\n" +
            "\t\t\t\"id\": 52,\n" +
            "\t\t\t\"sourceNodeId\": 28,\n" +
            "\t\t\t\"sourceOutputIndex\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 0,\n" +
            "\t\t\t\"targetNodeId\": 46,\n" +
            "\t\t\t\"id\": 53,\n" +
            "\t\t\t\"sourceNodeId\": 42,\n" +
            "\t\t\t\"sourceOutputIndex\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 1,\n" +
            "\t\t\t\"targetNodeId\": 46,\n" +
            "\t\t\t\"id\": 54,\n" +
            "\t\t\t\"sourceNodeId\": 31,\n" +
            "\t\t\t\"sourceOutputIndex\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 0,\n" +
            "\t\t\t\"targetNodeId\": 47,\n" +
            "\t\t\t\"id\": 55,\n" +
            "\t\t\t\"sourceNodeId\": 43,\n" +
            "\t\t\t\"sourceOutputIndex\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 1,\n" +
            "\t\t\t\"targetNodeId\": 47,\n" +
            "\t\t\t\"id\": 56,\n" +
            "\t\t\t\"sourceNodeId\": 34,\n" +
            "\t\t\t\"sourceOutputIndex\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 0,\n" +
            "\t\t\t\"targetNodeId\": 48,\n" +
            "\t\t\t\"id\": 57,\n" +
            "\t\t\t\"sourceNodeId\": 39,\n" +
            "\t\t\t\"sourceOutputIndex\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 1,\n" +
            "\t\t\t\"targetNodeId\": 48,\n" +
            "\t\t\t\"id\": 58,\n" +
            "\t\t\t\"sourceNodeId\": 21,\n" +
            "\t\t\t\"sourceOutputIndex\": 1\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 0,\n" +
            "\t\t\t\"targetNodeId\": 59,\n" +
            "\t\t\t\"id\": 61,\n" +
            "\t\t\t\"sourceNodeId\": 48,\n" +
            "\t\t\t\"sourceOutputIndex\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 1,\n" +
            "\t\t\t\"targetNodeId\": 59,\n" +
            "\t\t\t\"id\": 62,\n" +
            "\t\t\t\"sourceNodeId\": 46,\n" +
            "\t\t\t\"sourceOutputIndex\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 2,\n" +
            "\t\t\t\"targetNodeId\": 59,\n" +
            "\t\t\t\"id\": 63,\n" +
            "\t\t\t\"sourceNodeId\": 45,\n" +
            "\t\t\t\"sourceOutputIndex\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 3,\n" +
            "\t\t\t\"targetNodeId\": 59,\n" +
            "\t\t\t\"id\": 64,\n" +
            "\t\t\t\"sourceNodeId\": 44,\n" +
            "\t\t\t\"sourceOutputIndex\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 4,\n" +
            "\t\t\t\"targetNodeId\": 59,\n" +
            "\t\t\t\"id\": 65,\n" +
            "\t\t\t\"sourceNodeId\": 47,\n" +
            "\t\t\t\"sourceOutputIndex\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 0,\n" +
            "\t\t\t\"targetNodeId\": 60,\n" +
            "\t\t\t\"id\": 66,\n" +
            "\t\t\t\"sourceNodeId\": 59,\n" +
            "\t\t\t\"sourceOutputIndex\": 0\n" +
            "\t\t}\n" +
            "\t],\n" +
            "\t\"nodes\": [\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 0,\n" +
            "\t\t\t\"inPorts\": [],\n" +
            "\t\t\t\"name\": \"X_SUB_PD0_V01\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 188.66666666666669,\n" +
            "\t\t\t\"y\": 144,\n" +
            "\t\t\t\"type\": \"Variable\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 1,\n" +
            "\t\t\t\"inPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"name\": \"XGBoostOp\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 108,\n" +
            "\t\t\t\"y\": 382,\n" +
            "\t\t\t\"type\": \"XGBoostOp\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 3,\n" +
            "\t\t\t\"inPorts\": [],\n" +
            "\t\t\t\"name\": \"X_SUB_PD0_V02\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 513,\n" +
            "\t\t\t\"y\": 226,\n" +
            "\t\t\t\"type\": \"Variable\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 4,\n" +
            "\t\t\t\"inPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"name\": \"XGBoostOp\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 374,\n" +
            "\t\t\t\"y\": 325,\n" +
            "\t\t\t\"type\": \"XGBoostOp\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 6,\n" +
            "\t\t\t\"inPorts\": [],\n" +
            "\t\t\t\"name\": \"X_SUB_PD0_V03\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 803,\n" +
            "\t\t\t\"y\": 345,\n" +
            "\t\t\t\"type\": \"Variable\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 7,\n" +
            "\t\t\t\"inPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"name\": \"RandomForestClassifierOp\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 608,\n" +
            "\t\t\t\"y\": 422,\n" +
            "\t\t\t\"type\": \"RandomForestClassifierOp\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 9,\n" +
            "\t\t\t\"inPorts\": [],\n" +
            "\t\t\t\"name\": \"Constant\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": -5,\n" +
            "\t\t\t\"y\": 598,\n" +
            "\t\t\t\"type\": \"Constant\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 10,\n" +
            "\t\t\t\"inPorts\": [],\n" +
            "\t\t\t\"name\": \"Constant\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 145,\n" +
            "\t\t\t\"y\": 469,\n" +
            "\t\t\t\"type\": \"Constant\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 11,\n" +
            "\t\t\t\"inPorts\": [],\n" +
            "\t\t\t\"name\": \"Constant\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 407,\n" +
            "\t\t\t\"y\": 405,\n" +
            "\t\t\t\"type\": \"Constant\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 12,\n" +
            "\t\t\t\"inPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"name\": \"Multiply\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 45,\n" +
            "\t\t\t\"y\": 734,\n" +
            "\t\t\t\"type\": \"Multiply\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 13,\n" +
            "\t\t\t\"inPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"name\": \"Multiply\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 160,\n" +
            "\t\t\t\"y\": 619,\n" +
            "\t\t\t\"type\": \"Multiply\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 14,\n" +
            "\t\t\t\"inPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"name\": \"Multiply\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 371,\n" +
            "\t\t\t\"y\": 506,\n" +
            "\t\t\t\"type\": \"Multiply\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 21,\n" +
            "\t\t\t\"inPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"name\": \"Add\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 157,\n" +
            "\t\t\t\"y\": 856,\n" +
            "\t\t\t\"type\": \"Add\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 22,\n" +
            "\t\t\t\"inPorts\": [\n" +
            "\t\t\t\t0\n" +
            "\t\t\t],\n" +
            "\t\t\t\"name\": \"Output\",\n" +
            "\t\t\t\"outPorts\": [],\n" +
            "\t\t\t\"x\": 5,\n" +
            "\t\t\t\"y\": 932,\n" +
            "\t\t\t\"type\": \"Output\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 27,\n" +
            "\t\t\t\"inPorts\": [],\n" +
            "\t\t\t\"name\": \"X_SUB_169\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 1301,\n" +
            "\t\t\t\"y\": 330,\n" +
            "\t\t\t\"type\": \"Variable\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 28,\n" +
            "\t\t\t\"inPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"name\": \"XGBoostOp\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 897,\n" +
            "\t\t\t\"y\": 692,\n" +
            "\t\t\t\"type\": \"XGBoostOp\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 30,\n" +
            "\t\t\t\"inPorts\": [],\n" +
            "\t\t\t\"name\": \"X_SUB_PD1\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 1025,\n" +
            "\t\t\t\"y\": 334,\n" +
            "\t\t\t\"type\": \"Variable\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 31,\n" +
            "\t\t\t\"inPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"name\": \"XGBoostOp\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 637,\n" +
            "\t\t\t\"y\": 570,\n" +
            "\t\t\t\"type\": \"XGBoostOp\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 33,\n" +
            "\t\t\t\"inPorts\": [],\n" +
            "\t\t\t\"name\": \"X_SUB_111_V01\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 2067,\n" +
            "\t\t\t\"y\": 350,\n" +
            "\t\t\t\"type\": \"Variable\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 34,\n" +
            "\t\t\t\"inPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"name\": \"XGBoostOp\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 1861,\n" +
            "\t\t\t\"y\": 664,\n" +
            "\t\t\t\"type\": \"XGBoostOp\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 36,\n" +
            "\t\t\t\"inPorts\": [],\n" +
            "\t\t\t\"name\": \"X_SUB_PD0_HISTORY\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 1643,\n" +
            "\t\t\t\"y\": 330,\n" +
            "\t\t\t\"type\": \"Variable\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 37,\n" +
            "\t\t\t\"inPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"name\": \"XGBoostOp\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 1197,\n" +
            "\t\t\t\"y\": 696,\n" +
            "\t\t\t\"type\": \"XGBoostOp\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 39,\n" +
            "\t\t\t\"inPorts\": [],\n" +
            "\t\t\t\"name\": \"Constant\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 8,\n" +
            "\t\t\t\"y\": 1003,\n" +
            "\t\t\t\"type\": \"Constant\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 40,\n" +
            "\t\t\t\"inPorts\": [],\n" +
            "\t\t\t\"name\": \"Constant\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 917,\n" +
            "\t\t\t\"y\": 792,\n" +
            "\t\t\t\"type\": \"Constant\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 41,\n" +
            "\t\t\t\"inPorts\": [],\n" +
            "\t\t\t\"name\": \"Constant\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 629,\n" +
            "\t\t\t\"y\": 674,\n" +
            "\t\t\t\"type\": \"Constant\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 42,\n" +
            "\t\t\t\"inPorts\": [],\n" +
            "\t\t\t\"name\": \"Constant\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 413,\n" +
            "\t\t\t\"y\": 574,\n" +
            "\t\t\t\"type\": \"Constant\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 43,\n" +
            "\t\t\t\"inPorts\": [],\n" +
            "\t\t\t\"name\": \"Constant\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 1643,\n" +
            "\t\t\t\"y\": 662,\n" +
            "\t\t\t\"type\": \"Constant\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 44,\n" +
            "\t\t\t\"inPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"name\": \"Multiply\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 997,\n" +
            "\t\t\t\"y\": 954,\n" +
            "\t\t\t\"type\": \"Multiply\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 45,\n" +
            "\t\t\t\"inPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"name\": \"Multiply\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 671,\n" +
            "\t\t\t\"y\": 788,\n" +
            "\t\t\t\"type\": \"Multiply\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 46,\n" +
            "\t\t\t\"inPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"name\": \"Multiply\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 371,\n" +
            "\t\t\t\"y\": 790,\n" +
            "\t\t\t\"type\": \"Multiply\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 47,\n" +
            "\t\t\t\"inPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"name\": \"Multiply\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 1495,\n" +
            "\t\t\t\"y\": 942,\n" +
            "\t\t\t\"type\": \"Multiply\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 48,\n" +
            "\t\t\t\"inPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"name\": \"Multiply\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 303,\n" +
            "\t\t\t\"y\": 1122,\n" +
            "\t\t\t\"type\": \"Multiply\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 59,\n" +
            "\t\t\t\"inPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"name\": \"Add\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 887,\n" +
            "\t\t\t\"y\": 1180,\n" +
            "\t\t\t\"type\": \"Add\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 60,\n" +
            "\t\t\t\"inPorts\": [\n" +
            "\t\t\t\t0\n" +
            "\t\t\t],\n" +
            "\t\t\t\"name\": \"Output2\",\n" +
            "\t\t\t\"outPorts\": [],\n" +
            "\t\t\t\"x\": 881,\n" +
            "\t\t\t\"y\": 1266,\n" +
            "\t\t\t\"type\": \"Output\"\n" +
            "\t\t}\n" +
            "\t]\n" +
            "}";



    private static String dagStr = "{\n" +
            "\t\"edges\": [\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 0,\n" +
            "\t\t\t\"targetNodeId\": 2,\n" +
            "\t\t\t\"id\": 1,\n" +
            "\t\t\t\"sourceNodeId\": 1,\n" +
            "\t\t\t\"sourceOutputIndex\": 3,\n" +
            "\t\t\t\"edgesText\": \"example text\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 1,\n" +
            "\t\t\t\"targetNodeId\": 3,\n" +
            "\t\t\t\"id\": 2,\n" +
            "\t\t\t\"sourceNodeId\": 1,\n" +
            "\t\t\t\"sourceOutputIndex\": 1,\n" +
            "\t\t\t\"edgesText\": \"personal style\",\n" +
            "\t\t\t\"textStyle\": {\n" +
            "\t\t\t\t\"fontSize\": \"12px\",\n" +
            "\t\t\t\t\"stroke\": \"yellow\"\n" +
            "\t\t\t}\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"targetInputIndex\": 2,\n" +
            "\t\t\t\"targetNodeId\": 3,\n" +
            "\t\t\t\"id\": 3,\n" +
            "\t\t\t\"sourceNodeId\": 1,\n" +
            "\t\t\t\"sourceOutputIndex\": 2\n" +
            "\t\t}\n" +
            "\t],\n" +
            "\t\"nodes\": [\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 1,\n" +
            "\t\t\t\"inPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2\n" +
            "\t\t\t],\n" +
            "\t\t\t\"name\": \"NODE_PARENT开始\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 157,\n" +
            "\t\t\t\"y\": 120\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 2,\n" +
            "\t\t\t\"inPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2\n" +
            "\t\t\t],\n" +
            "\t\t\t\"name\": \"NODE_CHILD\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 459,\n" +
            "\t\t\t\"y\": 257\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 3,\n" +
            "\t\t\t\"inPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3,\n" +
            "\t\t\t\t4\n" +
            "\t\t\t],\n" +
            "\t\t\t\"name\": \"NODE_CHILD\",\n" +
            "\t\t\t\"outPorts\": [\n" +
            "\t\t\t\t0,\n" +
            "\t\t\t\t1,\n" +
            "\t\t\t\t2,\n" +
            "\t\t\t\t3\n" +
            "\t\t\t],\n" +
            "\t\t\t\"x\": 50,\n" +
            "\t\t\t\"y\": 400\n" +
            "\t\t}\n" +
            "\t]\n" +
            "}";

}
