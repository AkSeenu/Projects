package com.testpro.w2ssolution.repo;

import org.springframework.data.repository.CrudRepository;

import com.testpro.w2ssolution.entity.LapTopEntity;

public interface LapTopRepo extends CrudRepository<LapTopEntity, Integer> {

	LapTopEntity findOneByLaptopBrandIgnoreCaseAndLaptopModelIgnoreCase(String laptopBrand,String laptopModel);

	void deleteOneByLaptopBrandIgnoreCaseAndLaptopModelIgnoreCase(String laptopBrand,String laptopModel);

}
