package pl.zielinska.trashAlert.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.zielinska.trashAlert.DTO.UserDto;
import pl.zielinska.trashAlert.service.UserService;

import javax.validation.Valid;

@Slf4j
@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("newUser", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@Valid @ModelAttribute("newUser") UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.debug("Registration form has errors");
            return "register";
        }
        userService.registerNewUserAccount(userDto);
        log.debug("New user has been registered");
        return "redirect:/login";
    }


}
