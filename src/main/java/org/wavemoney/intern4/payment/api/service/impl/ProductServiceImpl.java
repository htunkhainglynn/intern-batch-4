package org.wavemoney.intern4.payment.api.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.wavemoney.intern4.payment.api.dto.ProductRequest;
import org.wavemoney.intern4.payment.api.dto.ProductResponse;
import org.wavemoney.intern4.payment.api.entity.Product;
import org.wavemoney.intern4.payment.api.repo.ProductRepository;
import org.wavemoney.intern4.payment.api.service.ProductService;

@Service
@AllArgsConstructor

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;

    @Override
    public ProductResponse createProduct (ProductRequest productRequest){

        //DTO to Entity Mapping
        Product product = mapDtoToEntity(productRequest);

        //To DB
        Product savedProduct = productRepo.createProduct(product);

        //Entity to DTO Mapping
        ProductResponse productResponse = mapEntitytoDto(savedProduct);

        return productResponse;
    }

    private Product mapDtoToEntity(ProductRequest productRequest) {
        Product product = new Product();
        product.setId("123");
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());

        return product;
    }

    private ProductResponse mapEntitytoDto(Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());

        return productResponse;
    }
}
