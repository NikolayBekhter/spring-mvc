package ru.geekbrains.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.model.Product;
import ru.geekbrains.repository.ProductRepository;

import java.util.Objects;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public String getProducts (Model model) {
        model.addAttribute("productList", repository.getProducts());
        return "products";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String getProduct(@PathVariable int id) {
        Product product = repository.getProducts()
                .stream()
                .filter(it -> Objects.equals(id, it.getId()))
                .findFirst()
                .orElse(null);
        return product.getTitle() + " " + product.getCost() + " p.";
        }

    @GetMapping("/add_product")
    public String addProduct() {
        return "add_product";
    }

    @PostMapping("/add_product")
    public String addProduct(@RequestParam String title, @RequestParam String cost) {
        repository.addProduct(title, Integer.parseInt(cost));
        return "redirect:/products";
    }

    @GetMapping("/delete")
    public String deleteProduct () {
        return "delete";
    }

    @PostMapping("/delete")
    public String deleteProduct (@RequestParam String id) {
        repository.deleteProduct(Integer.parseInt(id));
        return "redirect:/products";
    }

}
