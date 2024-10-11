package com.nhom3.Servicelmpl;

import com.nhom3.AccountRepository.AccountRepository;
import com.nhom3.AccountRepository.RoleRepository;
import com.nhom3.AccountSevice.AccountService;
import com.nhom3.Entity.Account;
import com.nhom3.Entity.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AccountServicelmpl implements AccountService {
    private final AccountRepository repo;
    private final RoleRepository roleRepository; // Thêm repository để truy vấn Role
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AccountServicelmpl(AccountRepository repo, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.roleRepository = roleRepository; // Khởi tạo RoleRepository
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Account> findAccountAll() {
        return repo.findAll(); // Trả về danh sách tất cả tài khoản
    }

    @Override
    public Account findAccountByUsername(String username) {
        return repo.findByUsername(username); // Tìm tài khoản theo tên người dùng
    }

    @Override
    public Account saveAccount(Account account) {
        return repo.save(account); // Lưu tài khoản vào cơ sở dữ liệu
    }

    @Override
    public Account register(Account account) {
        Account existingAccount = repo.findByUsername(account.getUsername());
        if (existingAccount != null) {
            return null; // Nếu tài khoản đã tồn tại, trả về null
        }

        String encodedPassword = passwordEncoder.encode(account.getPassword());
        System.out.println("Mật khẩu mã hóa: " + encodedPassword); // Log mật khẩu đã mã hóa
        account.setPassword(encodedPassword);

        // Tìm vai trò mặc định "user"
        Role userRole = roleRepository.findByDescription("user"); // Sử dụng roleRepository
        if (userRole != null) {
            account.setRoles(Collections.singleton(userRole)); // Gán vai trò "user" cho tài khoản
        }

        return repo.save(account); // Lưu tài khoản vào cơ sở dữ liệu
    }



    @Override
    public boolean authenticate(String username, String password) {
        Account account = repo.findByUsername(username);
        if (account != null) {
            boolean matches = passwordEncoder.matches(password.trim(), account.getPassword());
            if (matches) {
                System.out.println("Mật khẩu đúng."); // Log thông báo mật khẩu đúng
            } else {
                System.out.println("Mật khẩu không đúng."); // Log thông báo mật khẩu không đúng
            }
            return matches; // Trả về kết quả so sánh
        }
        System.out.println("Tài khoản không tồn tại."); // Log thông báo tài khoản không tồn tại
        return false; // Tài khoản không tồn tại
    }

}
