package com.example.demo.service.impl;

import com.example.demo.mapper.DeviceMapper;
import com.example.demo.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;


@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;


    @Override
    public List<HashMap> getOnlineDeviceDataValue(HashMap<String, String> jsonString) {
        return deviceMapper.getOnlineDeviceDataValue(jsonString.get("place_id"), jsonString.get("site_type"), jsonString.get("start_time"), jsonString.get("end_time"));
    }

    /**
     * 计算是否超警方法
     * b:上次数据
     * b1:当前数据
     *
     * @param b
     * @param b1
     * @return
     */
    public int Calculation(BigDecimal b, BigDecimal b1, String rule) {
        //先判断数据是否为空 不为空则比较 防止数据转换异常
        if (null != b && null != b1) {
            BigDecimal b2 = new BigDecimal(Double.valueOf(rule)).setScale(2, RoundingMode.HALF_UP);
            BigDecimal multiply = (b.multiply(b2)).add(b);
            BigDecimal bigDecimal = multiply.setScale(3, RoundingMode.HALF_UP);
            if (bigDecimal.compareTo(b1) == 1) {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public List<HashMap> getHistoryDataValue(HashMap<String, String> jsonString) {
        return deviceMapper.getHistoryDataValue(jsonString.get("place_id"), jsonString.get("site_type"), jsonString.get("start_time"), jsonString.get("end_time"));
    }
}
