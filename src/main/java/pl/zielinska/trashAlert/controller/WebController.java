package pl.zielinska.trashAlert.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebController {
    @RequestMapping("/")
    public String home(Model model, HttpServletRequest request) {
        model.addAttribute("username", request.getUserPrincipal().getName());
        return "index";
    }
}
