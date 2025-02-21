package Pegas.controller;

import Pegas.entity.Product;
import Pegas.mapper.UpdateProduct;
import Pegas.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/catalogue/products/{productId:\\d+}")
public class ProductController {
    private final ProductService productService;
    private final MessageSource messageSource;

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
                                 @Valid UpdateProduct updateProduct,
                                 BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("updateProduct", updateProduct);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage).toList());
            return "catalogue/products/edit";
        }else {
            productService.updateProduct(product.getId(), updateProduct.title(), updateProduct.details());
            return "redirect:/api/v1/catalogue/products/%d".formatted(product.getId());
        }
    }
    @PostMapping("delete")
    public String deleteProduct(@ModelAttribute("product") Product product){
        productService.deleteProduct(product.getId());
        return "redirect:/api/v1/catalogue/products/list";
    }
    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException exception, Model model,Locale locale){
        model.addAttribute("error", this.messageSource.getMessage(exception.getMessage(),new Object[0],
                exception.getMessage(), locale));
        return "errors/404";
    }
}
