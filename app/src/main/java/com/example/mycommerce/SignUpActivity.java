package com.example.mycommerce;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mycommerce.databinding.ActivitySignUpBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

	ActivitySignUpBinding binding;
	String name, email, password, mobile, address;
	SharedPreferenceManager sharedPreferenceManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivitySignUpBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		Objects.requireNonNull(getSupportActionBar()).hide();
		getWindow().setStatusBarColor(getResources().getColor(R.color.seljuk_blue));
		getWindow().setNavigationBarColor(getResources().getColor(R.color.seljuk_blue));

		sharedPreferenceManager = new SharedPreferenceManager(this);

		binding.signInText.setOnClickListener(v -> {
			startActivity(new Intent(this, SignInActivity.class));
			finish();
		});

		binding.signUpButton.setOnClickListener(v -> {
			CreateAccount();
		});
	}

	private void CreateAccount() {

		name = "None";
		email = Objects.requireNonNull(binding.emailIDR.getText()).toString().trim();
		password = Objects.requireNonNull(binding.passwordIDR.getText()).toString().trim();
		mobile = Objects.requireNonNull(binding.mobileIDR.getText().toString());
		address = "None";

		ProgressDialog dialog = new ProgressDialog(this);
		dialog.setCancelable(false);
		dialog.setMessage("Creating your account...");
		dialog.show();

		Call<ResponseModel> call = ApiController.getInstance()
				.getApi().signUpApi(name, email, password, mobile, address);

		call.enqueue(new Callback<ResponseModel>() {
			@Override
			public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

				ResponseModel responseModel = response.body();
				String result = responseModel.getMessage();
				UserModel userModel = new UserModel(email,password);

				if (response.isSuccessful()) {
					dialog.dismiss();
					if (result.equals("inserted")) {
						sharedPreferenceManager.saveUser(userModel);
						Snackbar.make(binding.signUpLayout, "Account Created Successfully", Snackbar.LENGTH_LONG).show();
						startActivity(new Intent(SignUpActivity.this, MainActivity.class));
						finish();
					} else if (result.equals("failed")) {
						Snackbar.make(binding.signUpLayout, "Failed", Snackbar.LENGTH_LONG).show();
					} else if (result.equals("exists")) {
						Snackbar.make(binding.signUpLayout, "Already have an account", Snackbar.LENGTH_LONG).show();
					}
					binding.emailIDR.setText("");
					binding.mobileIDR.setText("");
					binding.passwordIDR.setText("");
				} else {
					Snackbar.make(binding.signUpLayout, "Something went wrong", Snackbar.LENGTH_LONG).show();

				}
			}

			@Override
			public void onFailure(Call<ResponseModel> call, Throwable t) {
				dialog.dismiss();
				Snackbar.make(binding.signUpLayout, "Error : " + t.getMessage(), Snackbar.LENGTH_LONG).show();
				Log.e("error1", "Error = " + t.getMessage());
				binding.emailIDR.setText("");
				binding.mobileIDR.setText("");
				binding.passwordIDR.setText("");
			}
		});

	}

	@Override
	protected void onStart() {
		super.onStart();
		if (sharedPreferenceManager.isLoggedIn()){
			startActivity(new Intent(SignUpActivity.this,MainActivity.class));
			finish();
		}
	}
}