package com.pjbsoftware.android.CarMunicator;

import com.pjbsoftware.android.CarMunicator.R;
import com.pjbsoftware.android.CarMunicator.Message;
import com.pjbsoftware.android.CarMunicator.MessageDBHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseMessage extends Activity implements View.OnClickListener,
		View.OnLongClickListener {
	private int buttonID[] = { R.id.button1, R.id.button2, R.id.button3,
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
		for (int i = 0; i < 6; i++) {
			if (buttonID[i] == v.getId()) {
				id = i;
			}
		}
		Intent myInt = new Intent();
		myInt.setClass(this, EditMessage.class);
		myInt.putExtra(Message._ID, id);
		startActivity(myInt);
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
}
