package com.mb.assignment.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.mb.assignment.entity.Image;

public class ProductDto {
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String modelNumber;
	
	@NotBlank
	private String description;
	
	@NotNull
	private Long price;
	
	@NotEmpty
	private List<Image> image;
	

	public String getName() {
		return name;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public String getDescription() {
		return description;
	}

	public Long getPrice() {
		return price;
	}

	public List<Image> getImage() {
		return image;
	}
	
	

}
