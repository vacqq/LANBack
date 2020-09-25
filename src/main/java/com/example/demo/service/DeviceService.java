package com.example.demo.service;


import java.util.HashMap;
import java.util.List;


public interface DeviceService {

	List<HashMap> getOnlineDeviceDataValue(HashMap<String, String> jsonString);

	List<HashMap> getHistoryDataValue(HashMap<String, String> jsonString);
}
