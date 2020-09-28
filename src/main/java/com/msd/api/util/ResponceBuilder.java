package com.msd.api.util;

import org.springframework.stereotype.Component;

@Component
public class ResponceBuilder {

	public Responce getResponce(Object data) {
		Responce responce=new Responce();
		responce.setSuccess(true);
		responce.setData(data);
		return responce;
	}


	public Responce getResponce(String message, Boolean success) {
		Responce responce=new Responce();
		responce.setSuccess(success);
		responce.setMessage(message);
		return responce;
	}

}
