package com.testpro.w2ssolution.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "Laptop_Pro")
public class LapTopEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private Integer laptoId;
	
	@Column(nullable = false,length = 50)
	private String laptopBrand;
	
	@Column(nullable = false,length = 50,unique = true)
	private String laptopModel;
	
	@Column(nullable = false)
	private Double laptopPrice;
	
	@Column(nullable = false,length = 50)
	private String laptopConfig;
	
	@Column(nullable = false,length = 10)
	private Integer laptopWarranty;

	private Date createdDate;
	
	private Date updatedDate;
	

	public Integer getLaptoId() {
		return laptoId;
	}

	public void setLaptoId(Integer laptoId) {
		this.laptoId = laptoId;
	}

	public String getLaptopBrand() {
		return laptopBrand;
	}

	public void setLaptopBrand(String laptopBrand) {
		this.laptopBrand = laptopBrand;
	}

	public String getLaptopModel() {
		return laptopModel;
	}

	public void setLaptopModel(String laptopModel) {
		this.laptopModel = laptopModel;
	}

	public Double getLaptopPrice() {
		return laptopPrice;
	}

	public void setLaptopPrice(Double laptopPrice) {
		this.laptopPrice = laptopPrice;
	}

	public String getLaptopConfig() {
		return laptopConfig;
	}

	public void setLaptopConfig(String laptopConfig) {
		this.laptopConfig = laptopConfig;
	}

	public Integer getLaptopWarranty() {
		return laptopWarranty;
	}

	public void setLaptopWarranty(Integer laptopWarranty) {
		this.laptopWarranty = laptopWarranty;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	

	@PrePersist
	public void onSave() {
		if(createdDate == null)
			this.createdDate = new Date(System.currentTimeMillis());
	}
	
	@PreUpdate
	public void onUpdate() {
		if(updatedDate == null)
			this.updatedDate = new Date(System.currentTimeMillis());
	}
	
	
	@Override
	public String toString() {
		return laptopBrand + " ( " + laptopModel + " ) Is Successfuly Created "; 
	}
}
