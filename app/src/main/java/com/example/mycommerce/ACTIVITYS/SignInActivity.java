package com.example.mycommerce.ACTIVITYS;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mycommerce.API.ApiController;
import com.example.mycommerce.R;
import com.example.mycommerce.CLASSES.ResponseModel;
import com.example.mycommerce.CLASSES.SharedPreferenceManager;
import com.example.mycommerce.CLASSES.UserModel;
import com.example.mycommerce.databinding.ActivitySignInBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

	ActivitySignInBinding binding;
	String email, password;
	SharedPreferenceManager sharedPreferenceManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivitySignInBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		Objects.requireNonNull(getSupportActionBar()).hide();
		getWindow().setStatusBarColor(getResources().getColor(R.color.seljuk_blue));
		getWindow().setNavigationBarColor(getResources().getColor(R.color.seljuk_blue));


		sharedPreferenceManager = new SharedPreferenceManager(this);

		binding.signUpText.setOnClickListener(v -> {
			startActivity(new Intent(this, SignUpActivity.class));
			finish();
		});

		binding.signInButton.setOnClickListener(v -> {
			SignInUser();
		});
	}

	private void SignInUser() {
		email = Objects.requireNonNull(binding.emailID.getText()).toString().trim();
		password = Objects.requireNonNull(binding.passwordID.getText()).toString().trim();

		ProgressDialog dialog = new ProgressDialog(this);
		dialog.setCancelable(false);
		dialog.setMessage("Creating your account...");
		dialog.show();

		if (email.isEmpty() || password.isEmpty()) {
			dialog.dismiss();
			Snackbar.make(binding.signInLayout, "Required Email and Password", Snackbar.LENGTH_LONG).show();
		} else {
			Call<ResponseModel> call = ApiController.getInstance().getApi().signInApi(email, password);

			call.enqueue(new Callback<ResponseModel>() {
				@Override
				public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

					ResponseModel signUpResponse = response.body();
					String result = signUpResponse.getMessage();
					UserModel userModel = new UserModel(email, password);

					if (response.isSuccessful()) {
						dialog.dismiss();
						if (result.equals("exist")) {
							sharedPreferenceManager.saveUser(userModel);
							Snackbar.make(binding.signInLayout, "Account Logged In", Snackbar.LENGTH_LONG).show();
							startActivity(new Intent(SignInActivity.this, MainActivity.class));
							finish();

						}

					} else {
						Snackbar.make(binding.signInLayout, "Something went wrong", Snackbar.LENGTH_LONG).show();
					}

					binding.emailID.setText("");
					binding.passwordID.setText("");
				}

				@Override
				public void onFailure(Call<ResponseModel> call, Throwable t) {
					dialog.dismiss();

					Snackbar.make(binding.signInLayout, "Something went wrong", Snackbar.LENGTH_LONG).show();
					binding.emailID.setText("");
					binding.passwordID.setText("");

				}
			});

		}
	}


	@Override
	protected void onStart() {
		super.onStart();
		if (sharedPreferenceManager.isLoggedIn()){
			startActivity(new Intent(SignInActivity.this,MainActivity.class));
			finish();
		}
	}
}