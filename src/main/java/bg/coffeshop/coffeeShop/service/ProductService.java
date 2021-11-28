package bg.coffeshop.coffeeShop.service;

import bg.coffeshop.coffeeShop.model.binding.ProductBindingModel;
import bg.coffeshop.coffeeShop.model.service.ProductServiceModel;
import bg.coffeshop.coffeeShop.model.entity.Product;
import bg.coffeshop.coffeeShop.model.view.ProductViewModel;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

public interface ProductService {

    // TODO: fix this to return ProductServiceModel
    Product saveInDB(ProductServiceModel product);

    List<ProductViewModel> findAll();

    boolean isProductExists(String name);

    ProductServiceModel findById(Long id);

    void deleteById(Long id) throws Exception;

    void update(ProductServiceModel productServiceModel, Long id);

    ProductServiceModel mapProductServiceModel(@Valid ProductBindingModel productBindingModel,
                                               @RequestParam("file") MultipartFile file) throws IOException;

    ProductServiceModel mapProductServiceModel(@Valid ProductServiceModel productServiceModel1,
                                                       @RequestParam("file") MultipartFile file) throws IOException;
}
