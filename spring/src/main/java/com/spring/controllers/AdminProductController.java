package com.spring.controllers;

import com.spring.beans.ProductForm;
import com.spring.entities.Category;
import com.spring.entities.Product;
import com.spring.services.CategoryService;
import com.spring.services.ExcelService;
import com.spring.services.ParamService;
import com.spring.services.ProductService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Date;
import java.util.List;
@Controller
@RequestMapping("/admin")
public class AdminProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ParamService paramService;
    @Autowired
    private ExcelService excelService;

    @GetMapping("/products")
    public String tableProduct(Model model) {
//		String order = paramService.getString("order", "id");
//		Direction direction = paramService.getDirection("sort", Direction.ASC);
//		Sort sort = Sort.by(direction, order);
        String keyword = paramService.getString("keyword", "");
        int currPage = paramService.getInt("p", 1);
        if (currPage <= 0) {
            return "redirect:/admin/products";
        }
        Pageable pageable = PageRequest.of(currPage - 1, 5);
        Page<Product> page = productService.findByKeyword("%" + keyword + "%", pageable);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "admin/products/table";
    }

    @GetMapping("/products/create")
    public String createProductForm(Model model) {
        Product product = new Product();
        product.setAvailable(1);
        model.addAttribute("data", product);
        return "admin/products/create";
    }

    @PostMapping("/products/create")
    public String createHandler(Model model, RedirectAttributes redirect, @RequestParam("image") MultipartFile image,
                                @Valid @ModelAttribute("data") ProductForm form, BindingResult result) {
        if (!result.hasErrors()) {
            Product product = form.copyAttributes(null);
            String fileName = image.getOriginalFilename();
            paramService.save(image, "/product");

            product.setImage(fileName);
            product.setCreatedDate(new Date());
            model.addAttribute("image", fileName);
            if (productService.save(product) != null) {
                redirect.addFlashAttribute("message", "Thêm sản phẩm thành công");
                return "redirect:/admin/accounts";
            } else {
                redirect.addFlashAttribute("error", "Thêm sản phẩm thất bại");
                return "redirect:/admin/accounts/create";
            }
        }
        return "admin/products/create";
    }

    @GetMapping("/products/update/{id}")
    public String updateProductForm(Model model, @PathVariable("id") Integer id) {
        Product product = productService.findById(id);
        if (product != null) {
            model.addAttribute("image", product.getImage());
            model.addAttribute("data", product);
            return "admin/products/update";
        } else {
            return "redirect:/admin/products";
        }
    }

    @PostMapping("/products/update")
    public String updateHandler(Model model, RedirectAttributes redirect, @RequestParam("image") MultipartFile image,
                                @Valid @ModelAttribute("data") ProductForm form, BindingResult result) {
        if (form.getId() == null) {
            return "redirect:/admin/products";
        }
        if (!result.hasErrors()) {
            Product product = productService.findById(form.getId());
            if (product != null) {
                product = form.copyAttributes(product);

            }
            String fileName = image.getOriginalFilename();
            paramService.save(image, "/product");

            product.setImage(fileName);
            model.addAttribute("image", fileName);
            if (productService.save(product) != null) {
                redirect.addFlashAttribute("message", "Cập nhật sản phẩm thành công");
                return "redirect:/admin/products" ;
            } else {
                redirect.addFlashAttribute("error", "Cập nhật sản phẩm thất bại");
                return "redirect:/admin/products/update/" + form.getId();
            }
        }
        return "admin/products/update";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/export")
    public void exportProduct(HttpServletResponse resp) throws IOException {
        List<Product> products = productService.findAll();
        excelService.exportProducts(resp, products);
    }

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryService.findAll();
    }
}
