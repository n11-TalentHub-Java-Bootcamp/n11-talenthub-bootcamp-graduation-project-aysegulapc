package com.aysegulapc.graduation.project.notification.sms;

import com.aysegulapc.graduation.project.notification.entity.SmsRequest;

public interface SmsSender {

    void sendSms(SmsRequest smsRequest);

    // or maybe void sendSms(String phoneNumber, String message);
}
