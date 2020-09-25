package com.example.demo.controller;

import com.example.demo.bean.SysSiteEntity;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RestController
public class DataAnalysisController {

    @Autowired
    private AnalysSiteRankService analysSiteRankService;
    @Autowired
    private AnalysAllTrendService analysAllTrendService;
    @Autowired
    private SelectSiteService selectSiteService;
    @Autowired
    private AnalysisSiteRelevanceService analysisSiteRelevanceService;
    @Autowired
    private DeviceService deviceService;


    /**
     * @param jsonString 前端传入的相关信息
     * @return {{@link List < HashMap >}}
     * @description 站点排名分析, 排名表格显示
     * @author lcz
     * @date 2020/4/23 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/AnalysisSiteRank", method = RequestMethod.POST)
    public List<HashMap> AnalysisSiteRank(@RequestBody HashMap<String, String> jsonString) throws Exception {
        return analysSiteRankService.SelectData(jsonString);
    }

    /**
     * @param jsonString 前端传入的相关信息
     * @return {{@link List < HashMap >}}
     * @description 综合趋势分析
     * @author lcz
     * @date 2020/4/23 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/AnalysisAllTrend", method = RequestMethod.POST)
    public List<HashMap> AnalysisAllTrend(@RequestBody HashMap<String, String> jsonString) throws Exception {
        return analysisSiteRelevanceService.SelectDataSiteRange(jsonString);
    }


    /**
     * @param jsonString 前端传入的相关信息
     * @return {{@link List < HashMap >}}
     * @description 站点相关分析根据id值查询
     * @author lcz
     * @date 2020/4/23 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/AnalysisSiteRelevance", method = RequestMethod.POST)
    public List<HashMap> AnalysisSiteRelevanceId(@RequestBody HashMap<String, String> jsonString) throws Exception {
        return analysisSiteRelevanceService.SelectDataSiteRange(jsonString);
    }


    /**
     * @param jsonString 前端传入的相关信息
     * @return {{@link List<HashMap>}}
     * @description 在线设备获取的数据
     * @author lcz
     * @date 2020/7/12 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/getOnlineDeviceDataValue", method = RequestMethod.POST ,produces="application/json;charset=utf-8")
    public List<HashMap> getOnlineDeviceDataValue(@RequestBody HashMap<String, String> jsonString) throws Exception {
        return deviceService.getOnlineDeviceDataValue(jsonString);
    }

    /**
     * @param jsonString 前端传入的相关信息
     * @return {{@link List<HashMap>}}
     * @description 获取历史数据
     * @author lcz
     * @date 2020/7/12 15:42
     */
    @CrossOrigin
    @RequestMapping(value = "/api/getHistoryDataValue", method = RequestMethod.POST ,produces="application/json;charset=utf-8")
    public List<HashMap> getHistoryDataValue(@RequestBody HashMap<String, String> jsonString) throws Exception {
        return deviceService.getHistoryDataValue(jsonString);
    }





}