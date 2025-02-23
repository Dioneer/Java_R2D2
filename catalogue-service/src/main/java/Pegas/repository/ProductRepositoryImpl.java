package Pegas.repository;

import Pegas.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.IntStream;

//@Repository
//public class ProductRepositoryImpl implements ProductRepository{
//    private final List<Product> productList = Collections.synchronizedList(new LinkedList<>());
//
//    public ProductRepositoryImpl() {
//        IntStream.range(1,4).forEach(i-> productList.add(new Product(i, "ticket №%d".formatted(i),
//                "ticket's №%d description".formatted(i))));
//    }
//
//    @Override
//    public List<Product> findAll() {
//        return Collections.unmodifiableList(productList);
//    }
//
//    @Override
//    public Product save(Product product) {
//        product.setId(productList.stream().max(Comparator.comparingInt(Product::getId))
//                .map(Product::getId)
//                .orElse(0)+1);
//        productList.add(product);
//        return product;
//    }
//
//    @Override
//    public Optional<Product> findById(int productId) {
//        return this.productList.stream().filter(i->i.getId()==productId).findFirst();
//    }
//
//    @Override
//    public void deleteById(Integer id) {
//        productList.removeIf(i->Objects.equals(id, i.getId()));
//    }
//}
