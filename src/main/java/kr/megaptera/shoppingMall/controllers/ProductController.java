package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.dtos.ProductDto;
import kr.megaptera.shoppingMall.dtos.ProductListDto;
import kr.megaptera.shoppingMall.services.GetProductsService;
import kr.megaptera.shoppingMall.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final GetProductsService getProductsService;

    public ProductController(
        ProductService productService,
        GetProductsService getProductsService) {
        this.productService = productService;
        this.getProductsService = getProductsService;
    }

    @GetMapping("/{id}")
    public ProductDto productDetail(
        @PathVariable("id") Long productId) {

        return productService.detail(productId);
    }

    @GetMapping()
    public List<ProductListDto> products(
        @RequestParam(required = false, defaultValue = "1") Integer page
    ) {
        return getProductsService.getProducts(page);
    }
}
