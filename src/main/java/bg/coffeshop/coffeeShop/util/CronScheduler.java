package bg.coffeshop.coffeeShop.util;

import bg.coffeshop.coffeeShop.service.OrderService;
import bg.coffeshop.coffeeShop.service.StatisticService;
import bg.coffeshop.coffeeShop.util.sms.SmsService;
import bg.coffeshop.coffeeShop.util.sms.SmsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CronScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CronScheduler.class);
    private final StatisticService statisticService;
    private final SmsService smsService;
    private final OrderService orderService;

    public CronScheduler(StatisticService statisticService, SmsService smsService, OrderService orderService) {
        this.statisticService = statisticService;
        this.smsService = smsService;
        this.orderService = orderService;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void dropVisitations() {
        this.statisticService.dropTable();
        LOGGER.info("The statistic database was refreshed successfully!");
    }

    @Scheduled(cron = "0 0 * * * *")
    public void dropOrders() {
        this.orderService.dropTable();
        LOGGER.info("The orders database was refreshed successfully!");
    }

    @Scheduled(fixedDelay = 14400000)
    public void textMessage() {
        BigDecimal totalTurnover = this.orderService.getTotalTurnover();
        if (totalTurnover != null && totalTurnover.doubleValue() > 0.01) {
            SmsRequest smsRequest = new SmsRequest("+359888022053",
                    String.format("The total turnover till now is: %s$", totalTurnover));
            smsService.sendSms(smsRequest);
            LOGGER.info("The custom SMS was sent successfully!");
        }
    }
}
