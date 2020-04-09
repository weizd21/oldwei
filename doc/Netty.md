## netty知识点

### 集群环境下的问题
1. 多个netty server实例,客户端如何通过server1 找到 server2上的channel 进行发送消息
    - 方案1：
    ```
    主要点：使用redis存放 用户和channel之间的映射关系
    其中，用户信息，用户登录后会有token或者userid之类的用户标识；
    channel标识，因为Channel这个类本身没有实现序列化接口，所以不能存放到redis中。
    但是 ChannelId 这个类可以存放到redis中去。
    通过ChannelId可以找到对应的Channel信息，进而可以发送消息给对应的用户。
    
    所有的server断都订阅redis集群的一个服务，所有的server断收到消息后，根据channelId查找
    Channel，如果当前的Server端存在channel信息，则进行发送处理，如果没有，则不做处理。
    
   
    ```
    - 方案2：
    ```
    
    ```
2. 
