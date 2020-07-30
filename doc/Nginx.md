## Nginx

### 配置文件 nginx.conf
```

#user  nobody;
#user user [group]
user weizd weizd;
worker_processes 2;

#error_log  logs/error.log;
#error_log  logs/error.log  notice;
#error_log  logs/error.log  info;

#pid        logs/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       mime.types;
    default_type  application/octet-stream;

    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    log_format self_log '$remote_addr -- [$time_local] -- "$request" -- $http_cookie -- $status -- "$http_referer"';

    access_log  logs/access.log  main;
    access_log  logs/access_self.log  self_log;



    sendfile        on;
    #tcp_nopush     on;

    client_max_body_size 10240m;
    client_body_buffer_size 128k;

    #keepalive_timeout  0;
    keepalive_timeout  65;

    #gzip  on;

    server {
        listen       7070;
        server_name  localhost;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        # nginx 内置变量
        location /test {
            echo "uri = $uri";
            echo "request_uri = $request_uri";
            echo "request_length=$request_length";
            #echo "request_id = $request_id";
            echo "request_time=$request_time";
            echo "request=$request";


            echo "args=$args";
            echo "arg_p=$arg_p";
            echo "arg_name=$arg_name";

            echo "server_addr=$server_addr";
            echo "server_name=$server_name";
            echo "server_port=$server_port";
            echo "server_protocol=$server_protocol";

            echo "time_local=$time_local";

            echo "host=$host";
            echo "hostname=$hostname";

            echo "remote_user=$remote_user";
            echo "remote_addr=$remote_addr";
            echo "remote_port=$remote_port";

        }

        location / {
            root   /home/weizd/nginxHtml;
            index  main.html login.html;
        }
        location =/ {
            root   /home/weizd/nginxHtml;
            index  main.html index.html index.htm;
        }

        location = /t {
            echo "= /t ---> uri = $uri";
        }

        location = /t/ {
            echo "= /t/ ---> uri = $uri";
        }

        location ^~ /t {
            echo "^~ /t ---> uri = $uri";
        }

        location ^~ /t0 {
            echo "^~ /t0 ---> uri = $uri";
        }

        location ^~ /t0/t1 {
            echo "^~ /t0/t1 ---> uri = $uri";
        }

        location ~ /t0/(.*) {
            echo "~ /t0/(.*) ---> uri = $uri";
        }


        location ~ /t1/(.*) {
            echo " ~ /t1/(.*) ---> uri = $uri";
        }
        location /t1 {
            echo " /t1/ ---> uri = $uri";
        }
        location /t1/t2 {
            echo " /t1/t2 ---> uri = $uri";
        }


        # 反向代理
        # 实际请求地址为 http://b-server/api/b/
        location /api/a/ {
            proxy_set_header Host $host:$server_port;
            proxy_set_header X_Real_IP $remote_addr;
            proxy_set_header Connection keep-alive;
            proxy_set_header Keep-Alive 600;
            proxy_pass http://a-server; 
        }
        # 实际请求地址为 http://b-server/api/b/
        location /api/b/ {
            proxy_set_header Host $host:$server_port;
            proxy_set_header X_Real_IP $remote_addr;
            proxy_set_header Connection keep-alive;
            proxy_set_header Keep-Alive 600;
            proxy_pass http://b-server; 
        }



        # rewrite 重定向
        #location /api/ {
        #    proxy_set_header Host $host:$server_port;
        #    proxy_set_header X_Real_IP $remote_addr;
        #    rewrite ^/api/(.*)$ /$1 last;
        #}
        #location /chunk/ {
        #    proxy_pass http://demo-big-file-server;
        #}

        location /api/ {
          proxy_set_header Host $host:$server_port;
          proxy_set_header X_Real_IP $remote_addr;
          proxy_set_header Connection keep-alive;
          proxy_set_header Keep-Alive 600;
          proxy_pass http://demo-big-file-server;
          rewrite "^/api/(.*)$" /$1 break;
        }


        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }

        # proxy the PHP scripts to Apache listening on 127.0.0.1:80
        #
        #location ~ \.php$ {
        #    proxy_pass   http://127.0.0.1;
        #}

        # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
        #
        #location ~ \.php$ {
        #    root           html;
        #    fastcgi_pass   127.0.0.1:9000;
        #    fastcgi_index  index.php;
        #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
        #    include        fastcgi_params;
        #}

        # deny access to .htaccess files, if Apache's document root
        # concurs with nginx's one
        #
        #location ~ /\.ht {
        #    deny  all;
        #}

    }

    # 负载均衡 默认策略为轮训方式
    upstream demo-big-file-server {
        server localhost:8015;
        server localhost:8015;
    }
    # 权重
    upstream a-server {
        server localhost:8015 weight=1;
        server localhost:8016 weight=2;
    }
    # ip hash
    upstream b-server {
        ip_hash;
        server localhost:8017;
        server localhost:8018;
    }
    # 最少连接
    upstream c-server {
        least_conn;
        server localhost:8017;
        server localhost:8018;
    }
    # 最少响应时间
    #upstream d-server {
    #    fair;
    #    server localhost:8017;
    #    server localhost:8018;
    #}



    # another virtual host using mix of IP-, name-, and port-based configuration
    #
    #server {
    #    listen       8000;
    #    listen       somename:8080;
    #    server_name  somename  alias  another.alias;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}


    # HTTPS server
    #
    #server {
    #    listen       443;
    #    server_name  localhost;

    #    ssl                  on;
    #    ssl_certificate      cert.pem;
    #    ssl_certificate_key  cert.key;

    #    ssl_session_timeout  5m;

    #    ssl_protocols  SSLv2 SSLv3 TLSv1;
    #    ssl_ciphers  HIGH:!aNULL:!MD5;
    #    ssl_prefer_server_ciphers   on;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}

}


```

