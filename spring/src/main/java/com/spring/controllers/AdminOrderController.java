package com.spring.controllers;

import com.spring.beans.AdminOrderForm;
import com.spring.entities.Account;
import com.spring.entities.Order;
import com.spring.entities.OrderDetail;
import com.spring.services.AccountService;
import com.spring.services.ExcelService;
import com.spring.services.OrderService;
import com.spring.services.ParamService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
@Controller
@RequestMapping("/admin")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ParamService paramService;
    @Autowired
    private ExcelService excelService;

    @GetMapping("/orders")
    public String tableProduct(Model model) {
        int currPage = paramService.getInt("p", 1);
        if (currPage <= 0) {
            return "redirect:/admin/accounts";
        }
        Pageable pageable = PageRequest.of(currPage - 1, 5);
        Page<Order> page = orderService.findAll(pageable);
        model.addAttribute("page", page);
        return "admin/orders/table";
    }

    @GetMapping("/orders/create")
    public String createForm(Model model) {
        AdminOrderForm form = new AdminOrderForm();
        model.addAttribute("data", form);
        return "admin/orders/create";
    }

    @GetMapping("/orders/update/{id}")
    public String updateForm(Model model, @PathVariable("id") Integer id) {
        Order order = orderService.findById(id);
        if (order == null) {
            return "redirect:/admin/orders";
        }
        model.addAttribute("data", order);
        return "admin/orders/update";
    }

    @PostMapping("/orders/update")
    public String updateHandler(Model model, RedirectAttributes redirect, @Valid @ModelAttribute("data") AdminOrderForm form,
                                BindingResult result) {
        if (form.getId() == null) {
            return "redirect:/admin/orders";
        }
        if (!result.hasErrors()) {
            Order order = orderService.findById(form.getId());
            order.setAccount(form.getAccount());
            order.setAddress(form.getAddress());
            if (orderService.save(order) != null) {
                redirect.addFlashAttribute("message", "Cập nhật đơn hàng thành công");
                return "redirect:/admin/orders/update/" + form.getId();
            } else {
                redirect.addFlashAttribute("message", "Cập nhật đơn hàng thất bại");
                return "redirect:/admin/orders/update/" + form.getId();
            }
        }
        return "admin/orders/update";
    }

    @PostMapping("/orders/create")
    public String createHandler(Model model, RedirectAttributes redirect, @Valid @ModelAttribute("data") AdminOrderForm form,
                                BindingResult result) {
        if (!result.hasErrors()) {
            Order order = orderService.create(form.getAddress(), form.getAccount());
            if (order != null) {
                redirect.addFlashAttribute("message", "Thêm đơn hàng thành công");
                return "redirect:/admin/orders/create";
            } else {
                redirect.addFlashAttribute("message", "Thêm đơn hàng thất bại");
                return "redirect:/admin/orders/create";
            }
        }
        return "admin/orders/create";
    }

    @GetMapping("/orders/detail")
    public String orderHistoryDetail(Model model) {
        int id = paramService.getInt("id", -1);
        if (id == -1) {
            return "redirect:/admin/orders";
        }
        Order order = orderService.findById(id);
        if (order == null) {
            return "redirect:/admin/orders";
        }
        List<OrderDetail> orderDetails = order.getOrderDetails();
        model.addAttribute("orderDetails", orderDetails);
        return "admin/orders/detail";
    }

    @GetMapping("/orders/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        orderService.deleteById(id);
        return "redirect:/admin/orders";
    }

    @GetMapping("/orders/export")
    public void exportOrders(HttpServletResponse resp) throws IOException {
        List<Order> orders = orderService.findAll();
        excelService.exportOrders(resp, orders);
    }

    @ModelAttribute("accounts")
    public List<Account> getAccounts() {
        return accountService.findAll();
    }

}
