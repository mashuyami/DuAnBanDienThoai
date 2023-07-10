package com.spring.controllers;

import com.spring.beans.AccountForm;
import com.spring.entities.Account;
import com.spring.entities.AccountRole;
import com.spring.services.AccountService;
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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminAccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private ParamService paramService;
    @Autowired
    private ExcelService excelService;


    @GetMapping("/accounts")
    public String table(Model model) {
        String keyword = paramService.getString("keyword", "");
        int currPage = paramService.getInt("p", 1);
        if (currPage <= 0) {
            return "redirect:/admin/accounts";
        }
        Pageable pageable = PageRequest.of(currPage - 1, 5);

        // Cắt khoảng trắng trong giá trị tìm kiếm
        String trimmedKeyword = StringUtils.trimWhitespace(keyword);

        Page<Account> page = accountService.findByKeyword("%" + trimmedKeyword + "%", pageable);
        model.addAttribute("keyword", trimmedKeyword);
        model.addAttribute("page", page);
        return "admin/accounts/table";
    }


    @GetMapping("/accounts/create")
    public String createForm(Model model) {
        Account account = new Account();
        account.setActivated(1);
        account.setRole(AccountRole.USER);
        model.addAttribute("data", account);
        return "admin/accounts/create";
    }

    @PostMapping("/accounts/create")
    public String createHandler(Model model, RedirectAttributes redirect,
                                @Valid @ModelAttribute("data") AccountForm form, BindingResult result) {
        if (accountService.existsByUsername(form.getUsername())) {
            result.addError(new FieldError("register", "username", "Tên đăng nhập đã tồn tại"));
        }
        if (accountService.existsByUsernameAndEmail(form.getUsername(), form.getEmail())) {
            result.addError(new FieldError("register", "email", "Email đã được sử dụng"));
        }
        if (!result.hasErrors()) {
//            String fileName = image.getOriginalFilename();
//            paramService.save(image, "/account");
            // lấy giá trị từ form
            Account account = new Account();
            account.setId(form.getId());
            account.setUsername(form.getUsername());
            account.setPassword(form.getPassword());
            account.setFullname(form.getFullname());
            account.setPhoto(form.getPhoto());
            account.setEmail(form.getEmail());
            account.setActivated(form.getActivated());
            account.setRole(form.getRole());
            //
//            model.addAttribute("image", fileName);
//            System.out.println("Image: " + image);
            System.out.println("Form: " + form);
            if (accountService.save(account) != null) {
                redirect.addFlashAttribute("message", "Thêm người dùng thành công");
                return "redirect:/admin/accounts";
            } else {
                model.addAttribute("error", "Thêm người dùng thất bại");
            }
        }
        return "admin/accounts/create";
    }

    @GetMapping("/accounts/update/{id}")
    public String updateForm(Model model, @PathVariable("id") Integer id) {
        Account account = accountService.findById(id);
        if (account != null) {
            account.setPassword("");
            model.addAttribute("image", account.getPhoto());
            model.addAttribute("data", account);
            return "admin/accounts/update";
        } else {
            return "redirect:/admin/accounts";
        }
    }

    @PostMapping("/accounts/update")
    public String updateHandler(Model model, RedirectAttributes redirect,
                                @Valid @ModelAttribute("data") AccountForm form, BindingResult result) {
        if (form.getId() == null) {
            return "redirect:/admin/accounts";
        }
        if (accountService.existsByUsernameAndEmail(form.getUsername(), form.getEmail())) {
            result.addError(new FieldError("register", "email", "Email đã được sử dụng"));
        }
        if (!result.hasErrors()) {

            // lấy giá trị từ form
            Account account = accountService.findById(form.getId());
            account.setPassword(form.getPassword());
            account.setFullname(form.getFullname());
            account.setEmail(form.getEmail());
            account.setPhoto(form.getPhoto());
            account.setActivated(form.getActivated());
            account.setRole(form.getRole());
            //
//            model.addAttribute("image", fileName);
            if (accountService.save(account) != null) {
                redirect.addFlashAttribute("message", "Cập nhật người dùng thành công");
                return "redirect:/admin/accounts";
            } else {
                redirect.addFlashAttribute("error", "Cập nhật người dùng thất bại");
                return "redirect:/admin/accounts/update/" + form.getId();
            }
        }
        return "admin/accounts/update";
    }

    @RequestMapping(value = "/accounts/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id) {
        accountService.deleteById(id);
        return "redirect:/admin/accounts";
    }

    @GetMapping("/accounts/export")
    public void exportAccounts(HttpServletResponse resp) throws IOException {
        List<Account> accounts = accountService.findAll();
        excelService.exportAccounts(resp, accounts);
    }

}
