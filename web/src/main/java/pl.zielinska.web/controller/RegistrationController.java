package pl.zielinska.web.controller;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zielinska.trashAlert.dto.UserDto;
import pl.zielinska.trashAlert.service.RecaptchaService;
import pl.zielinska.trashAlert.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private RecaptchaService captchaService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("newUser", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@Valid @ModelAttribute("newUser") UserDto userDto,
                                  BindingResult bindingResult,
                                  @RequestParam(name="g-recaptcha-response") String recaptchaResponse,
                                  HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            log.debug("Registration form has errors");
            return "register";
        }

        String ip = request.getRemoteAddr();
        String captchaVerifyMessage = captchaService.verifyRecaptcha(ip, recaptchaResponse);

        if (StringUtils.isNotEmpty(captchaVerifyMessage)) {
            bindingResult.rejectValue("recaptcha", "recaptcha.error");
            return "register";
        }
        userService.registerNewUserAccount(userDto);
        log.debug("New user has been registered");

        return "redirect:/login";
    }


}
