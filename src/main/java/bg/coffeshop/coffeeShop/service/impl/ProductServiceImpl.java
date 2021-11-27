package bg.coffeshop.coffeeShop.service.impl;

import bg.coffeshop.coffeeShop.model.Service.ProductServiceModel;
import bg.coffeshop.coffeeShop.model.entity.Product;
import bg.coffeshop.coffeeShop.model.view.ProductViewModel;
import bg.coffeshop.coffeeShop.repository.ProductRepository;
import bg.coffeshop.coffeeShop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Product saveInDB(ProductServiceModel productServiceModel) {
        Product product = this.modelMapper.map(productServiceModel, Product.class);
        return this.productRepository.saveAndFlush(product);
    }

    @Override
    public List<ProductViewModel> findAll() {

        return this.productRepository
                .findAll()
                .stream()
                .map(p -> this.modelMapper.map(p, ProductViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isProductExists(String name) {
        return this.productRepository.findByName(name).isPresent();
    }


    @Override
    @Transactional
    public ProductServiceModel findById(Long id) {
        Product product = this.productRepository.findById(id).orElseThrow(Error::new);
        return this.modelMapper.map(product, ProductServiceModel.class);
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }



    @Override
    public void update(ProductServiceModel productServiceModel, Long id) {
        Product product = this.modelMapper.map(productServiceModel, Product.class);
        this.productRepository.update(product.getName(),
                product.getPicture(),
                product.getPrice(),
                product.getType(),
                id);
    }


}
