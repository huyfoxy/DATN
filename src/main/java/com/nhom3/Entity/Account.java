package com.nhom3.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "Accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Sử dụng kiểu Long cho ID

    private String username;
    private String password;
    private String fullname;
    private String photo;
    private String email;

    @ManyToMany(fetch = FetchType.EAGER) // Tải dữ liệu liên kết ngay lập tức
    @JoinTable(
        name = "RoleDetails", // Tên bảng liên kết
        joinColumns = @JoinColumn(name = "account_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles; // Danh sách vai trò của tài khoản
}
