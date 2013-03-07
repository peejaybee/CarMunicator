package com.pjbsoftware.android.CarMunicatorLibrary;

import com.pjbsoftware.android.CarMunicatorLibrary.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.pjbsoftware.android.CarMunicatorLibrary.Message;
import com.pjbsoftware.android.CarMunicatorLibrary.MessageDBHelper;

public class EditMessage extends Activity implements View.OnClickListener {
	private Message myMessage;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit);
		((Button) findViewById(R.id.btnOK)).setOnClickListener(this);
		((Button) findViewById(R.id.btnCancel)).setOnClickListener(this);
	}

	public void onClick(View v) {
		if (v.getId() == R.id.btnCancel) {
			finish();
		}

		if (v.getId() == R.id.btnOK) {
			myMessage.mCaption = ((TextView) findViewById(R.id.txtDescription))
					.getText().toString();
			myMessage.mText = ((TextView) findViewById(R.id.txtMessage))
					.getText().toString();
			MessageDBHelper db = new MessageDBHelper(this);
			db.saveMessage(myMessage);

			finish();
		}
	}

	@Override
	protected void onStart() {
		super.onStart();

		Intent myIntent = getIntent();
		int id = myIntent.getIntExtra(Message._ID, 0);
		MessageDBHelper db = new MessageDBHelper(this);

		myMessage = db.getMessage(id);
		TextView myCaption = (TextView) findViewById(R.id.txtDescription);
		TextView myText = (TextView) findViewById(R.id.txtMessage);
		myCaption.setText(myMessage.mCaption);
		myText.setText(myMessage.mText);
	}

}
