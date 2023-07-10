package com.spring.controllers;

import com.spring.services.ExcelService;
import com.spring.services.ParamService;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class AdminImportController {
    @Autowired
    private ParamService paramService;
    @Autowired
    private ExcelService excelService;
    @Autowired
    private JobScheduler jobScheduler;

    @GetMapping("/admin/import")
    public String importForm() {
        return "admin/import";
    }

    @PostMapping("/admin/import")
    public String importHandler(Model model, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            model.addAttribute("error", "Không để trống file");
            return "admin/import";
        }
        File fileExcel = paramService.save(file, "/excel");
        // đẩy công việc vào hàng đợi sử dụng lambda
        BackgroundJob.enqueue(() -> excelService.importExcel(fileExcel));
        jobScheduler.enqueue(() -> excelService.importExcel(fileExcel));
//		model.addAttribute("message", "Hoàn thành nhập file excel");
        return "admin/import";
    }
}
