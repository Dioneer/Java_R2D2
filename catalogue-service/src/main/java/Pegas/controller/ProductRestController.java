package Pegas.controller;

import Pegas.entity.Product;
import Pegas.mapper.UpdateProduct;
import Pegas.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/catalogue-api/product/{productId:\\d+}")
public class ProductRestController {
    private final ProductService productService;

    @ModelAttribute
    public Product getProduct(@PathVariable("productId") int productId){
        return productService.findProduct(productId).orElseThrow(()->
                new NoSuchElementException("catalogue.errors.product.not_found"));
    }

    @GetMapping
    public Product findProduct(@ModelAttribute("product") Product product){
        return product;
    }

    @PatchMapping
    public ResponseEntity<?> updateProduct(@PathVariable("productId") int productId, @Valid
                                              @RequestBody UpdateProduct updateProduct,
                                           BindingResult bindingResult) throws BindException {
        if(bindingResult.hasErrors()){
            if(bindingResult instanceof  BindException bindException){
                throw bindException;
            } else{
                throw new BindException(bindingResult);
            }
        }else{
            productService.updateProduct(productId, updateProduct.title(), updateProduct.details());
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") int product){
        productService.deleteProduct(product);
        return ResponseEntity.ok().build();
    }
}
