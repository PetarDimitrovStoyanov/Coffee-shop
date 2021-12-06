package bg.coffeshop.coffeeShop.util.sms;

@org.springframework.stereotype.Service
public class SmsService {
    private final TwilioSmsSender twilioSmsSender;

    public SmsService(TwilioSmsSender twilioSmsSender) {
        this.twilioSmsSender = twilioSmsSender;
    }

    public void sendSms(SmsRequest smsRequest){
      twilioSmsSender.sendSms(smsRequest);
    }
}
