package com.example.demo.service.impl;

import com.example.demo.baseBeanUtIls.ExcelFormatUtil;
import com.example.demo.controller.BaseFrontController;
import com.example.demo.service.ExportService;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;


@Service
public class ExportServiceImpl implements ExportService {
    Logger logger = LoggerFactory.getLogger(ExportServiceImpl.class);

    @Override
    public ResponseEntity<byte[]> exportExcel(HttpServletRequest request, HttpServletResponse response, List<HashMap> jsonString) {
        try {
            logger.info(">>>>>>>>>>开始导出excel>>>>>>>>>>");
            BaseFrontController baseFrontController = new BaseFrontController();
            return baseFrontController.buildResponseEntity(export(jsonString), "用户表.xls");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(">>>>>>>>>>导出excel 异常，原因为：" + e.getMessage());
        }
        return null;
    }

    private InputStream export(List<HashMap> jsonString) {
        logger.info(">>>>>>>>>>>>>>>>>>>>开始进入导出方法>>>>>>>>>>");
        ByteArrayOutputStream output = null;
        InputStream inputStream1 = null;
        SXSSFWorkbook wb = new SXSSFWorkbook(1000);// 保留1000条数据在内存中
        SXSSFSheet sheet = wb.createSheet();
        // 设置报表头样式
        CellStyle header = ExcelFormatUtil.headSytle(wb);// cell样式
        CellStyle content = ExcelFormatUtil.contentStyle(wb);// 报表体样式

        // 每一列字段名
        String[] strs = new String[]{"用户名称", "访问类型", "时间", "数据包大小"};

        // 字段名所在表格的宽度
        int[] ints = new int[]{5000, 3000, 6000, 3000};

        // 设置表头样式
        ExcelFormatUtil.initTitleEX(sheet, header, strs, ints);
        logger.info(">>>>>>>>>>>>>>>>>>>>表头样式设置完成>>>>>>>>>>");

        if (jsonString != null && jsonString.size() > 0) {
            logger.info(">>>>>>>>>>>>>>>>>>>>开始遍历数据组装单元格内容>>>>>>>>>>");
            int i = 0;
            for (HashMap strList : jsonString) {
                int j = 0;
                SXSSFRow row = sheet.createRow(i + 1);
                i++;
                SXSSFCell cell = row.createCell(j++);
                cell.setCellValue(strList.get("user_name").toString()); // 姓名
                cell.setCellStyle(content);

                cell = row.createCell(j++);
                cell.setCellValue(strList.get("type_name").toString()); // 性别
                cell.setCellStyle(content);

                cell = row.createCell(j++);
                cell.setCellValue(strList.get("record_time").toString()); // 年龄
                cell.setCellStyle(content);

                cell = row.createCell(j++);
                cell.setCellValue(strList.get("use_num").toString()); // 手机号
                cell.setCellStyle(content);
            }
            logger.info(">>>>>>>>>>>>>>>>>>>>结束遍历数据组装单元格内容>>>>>>>>>>");
        }
        try {
            output = new ByteArrayOutputStream();
            wb.write(output);
            inputStream1 = new ByteArrayInputStream(output.toByteArray());
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (output != null) {
                    output.close();
                    if (inputStream1 != null) {
                        inputStream1.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return inputStream1;
    }
}
