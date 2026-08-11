package org.wavemoney.intern4.payment.api.repo;

import org.springframework.stereotype.Repository;
import org.wavemoney.intern4.payment.api.entity.Product;

@Repository
public class ProductRepository {

    public Product createProduct(Product product){
        System.out.println("Saving to db" + product);
        return product;
    }
}
