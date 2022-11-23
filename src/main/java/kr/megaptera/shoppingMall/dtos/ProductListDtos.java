package kr.megaptera.shoppingMall.dtos;

import java.util.List;

public class ProductListDtos {
    private final List<ProductListDto> productListDtos;
    private final int pages;

    public ProductListDtos(List<ProductListDto> productListDtos, int pages) {
        this.productListDtos = productListDtos;
        this.pages = pages;
    }

    public List<ProductListDto> getProducts() {
        return productListDtos;
    }

    public int getPages() {
        return pages;
    }
}
