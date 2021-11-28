package bg.coffeshop.coffeeShop.service;

import bg.coffeshop.coffeeShop.model.view.StatisticViewModel;

import java.util.List;

public interface StatisticService {
    void saveVisitationInDataBase(String ip);
    List<StatisticViewModel> getStatistic();

    void dropTable();
}
