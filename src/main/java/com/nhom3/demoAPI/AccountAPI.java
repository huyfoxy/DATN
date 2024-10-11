package com.nhom3.demoAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nhom3.AccountSevice.AccountService;
import com.nhom3.Entity.Account;

@RestController
public class AccountAPI {

    @Autowired
    AccountService accountService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/postlogin")
    public ResponseEntity<String> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        // Thực hiện xác thực đăng nhập
        boolean isAuthenticated = accountService.authenticate(username, password);
        if (isAuthenticated) {
            return ResponseEntity.ok("Đăng nhập thành công");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tên đăng nhập hoặc mật khẩu không đúng!");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestParam("username") String username,
            @RequestParam("fullname") String fullname,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("photo") MultipartFile photo) { // Thêm tham số cho ảnh

        // Kiểm tra thông tin tài khoản
        if (username == null || username.isEmpty() ||
            password == null || password.isEmpty() ||
            email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("Thông tin tài khoản không hợp lệ");
        }

        try {
            // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
            String encodedPassword = passwordEncoder.encode(password);
            Account account = new Account();
            account.setUsername(username);
            account.setFullname(fullname);
            account.setEmail(email);
            account.setPassword(encodedPassword);

            // Xử lý ảnh nếu cần
            if (photo != null && !photo.isEmpty()) {
                // Xử lý lưu trữ ảnh tại đây (nếu bạn cần lưu ảnh)
            }

            // Thực hiện đăng ký tài khoản
            Account registeredAccount = accountService.register(account);
            if (registeredAccount != null) {
                return ResponseEntity.ok("Tài khoản đã đăng ký thành công");
            } else {
                return ResponseEntity.badRequest().body("Tên người dùng đã tồn tại");
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra thông báo lỗi để dễ dàng gỡ lỗi
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đăng ký tài khoản không thành công");
        }
    }
}
