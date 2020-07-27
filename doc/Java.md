## 技巧


### 单java文件形成jar




### 实体类 do dto vo pojo 
````
Controller 接收 XXRequest 类
Controller 转成 XXDTO 给 Service
Service 传 DTO 给 Mapper
Service 返回 VO 给 controller
controller 最终返回 XXResponse

````
