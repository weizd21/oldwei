package top.oldwei.demo.dag.test;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.simple.SimpleDataSource;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.clearspring.analytics.util.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

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
public class Excel2Db {

    public static void main(String[] args) throws Exception{
        String omp = "jdbc:mysql://localhost:3306/omp?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&autoReconnectForPools=true";

        String user = "root";
        String password = "123456";
        DataSource pDs = new SimpleDataSource(omp,user,password);


        ExcelReader reader1 = ExcelUtil.getReader("/Users/zxm/Downloads/api-resources.xlsx");
        List<Map<String,Object>> readAll1 = reader1.readAll();

        //
        List<Entity> apiEntities = Lists.newArrayList();
        Entity apiResourceEntity = null;

        List<Entity> roleApiResourceEntities = Lists.newArrayList();
        Entity roleApiResourceEntity = null;

        int i = 1;
        for(Map<String,Object> objects: readAll1){
            log.info("{}",JSONUtil.toJsonStr(objects));
            if(objects.get("接口名称") != null && objects.get("接口名称") != ""){
//                log.info("接口名称: {}",objects.get("接口名称").toString());
                Integer tenantIdLocation = getLocation(objects.get("Tenant-Id获取方式").toString());
                Integer workspaceIdLocation = getLocation(objects.get("Workspace-Id获取方式").toString());
                apiResourceEntity = Entity.create("t_api_resource")
                        .set("id",i)
                        .set("api_name",objects.get("接口名称"))
                        .set("api_uri",objects.get("url"))
                        .set("api_method",objects.get("http_method"))
                        .set("tenant_id_location",tenantIdLocation)
                        .set("workspace_id_location",workspaceIdLocation)
                ;

                List<Integer> roleIds = getRoleId(
                        objects.get("算法人员角色").toString(),
                        objects.get("工作空间管理员角色").toString(),
                        objects.get("租户普通成员角色").toString(),
                        objects.get("租户管理员角色").toString(),
                        objects.get("平台普通用户角色").toString(),
                        objects.get("平台管理员角色").toString()
                );
                for (Integer roleId: roleIds){
                    roleApiResourceEntity = Entity.create("t_role_api_resource")
                            .set("role_id",roleId)
                            .set("api_resource_id",i)
                    ;
                    roleApiResourceEntities.add(roleApiResourceEntity);
                }
                apiEntities.add(apiResourceEntity);
                i++;
            }
        }

        log.info("{}",roleApiResourceEntities);
        log.info("{}",apiEntities);

        Db.use(pDs).execute("delete from t_api_resource");
        Db.use(pDs).execute("delete from t_role_api_resource");

        Db.use(pDs).insert(apiEntities);
        Db.use(pDs).insert(roleApiResourceEntities);



//        ExcelReader reader = ExcelUtil.getReader("d:/aaa.xlsx");
//        List<Person> all = reader.readAll(Person.class);


//        List<Entity> entities = Lists.newArrayList();
//
//        Entity apiResourceEntity = null;
//
//        apiResourceEntity = Entity.create("")
//                .set("id",)
//                .set("api_name",)
//
//        Db.use(pDs).insert();


    }


    public static Integer getLocation(String strValue){
        Integer value = 0;
        if(strValue.equals("Header")){
            value = 1;
        }else if(strValue.equals("Query")){
            value = 2;
        }else if(strValue.equals("Path")){
            value = 3;
        }else if (strValue.equals("None")){
            value = 0;
        }
//        log.info("strValue: {},value: {}",strValue,value);
        return value;
    }


    /**
     * # 角色
     * 平台管理员: 1
     * 平台普通用户: 2
     * 租户管理员: 3
     * 租户普通用户: 4
     * 工作空间管理员: 5
     * 算法工程师: 6
     *
     * @param role1
     * @param role2
     * @return
     */
    public static List<Integer> getRoleId(String role6,String role5,String role4,String role3,String role2,String role1){
        List<Integer> roleIds = Lists.newArrayList();
        if("1".equals(role6)){
            roleIds.add(6);
        }
        if("1".equals(role5)){
            roleIds.add(5);
        }
        if("1".equals(role4)){
            roleIds.add(4);
        }
        if("1".equals(role3)){
            roleIds.add(3);
        }
        if("1".equals(role2)){
            roleIds.add(2);
        }
        if("1".equals(role1)){
            roleIds.add(1);
        }
        return roleIds;
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
//      String zyqUrl = "jdbc:mysql://localhost:3306/ai_zyq";
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
