package com.codegym.service.product;

import com.codegym.model.Product;
import com.codegym.model.dto.ProductCreateDTO;
import com.codegym.model.dto.ProductDTO;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface IProductService extends IGeneralService<Product> {

    Product create(ProductCreateDTO productCreateDTO);
    Product update(Product product, ProductCreateDTO productCreateDTO);
    List<Product> findAllByDeletedIsFalse();
}
