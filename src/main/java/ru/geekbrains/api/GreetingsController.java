package ru.geekbrains.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.geekbrains.model.Student;

import java.util.List;

@Controller
public class GreetingsController {

    @GetMapping ("/hello")
    @ResponseBody
    public String helloWorld (@RequestParam String name) {
        return "Hello " + name + " !";
    }

    @GetMapping ("/home")
    public String home (@RequestParam(required = false) String name, Model model) {
        if (name != null) {
            model.addAttribute("name", name);
        } else {
            model.addAttribute("name", "Java");
        }
        return "home";

    }
}
