### Logstash配置

1.  下载驱动包

   ```
   下载驱动包
   #input输入插件
   bin/logstash-plugin install logstash-input-jdbc
   #output输出插件
   bin/logstash-plugin install logstash-output-elasticsearch
   #聚合过滤插件
   bin/logstash-plugin install logstash-filter-aggregate
   ```

2.  编写配置文件--logstash.config

```
input {
 stdin { }
    jdbc {
        #注意mysql连接地址一定要用ip，不能使用localhost等
        jdbc_connection_string => "#{数据库连接url}"
        #数据库连接的账号密码
        jdbc_user => "#{数据库用户名}"
        jdbc_password => "#{数据库密码}"
        #这个jar包的地址是容器内的地址
        jdbc_driver_library => "#{数据库连接驱动包路径}"
        jdbc_driver_class => "com.mysql.cj.jdbc.Driver"
         # 开启分页查询（默认false不开启）
        jdbc_paging_enabled => "true"
        # 单次分页查询条数
        jdbc_page_size => "#{分页条数}"
        #追踪的字段
        tracking_column => "modify_time"
        # statement为查询数据sql，如果sql较复杂，建议配通过statement_filepath配置sql文件的存放路径
        # statement_filepath => "#{配置的sql路径，列：/xxx/xxx.sql}"
        #sql_last_value为内置的变量，存放上次查询结果中最后一条数据tracking_column的值，此处为：modify_time
        # statement => "select * from t_order where modify_time >= :sql_last_value;"
        statement => "#{SQL}"
        # 定时任务：默认一分钟一次
        schedule => "* * * * *"
    }
}
filter{
	stdin { }
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
            #声明你要的冗余的数组名
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
        #可以使用数据库查询到的id当做es的id
        document_id => "%{id}"
    }
}
```

3. 配置logstash.yml文件 

```
http.host: "0.0.0.0"
# 集群ip和端口
xpack.monitoring.elasticsearch.hosts: [ "http://192.168.137.98:9200" ]
```



4. 配置pipeline.yml

```
# id名称可自定义，保证唯一
- pipeline.id: table  
#配置文件的地址
  path.config: "/etc/logstash/pipeline/logstash.config"
```

