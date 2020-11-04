# docker安装ElasticSearch+Kibana+logstash

#### 安装ElasticSearch

* 执行以下命令安装镜像

```linux
docker pull docker.elastic.co/elasticsearch/elasticsearch:7.5.0
```

* 安装完成后运行ElasticSearch

```linux
docker run -id --name=es -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.5.0
```

* 安装IK分词器，进入es容器中 

```linux
docker exec -it es /bin/bash
```

* 进入plugins目录下创建ik文件夹

```
mkdir ik
```

* 下载并安装IK分词器

```
elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.5.0/elasticsearch-analysis-ik-7.5.0.zip
```







##### 安装kibana

* 执行以下命令安装官方镜像

```linux
docker pull docker.elastic.co/kibana/kibana:7.5.0
```

* 安装完成后运行（当前执行方式官方不推荐）

```linux
docker run --link #{你的elasticsearch在docker容器的名字或者id}:elasticsearch -p 5601:5601 docker.elastic.co/kibana/kibana:7.5.0
```

使用此方式运行

```
docker run -id --name=kibana --link {es名称或者容器id}:elasticsearch -p 5601:5601 docker.elastic.co/kibana/kibana:7.5.0
```











#### 安装logstash

* 拉取镜像

```
docker pull docker.elastic.co/logstash/logstash:7.5.0
```

* 启动容器 ，把docker配合路径挂载到自定义目录下

```
docker run -d --name logstash -zv /Users/work/docker/logstash/:/etc/logstash/pipeline/ docker.elastic.co/logstash/logstash:7.5.0
```

* 在挂载目录下，下载 mysql-connector-java-8.0.11.jar

* 然后创建配置文件 

```
vim logstash.config

#存入以下内容
input {
 stdin { }
    jdbc {
        #注意mysql连接地址一定要用ip，不能使用localhost等
        jdbc_connection_string => "jdbc:mysql://120.77.217.157:2873/qhyf2?useUnicode=true&serverTimezone=GMT&characterEncoding=utf8"
        #数据库连接的账号密码
        jdbc_user => "qhyf_dev"
        jdbc_password => "123456"
        #这个jar包的地址是容器内的地址
        jdbc_driver_library => "/etc/logstash/pipeline/mysql-connector-java-8.0.18.jar"
        jdbc_driver_class => "com.mysql.cj.jdbc.Driver"
         # 开启分页查询（默认false不开启）
        jdbc_paging_enabled => "true"
        # 单次分页查询条数
        jdbc_page_size => "50000"
        #追踪的字段
        tracking_column => "modify_time"
        # statement为查询数据sql，如果sql较复杂，建议配通过statement_filepath配置sql文件的存放路径
        # statement_filepath => "mysql/jdbc.sql"
        #sql_last_value为内置的变量，存放上次查询结果中最后一条数据tracking_column的值，此处为：modify_time
        # statement => "select * from t_order where modify_time >= :sql_last_value;"
        statement => "SELECT a.pay_id,a.invoice_no,a.invoice_name,b.uuid AS id,b.item_name,b.base_contract_name,b.due_date,b.base_contract_no ,b.invoice_description FROM `biz_pay_comfirm_info` b LEFT JOIN biz_invoice_info a ON b.uuid = a.pay_id ORDER BY id DESC"
        schedule => "* * * * *"
    }
}
# 简单结构存储时不用配置filter
filter{
    aggregate{
        task_id=>"%{id}"
        code=>"
        	#最外层source数据
            map['id'] = event.get('id')
            map['item_name'] = event.get('item_name')
            map['base_contract_name'] = event.get('base_contract_name')
            map['due_date'] = event.get('due_date')
            map['base_contract_no'] = event.get('base_contract_no')
            map['invoice_description'] = event.get('invoice_description')
            #嵌套的数组名
            map['invoice'] ||=[]
            if (event.get('pay_id') != nil)
            	#数组里面的数据
                map['invoice'] << {
                    'pay_id' => event.get('pay_id'),
                    'invoice_no' => event.get('invoice_no'),
                    'invoice_name' => event.get('invoice_name')
                }
            end
            event.cancel()"
        push_previous_map_as_event=>true
        timeout=>5
         }
    json {
        source => "message"
        remove_field => ["message"]
    }
    mutate  {
        #将不需要的JSON字段过滤，且不会被存入 ES 中
        remove_field => ["tags", "@timestamp", "@version"]
    }
}

output {
	# JSON格式输出
     stdout {
        codec => json_lines
    }
    elasticsearch {
        #注意mysql连接地址一定要用ip，不能使用localhost等
        hosts => ["192.168.137.98:9200"]
        #索引名（必须小写）
        index => "pay_comfir_test"
        #可以使用数据库查询到的id当做es的id，不配置默认生成id
        document_id => "%{id}"
    }
}
```

* 进入容器内

  ```
  docker exec -it logstash /bin/bash
  进入下面目录
  cd /etc/logstash/pipeline
  下载驱动包
  bin/logstash-plugin install logstash-input-jdbc
  bin/logstash-plugin install logstash-output-elasticsearch
  再次进入容器进入config目录
  cd config
  vi logstas.yml
  保存以下配置
  http.host: "0.0.0.0"
  xpack.monitoring.elasticsearch.hosts: [ "http://192.168.137.98:9200" ]
  然后编辑pipeline.yml
  vi pipeline.yml
  保存以下配置
  - pipeline.id: logstash1
    path.config: "/etc/logstash/pipeline/logstash.config"
  ```

* 退出容器，重启logstash

```
docker restart logstash

查看日志
docker logs -f --tail=300 logstash
加载驱动失败问题解决：
默认logstash-input-jdbc4.3.16有bug升级就可以了
bin/logstash-plugin update logstash-input-jdbc
```

