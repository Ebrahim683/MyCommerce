package com.example.mycommerce.ACTIVITYS;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mycommerce.CLASSES.LiquidPagerAdapter;
import com.example.mycommerce.R;
import com.example.mycommerce.databinding.ActivityLiquideSwipeIntroSliderBinding;

public class LiquidSwipeIntroSliderActivity extends AppCompatActivity {

	ActivityLiquideSwipeIntroSliderBinding binding;
	LiquidPagerAdapter pagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityLiquideSwipeIntroSliderBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		getSupportActionBar().hide();
		getWindow().setStatusBarColor(getResources().getColor(R.color.purple_200));

		pagerAdapter = new LiquidPagerAdapter(getSupportFragmentManager(),1);
		binding.liquidPagerID.setAdapter(pagerAdapter);


	}
}