package com.nhom3.AccountRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom3.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByDescription(String description); // Tìm vai trò theo mô tả
}
