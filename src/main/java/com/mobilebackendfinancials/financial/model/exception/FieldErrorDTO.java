package com.mobilebackendfinancials.financial.model.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldErrorDTO {
	
	private String fieldName;
	private String errorMessage;

}
