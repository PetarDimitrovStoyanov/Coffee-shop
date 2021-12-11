package bg.coffeshop.coffeeShop.repository;

import bg.coffeshop.coffeeShop.model.entity.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {
    Product product;
    Product product2;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setType("Grinded")
                .setPicture(null)
                .setName("Sweet hart")
                .setPrice(BigDecimal.valueOf(20))
                .setPiece(0);

        product2 = new Product();
        product2.setType("Whole Beam")
                .setPicture(null)
                .setName("Stone hart")
                .setPrice(BigDecimal.valueOf(10))
                .setPiece(0);
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    public void saveAndFlush() {
        productRepository.saveAndFlush(product);
        assertEquals(productRepository.count(), 1);
    }

    @Test
    public void findAll(){
        productRepository.saveAndFlush(product);
        productRepository.saveAndFlush(product2);
        assertEquals(productRepository.count(), 2);
    }

    @Test
    public void findByName() throws Exception {
        productRepository.saveAndFlush(product);
        Product actual = productRepository.findByName(this.product.getName()).orElseThrow(Exception::new);
        assertEquals(actual.getName(), product.getName());
    }

    @Test
    public void findById() throws Exception {
        productRepository.saveAndFlush(product);
        Product foundByName = productRepository.findByName(this.product.getName()).orElseThrow(Exception::new);
        Product actual = productRepository.findById(foundByName.getId()).orElseThrow(Exception::new);
        assertEquals(actual.getName(), product.getName());
    }

    @Test
    public void deleteById() throws Exception {
        productRepository.saveAndFlush(product);
        assertEquals(productRepository.count(), 1);
        Product foundByName = productRepository.findByName(this.product.getName()).orElseThrow(Exception::new);
        productRepository.deleteById(foundByName.getId());
        assertEquals(productRepository.count(), 0);
    }

}