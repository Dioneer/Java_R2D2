package Pegas.controller;

import Pegas.entity.Product;
import Pegas.mapper.NewProduct;
import Pegas.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class ProductsRestController {
    private final ProductService productService;
    private final MessageSource messageSource;

    @GetMapping
    public List<Product> findProducts(){
        return productService.findAllProducts();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createProduct(@Valid @RequestBody NewProduct newProduct,
                                           BindingResult bindingResult, UriComponentsBuilder uri,
                                           Locale locale){
        if(bindingResult.hasErrors()){
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                    Objects.requireNonNull(messageSource.getMessage("errors.403.title", new Object[0],
                            "errors.403.title", locale)));
            problemDetail.setProperty("errors", bindingResult.getAllErrors().stream()
                    .map(MessageSourceResolvable::getDefaultMessage).toList());
            return ResponseEntity.badRequest().body(problemDetail);
        }else {
            Product product = productService.createProduct(newProduct.title(), newProduct.details());
            return ResponseEntity.created(uri.replacePath("/catalogue-api/products/{productId}")
                            .build(Map.of("product", product.getId())))
                    .body(product);
        }
    }
}
