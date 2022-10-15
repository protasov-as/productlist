package com.example.productlist.Controller;

import com.example.productlist.models.Product;
import com.example.productlist.Repository.ProductMongoRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private ProductMongoRepository productMongoRepository;

    @Autowired
    public ProductController(ProductMongoRepository productMongoRepository) {
        this.productMongoRepository = productMongoRepository;
    }

    @ApiOperation(value = "Get all products", notes = "Retrieves all products from the collection")
    @RequestMapping("/products")
    public List<Product> getProducts(){
        return productMongoRepository.findAll();
    }

    @ApiOperation(value = "Add a product", notes = "Saves a product with the specified values")
    @PostMapping("/products")
    public void add(Product product) {
        if(product.getId()==0) {
            product.setId(product.hashCode());
        }
        productMongoRepository.save(product);
    }




}
