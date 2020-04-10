## ubuntu 安装启动zookeeper

````
下载地址：https://downloads.apache.org/zookeeper/

    [PARENTDIR] Parent Directory                             -   
    [DIR] current/                2020-03-03 21:30    -   
    [DIR] stable/                 2020-02-14 12:32    -   
    [DIR] zookeeper-3.4.14/       2019-04-01 14:45    -   
    [DIR] zookeeper-3.5.7/        2020-02-14 12:32    -   
    [DIR] zookeeper-3.6.0/        2020-03-03 21:30    -   
    [   ] KEYS                    2020-01-30 08:02   59K  

选择具体的版本，下面有带bin的和不带bin的压缩包

---带bin的解压后配置一下即可启动;不带bin的解压后需要使用mvn编译一下才可使用---

````


### 单机

- 将下载的压缩包解压
- conf下的zoo_sample.cfg 复制一份命名为zoo.cfg(zookeeper默认启动的时候读取conf下名字为zoo.cfg的配置文件)
- 目录结构如下
```
apache-zookeeper-3.6.0
├── bin
│   ├── README.txt
│   ├── zkCleanup.sh
│   ├── zkCli.cmd
│   ├── zkCli.sh
│   ├── zkEnv.cmd
│   ├── zkEnv.sh
│   ├── zkServer.cmd
│   ├── zkServer-initialize.sh
│   ├── zkServer.sh
│   ├── zkSnapShotToolkit.cmd
│   ├── zkSnapShotToolkit.sh
│   ├── zkTxnLogToolkit.cmd
│   └── zkTxnLogToolkit.sh
├── checkstyle-simple.xml
├── checkstyle-strict.xml
├── checkstyleSuppressions.xml
├── conf
│   ├── configuration.xsl
│   ├── log4j.properties
│   ├── zoo.cfg
│   └── zoo_sample.cfg
├── dev
│   └── docker
│       ├── Dockerfile
│       └── run.sh


```
- 配置环境变量
```
执行vi ~/.bashrc

追加内容
export ZOOKEEPER_HOME=/opt/zookeeper/apache-zookeeper-3.6.0
export PATH=$ZOOKEEPER_HOME/bin:$PATH

执行 source ~/.bashrc 使环境变量生效
    
```

- 执行命令启动
```
1、 如果下载的不带bin的压缩包,先执行mvn编译命令：进入解压后的根目录执行 mvn clean install

2、 ./bin/zkServer.sh start
```





