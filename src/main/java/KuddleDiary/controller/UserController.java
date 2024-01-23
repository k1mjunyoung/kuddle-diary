package KuddleDiary.controller;

import KuddleDiary.dto.UserDto;
import KuddleDiary.form.UserForm;
import KuddleDiary.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @PostMapping("/login")
    public void loginRequest() {
    }

    @GetMapping("/signup")
    public String signup(UserForm userForm) {
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String signupRequest(@Valid UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/signup";
        }

        UserDto user = new UserDto(userForm.getId(), userForm.getPassword(), userForm.getName(), userForm.getEmail());

        try {
            userService.create(user);
        } catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "auth/signup";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "auth/signup";
        }

        return "redirect:/";
    }
}
