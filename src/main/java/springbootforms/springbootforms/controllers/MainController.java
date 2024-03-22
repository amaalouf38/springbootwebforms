package springbootforms.springbootforms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/students/")
    public String showStudentForm(Model model) {
        return "student";
    }

    @GetMapping("/")
    public String showMainForm(Model model) {
        return "main";
    }
}