### 配置详解
> user
```
nginx worker进程 以哪个系统用户和用户组 启动

user user [group]

默认配置为: user nobody nobody

当[group]不指定时,默认取和user相同名字的用户组
```
> location 匹配
- 语法
````
location [=|~|~*|^~] /uri/ { … }


优先 精确匹配 = ,然后 前缀匹配 ^~ ,然后 正则匹配 ~ ~* ,最后是 最长前缀匹配 .

1. location = /a {…} #精准匹配
2. location ^~ /a {…} #前缀匹配
3. location ~ /a.* {…} #正则匹配（区分大小写）
4. location ~* /a.* {…} #正则匹配（不区分大小写）
5. location /a {…} #最长前缀匹配，但是优先级低于正则匹配。 /a 和 ^~ /a 会冲突，报错
6. location / {} #任何没有匹配成功的，都会匹配这里处理

````
- 示例
``` 
        # 精确匹配 优先级高于 最长前缀匹配
        location = /t {
            echo "= /t ---> uri = $uri";
        }
        location = /t/ {
            echo "= /t/ ---> uri = $uri";
        }
        location ^~ /t {
            echo "^~ /t ---> uri = $uri";
        }


        # 最长前缀匹配 优先级高于 正则匹配
        location ^~ /t0 {
            echo "^~ /t0 ---> uri = $uri";
        }
        location ^~ /t0/t1 {
            echo "^~ /t0/t1 ---> uri = $uri";
        }
        location ~ /t0/(.*) {
            echo "~ /t0/(.*) ---> uri = $uri";
        }


        # 正则匹配优先级高于 uri最长匹配贵
        location ~ /t1/(.*) {
            echo " ~ /t1/(.*) ---> uri = $uri";
        }
        location /t1 {
            echo " /t1/ ---> uri = $uri";
        }
        location /t1/t2 {
            echo " /t1/t2 ---> uri = $uri";
        }

```



