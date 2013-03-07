package com.pjbsoftware.android.CarMunicatorLibrary;

import android.app.Application;
import android.util.Log;

public class CarMunicatorLibraryApplication extends Application {
	public boolean isLiteVersion() {
		Log.d("CarMunicatorLibrary", getPackageName());
		return getPackageName().toLowerCase().contains("lite"); 
	}

}
