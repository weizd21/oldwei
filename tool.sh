#!/usr/bin/env bash

# 将当前项目的md文档 移动到doc项目下，并执行git相关命令进行提交推送到远程仓库

echo "[start]----";
file_name="README.md";
new_file_name="oldwei-readme.md";
target_dir="/home/github/doc/project";
current_dir=$(dirname "$0");

cp -f "$current_dir/$file_name" $target_dir;

cd $target_dir;

mv $file_name $new_file_name;

rm -f $file_name;

ls -l;

cd ..

pwd;

git add .;

git commit -m "完善oldwei项目的readme.md文档 from oldwei > tool.sh";

git push origin develop:develop;

echo "[end]----";




