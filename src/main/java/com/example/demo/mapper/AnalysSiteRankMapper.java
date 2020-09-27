package com.example.demo.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface AnalysSiteRankMapper {
    /**
     * @param type       参数类型
     * @param order_type 排序方式
     * @return {{@link java.util.HashMap}}
     * @description 查询排名table信息
     * @author lcz
     * @date 2020/4/24 14:39
     */
    @Select({"SELECT ${type} as type_value,staname as site_name,(@rownum := @rownum + 1) AS col_rank ,\n" +
            "        CASE\n" +
            "        WHEN ${type} < 60 THEN '#58C750'\n" +
            "        WHEN ${type} >=60 and ${type} < 70 THEN '#E3CA55'\n" +
            "        ELSE '#7A3D3D'\n" +
            "        END as col_color\n" +
            "        from\n" +
            "        (SELECT @rownum := 0) r, v_data\n" +
            "\n" +
            "       where site_type=${site_type} order by ${type} ${order_type}"})
    List<HashMap> Select(@Param("type") String type, @Param("order_type") String order_type, @Param("site_type") String site_type);


    /**
     * @return {{@link java.util.HashMap}}
     * @description 查询排名table信息
     * @author lcz
     * @date 2020/4/24 14:39
     */
    @Select({" select  ROUND((UNIX_TIMESTAMP(max(create_at))-UNIX_TIMESTAMP(min(create_at)))/60) as minute" +
            ",(select name from lan_dictionaries where type='ip' and value = a.visit_ip )as ip_name from lan_data as a " +
            "where create_at>=#{start_time} and create_at<=#{end_time} GROUP BY visit_ip ORDER BY minute desc "})
    List<HashMap> SelectData(@Param("start_time") String start_time, @Param("end_time") String end_time);


}
