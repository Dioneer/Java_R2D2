package Pegas.services;

import Pegas.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductsRestClient {

    List<Product> findAllProducts();
    Product createProduct(String title, String details);
    Optional<Product> findProduct(int productId);
    ResponseEntity<Void> updateProduct(int productId, String title, String details);
    ResponseEntity<Void> deleteProduct(int productId);
}
