package com.nhom3.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhom3.AccountSevice.AccountService;
import com.nhom3.Entity.Account;

@Controller
public class AuthController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String showLoginPage() {
    	System.out.println("đăng nhập");
        return "login"; // Trả về trang login
    }

   
    @GetMapping("/index")
    public String showIndexPage() {
    	System.out.println("đã vào page đăng nhập");
        return "index"; // Trả về trang index
    }
    @GetMapping("/register")
    public String showregister() {
    	System.out.println("đã vào page đăng ký");
        return "register"; // Trả về trang đăng ký
    }
    

}
