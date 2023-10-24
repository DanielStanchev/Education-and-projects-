package com.example.coffeeshopweb.repository;

import com.example.coffeeshopweb.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("select o from Order o ORDER BY o.price desc ")
    List<Order> findAllByPriceDesc();

}
