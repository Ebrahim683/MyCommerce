package com.example.mycommerce.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {

	private static ApiController apiController;
	private static Retrofit retrofit;
	static String url = "http://192.168.0.8/PHP/";


	ApiController(){
		retrofit = new Retrofit.Builder()
				.baseUrl(url)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}

	public static synchronized ApiController getInstance(){
		if (apiController == null)
			apiController = new ApiController();

		return apiController;
	}

	public Api getApi(){
		return retrofit.create(Api.class);
	}

}
