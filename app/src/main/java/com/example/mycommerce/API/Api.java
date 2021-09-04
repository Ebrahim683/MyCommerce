package com.example.mycommerce.API;

import com.example.mycommerce.CLASSES.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {


	@FormUrlEncoded
	@POST("sign_up.php")
	Call<ResponseModel> signUpApi(
			@Field("name") String name,
			@Field("email") String email,
			@Field("password") String password,
			@Field("mobile") String mobile,
			@Field("address") String address
	);

	@FormUrlEncoded
	@POST("sign_in.php")
	Call<ResponseModel> signInApi(
			@Field("email") String email,
			@Field("password") String password
	);


}
