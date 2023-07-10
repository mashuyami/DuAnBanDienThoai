package com.spring.controllers;

import com.spring.beans.OrderDetailForm;
import com.spring.entities.Order;
import com.spring.entities.OrderDetail;
import com.spring.entities.Product;
import com.spring.services.*;
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
public class AdminOrderDetailController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private ParamService paramService;
    @Autowired
    private ExcelService excelService;

    @GetMapping("/order-details")
    public String table(Model model) {
        int currPage = paramService.getInt("p", 1);
        if (currPage <= 0) {
            return "redirect:/admin/order-details";
        }
        Pageable pageable = PageRequest.of(currPage - 1, 5);
        Page<OrderDetail> page = orderDetailService.findAll(pageable);
        model.addAttribute("page", page);
        return "admin/order-details/table";
    }




    @GetMapping("/order-details/create")
    public String createForm(Model model) {
        OrderDetailForm form = new OrderDetailForm();
        model.addAttribute("data", form);
        return "admin/order-details/create";
    }

    @GetMapping("/order-details/update/{id}")
    public String updateForm(Model model, @PathVariable("id") Integer id) {
        OrderDetail orderDetail = orderDetailService.findById(id);
        if (orderDetail == null) {
            return "redirect:/admin/order-details";
        }
        model.addAttribute("data", orderDetail);
        return "admin/order-details/update";
    }

    @PostMapping("/order-details/update")
    public String updateHandler(Model model, RedirectAttributes redirect, @Valid @ModelAttribute("data") OrderDetailForm form,
                                BindingResult result) {
        if (form.getId() == null) {
            return "redirect:/admin/order-details";
        }
        if (!result.hasErrors()) {
            OrderDetail orderDetail = orderDetailService.findById(form.getId());
            orderDetail.setOrder(form.getOrder());
            orderDetail.setProduct(form.getProduct());
            orderDetail.setPrice(form.getPrice());
            orderDetail.setQuantity(form.getQuantity());
            if (orderDetailService.save(orderDetail) != null) {
                redirect.addFlashAttribute("message", "Cập nhật đơn hàng chi tiết thành công");
                return "redirect:/admin/order-details/update/" + form.getId();
            } else {
                redirect.addFlashAttribute("message", "Cập nhật đơn hàng chi tiết thất bại");
                return "redirect:/admin/order-details/update/" + form.getId();
            }
        }
        return "admin/order-details/update";
    }

    @PostMapping("/order-details/create")
    public String createHandler(Model model, RedirectAttributes redirect, @Valid @ModelAttribute("data") OrderDetailForm form,
                                BindingResult result) {
        if (!result.hasErrors()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(form.getOrder());
            orderDetail.setProduct(form.getProduct());
            orderDetail.setPrice(form.getPrice());
            orderDetail.setQuantity(form.getQuantity());
            if (orderDetailService.save(orderDetail) != null) {
                redirect.addFlashAttribute("message", "Thêm đơn hàng chi tiết thành công");
                return "redirect:/admin/order-details/create";
            } else {
                redirect.addFlashAttribute("message", "Thêm đơn hàng chi tiết thất bại");
                return "redirect:/admin/order-details/create";
            }
        }
        return "admin/order-details/create";
    }

//    @PostMapping("/order-details/delete/{id}")
//    public String delete(@PathVariable("id") Integer id) {
//        orderDetailService.deleteById(id);
//        return "redirect:/admin/order-details";
//    }
@GetMapping("/order-details/delete/{id}")
public String delete(@PathVariable("id") Integer id) {
    orderDetailService.deleteById(id);
    return "redirect:/admin/order-details";
}

    @GetMapping("/order-details/export")
    public void exportOrders(HttpServletResponse resp) throws IOException {
        List<Order> orders = orderService.findAll();
        excelService.exportOrders(resp, orders);
    }

    @ModelAttribute("orders")
    public List<Order> getOrders() {
        return orderService.findAll();
    }

    @ModelAttribute("products")
    public List<Product> getProducts() {
        return productService.findAll();
    }
}
