package com.example.coffeeshopweb.service.impl;

import com.example.coffeeshopweb.model.dto.OrderAddDto;
import com.example.coffeeshopweb.model.dto.OrderViewDto;
import com.example.coffeeshopweb.model.entity.Order;
import com.example.coffeeshopweb.repository.OrderRepository;
import com.example.coffeeshopweb.service.CategoryService;
import com.example.coffeeshopweb.service.OrderService;
import com.example.coffeeshopweb.service.UserService;
import com.example.coffeeshopweb.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final UserService userService;
    private final CategoryService categoryService;


    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, CurrentUser currentUser, UserService userService,
                            CategoryService categoryService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public String add(OrderAddDto orderAddDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("orderAddDto",orderAddDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderAddDto",bindingResult);

            return "redirect:add";
        }

        Order orderToSave = modelMapper.map(orderAddDto,Order.class);
        orderToSave.setEmployee(userService.findById(currentUser.getId()));
        orderToSave.setCategory(categoryService.findByCategoryNameEnum(orderAddDto.getCategory()));

        orderRepository.save(orderToSave);

        return "redirect:/";
    }

    @Override
    public List<OrderViewDto> findAllOrdersByPriceDesc() {
        return orderRepository.findAllByPriceDesc().stream().map(order ->
            modelMapper.map(order,OrderViewDto.class)).collect(Collectors.toList());
    }

    @Override
    public void readyOrder(Long id) {
        orderRepository.deleteById(id);
    }

}
