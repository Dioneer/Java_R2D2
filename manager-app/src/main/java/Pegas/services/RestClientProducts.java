package Pegas.services;

import Pegas.entity.Product;
import Pegas.errors.BadRequestException;
import Pegas.mapper.NewProduct;
import Pegas.mapper.UpdateProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
public class RestClientProducts implements ProductsRestClient{
    private final RestClient restClient;
    private static final ParameterizedTypeReference<List<Product>> TYPE_REFERENCE =
            new ParameterizedTypeReference<>() {
            };

    @Override
    public List<Product> findAllProducts(String filter) {
        return restClient
                .get()
                .uri("/catalogue-api/products?filter={filter}", filter)
                .retrieve()
                .body(TYPE_REFERENCE);
    }

    @Override
    public Product createProduct(String title, String details) {
        try {
            return restClient
                    .post()
                    .uri("/catalogue-api/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new NewProduct(title, details))
                    .retrieve()
                    .body(Product.class);
        }catch (HttpClientErrorException.BadRequest exception){
            ProblemDetail problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
            assert problemDetail != null;
            throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));
        }
    }

    @Override
    public Optional<Product> findProduct(int productId) {
        try {
            return Optional.ofNullable(restClient.get().
                    uri("/catalogue-api/product/{productId}", productId)
                    .retrieve()
                    .body(Product.class));
        }catch(HttpClientErrorException.NotFound exception){
            return Optional.empty();
        }
    }

    @Override
    public ResponseEntity<Void> updateProduct(int productId, String title, String details) {
        try {
            return restClient
                    .patch()
                    .uri("/catalogue-api/product/{productId}", productId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new UpdateProduct(title, details))
                    .retrieve()
                    .toBodilessEntity();
        }catch (HttpClientErrorException.BadRequest exception){
            ProblemDetail problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
            assert problemDetail != null;
            throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));
        }
    }

    @Override
    public ResponseEntity<Void> deleteProduct(int productId) {
        try {
            return restClient
                    .delete()
                    .uri("/catalogue-api/product/{productId}", productId)
                    .retrieve()
                    .toBodilessEntity();
        }catch (HttpClientErrorException.NotFound exception){
            ProblemDetail problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
            assert problemDetail != null;
            throw new NoSuchElementException(problemDetail.getDetail());
        }
    }
}
