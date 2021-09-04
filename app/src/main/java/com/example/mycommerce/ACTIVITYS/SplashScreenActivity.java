package com.example.mycommerce.ACTIVITYS;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mycommerce.R;
import com.example.mycommerce.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity {

	ActivitySplashScreenBinding binding;
	Animation animation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		getWindow().setStatusBarColor(this.getResources().getColor(R.color.orange));
		getWindow().setNavigationBarColor(getResources().getColor(R.color.orange));


		animation = AnimationUtils.loadAnimation(this,R.anim.splash_text_animation);
		binding.splashT.startAnimation(animation);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				try {
					startActivity(new Intent(SplashScreenActivity.this,LiquidSwipeIntroSliderActivity.class));
					finish();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		},1500);


	}
}