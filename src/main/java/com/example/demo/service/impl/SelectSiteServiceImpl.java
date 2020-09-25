package com.example.demo.service.impl;

import com.example.demo.bean.SysSiteEntity;
import com.example.demo.mapper.SelectSiteMapper;
import com.example.demo.service.SelectSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class SelectSiteServiceImpl implements SelectSiteService {
    @Autowired
    SelectSiteMapper selectSiteMapper;

    @Override
    public String SelectNameById(String Id) {
        return selectSiteMapper.SelectNameById(Id);
    }

    @Override
    public HashMap SelectSiteDataById(String Id) {
        return selectSiteMapper.SelectSiteDataById(Id);
    }

    @Override
    public String SelectPlaceNameById(String Id) {
        return selectSiteMapper.SelectPlaceNameById(Id);
    }

    @Override
    public String SelectIdByName(String name) {
        return selectSiteMapper.SelectIdByName(name);
    }

    @Override
    public List<SysSiteEntity> SelectNameInId(String IdRange) {
        return selectSiteMapper.SelectNameInId(IdRange);
    }

    @Override
    public List<HashMap> SelectSiteIdNameBySiteType(HashMap<String, String> jsonString) {
        return selectSiteMapper.SelectSiteIdNameBySiteType(jsonString.get("site_type"), jsonString.get("place_id"), jsonString.get("air_type"), jsonString.get("start_time"), jsonString.get("end_time"));
    }

    @Override
    public List<HashMap> SelectSiteIdNameBySiteTypeId(HashMap<String, String> jsonString) {
        return selectSiteMapper.SelectSiteIdNameBySiteTypeId(jsonString.get("site_type"), jsonString.get("place_id"));
    }

    @Override
    public List<HashMap> SelectHeatMapDataList(HashMap<String, String> jsonString) {
        return selectSiteMapper.SelectHeatMapDataList(jsonString.get("site_type"), jsonString.get("place_id"), jsonString.get("air_type"), jsonString.get("start_time"), jsonString.get("end_time"));
    }

    @Override
    public List<HashMap> SelectDictionariesByType(HashMap<String, String> jsonString) {
        return selectSiteMapper.SelectDictionariesByType(jsonString.get("type"));
    }

    @Override
    public List<SysSiteEntity> GetPlaceTreeList(String place_id) {
        return selectSiteMapper.GetPlaceTreeList(place_id);
    }

    @Override
    public List<HashMap> GetPlaceTreeListQuick(String place_id) {
        return selectSiteMapper.GetPlaceTreeListQuick(place_id);
    }

    @Override
    public List<HashMap> GetPlaceTreeListInParentId(String place_id) {
        return selectSiteMapper.GetPlaceTreeListInParentId(place_id);
    }

    @Override
    public List<HashMap> SelectPlaceIdByType(String type) {
        return selectSiteMapper.SelectPlaceIdByType(type);
    }

    @Override
    public List<HashMap> SelectSiteList(HashMap<String, String> jsonString) {
        return selectSiteMapper.SelectSiteList(jsonString.get("site_type"), jsonString.get("place_id"), jsonString.get("lat"), jsonString.get("lng"), jsonString.get("site_name"));
    }

    @Override
    public List<HashMap> getDateBySiteId(HashMap<String, String> jsonString) {
        List<HashMap> hashMapList = selectSiteMapper.getDateBySiteId(jsonString.get("site_id"),
                jsonString.get("data_type"), jsonString.get("start_time"), jsonString.get("end_time"));
        return hashMapList;
    }

    @Override
    public HashMap getLastSiteDataBySiteid(HashMap<String, String> jsonString) {
        return selectSiteMapper.getLastSiteDataBySiteid(jsonString.get("site_id"));
    }


    @Override
    public Integer countSiteNameIsHave(String name) {
        return selectSiteMapper.countSiteNameIsHave(name);
    }

    @Override
    public Integer InsertSiteData(HashMap<String, Object> jsonString) {
        return selectSiteMapper.InsertSiteData(jsonString.get("place_id"), jsonString.get("site_type"), jsonString.get("name"), jsonString.get("only_name"), jsonString.get("lng"), jsonString.get("lat"), jsonString.get("position"));
    }

    @Override
    public Integer UpdateSiteData(HashMap<String, String> jsonString) {
        return selectSiteMapper.UpdateSiteData(jsonString.get("id"), jsonString.get("place_id"), jsonString.get("site_type"), jsonString.get("name"), jsonString.get("only_name"), jsonString.get("lng"), jsonString.get("lat"), jsonString.get("position"), jsonString.get("modify_by"));
    }

    @Override
    public Integer DeleteSiteData(String id_all) {
        return selectSiteMapper.DeleteSiteData(id_all);
    }


    @Override
    public List<HashMap> GetSiteDataByPlaceId(String site_type, String place_id) {
        return selectSiteMapper.GetSiteDataByPlaceId(site_type, place_id);
    }

}
