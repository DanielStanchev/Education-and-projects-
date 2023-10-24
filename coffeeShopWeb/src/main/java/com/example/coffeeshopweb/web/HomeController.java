package com.example.coffeeshopweb.web;

import com.example.coffeeshopweb.model.dto.OrderViewDto;
import com.example.coffeeshopweb.service.OrderService;
import com.example.coffeeshopweb.service.UserService;
import com.example.coffeeshopweb.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final OrderService orderService;
    private final UserService userService;

    public HomeController(CurrentUser currentUser, OrderService orderService, UserService userService) {
        this.currentUser = currentUser;
        this.orderService = orderService;
        this.userService = userService;
    }


    @GetMapping()
    public String index(Model model) {

        if (currentUser.getId() == null) {
            return "index";
        }

        List<OrderViewDto> orders = orderService.findAllOrdersByPriceDesc();

        model.addAttribute("orders", orders);
        model.addAttribute("totalTime", orders.stream().map(orderViewDto -> orderViewDto.getCategory().getNeededTime())
            .reduce(Integer::sum)
            .orElse(0)
        );

        model.addAttribute("users",userService.findAllUserAndCountOfOrdersDesc());

        return "home";

    }

}
