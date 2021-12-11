package bg.coffeshop.coffeeShop.service;

import bg.coffeshop.coffeeShop.model.entity.Statistic;
import bg.coffeshop.coffeeShop.model.view.StatisticViewModel;

import java.util.List;

public interface StatisticService {
    Statistic saveVisitationInDataBase(String ip);
    List<StatisticViewModel> getStatistic();

    void dropTable();
}
