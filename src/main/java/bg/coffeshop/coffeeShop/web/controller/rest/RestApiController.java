package bg.coffeshop.coffeeShop.web.controller.rest;

import bg.coffeshop.coffeeShop.model.view.ProductViewModel;
import bg.coffeshop.coffeeShop.model.view.StatisticViewModel;
import bg.coffeshop.coffeeShop.service.ProductService;
import bg.coffeshop.coffeeShop.service.StatisticService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import java.util.List;

@RestController
public class RestApiController {
    private final ProductService productService;
    private final StatisticService statisticService;

    public RestApiController(ProductService productService, StatisticService statisticService) {
        this.productService = productService;
        this.statisticService = statisticService;
    }

    @GetMapping("/api/products")
    @Transactional
    public ResponseEntity<List<ProductViewModel>> getAllProducts() {
        List<ProductViewModel> products = this.productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/api/statistic")
    @Transactional
    public ResponseEntity<List<StatisticViewModel>> getStatistic() {
        List<StatisticViewModel> statisticViewModels = this.statisticService.getStatistic();
        return new ResponseEntity<>(statisticViewModels, HttpStatus.OK);
    }

}






