package com.spring.controllers;

import com.spring.beans.OrderForm;
import com.spring.entities.Account;
import com.spring.entities.Order;
import com.spring.entities.OrderDetail;
import com.spring.services.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class CartController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private CartService cartService;
    @Autowired
    private SessionService sessionService;

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("cart", cartService);
        return "cart";
    }

    @RequestMapping("/cart/add/{id}")
    public String add(@PathVariable("id") Integer id) {
        cartService.add(id);
        return "redirect:/cart";
    }

    @RequestMapping("/cart/remove/{id}")
    public String remove(@PathVariable("id") Integer id) {
        cartService.remove(id);
        return "redirect:/cart";
    }

    @RequestMapping("/cart/update/{id}")
    public String update(@PathVariable("id") Integer id, @RequestParam("quantity") Integer quantity) {
        cartService.update(id, quantity);
        return "redirect:/cart";
    }

    @RequestMapping("/cart/clear")
    public String clear() {
        cartService.clear();
        return "redirect:/cart";
    }

    @GetMapping("/cart/checkout")
    public String checkoutForm(Model model) {
        if (cartService.getItems().size() <= 0) {
            return "redirect:/cart";
        }
        OrderForm form = new OrderForm();
        model.addAttribute("data", form);
        model.addAttribute("cart", cartService);
        return "cart-checkout";
    }

    @RequestMapping("/cart/checkout")
    public String checkoutHandler(Model model, @Valid @ModelAttribute("data") OrderForm form, BindingResult result) {
        if (cartService.getItems().size() <= 0) {
            return "redirect:/cart";
        }
        if (!result.hasErrors()) {
            String username = sessionService.get("username");
            Account account = accountService.findByUsername(username);
            // save order
            Order order = orderService.create(form.getAddress(), account);
            if (order == null) {
                return "redirect:/cart";
            }
            List<OrderDetail> orderDetails = cartService.toOrderDetails(order);
            List<OrderDetail> orderDetailsNew = orderDetailService.saveAll(orderDetails);
            if (orderDetailsNew.size() == orderDetails.size()) {
                cartService.updateQuantityProducts(orderDetailsNew);
                cartService.clear();
                return "redirect:/user/order-history";
            }
            return "redirect:/cart";
        }
        model.addAttribute("cart", cartService);
        return "cart-checkout";
    }
}