> 反向代理
```


```
> 负载均衡
- 策略
```

    # 负载均衡 默认策略为轮训方式
    upstream demo-big-file-server {
        server localhost:8015;
        server localhost:8015;
    }
    # 权重
    upstream a-server {
        server localhost:8015 weight=1;
        server localhost:8016 weight=2;
    }
    # ip hash
    upstream b-server {
        ip_hash;
        server localhost:8017;
        server localhost:8018;
    }
    # 最少连接
    upstream c-server {
        least_conn;
        server localhost:8017;
        server localhost:8018;
    }
    # 最少响应时间
    upstream c-server {
        fair;
        server localhost:8017;
        server localhost:8018;
    }

```  

> rewrite重定向

- 语法
```
rewrite regex replacement [flag];

regex: 用来匹配URL的正则表达式
replament: 重定向的目标url
flag: last/break/redirect/permanent
    last: 用replacement这个url 重新进行location匹配。
    break: 停止进行匹配
    redirect: 返回302重定向
    permanent: 返回301重定向
    
```
- 示例
```
场景: 前端为了统一,将原有的接口地址前面统一添加 /api 则可以使用重定向处理
http {
    server {
    
        # 这两种重定向的效果是一样的,推荐使用第一种方式
        
        #location /api/ {
        #    proxy_set_header Host $host:$server_port;
        #    rewrite ^/api/(.*)$ /$1 last;
        #}
        #location /chunk/ {
        #    proxy_pass http://demo-big-file-server;
        #}
        
        
        location /api/ {
          proxy_set_header Host $host:$server_port;
          proxy_pass http://demo-big-file-server;
          rewrite "^/api/(.*)$" /$1 break;
        }        
    }
}


```


### 内置变量
```

# nginx 内置变量
location /test {
    echo "uri = $uri";
    echo "request_uri = $request_uri";
    echo "request_length=$request_length";
    #echo "request_id = $request_id";
    echo "request_time=$request_time";

    echo "args=$args";
    echo "arg_p=$arg_p";
    echo "arg_name=$arg_name";

    echo "server_addr=$server_addr";
    echo "server_name=$server_name";
    echo "server_port=$server_port";
    echo "server_protocol=$server_protocol";

    echo "time_local=$time_local";

    echo "host=$host";
    echo "hostname=$hostname";

    echo "remote_user=$remote_user";
    echo "remote_addr=$remote_addr";
    echo "remote_port=$remote_port";

}



$ curl 'http://localhost:7070/test?p=nihao&name=weizd'
    uri = /test
    request_uri = /test?p=nihao&name=weizd
    request_length=101
    request_time=0.000
    args=p=nihao&name=weizd
    arg_p=nihao
    arg_name=weizd
    server_addr=127.0.0.1
    server_name=localhost
    server_port=7070
    server_protocol=HTTP/1.1
    time_local=28/Jul/2020:17:44:30 +0800
    host=localhost
    hostname=weizd
    remote_user=
    remote_addr=127.0.0.1
    remote_port=45122

```



### 基本命令
> 启动
```
./sbin/nginx
```
> 指定配置文件启动
```
./sbin/nginx -c ./conf/nginx.conf
```
> 刷新使配置文件生效
```
./sbin/nginx -s reload
```
> 检测配置文件的正确性
```
./sbin/nginx -t
```
> 关闭
```
./sbin/nginx -s stop
```


### 依赖库安装
> pcre库安装

```
nginx 中rewrite 和 http 模块使用


apt-get install libpcre3 libpcre3-dev
或者
yum install pcre pcre-devel


```

> OpenSSL库安装
```
若服务器提供安全网页(https://)时，会用到OpenSSL库

yum install openssl openssl-devel
或者
apt-get install openssl openssl-dev

```


### nginx模块安装
> echo模块安装
```

 $ wget 'http://nginx.org/download/nginx-1.11.2.tar.gz'
 $ tar -xzvf nginx-1.11.2.tar.gz
 $ cd nginx-1.11.2/

 # Here we assume you would install you nginx under /opt/nginx/.
 $ ./configure --prefix=/opt/nginx \
     --add-module=/path/to/echo-nginx-module

 $ make -j2
 $ make install


参考网址: https://github.com/openresty/echo-nginx-module

```
