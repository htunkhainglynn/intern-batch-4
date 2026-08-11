package org.wavemoney.intern4.payment.api.service;

import org.wavemoney.intern4.payment.api.dto.ProductRequest;
import org.wavemoney.intern4.payment.api.dto.ProductResponse;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
}
