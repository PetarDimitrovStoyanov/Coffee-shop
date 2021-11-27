package bg.coffeshop.coffeeShop.web.rest;

import bg.coffeshop.coffeeShop.model.entity.Product;
import bg.coffeshop.coffeeShop.model.view.ProductViewModel;
import bg.coffeshop.coffeeShop.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
/*import javax.validation.Valid;

import java.net.URI;*/
import java.util.List;

@RestController
public class ProductRestController {
    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/products")
    @Transactional
    public ResponseEntity<List<ProductViewModel>> getAllProducts() {
        List<ProductViewModel> products = this.productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

/*    @PostMapping("/api/add-products")
    public ResponseEntity<Product> create() {

        Product product1 = this.productService.saveInDB(new Product());
        URI location = URI.create(String.format("/api/products/%s", product1.getId()));

        return ResponseEntity.created(location).body(product1);
    }*/

}






