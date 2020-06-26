package com.myjavablog.controller;

import com.myjavablog.model.Contact;
import com.myjavablog.model.Role;
import com.myjavablog.model.TypeContact;
import com.myjavablog.model.User;
import com.myjavablog.repository.UserRepository;
import com.myjavablog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
    @RequestMapping(value="/user/index_user", method = RequestMethod.GET)
    public ModelAndView userIndex(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", userService.findAllByOrderByIdAsc());
        modelAndView.setViewName("index_user");
        return modelAndView;
    }


    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }
//wdwerw pruebaaaaa

    @GetMapping("/users/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<User> user = userService.findById(id);
        user.get().setPassword("");
        model.addAttribute("user", user.get());
        return "update-user";
    }
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user, BindingResult result,
                                Model model) {


        //if (result.hasErrors()) {
        //    //user.setId(id);
        //    return "update-user";
        //}

        Optional<User> userOriginal = userService.findById(id);

        String role="";

        for (Role temp : userOriginal.get().getRoles()) {
            role = (temp.getRole());
        }

        userOriginal.get().setPassword(user.getPassword());

        userService.saveUser(userOriginal.get(), role);
        model.addAttribute("users", userService.findAllByOrderByIdAsc());
        return "index_user";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        Optional<User> user = userService.findById(id);
        userService.deleteUser(user.get());
        model.addAttribute("users", userService.findAllByOrderByIdAsc());
        return "index_user";
    }


    //wdwerw pruebaaaaa

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {

            User firstUser = userService.findFirstByOrderByIdAsc();
            if (firstUser==null){
                userService.saveUser(user, "ADMIN");
            }else{
                userService.saveUser(user, "USER");

            }
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    @RequestMapping("/user/cancelar")
    public ModelAndView cancelar() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", userService.findAllByOrderByIdAsc());
        modelAndView.setViewName("index_user");
        return modelAndView;
    }

    @RequestMapping(value="/resolve", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        String role = "";
        for (Role temp : user.getRoles()) {
            role = (temp.getRole());
        }

        if (role=="ADMIN") {
            modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
            modelAndView.addObject("adminMessage","This Page is available to Users with Admin Role");
            modelAndView.setViewName("admin/adminHome");
            return modelAndView;

        }else{

            modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
            modelAndView.addObject("userMessage","This Page is available to Users with User Role");
            modelAndView.setViewName("user/userHome");
            return modelAndView;
        }

    }

    @RequestMapping(value="/user/userHome", method = RequestMethod.GET)
    public ModelAndView user(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("userMessage","This Page is available to Users with User Role");
        modelAndView.setViewName("user/userHome");
        return modelAndView;
    }

}
