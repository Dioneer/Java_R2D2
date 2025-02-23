package Pegas.repository;

import Pegas.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Integer> {

//    @Query(value="select p from Product p where p.title ilike :filter")
//    @Query(value="select * from catalogue.t_product where c_title ilike :filter", nativeQuery = true)
    @Query(name = "Product.findAllByTitleLikeIgnoreCase", nativeQuery = true)
    Iterable<Product> findAllByTitleLikeIgnoreCase(String filter);

}
