package com.pjbsoftware.android.CarMunicatorLibrary;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

public class CarMunicatorLibraryApplication extends Application {
	@SuppressLint("DefaultLocale")
	public boolean isLiteVersion() {
		Log.d("CarMunicatorLibrary", getPackageName());
		return getPackageName().toLowerCase().contains("lite"); 
	}

}
