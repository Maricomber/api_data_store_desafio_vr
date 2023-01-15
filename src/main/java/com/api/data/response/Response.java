package com.api.data.response;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@Getter
public class Response<T>{

	private T data;
	private List < String > errors;
	
}