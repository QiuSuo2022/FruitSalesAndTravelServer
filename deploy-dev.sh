#!/bin/bash

# 在服务器上 ~/.ssh/authorized_keys 增加本机的 ssh公钥(~/.ssh/id_rsa.pub)，可以不输入密码直接部署
# 先停止（停止可能失败）
ssh root@120.76.200.109"cd /root/project/fruitSaleAndTravel && ./restart.sh"

# 编译并部署到服务器

# 编译
mvn clean package -DskipTests || exit

# 上传
scp target/fruit-travel-0.0.1-SNAPSHOT.jar root@120.76.200.109:/root/project/fruitSaleAndTravel/

# 部署
ssh root@120.76.200.109 "cd /root/project/fruitSaleAndTravel && ./restart.sh"

# 查看运行日志
ssh root@120.76.200.109 "tail -f -n 300 /root/project/fruitSaleAndTravel/stdout.log"

# swagger-ui 地址：http://120.76.200.109:8030/swagger-ui/




ssh root@120.76.200.109 "cd /root/git-project/yun && ./update.sh"

# 查看运行日志
ssh root@120.76.200.109 "tail -f -n 300 /root/git-project/yun/stdout.log"