package com.example.coffeeshopweb.service;

import com.example.coffeeshopweb.model.dto.OrderAddDto;
import com.example.coffeeshopweb.model.dto.OrderViewDto;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

public interface OrderService {

    String add(OrderAddDto orderAddDto, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    List<OrderViewDto> findAllOrdersByPriceDesc();

    void readyOrder(Long id);
}
