#!/usr/bin/env bash


cd /opt/realNginx;
PWD;
echo "---> nginx <---";


echo "|--> 停止 ";
./sbin/nginx -s stop
echo "|--> 启动 ";
./sbin/nginx
echo "|--> 刷新配置文件 ";
./sbin/nginx -s reload

echo "---> nginx <---";



