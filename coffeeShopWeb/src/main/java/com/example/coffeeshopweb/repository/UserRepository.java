package com.example.coffeeshopweb.repository;

import com.example.coffeeshopweb.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByUsernameAndPassword(String username, String password);

    @Query("select u from User u order by size(u.orders) desc ")
    List<User> findAllByOrdersDesc();

}
