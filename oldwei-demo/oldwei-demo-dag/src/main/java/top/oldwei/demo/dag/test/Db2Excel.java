package top.oldwei.demo.dag.test;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.simple.SimpleDataSource;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.clearspring.analytics.util.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: weizd
 * @time: 2021/9/26 11:41 下午
 */
@Slf4j
public class Db2Excel {

    public static void main(String[] args) throws Exception{
        String platform = "jdbc:mysql://localhost:3306/ai_platform_0210";
        String ms = "jdbc:mysql://localhost:3306/ai_model_deployment_0210";

        String user = "root";
        String password = "123456";
        DataSource pDs = new SimpleDataSource(platform,user,password);
        DataSource mDs = new SimpleDataSource(ms,user,password);

        ExcelWriter writer = ExcelUtil.getWriter("/Users/weizd/share/sql/ai-platform-model-datas.xlsx","模型信息");

        writer.write(createExcelData(mDs,pDs), true);

        // 关闭writer，释放内存
        writer.close();
    }


    public static List createExcelData(DataSource msDs,DataSource platformDs) throws Exception{

        Map<Long,Map<String,String>> projects = Maps.newHashMap();
        List<Entity> projectList = Db.use(platformDs).findAll("svc_projects");
        projectList.forEach(e->{
            Map<String,String> item = Maps.newHashMap();
            item.put("project_name",e.getStr("project_name"));
            item.put("project_type",e.getStr("project_type"));
            projects.put(e.getLong("project_id"),item);
        });
        log.info("project info : {}",projects);


        Map<Long,Map<String,String>> models = Maps.newHashMap();
        List<Entity> registerModelList = Db.use(msDs).findAll("model_manage_model");

        registerModelList.forEach(e->{
            Map<String,String> item = Maps.newHashMap();
            item.put("owner_username",e.getStr("owner_username"));
            item.put("created_at",e.getStr("created_at"));
            models.put(e.getLong("id"),item);
        });
        log.info("model info: {}",models);

        List<Entity> itmModelList = Db.use(platformDs).findAll("ai_itm_model");
        log.info("itm model number:{}",itmModelList.size());

        List<Map<String,Object>> excel = Lists.newArrayList();
        Map<String,Object> row = null;
        for(Entity entity: itmModelList){
            row = new HashMap<>();
            row.put("项目名称",projects.get(entity.getLong("project_id")).get("project_name"));
//            row.put("项目类型",projects.get(entity.getLong("project_id")).get("project_name"));
            row.put("模型名称",entity.getStr("name"));
            row.put("模型描述",entity.getStr("description"));
            Long ref_model_id = entity.getLong("ref_model_id");
            String createBy = entity.getStr("create_by");
            String createDate = entity.getStr("create_time");
            if(ref_model_id != null){
                createBy = models.get(ref_model_id).get("owner_username");
                createDate = models.get(ref_model_id).get("created_at");
            }
            row.put("创建人",createBy);
            row.put("创建时间",createDate);
            log.info("{}",row);
            excel.add(row);
        }

        return excel;
    }




/**
    public static void main(String[] args) throws Exception{

        String bzqUrl = "jdbc:mysql://localhost:3306/ai_bzq";
//        String zyqUrl = "jdbc:mysql://localhost:3306/ai_zyq";
        String zyqUrl = "jdbc:mysql://localhost:3306/ai_model_traning_zyq_1120";
        String user = "root";
        String password = "123456";
        DataSource bzqDataSource = new SimpleDataSource(bzqUrl,user,password);

        DataSource zyqDataSource = new SimpleDataSource(zyqUrl,user,password);


        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("/Users/weizd/00ccbft/platform-sql/ai-platform-model-datas.xlsx","自用区");


        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(2, "1120自用区数据");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(createExcelData(zyqDataSource), true);

//        writer.setSheet("标准区");
//           // 合并单元格后的标题行，使用默认标题样式
//        writer.merge(2, "0924标准区数据");
//        // 一次性写出内容，使用默认样式，强制输出标题
//        writer.write(createExcelData(bzqDataSource), true);


        // 关闭writer，释放内存
        writer.close();

    }


    public static List createExcelData(DataSource dataSource) throws Exception{

        Map<String,Number> result = Maps.newHashMap();
        List<Entity> registerModelList = Db.use(dataSource).findAll("model_manage_model");
        log.info("register number:{}",registerModelList.size());
        registerModelList.forEach(e->{
//            log.info("{}",e.get("owner_username"));
            if(result.containsKey(e.get("owner_username"))){
                result.get(e.get("owner_username")).setRegisterNumber(result.get(e.get("owner_username")).getRegisterNumber() + 1);
            }else {
                result.put(e.getStr("owner_username"),new Number(0,1));
            }
        });

        List<Entity> trainModelList = Db.use(dataSource).findAll("task_models");
        log.info("train number:{}",trainModelList.size());
        trainModelList.forEach(e->{
//            log.info("{}",e.get("owner"));
            if(result.containsKey(e.get("owner"))){
                result.get(e.get("owner")).setTrainNumber(result.get(e.get("owner")).getTrainNumber() + 1);
            }else {
                result.put(e.getStr("owner"),new Number(1,0));
            }
        });

        log.info("{}", JSON.toJSONString(result));

        List<Map<String,Object>> excel = Lists.newArrayList();
        Map<String,Object> row = null;
        for(Map.Entry<String,Number> entry: result.entrySet()){
            row = new HashMap<>();
            row.put("用户",entry.getKey());
            row.put("注册模型数量",entry.getValue().getRegisterNumber());
            row.put("训练模型数量",entry.getValue().getTrainNumber());
            excel.add(row);
        }

        return excel;
    }

**/


}
