package com.spring.services;

import com.spring.entities.Account;
import com.spring.entities.Order;
import com.spring.repositoties.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepo;

    public List<Order> findAll() {
        return orderRepo.findAll();
    }

    public Order save(Order order) {
        return orderRepo.save(order);
    }

    public Order create(String address, Account account) {
        Order order = new Order();
        order.setAddress(address);
        order.setAccount(account);
        order.setCreatedDate(new Date(System.currentTimeMillis()));
        return orderRepo.save(order);
    }

    public Order findById(Integer id) {
        Optional<Order> optional = orderRepo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public Page<Order> findByAccountId(Integer accountId, Pageable pageable) {
        return orderRepo.findByAccountId(accountId, pageable);
    }

    public Page<Order> findAll(Pageable pageable) {
        return orderRepo.findAll(pageable);
    }

    public void deleteById(Integer id) {
        try {
            orderRepo.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
