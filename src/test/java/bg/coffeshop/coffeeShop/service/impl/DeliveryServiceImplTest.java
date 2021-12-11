package bg.coffeshop.coffeeShop.service.impl;

import bg.coffeshop.coffeeShop.model.entity.Delivery;
import bg.coffeshop.coffeeShop.model.entity.Order;
import bg.coffeshop.coffeeShop.model.service.DeliveryServiceModel;
import bg.coffeshop.coffeeShop.repository.DeliveryRepository;
import bg.coffeshop.coffeeShop.service.DeliveryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceImplTest {
    private ModelMapper modelMapper = new ModelMapper();

    @Mock
    private DeliveryRepository deliveryRepository;
    private DeliveryService underTest;

    @BeforeEach
    void setUp() {
        underTest = new DeliveryServiceImpl(modelMapper, deliveryRepository);
    }

    @Test
    public void save() {
        DeliveryServiceModel delivery = new DeliveryServiceModel();
        delivery.setAddress("adressssss");
        delivery.setCity("Plovdiv");
        delivery.setCountry("Bulgaria");
        delivery.setPostalCode("4000");
        delivery.setEmail("asd@asd.bg");
        delivery.setPerson("Someone");
        delivery.setPhone("+359888888888");
        List<Order> orders = new ArrayList<>();
        delivery.setOrders(orders);
        underTest.save(delivery);
        ArgumentCaptor<Delivery> deliveryArgumentCaptor = ArgumentCaptor.forClass(Delivery.class);
        verify(deliveryRepository).saveAndFlush(deliveryArgumentCaptor.capture());
        Delivery delivery1 = deliveryArgumentCaptor.getValue();
        assertThat(delivery1.getEmail()).isEqualTo(delivery.getEmail());
    }

    @Test
    public void findById() throws Exception {
        DeliveryServiceModel delivery = new DeliveryServiceModel();
        delivery.setAddress("adressssss");
        delivery.setCity("Plovdiv");
        delivery.setCountry("Bulgaria");
        delivery.setPostalCode("4000");
        delivery.setEmail("asd@asd.bg");
        delivery.setPerson("Someone");
        delivery.setPhone("+359888888888");
        List<Order> orders = new ArrayList<>();
        delivery.setOrders(orders);
        underTest.save(delivery);

        Delivery map = this.modelMapper.map(delivery, Delivery.class);

        when(deliveryRepository.findById(delivery.getId()))
                .thenReturn(Optional.of(map));

        String actual = underTest.findById(delivery.getId());

        Assertions.assertEquals(actual, delivery.getCourier());
    }
}