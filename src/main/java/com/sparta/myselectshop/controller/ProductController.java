package com.sparta.myselectshop.controller;

import com.sparta.myselectshop.dto.ProductMypriceRequestDto;
import com.sparta.myselectshop.dto.ProductRequestDto;
import com.sparta.myselectshop.dto.ProductResponseDto;
import com.sparta.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto){
        return productService.createProduct(productRequestDto);
    }

    @PutMapping("/products/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto productMypriceRequestDto){
        return productService.updateProduct(id, productMypriceRequestDto);
    }
}
