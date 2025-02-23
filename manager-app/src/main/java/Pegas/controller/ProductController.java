package Pegas.controller;

import Pegas.entity.Product;
import Pegas.errors.BadRequestException;
import Pegas.mapper.UpdateProduct;
import Pegas.services.ProductsRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/catalogue/products/{productId:\\d+}")
public class ProductController {
    private final ProductsRestClient productService;

    @ModelAttribute("product")
    public Product product(@PathVariable("productId") int productId){
        return productService.findProduct(productId).orElseThrow(()->
                new NoSuchElementException("catalogue.errors.product.not_found"));
    }
    @GetMapping()
    public String getProduct(){
        return "catalogue/products/item";
    }
    @GetMapping("edit")
    public String getProductEditPage (){
        return "catalogue/products/edit";
    }
    @PostMapping("edit")
    public String updateProduct (@ModelAttribute(name = "product", binding = false) Product product,
                                UpdateProduct updateProduct, Model model){
            try{
            productService.updateProduct(product.id(), updateProduct.title(), updateProduct.details());
            return "redirect:/api/v1/catalogue/products/%d".formatted(product.id());
            }catch (BadRequestException exception){
                model.addAttribute("updateProduct", updateProduct);
                model.addAttribute("errors", exception.getErrors());
                return "catalogue/products/edit";
            }catch (NoSuchElementException exception){
                model.addAttribute("updateProduct", updateProduct);
                model.addAttribute("errors", exception.getMessage());
                return "catalogue/products/edit";
            }
    }

    @PostMapping("delete")
    public String deleteProduct(@ModelAttribute("product") Product product){
        productService.deleteProduct(product.id());
        return "redirect:/api/v1/catalogue/products/list";
    }
}
