package com.example.demo.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


@Repository
public interface DeviceMapper{

    /**
     * @param place_id   站点地区id
     * @param site_type  站点类型
     * @param start_time 查询开始时间
     * @param end_time   查询结束时间
     * @return {{@link java.util.HashMap}}
     * @description 查询排名table信息
     * @author lcz
     * @date 2020/8/12 14:39
     */
    @Select({"<script>" +
            "SELECT use_num," +
            "(select name from lan_dictionaries where value = a.visit_ip and type = 'ip') as user_name , " +
            " type_name,DATE_FORMAT(a.create_at, '%Y-%m-%d %H:%i:%S') as record_time  " +
            " from lan_data as a " +
            "order by a.create_at desc limit 100 " +
            "</script>"})
    List<HashMap> getOnlineDeviceDataValue(@Param("place_id") String place_id, @Param("site_type") String site_type, @Param("start_time") String start_time, @Param("end_time") String end_time);

    /**
     * @param start_time 查询开始时间
     * @param end_time   查询结束时间
     * @return {{@link java.util.HashMap}}
     * @description 查询排名table信息
     * @author lcz
     * @date 2020/5/12 14:39
     */
    @Select({"<script>" +
            "SELECT use_num," +
            "(select name from lan_dictionaries where value = a.visit_ip and type = 'ip') as user_name , " +
            "type_name,DATE_FORMAT(a.create_at, '%Y-%m-%d %H:%i:%S') as record_time  " +
            " from lan_data as a " +
            "where a.create_at &gt;=#{start_time} and a.create_at &lt;= #{end_time} " +
            "order by a.create_at desc " +
            "</script>"})
    List<HashMap> getHistoryDataValue(@Param("place_id") String place_id, @Param("site_type") String site_type, @Param("start_time") String start_time, @Param("end_time") String end_time);
}
