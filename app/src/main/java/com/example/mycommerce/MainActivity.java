package com.example.mycommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mycommerce.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
	ActivityMainBinding binding;
SharedPreferenceManager sharedPreferenceManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		sharedPreferenceManager = new SharedPreferenceManager(this);

	}

}