package com.shubh.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{

	String resouceName;
	String fileName;
	long fieldValue;
	public ResourceNotFoundException(String resouceName, String fileName, long fieldValue) {
		super(String.format("%s not found with %s : %s", resouceName, fileName, fieldValue));
		this.resouceName = resouceName;
		this.fileName = fileName;
		this.fieldValue = fieldValue;
	}
}
