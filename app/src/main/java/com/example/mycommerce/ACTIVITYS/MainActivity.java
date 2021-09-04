package com.example.mycommerce.ACTIVITYS;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import com.example.mycommerce.R;
import com.example.mycommerce.CLASSES.SharedPreferenceManager;
import com.example.mycommerce.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
	ActivityMainBinding binding;
	SharedPreferenceManager sharedPreferenceManager;
	ActionBarDrawerToggle toggle;
	NavigationView navigationID;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		getWindow().setStatusBarColor(getResources().getColor(R.color.orange));
		getWindow().setNavigationBarColor(getResources().getColor(R.color.orange));
		Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);



		navigationID = findViewById(R.id.navigationID);
		sharedPreferenceManager = new SharedPreferenceManager(this);
		toggle = new ActionBarDrawerToggle(this, binding.drawerID, R.string.nav_open, R.string.nav_close);
		binding.drawerID.addDrawerListener(toggle);
		toggle.syncState();
		navigationID.setNavigationItemSelectedListener(this);


	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		if (toggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@SuppressLint("NonConstantResourceId")
	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {

		switch (item.getItemId()) {

			case R.id.categoryMenuId:
				Toast.makeText(MainActivity.this, "Category", Toast.LENGTH_SHORT).show();
				break;

			case R.id.myOrdersMenuId:
				Toast.makeText(MainActivity.this, "My Orders", Toast.LENGTH_SHORT).show();
				break;

			case R.id.myCartMenuId:
				Toast.makeText(MainActivity.this, "My Carts", Toast.LENGTH_SHORT).show();
				break;

			case R.id.accountMenuId:
				Toast.makeText(MainActivity.this, "My Account", Toast.LENGTH_SHORT).show();
				break;

			case R.id.logoutMenuId:
				logOutUser();
				break;
		}
		binding.drawerID.closeDrawer(GravityCompat.START);
		return true;
	}

	private void logOutUser() {
		sharedPreferenceManager.logOut();
		startActivity(new Intent(MainActivity.this,SignInActivity.class));
		finish();
	}

}