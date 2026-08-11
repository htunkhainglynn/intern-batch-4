package org.wavemoney.intern4.payment.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wavemoney.intern4.payment.api.dto.ProductRequest;
import org.wavemoney.intern4.payment.api.dto.ProductResponse;
import org.wavemoney.intern4.payment.api.service.ProductService;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest product){
        ProductResponse productResponse = productService.createProduct(product);
        return ResponseEntity.ok(productResponse);
    }

}
