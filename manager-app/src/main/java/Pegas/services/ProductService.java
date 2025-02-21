package Pegas.services;

import Pegas.entity.Product;
import Pegas.mapper.NewProduct;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProducts();

    Product createProduct(String title, String details);

    Optional<Product> findProduct(int productId);

    void updateProduct(Integer id, String title, String details);

    void deleteProduct(Integer id);
}
