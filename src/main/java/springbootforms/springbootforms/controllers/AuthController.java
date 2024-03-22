package springbootforms.springbootforms.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

@Controller
public class AuthController {
    @GetMapping("/authentication/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            @RequestParam(value = "expired", required = false) String expired,
            Model model) {

        if (error != null) {
            model.addAttribute("loginError", true);
        }

        if (logout != null) {
            model.addAttribute("logoutSuccess", true);
        }

        if (expired != null) {
            model.addAttribute("loginExpired", true);
        }

        return "login";
    }

    @GetMapping("/custom-logout")
    public String customLogout(HttpServletRequest request, HttpServletResponse response) {

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        request.getSession().invalidate();
        SecurityContextHolder.clearContext();

        return "redirect:/authentication/login?logout";
    }

}
