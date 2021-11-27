package bg.coffeshop.coffeeShop.service;

import bg.coffeshop.coffeeShop.model.Service.ProductServiceModel;
import bg.coffeshop.coffeeShop.model.entity.Product;
import bg.coffeshop.coffeeShop.model.view.ProductViewModel;

import java.util.List;

public interface ProductService {

    Product saveInDB(ProductServiceModel product);

    List<ProductViewModel> findAll();

    boolean isProductExists(String name);
}
