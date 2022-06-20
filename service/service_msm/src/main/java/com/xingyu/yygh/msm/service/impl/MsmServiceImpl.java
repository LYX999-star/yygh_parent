package com.xingyu.yygh.msm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xingyu.yygh.msm.service.MsmService;
import com.xingyu.yygh.msm.utils.EmailUtils;
import com.xingyu.yygh.vo.msm.MsmVo;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class MsmServiceImpl implements MsmService {

    public static String LOGIN_TEMPLATE = "预约挂号系统登录/注册，你的验证码是：%s。验证码有效时间两分钟。";
    public static String LOGIN_SUBJECT = "预约挂号系统登录/注册";

    public static String SCHEDULE_TEMPLATE = "【预约挂号】{0},您成功预约{1}的{2}的{3}{4}的{5}号," +
            "取号时间：{6}，取号地址：{7}，请及时到医院就诊。退号截止时间：{8}";
    public static String SCHEDULE_SUBJECT = "预约挂号通知";

    public static String SCHEDULED_TEMPLATE = "【预约挂号】{0},您预约{1}的{2}的{3}的预约挂号即将到时，" +
            "请及时到医院就诊。";
    public static String SCHEDULED_SUBJECT = "预约到时提醒";


    @Override
    public boolean send(String email, String code) {
        //判断手机号是否为空
        if (StringUtils.isEmpty(email)) {
            return false;
        }
        String msg = String.format(LOGIN_TEMPLATE, code);
        try {
            // 执行验证码发送
            String logging = "执行验证码发送：";
            log.info(logging + msg);
            EmailUtils.sendEmail(email, LOGIN_SUBJECT, msg);
        } catch (Exception e) {
            return false;
        }

        return true;
    }


    //mq发送短信封装:预约成功时，发送短信
    @Override
    public boolean send(MsmVo msmVo) {
        if (!StringUtils.isEmpty(msmVo.getPhone())) {
            boolean isSend = this.send(msmVo.getPhone(), msmVo.getParam());
            return isSend;
        }
        return false;
    }

    private boolean send(String email, Map<String, Object> param) {
        //判断手机号是否为空
        if (StringUtils.isEmpty(email)) {
            return false;
        }

        String type = (String) param.get("type");
        String name = (String) param.getOrDefault("name", "");
        String hospname = (String) param.getOrDefault("hospname", "");
        String reserveDate = (String) param.getOrDefault("reserveDate", "");
        String depname = (String) param.getOrDefault("depname", "");
        String docname = (String) param.getOrDefault("docname", "");
        String number = (String) param.getOrDefault("number", "");
        String fetchTime = (String) param.getOrDefault("fetchTime", "");
        String fetchAddress = (String) param.getOrDefault("fetchAddress", "");
        String reserveTime = (String) param.getOrDefault("reserveTime", "");
        String quitTime = (String) param.getOrDefault("quitTime", "");

        // 发送预约短信
        String msm = "";
        if (type.equals("ORDER")) {
            //        String SCHEDULE_TEMPLATE = "【预约挂号】{0},您成功预约{1}的{2}的{3}{4}的{6}号," +
//                "取号时间：{7}，取号地址：{8}，就诊时间：{9},请及时到医院就诊。退号截止时间：{10}";
            msm = MessageFormat.format(SCHEDULE_TEMPLATE,
                    name,
                    hospname,
                    reserveDate,
                    depname,
                    docname,
                    number,
                    fetchTime,
                    fetchAddress,
                    quitTime
            );

            try {
                // 执行验证码发送
                String logging = "执行预约挂号发送：";
                log.info(logging + msm);
                EmailUtils.sendEmail(email, SCHEDULE_SUBJECT, msm);
            } catch (Exception e) {
                return false;
            }
            return true;
        } else if (type.equals("SCHEDULED")) { // 发送定时提醒
            msm = MessageFormat.format(SCHEDULED_TEMPLATE,
                    name,
                    hospname,
                    reserveDate,
                    depname
            );
            try {
                // 执行验证码发送
                String logging = "【模拟发送】执行定时提醒发送：";
                log.info(logging + "::" + email + "::" + msm);
//                EmailUtils.sendEmail(email, SCHEDULED_SUBJECT, msm);
            } catch (Exception e) {
                return false;
            }
            return true;
        } else if (type.equals("CANCEL")) {
            try {
                // 执行验证码发送
                String logging = "【模拟发送】执行预约取消发送：";
                msm = "预约挂号订单已成功取消";
                log.info(logging + "::" + email + "::" + msm);
                EmailUtils.sendEmail(email, SCHEDULED_SUBJECT, msm);
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        //调用方法进行邮件发送
        return true;
    }
}
