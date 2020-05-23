package com.cdyfsz.base.biz.svc.zhongdeng;

import club.newepoch.isf.BaseApplication;
import club.newepoch.isf.dozer.annotation.EnableDTO2POTransmitter;
import club.newepoch.isf.ext.feign.EnableIsfCustomFeign;
import club.newepoch.isf.ext.zipkin.annotation.EnableIsfZipkin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.amqp.RabbitHealthIndicatorAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author lj
 * 服务启动类
 * Created by intasect on 2019-05-14 10:05:39.
 */
@SpringBootApplication(exclude = {RabbitHealthIndicatorAutoConfiguration.class})
@EnableDTO2POTransmitter
@EnableTransactionManagement
@EnableFeignClients(basePackages = {"com.cdyfsz.*"})
@ComponentScan({"com.cdyfsz"})
@EnableIsfCustomFeign
@EnableIsfZipkin
@EnableDiscoveryClient
@EnableRetry
public class StartApplication extends BaseApplication {

    /**
     * 启动执行入口
     *
     * @param args 执行参数
     */
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
        System.out.println("启动成功…………");
    }
}

