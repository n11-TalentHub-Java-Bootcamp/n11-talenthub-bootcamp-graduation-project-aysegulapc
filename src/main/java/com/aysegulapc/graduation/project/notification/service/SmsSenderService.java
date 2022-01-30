package com.aysegulapc.graduation.project.notification.service;

import com.aysegulapc.graduation.project.notification.entity.SmsRequest;
import com.aysegulapc.graduation.project.notification.sms.SmsSender;
import com.aysegulapc.graduation.project.notification.twilio.TwilioSmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SmsSenderService {
    private final SmsSender smsSender;

    @Autowired
    public SmsSenderService(@Qualifier("twilio") TwilioSmsSender smsSender) {
        this.smsSender = smsSender;
    }

    public void sendSms(SmsRequest smsRequest) {
        smsSender.sendSms(smsRequest);
    }
}
