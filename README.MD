再确定好nacos-client版本后启动对应版本的nacos
```
    # 启动单机模式
    sudo docker run -d -p 8848:8848 -p 9848:9848 -p 10848:10848 -e MODE=standalone --name nacos nacos/nacos-server:v2.4.2
```
