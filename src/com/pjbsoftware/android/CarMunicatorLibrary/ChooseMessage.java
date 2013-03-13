package com.pjbsoftware.android.CarMunicatorLibrary;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseMessage extends Activity implements
		View.OnClickListener, View.OnLongClickListener {

	private String mPackageNameFullVersion = "com.pjbsoftware.carmunicatorfull";
	
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
		} else {
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
		return true;
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

	private void showDialog() {
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		adb.setTitle(R.string.strBuyDialogTitle)
				.setCancelable(true)
				.setMessage(R.string.buy_pitch)
				.setPositiveButton(R.string.strBuy,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface di, int id) {
								try {
									startActivity(new Intent(
											Intent.ACTION_VIEW,
											Uri.parse("market://details?id=" + mPackageNameFullVersion)));
								} finally {
									startActivity(new Intent(
											Intent.ACTION_VIEW,
											Uri.parse("market://details?id=" + mPackageNameFullVersion)));
								}
								di.dismiss();
							}
						})
				.setNegativeButton(R.string.strCancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface di, int id) {
								di.dismiss();
							}
						});
		AlertDialog ad = adb.create();
		ad.show();
	}

	public boolean isLiteVersion() {
		return ((CarMunicatorLibraryApplication) getApplication())
				.isLiteVersion();
	}
}
