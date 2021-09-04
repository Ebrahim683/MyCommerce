package com.example.mycommerce.FRAGMENTS;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.mycommerce.R;
import com.example.mycommerce.ACTIVITYS.SignInActivity;

public class FragmentC extends Fragment {

	Button button;
	Context context;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_c, container, false);

		button = view.findViewById(R.id.start);
		context = view.getContext();

		if (savedData()){
			startApp();
		}

		button.setOnClickListener(v -> {
			saveData();
			startApp();

		});

		return view;
	}


	private void saveData() {
		SharedPreferences sharedPreference = getActivity().getSharedPreferences("saveData", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreference.edit();
		editor.putBoolean("isIntroOpened", true);
		editor.apply();
	}

	private boolean savedData(){
		SharedPreferences sharedPreference = getActivity().getSharedPreferences("saveData", Context.MODE_PRIVATE);
		boolean launch = sharedPreference.getBoolean("isIntroOpened",false);
		return launch;
	}

	private void startApp() {
		Intent intent = new Intent(getActivity(), SignInActivity.class);
		startActivity(intent);
		requireActivity().finish();
	}


}