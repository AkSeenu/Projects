package com.testpro.w2ssolution.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testpro.w2ssolution.common.CommonClass;
import com.testpro.w2ssolution.common.RoleType;
import com.testpro.w2ssolution.entity.LapTopEntity;
import com.testpro.w2ssolution.model.LapTopModel;
import com.testpro.w2ssolution.model.PayLoadData;
import com.testpro.w2ssolution.service.LapTopService;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("product")
@Log4j2
public class ProductController {

	@Autowired
	private CommonClass commonClass;

	@Autowired
	private LapTopService lapTopService;

	@Autowired
	private PayLoadData payLoadData;

	@PostMapping("saveProduct")
	public ResponseEntity<?> saveProduct(@RequestBody LapTopModel lapTopModel) {
		try {
			return new ResponseEntity<String>(lapTopService.saveLapTop(lapTopModel), HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(commonClass.errorTraceForLogger(e.getStackTrace(), e.getMessage()));
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("updateProduct")
	public ResponseEntity<?> updateProduct(@RequestBody LapTopModel lapTopModel) {
		try {

			if (payLoadData.getType().equalsIgnoreCase(RoleType.SUPER.toString())
					|| payLoadData.getType().equalsIgnoreCase(RoleType.NORMAL.toString()))
				return new ResponseEntity<String>(lapTopService.updateLapTop(lapTopModel), HttpStatus.ACCEPTED);
			else
				return new ResponseEntity<String>("User Don't Have Permission To Acces It", HttpStatus.UNAUTHORIZED);

		} catch (Exception e) {
			log.error(commonClass.errorTraceForLogger(e.getStackTrace(), e.getMessage()));
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("deleteProduct")
	public ResponseEntity<?> deleteProduct(@RequestBody LapTopModel lapTopModel) {
		try {
			if (payLoadData.getType().equalsIgnoreCase(RoleType.SUPER.toString()))
				return new ResponseEntity<String>(lapTopService.deleteProduct(lapTopModel), HttpStatus.ACCEPTED);
			else
				return new ResponseEntity<String>("User Don't Have Permission To Acces It", HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			log.error(commonClass.errorTraceForLogger(e.getStackTrace(), e.getMessage()));
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("getProduct")
	public ResponseEntity<?> getProduct() {
		try {
			if (payLoadData.getType().equalsIgnoreCase(RoleType.ADMIN.toString()))
				return new ResponseEntity<List<LapTopEntity>>(lapTopService.getProduct(), HttpStatus.ACCEPTED);
			else
				return new ResponseEntity<String>("User Don't Have Permission To Acces It", HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			log.error(commonClass.errorTraceForLogger(e.getStackTrace(), e.getMessage()));
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
