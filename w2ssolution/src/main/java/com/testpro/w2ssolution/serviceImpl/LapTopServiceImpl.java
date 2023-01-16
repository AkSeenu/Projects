package com.testpro.w2ssolution.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testpro.w2ssolution.entity.LapTopEntity;
import com.testpro.w2ssolution.model.LapTopModel;
import com.testpro.w2ssolution.repo.LapTopRepo;
import com.testpro.w2ssolution.service.LapTopService;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class LapTopServiceImpl implements LapTopService {

	@Autowired
	private LapTopRepo lapTopRepo;

	@Transactional
	@Override
	public String saveLapTop(LapTopModel lapTopModel) throws Exception {
		LapTopEntity lapTopEntity;
		try {

			lapTopEntity = new LapTopEntity();
			lapTopEntity.setLaptopBrand(lapTopModel.getLaptopBrand());
			lapTopEntity.setLaptopModel(lapTopModel.getLaptopModel());
			lapTopEntity.setLaptopPrice(lapTopModel.getLaptopPrice());
			lapTopEntity.setLaptopConfig(lapTopModel.getLaptopConfig());
			lapTopEntity.setLaptopWarranty(lapTopModel.getLaptopWarranty());

			log.info("Save lapTopEntity Start");
			lapTopRepo.save(lapTopEntity);
			log.info("Save lapTopEntity End");

			return lapTopEntity.toString();
		} catch (Exception e) {
			log.error("Error In Saving lapTopEntity");
			throw e;
		} finally {
			lapTopEntity = null;
		}
	}

	@Transactional
	@Override
	public String updateLapTop(LapTopModel lapTopModel) throws Exception {
		LapTopEntity lapTopEntity;
		try {

			lapTopEntity = new LapTopEntity();
			lapTopEntity.setLaptopBrand(lapTopModel.getLaptopBrand());
			lapTopEntity.setLaptopModel(lapTopModel.getLaptopModel());

			lapTopEntity = lapTopRepo.findOneByLaptopBrandIgnoreCaseAndLaptopModelIgnoreCase(
					lapTopEntity.getLaptopBrand(), lapTopEntity.getLaptopModel());

			lapTopEntity.setLaptopPrice(lapTopModel.getLaptopPrice());
			lapTopEntity.setLaptopConfig(lapTopModel.getLaptopConfig());

			log.info("Update lapTopEntity Start");
			lapTopRepo.save(lapTopEntity);
			log.info("Update lapTopEntity End");

			return "Update SuccessFully";
		} catch (Exception e) {
			log.error("Error In Saving lapTopEntity");
			throw e;
		} finally {
			lapTopEntity = null;
		}
	}

	@Transactional
	@Override
	public String deleteProduct(LapTopModel lapTopModel) throws Exception {
		LapTopEntity lapTopEntity;
		try {

			lapTopEntity = new LapTopEntity();
			lapTopEntity.setLaptopBrand(lapTopModel.getLaptopBrand());
			lapTopEntity.setLaptopModel(lapTopModel.getLaptopModel());

			lapTopRepo.deleteOneByLaptopBrandIgnoreCaseAndLaptopModelIgnoreCase(lapTopEntity.getLaptopBrand(),
					lapTopEntity.getLaptopModel());

			return "Deleted SuccessFully";
		} catch (Exception e) {
			log.error("Error In Deleting lapTopEntity");
			throw e;
		} finally {
			lapTopEntity = null;
		}
	}

	@Override
	public List<LapTopEntity> getProduct() throws Exception {
		try {
			List<LapTopEntity> listProduct = (List<LapTopEntity>) lapTopRepo.findAll();
			return listProduct;
		} catch (Exception e) {
			log.error("Error In Fetching lapTopEntity");
			throw e;
		}
	}

}
