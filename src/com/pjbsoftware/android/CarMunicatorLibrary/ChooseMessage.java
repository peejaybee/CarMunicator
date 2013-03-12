package com.pjbsoftware.android.CarMunicatorLibrary;


import com.pjbsoftware.android.CarMunicatorLibrary.R;
import com.pjbsoftware.android.CarMunicatorLibrary.Message;
import com.pjbsoftware.android.CarMunicatorLibrary.MessageDBHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


public class ChooseMessage extends FragmentActivity implements View.OnClickListener,
		View.OnLongClickListener {
	
	private static final int FULL_VERSION_REQUIRED = 1; 

	private int buttonID[] = { R.id.button1, R.id.btnBuy, R.id.button3,
			R.id.button4, R.id.button5, R.id.button6 };

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose);
		for (int i = 0; i < Message.NUMBER_OF_MESSAGES; i++) {
			Button thisButton = (Button) findViewById(buttonID[i]);

			thisButton.setOnClickListener(this);

			thisButton.setOnLongClickListener(this);
		}
	}

	public void onClick(View v) {
		int id = 0;
		for (int i = 0; i < 6; i++) {
			if (buttonID[i] == v.getId()) {
				id = i;
			}
		}
		Intent myInt = new Intent();
		myInt.setClass(this, DisplayMessage.class);
		myInt.putExtra(Message._ID, id);
		startActivity(myInt);

	}

	public boolean onLongClick(View v) {
		int id = 0;
		if (isLiteVersion()) {
			showDialog(); 
		}else{
			for (int i = 0; i < 6; i++) {
				if (buttonID[i] == v.getId()) {
					id = i;
				}
			}
			Intent myInt = new Intent();
			myInt.setClass(this, EditMessage.class);
			myInt.putExtra(Message._ID, id);
			startActivity(myInt);
		}
		return false;
	}

	@Override
	protected void onResume() {
		super.onResume();
		MessageDBHelper dbh = new MessageDBHelper(this);
		String[] captions = dbh.getCaptions();
		for (int i = 0; i < Message.NUMBER_OF_MESSAGES; i++) {
			Button thisButton = (Button) findViewById(buttonID[i]);
			thisButton.setText(captions[i]);
		}

	}
	
	private void showDialog(){
	    FragmentManager fm = getSupportFragmentManager();
	    FullVersionOnlyDialog fvod = new FullVersionOnlyDialog();
	    fvod.show(fm,"fullversiononly");

	}
	
	 	public boolean isLiteVersion(){
		return ((CarMunicatorLibraryApplication)getApplication()).isLiteVersion();
	}
}
