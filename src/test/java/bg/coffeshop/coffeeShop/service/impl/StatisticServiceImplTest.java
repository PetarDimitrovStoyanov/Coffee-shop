package bg.coffeshop.coffeeShop.service.impl;

import bg.coffeshop.coffeeShop.model.entity.Statistic;
import bg.coffeshop.coffeeShop.repository.StatisticRepository;
import bg.coffeshop.coffeeShop.service.StatisticService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StatisticServiceImplTest {
    private ModelMapper modelMapper = new ModelMapper();

    @Mock
    private StatisticRepository statisticRepository;
    private StatisticService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StatisticServiceImpl(statisticRepository, modelMapper);
    }

    @Test
    public void getStatistic() {
        underTest.getStatistic();
        verify(statisticRepository).findAll();
    }

    @Test
    public void saveVisitationInDataBase(){
        Statistic statistic = new Statistic();
        statistic.setIpAddress("01.01.00.01.05");
        statistic.setLocalDateTime(LocalDateTime.now());
        underTest.saveVisitationInDataBase("01.01.00.01.05");
        ArgumentCaptor<Statistic> statisticArgumentCaptor = ArgumentCaptor.forClass(Statistic.class);
        verify(statisticRepository).saveAndFlush(statisticArgumentCaptor.capture());
    }

    @Test
    public void dropTable(){
        underTest.dropTable();
        verify(statisticRepository).dropTable();
    }

}
