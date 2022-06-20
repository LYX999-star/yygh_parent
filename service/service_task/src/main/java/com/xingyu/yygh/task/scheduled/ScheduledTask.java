package com.xingyu.yygh.task.scheduled;

import com.xingyu.common.rabbit.constant.MqConst;
import com.xingyu.common.rabbit.service.RabbitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableScheduling
public class ScheduledTask {
    @Autowired
    RabbitService rabbitService;

    //每天8点执行方法，就医提醒
    //cron表达式，设置执行间隔
    //0 0 8 * * ?
    @Scheduled(cron = "0/30 * * * * ?")
//    @Scheduled(cron = "0 30 * * * ?")
    public void taskPatient() {
        log.debug("================定时任务执行================");
        rabbitService.sendMessage(MqConst.EXCHANGE_DIRECT_TASK,MqConst.ROUTING_TASK_8,"");
    }

}
