package com.nhom3.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests(authz -> authz
//                .requestMatchers("/admin/**").hasRole("ADMIN") // Chỉ ADMIN mới truy cập được
//                .requestMatchers("/nhanvien/**").hasRole("NHANVIEN") // Chỉ nhân viên mới truy cập được
//                .requestMatchers("/khachhang/**").hasRole("KHACHHANG") // Chỉ khách hàng mới truy cập được
//                .requestMatchers("/resister","/login","/index", "/css/**", "/js/**", "/images/**").permitAll() // Cho phép truy cập không cần xác thực
//                .anyRequest().authenticated() // Các yêu cầu khác cần xác thực
        		.requestMatchers("/admin/**").hasRole("ADMIN")
        		.requestMatchers("/nhanvien/**").hasRole("NHANVIEN")
        		.requestMatchers("/khachhang/**").hasRole("KHACHHANG")
        		.requestMatchers("/register", "/login", "/index", "/css/**", "/js/**", "/images/**").permitAll()
        		.anyRequest().authenticated()

            )
            .formLogin(dangnhap -> dangnhap
                .loginPage("/login")  // Đường dẫn đến trang đăng nhập
                .defaultSuccessUrl("/index", true)  // Chuyển đến trang index sau khi đăng nhập thành công
                .permitAll()
                .failureHandler(authenticationFailureHandler())//Trả về thông báo nếu có sai sót trong quá trình xác thực
            )
            .logout(logout -> logout
            	    .logoutUrl("/logout")
            	    .logoutSuccessUrl("/login?logout") // Chuyển hướng đến trang đăng nhập với thông báo đăng xuất thành công
            	    .permitAll()
            	);

        return http.build();
    }
    
}
