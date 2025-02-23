package Pegas.controller;

import Pegas.entity.Product;
import Pegas.mapper.NewProduct;
import Pegas.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/catalogue-api/products")
public class ProductsRestController {
    private final ProductService productService;

    @GetMapping
    public List<Product> findProducts(@RequestParam(name = "filter", required = false) String filter){
        return StreamSupport.stream(productService.findAllProducts(filter).spliterator(), false).toList();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createProduct(@Valid @RequestBody NewProduct newProduct,
                                           BindingResult bindingResult,
                                           UriComponentsBuilder uri) throws BindException {
        if(bindingResult.hasErrors()){
            if(bindingResult instanceof  BindException bindException){
                throw bindException;
            } else{
                throw new BindException(bindingResult);
            }
        }else {
            Product product = productService.createProduct(newProduct.title(), newProduct.details());
            return ResponseEntity.created(uri.replacePath("/catalogue-api/products/{productId}")
                            .build(Map.of("productId", product.getId())))
                    .body(product);
        }
    }
}
