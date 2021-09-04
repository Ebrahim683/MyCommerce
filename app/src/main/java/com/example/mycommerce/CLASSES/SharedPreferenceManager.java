package com.example.mycommerce.CLASSES;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {

	Context context;
	private static String SHARED_PREF = "shared_pref";
	private static SharedPreferences sharedPreferences;
	private static SharedPreferences.Editor editor;

	public SharedPreferenceManager(Context context) {
		this.context = context;
	}

	public void saveUser(UserModel userModel){
		sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		editor.putString("email", userModel.getEmail());
		editor.putBoolean("loggedId", true);
		editor.apply();
	}

	public boolean isLoggedIn(){
		sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
		return sharedPreferences.getBoolean("loggedId",false);
	}

	public void logOut(){
		sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		editor.clear();
		editor.apply();
	}


}
