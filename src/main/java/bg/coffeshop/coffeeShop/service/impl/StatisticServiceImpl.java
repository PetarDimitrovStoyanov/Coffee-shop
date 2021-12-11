package bg.coffeshop.coffeeShop.service.impl;

import bg.coffeshop.coffeeShop.model.entity.Statistic;
import bg.coffeshop.coffeeShop.model.view.StatisticViewModel;
import bg.coffeshop.coffeeShop.repository.StatisticRepository;
import bg.coffeshop.coffeeShop.service.StatisticService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticServiceImpl implements StatisticService {

    private final StatisticRepository statisticRepository;
    private final ModelMapper modelMapper;

    public StatisticServiceImpl(StatisticRepository statisticRepository, ModelMapper modelMapper) {
        this.statisticRepository = statisticRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Statistic saveVisitationInDataBase(String ip) {
        Statistic statistic = new Statistic();
        statistic.setIpAddress(ip);
        statistic.setLocalDateTime(LocalDateTime.now());
        return this.statisticRepository.saveAndFlush(statistic);
    }

    @Override
    public List<StatisticViewModel> getStatistic() {
        List<Statistic> statisticList = this.statisticRepository.findAll();
        return statisticList
                .stream()
                .map(s -> this.modelMapper.map(s, StatisticViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void dropTable() {
        this.statisticRepository.dropTable();
    }

}
