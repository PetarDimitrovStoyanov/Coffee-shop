package bg.coffeshop.coffeeShop.repository;

import bg.coffeshop.coffeeShop.model.entity.Statistic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StatisticRepositoryTest {
    Statistic statistic;

    @Autowired
    StatisticRepository statisticRepository;

    @BeforeEach
    void setUp() {
        statistic = new Statistic();
        statistic.setLocalDateTime(LocalDateTime.now());
        statistic.setIpAddress("01.01.20.202.25");
    }

    @Test
    public void dropTable() {
        statisticRepository.saveAndFlush(statistic);
        assertEquals(1, statisticRepository.count());
        statisticRepository.dropTable();
        assertEquals(0, statisticRepository.count());
    }

    @AfterEach
    void tearDown() {
        statisticRepository.deleteAll();
    }
}