//package com.nhom3.AccountRepository;
//
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import com.nhom3.Entity.Account;
//
//@Repository
//public interface AccountRepository extends JpaRepository<Account, Integer> {
//    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Account a WHERE a.username = :username AND a.password = :password")
//    boolean findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
//
//    Account findByUsername(String username);
//}
//
package com.nhom3.AccountRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nhom3.Entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUsername(String username); // Tìm tài khoản theo tên người dùng
}
