package com.spring.controllers;

import com.spring.entities.OrderDetail;
import com.spring.entities.ReportCategory;
import com.spring.repositoties.AccountRepository;
import com.spring.repositoties.OrderDetailRepository;
import com.spring.repositoties.OrderRepository;
import com.spring.repositoties.ProductRepository;
import com.spring.services.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminReportController {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ExcelService excelService;
    @Autowired
    private OrderRepository orderRepository;
@Autowired
private AccountRepository accountRepository;

    @GetMapping("/reports")
    public String admin(Model model) {
        long totalAcc = this.accountRepository.count();
        long totalPro = this.productRepository.count();
        long totalOrder = this.orderRepository.count();
        long totalPayment = 0;
        List<OrderDetail> odetail = this.orderDetailRepository.findAll();
        for (OrderDetail orderDetail : odetail) {
            totalPayment += orderDetail.getPrice() * orderDetail.getQuantity();
        }
        model.addAttribute("totalAcc", totalAcc);
        model.addAttribute("totalPro", totalPro);
        model.addAttribute("totalOrder", totalOrder);
        model.addAttribute("totalPayment", totalPayment);
        return "/admin/reports/table";
    }


}
