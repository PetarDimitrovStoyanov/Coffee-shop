package bg.coffeshop.coffeeShop.service.impl;

import bg.coffeshop.coffeeShop.model.binding.ProductBindingModel;
import bg.coffeshop.coffeeShop.model.service.ProductServiceModel;
import bg.coffeshop.coffeeShop.model.entity.Product;
import bg.coffeshop.coffeeShop.model.view.ProductViewModel;
import bg.coffeshop.coffeeShop.repository.ProductRepository;
import bg.coffeshop.coffeeShop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
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
        product.setPiece(0);
        return this.productRepository.saveAndFlush(product);
    }

    @Override
    @Transactional
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

    @Override
    public ProductServiceModel mapProductServiceModel(ProductBindingModel productBindingModel, MultipartFile file) throws IOException {
        ProductServiceModel productServiceModel = new ProductServiceModel();
        productServiceModel.setName(productBindingModel.getName());
        productServiceModel.setPrice(productBindingModel.getPrice());
        productServiceModel.setType(productBindingModel.getType());

        productServiceModel
                .setOrders(new ArrayList<>());

        if (!file.isEmpty()) {
            productServiceModel.setPicture(Base64.getEncoder().encodeToString(file.getBytes()));
        }

        return productServiceModel;
    }

    @Override
    public ProductServiceModel mapProductServiceModel(ProductServiceModel productServiceModel1, MultipartFile file) throws IOException {
        ProductServiceModel productServiceModel = new ProductServiceModel();
        productServiceModel.setName(productServiceModel1.getName());
        productServiceModel.setPrice(productServiceModel1.getPrice());
        productServiceModel.setType(productServiceModel1.getType());
        productServiceModel.setOrders(productServiceModel1.getOrders());

        if (!file.isEmpty()) {
            productServiceModel.setPicture(Base64.getEncoder().encodeToString(file.getBytes()));
        } else {
            ProductServiceModel product = this.findById(productServiceModel1.getId());
            productServiceModel.setPicture(product.getPicture());
        }

        return productServiceModel;
    }


}
