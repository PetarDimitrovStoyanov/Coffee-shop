package bg.coffeshop.coffeeShop.service.impl;

import bg.coffeshop.coffeeShop.model.entity.Product;
import bg.coffeshop.coffeeShop.model.service.ProductServiceModel;
import bg.coffeshop.coffeeShop.model.view.ProductViewModel;
import bg.coffeshop.coffeeShop.repository.ProductRepository;
import bg.coffeshop.coffeeShop.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    private ModelMapper modelMapper = new ModelMapper();
    private ProductService serviceToTest;
    private Product initialProduct;


    @BeforeEach
    void setUp() {
        serviceToTest = new ProductServiceImpl(productRepository, modelMapper);
        initialProduct = new Product();
        initialProduct.setPicture("some picture");
        initialProduct.setPrice(BigDecimal.valueOf(10.00));
        initialProduct.setOrder(null);
        initialProduct.setType("Grinded");
        initialProduct.setName("theName");
    }

    @Test
    public void saveInDB() {
        ProductServiceModel productServiceModel = new ProductServiceModel();
        productServiceModel.setPicture("some picture");
        productServiceModel.setPrice(BigDecimal.valueOf(10.00));
        productServiceModel.setOrders(new ArrayList<>());
        productServiceModel.setType("Grinded");
        productServiceModel.setName("theName");

        serviceToTest.saveInDB(productServiceModel);
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).saveAndFlush(productArgumentCaptor.capture());
    }

    @Test
    public void findAll() {
        serviceToTest.findAll();
        verify(productRepository).findAll();
    }

    @Test
    public void isExisting() {
        ProductServiceModel productServiceModel = new ProductServiceModel();
        productServiceModel.setPicture("some picture");
        productServiceModel.setPrice(BigDecimal.valueOf(10.00));
        productServiceModel.setOrders(new ArrayList<>());
        productServiceModel.setType("Grinded");
        productServiceModel.setName("theName");

        serviceToTest.saveInDB(productServiceModel);

        serviceToTest.isProductExists(productServiceModel.getName());
        verify(productRepository).findByName(productServiceModel.getName());
    }

    @Test
    public void delete() throws Exception {
        productRepository.saveAndFlush(initialProduct);
        productRepository.deleteById(initialProduct.getId());
        serviceToTest.deleteById(initialProduct.getId());

        var actual2 = serviceToTest.findAll()
                .stream()
                .map(u -> modelMapper.map(u, ProductViewModel.class))
                .collect(Collectors.toList());

        Assertions.assertEquals(actual2.size(), 0);
    }

    @Test
    public void findById(){
        when(productRepository.findById(initialProduct.getId()))
                .thenReturn(Optional.of(initialProduct));

        var actual = serviceToTest.findById(initialProduct.getId());

        Assertions.assertEquals(actual.getName(), initialProduct.getName());
    }


}