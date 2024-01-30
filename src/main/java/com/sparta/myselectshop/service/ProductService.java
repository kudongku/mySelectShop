package com.sparta.myselectshop.service;

import com.sparta.myselectshop.dto.ProductMypriceRequestDto;
import com.sparta.myselectshop.dto.ProductRequestDto;
import com.sparta.myselectshop.dto.ProductResponseDto;
import com.sparta.myselectshop.entity.Product;
import com.sparta.myselectshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private static final int MIN_MY_PRICE = 100;
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = new Product(productRequestDto);
        productRepository.save(product);
        return new ProductResponseDto(product);
    }

    @Transactional
    public ProductResponseDto updateProduct(Long id, ProductMypriceRequestDto productMypriceRequestDto) {
        int myprice = productMypriceRequestDto.getMyprice();

        if(myprice<MIN_MY_PRICE){
            throw new IllegalArgumentException("유효하지 않은 관심 가격입니다. 최소 "+MIN_MY_PRICE + "원 이상으로 설정해 주세요.");
        }

        Product product = findById(id);

        product.update(productMypriceRequestDto);

        return new ProductResponseDto(product);
    }

    private Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("해당 상품을 찾을 수 없습니다.")
        );
    }

    public List<ProductResponseDto> getProducts() {
        List<Product> productList = productRepository.findAll();
        return productList
                .stream()
                .map(product -> new ProductResponseDto(product))
                .toList();
    }
}
