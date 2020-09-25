package com.example.demo.controller;

import com.example.demo.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;


@RestController
public class ExportController {

    @Autowired
    private ExportService exportService;

    // 导出excel
    @CrossOrigin
    @RequestMapping("/api/exportExcel")
    public ResponseEntity<byte[]> exportExcel(@RequestBody List<HashMap> jsonString, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(jsonString);
        return exportService.exportExcel(request, response, jsonString);
    }
}
