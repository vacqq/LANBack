package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

public interface ExportService {
    ResponseEntity<byte[]> exportExcel(HttpServletRequest request, HttpServletResponse response, List<HashMap> jsonString);
}
