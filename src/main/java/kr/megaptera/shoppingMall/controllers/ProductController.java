package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.dtos.ProductDto;
import kr.megaptera.shoppingMall.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductDto productDetail(
        @PathVariable("id") Long productId) {

        return productService.detail(productId);
    }
}
