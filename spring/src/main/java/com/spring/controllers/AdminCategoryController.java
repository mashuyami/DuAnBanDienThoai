package com.spring.controllers;

import com.spring.beans.CategoryForm;
import com.spring.entities.Category;
import com.spring.services.CategoryService;
import com.spring.services.ExcelService;
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
public class AdminCategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ParamService paramService;
    @Autowired
    private ExcelService excelService;

    @GetMapping("/categories")
    public String tableProduct(Model model) {
        String keyword = paramService.getString("keyword", "");
        int currPage = paramService.getInt("p", 1);
        if (currPage <= 0) {
            return "redirect:/admin/categories";
        }
        Pageable pageable = PageRequest.of(currPage - 1, 5);
        Page<Category> page = categoryService.findByKeyword("%" + keyword + "%", pageable);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "admin/categories/table";
    }

    @GetMapping("/categories/create")
    public String createForm(Model model) {
        Category category = new Category();
        model.addAttribute("data", category);
        return "admin/categories/create";
    }

    @PostMapping("/categories/create")
    public String createHandler(Model model, RedirectAttributes redirect,
                                @Valid @ModelAttribute("data") CategoryForm form, BindingResult result) {
        if (!result.hasErrors()) {
            String name = form.getName();
            Category category = categoryService.create(name);
            if (category != null) {
                redirect.addFlashAttribute("message", "Thêm danh mục thành công");
                return "redirect:/admin/categories";
            }
        }
        return "admin/categories/create";
    }

    @GetMapping("/categories/update/{id}")
    public String updateForm(Model model, @PathVariable("id") Integer id) {
        Category category = categoryService.findById(id);
        if (category != null) {
            model.addAttribute("data", category);
            return "admin/categories/update";
        } else {
            return "redirect:/admin/categories";
        }
    }

    @PostMapping("/categories/update")
    public String updateHandler(Model model, RedirectAttributes redirect,
                                @Valid @ModelAttribute("data") CategoryForm form, BindingResult result) {
        if (form.getId() == null) {
            return "redirect:/admin/categories";
        }
        if (!result.hasErrors()) {
            Category category = categoryService.findById(form.getId());
            if (category == null) {
                return "redirect:/admin/categories";
            }
            String name = form.getName();
            if (categoryService.update(category, name) != null) {
                redirect.addFlashAttribute("message", "Cập nhật danh mục thành công");
                return "redirect:/admin/categories";
            }
        }
        return "admin/categories/update";
    }

    @GetMapping("/categories/delete/{id}")
    public String delete(Model model, @PathVariable("id") Integer id) {
        categoryService.deleteById(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/export")
    public void exportCategories(HttpServletResponse resp) throws IOException {
        List<Category> categories = categoryService.findAll();
        excelService.exportCategories(resp, categories);
    }
}
