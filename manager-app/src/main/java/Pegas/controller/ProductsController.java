package Pegas.controller;

import Pegas.entity.Product;
import Pegas.errors.BadRequestException;
import Pegas.mapper.NewProduct;
import Pegas.services.ProductsRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/catalogue/products")
public class ProductsController {
    private final ProductsRestClient productService;

    @GetMapping("list")
    public String getProductsList(Model model, @RequestParam(name = "filter", required = false) String filter){
        model.addAttribute("products", productService.findAllProducts(filter));
        model.addAttribute("filter", filter);
        return "catalogue/products/list";
    }
    @GetMapping("create")
    public String getNewProductPage(){
        return "catalogue/products/new_product";
    }
    @PostMapping("create")
    public String createNewProduct(NewProduct newProduct, Model model) {
        try {
            Product product = productService.createProduct(newProduct.title(), newProduct.details());
            return "redirect:/api/v1/catalogue/products/%d".formatted(product.id());
        } catch (BadRequestException exception) {
            model.addAttribute("newProduct", newProduct);
            model.addAttribute("errors", exception.getErrors());
            return "catalogue/products/new_product";
        } catch (NoSuchElementException exception){
            model.addAttribute("updateProduct", newProduct);
            model.addAttribute("errors", exception.getMessage());
            return "catalogue/products/edit";
        }

    }
}
