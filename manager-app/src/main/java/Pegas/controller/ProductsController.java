package Pegas.controller;

import Pegas.entity.Product;
import Pegas.mapper.NewProduct;
import Pegas.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/catalogue/products")
public class ProductsController {
    private final ProductService productService;

    @GetMapping("list")
    public String getProductsList(Model model){
        model.addAttribute("products", productService.findAllProducts());
        return "catalogue/products/list";
    }
    @GetMapping("create")
    public String getNewProductPage(){
        return "catalogue/products/new_product";
    }
    @PostMapping("create")
    public String createNewProduct(@Valid NewProduct newProduct, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("newProduct", newProduct);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage).toList());
            return "catalogue/products/new_product";
        }else {
            Product product = productService.createProduct(newProduct.title(), newProduct.details());
            return "redirect:/api/v1/catalogue/products/%d".formatted(product.getId());
        }
    }
}
