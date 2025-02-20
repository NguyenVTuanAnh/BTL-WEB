package food_store.example.foodstore.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class ProductController {


    @PostMapping("/products")
    public String getProductsList(Model model) {
        return "products";
    }

    @GetMapping("/products")
    public String getProductsPage(Model model) {
        return "products";
    }
}
