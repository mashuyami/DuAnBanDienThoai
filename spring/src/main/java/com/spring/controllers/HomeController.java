package com.spring.controllers;

import com.spring.entities.Category;
import com.spring.entities.Product;
import com.spring.services.CartService;
import com.spring.services.CategoryService;
import com.spring.services.ParamService;
import com.spring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
@Controller
public class HomeController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CartService cartService;
    @Autowired
    private ParamService paramService;

    @RequestMapping("/home")
    public String home(Model model) {
        List<Category> categories = categoryService.findAll();
        String keyword = paramService.getString("keyword", "");
        int currPage = paramService.getInt("p", 1);
        if (currPage <= 0) {
            return "redirect:/home";
        }
        Pageable pageable = PageRequest.of(currPage - 1, 6);
        Page<Product> page = productService.findByKeywordAndQuantity("%" + keyword + "%", pageable);
        // top 10 sản phẩm giảm giá
        List<Product> products = productService.findByDiscountIsYes(PageRequest.of(0, 10, Sort.Direction.ASC, "discount"));
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("cart", cartService);
        return "home";
    }

    @RequestMapping("/category/{cid}")
    public String category(Model model, @PathVariable("cid") Optional<Integer> cid) {
        if (cid.isPresent()) {
            int currPage = paramService.getInt("p", 1);
            if (currPage <= 0) {
                return "redirect:/home";
            }
            List<Category> categories = categoryService.findAll();
            Pageable pageable = PageRequest.of(currPage - 1, 9);
            Page<Product> page = productService.findByCategoryIdAndQuantity(cid.get(), pageable);
            model.addAttribute("categories", categories);
            model.addAttribute("page", page);
            model.addAttribute("id", cid.get());
            model.addAttribute("cart", cartService);
            return "category";
        } else {
            return "redirect:/home";
        }
    }

    @RequestMapping("/product/{id}")
    public String product(Model model, @PathVariable("id") Optional<Integer> id) {
        if (id.isPresent()) {
            Product product = productService.findById(id.get());
            List<Product> products = productService.findByCategoryIdExceptProductId(product.getCategory().getId(),
                    id.get(), PageRequest.ofSize(4));
            model.addAttribute("product", product);
            // sản phẩm cùng danh mục
            model.addAttribute("products", products);
            model.addAttribute("cart", cartService);
            return "product-detail";
        } else {
            return "redirect:/home";
        }
    }

    @RequestMapping("/admin")
    public String admin() {
        return "redirect:/admin/accounts";
    }
}
