package edu.kpi.hotel.controller;

import com.mongodb.DuplicateKeyException;
import edu.kpi.hotel.controller.dto.BalanceDto;
import edu.kpi.hotel.controller.dto.UserDto;
import edu.kpi.hotel.model.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        var balanceDto = new BalanceDto();
        userService.currentUser().ifPresent(user -> balanceDto.setBalance(user.getBalance()));

        model.addAttribute("balance", balanceDto);

        return "index";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUpForm(Model model) {
        model.addAttribute("user", new UserDto());

        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView signUp(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result) {
        try {
            if (!result.hasErrors())
                userService.createAccount(userDto.getUsername(), userDto.getEmail(), userDto.getPassword());
        } catch (DuplicateKeyException e) {
            result.reject("message.alreadyInUse", "Username or email already in use");
        }

        if (result.hasErrors())
            return new ModelAndView("signup", "user", userDto);
        else
            return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/refill", method = RequestMethod.POST)
    public ModelAndView refillBalance(
            @ModelAttribute("balance")
            @Valid BalanceDto balanceDto,
            BindingResult result) {
        var user = userService.currentUser().get();

        if (!result.hasErrors()) {
            userService.refillBalance(user, balanceDto.getAmount());
        }

        balanceDto.setBalance(user.getBalance());
        return new ModelAndView("index", "balance", balanceDto);
    }
}
