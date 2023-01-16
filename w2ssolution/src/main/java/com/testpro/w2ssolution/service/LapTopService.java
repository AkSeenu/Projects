package com.testpro.w2ssolution.service;

import java.util.List;

import com.testpro.w2ssolution.entity.LapTopEntity;
import com.testpro.w2ssolution.model.LapTopModel;

public interface LapTopService {

	public String saveLapTop(LapTopModel lapTopModel) throws Exception;

	public String updateLapTop(LapTopModel lapTopModel) throws Exception;

	public String deleteProduct(LapTopModel lapTopModel) throws Exception;
	
	public List<LapTopEntity> getProduct() throws Exception;
}
