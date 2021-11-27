package bg.coffeshop.coffeeShop.service;

import bg.coffeshop.coffeeShop.model.Service.ProductServiceModel;
import bg.coffeshop.coffeeShop.model.entity.Product;
import bg.coffeshop.coffeeShop.model.view.ProductViewModel;

import java.util.List;

public interface ProductService {

    // TODO: fix this to return ProductServiceModel
    Product saveInDB(ProductServiceModel product);

    List<ProductViewModel> findAll();

    boolean isProductExists(String name);

    ProductServiceModel findById(Long id);

    void deleteById(Long id) throws Exception;

    void update(ProductServiceModel productServiceModel, Long id);
}
