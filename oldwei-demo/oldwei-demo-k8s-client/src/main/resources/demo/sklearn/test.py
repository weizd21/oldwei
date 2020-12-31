#!/usr/bin/env python3
import json
import yaml
with open("deployment_nginx.yaml") as stream:
    yaml_data = yaml.safe_load(stream)
#此处如果yaml文件有问题，会报错）
json_data = json.dumps(yaml_data,sort_keys=False,indent=4,separators=(',',': '))

print(json_data)
