package com.nhom3.SecurityConfig;

import com.nhom3.AccountSevice.AccountService;
import com.nhom3.Entity.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    private AccountService accountService; // Sử dụng AccountService

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception)
            throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String errorMessage = "Lỗi xác thực";

        // Kiểm tra tài khoản dựa trên username
        Account account = accountService.findAccountByUsername(username);
        if (account == null) {
            errorMessage = "Không tìm thấy người dùng trong hệ thống";
        } else if (exception instanceof BadCredentialsException) {
            errorMessage = "Mật khẩu không chính xác";
        }

        response.getWriter().write("{\"error\": \"" + errorMessage + "\"}");
    }
}
