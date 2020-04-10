## java 端调用 .pb模型实现物体识别


### 思路

```
注：此处无需下载，已在之前下载的H：/demo/model文件夹中已准备好，其中.jar 为写好的模型服务，.pb就是【下载】的模型文件，.txt文件是类别标签文件。

将model文件夹上传至需要部署的服务器，如果没有自己的服务器，可暂时使用CRT登陆128.196.124.193  demo/demo，
因为model已经存在在用户目录下的demo文件夹中，所以不需要上传了。

可复制执行下面的命令发布服务，其中ai.model.path参数指定上传模型的位置和server.port指定端口。如:
cd /home/ap/demo/demo/model && java -jar predict-demo-0.0.0.1.jar --ai.model.path=mnist_model.pb 
--ai.label.path=mnist.txt 
--ai.tensor.input=x_image 
--ai.tensor.output=final_result 
--ai.image.shape=28,28 
--ai.image.mean=0 
--ai.image.scale=1 
--ai.image.channels=1 
--server.port=10001


```


