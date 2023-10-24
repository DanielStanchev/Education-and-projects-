package com.example.coffeeshopweb.web;

import com.example.coffeeshopweb.model.dto.OrderAddDto;
import com.example.coffeeshopweb.repository.OrderRepository;
import com.example.coffeeshopweb.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/add")
    private String add() {
        return "order-add";
    }


    @PostMapping("/add")
    private String addConfirm(@Valid OrderAddDto orderAddDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        return orderService.add(orderAddDto, bindingResult, redirectAttributes);
    }

    @GetMapping("/ready/{id}")
    private String ready(@PathVariable Long id) {

        orderService.readyOrder(id);

        return "redirect:/";
    }


    @ModelAttribute
    public OrderAddDto orderAddDto() {
        return new OrderAddDto();
    }


}
