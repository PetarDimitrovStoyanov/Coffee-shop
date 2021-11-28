package bg.coffeshop.coffeeShop.service.impl;

import bg.coffeshop.coffeeShop.model.Service.DeliveryServiceModel;
import bg.coffeshop.coffeeShop.model.entity.Delivery;
import bg.coffeshop.coffeeShop.repository.DeliveryRepository;
import bg.coffeshop.coffeeShop.service.DeliveryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl implements DeliveryService {
    private final ModelMapper modelMapper;
    private final DeliveryRepository deliveryRepository;

    public DeliveryServiceImpl(ModelMapper modelMapper, DeliveryRepository deliveryRepository) {
        this.modelMapper = modelMapper;
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public Delivery save(DeliveryServiceModel deliveryServiceModel) {
        Delivery delivery = this.modelMapper.map(deliveryServiceModel, Delivery.class);
        this.deliveryRepository.saveAndFlush(delivery);
        return delivery;
    }
}
