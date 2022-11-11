package com.example.loki.winners.northwindapi.controller;

import com.example.loki.winners.northwindapi.entity.Product;
import com.example.loki.winners.northwindapi.exception.CustomerNotFoundException;
import com.example.loki.winners.northwindapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilderDsl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ProductController {
    public static ProductController controller;
    private ProductRepository productRepo;

    @Autowired
    public ProductController(ProductRepository productRepo) {
        this.productRepo = productRepo;
        controller = WebMvcLinkBuilder.methodOn(this.getClass());
    }

    @GetMapping("product/all")
    public CollectionModel<Product> getAllProducts() {
        List<Product> products = productRepo.findAll();

        products.forEach(product -> {
            product.add(WebMvcLinkBuilder.linkTo(controller.getProductById(product.getId())).withSelfRel());
            product.add(WebMvcLinkBuilder.linkTo(CategoryController.controller.getCategoryById(product.getCategoryID().getId())).withRel(LinkRelation.of("category")));
            product.add(WebMvcLinkBuilder.linkTo(SupplierController.controller.getCategoryById(product.getSupplierID().getId())).withRel(LinkRelation.of("supplier")));
        });

        Link allDirectorsLink = WebMvcLinkBuilder.linkTo(controller.getAllProducts()).withSelfRel();
        return CollectionModel.of(products, allDirectorsLink).withFallbackType(Product.class);
    }

    @GetMapping("product/{id}")
    public Product getProductById(@PathVariable Integer id){
        return productRepo.findById(id).orElseThrow(() -> new CustomerNotFoundException(404, "Could not find product with id " + id + "."));
    }

    @PatchMapping("product")
    public void updateProduct(int id, BigDecimal unitPrice){
        Product product= productRepo.findById(id).get();
        product.setUnitPrice(unitPrice);
        productRepo.save(product);
    }

}
