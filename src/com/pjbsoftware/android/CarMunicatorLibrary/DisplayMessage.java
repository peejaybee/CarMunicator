package com.pjbsoftware.android.CarMunicatorLibrary;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;

public class DisplayMessage extends Activity {
	
	public static int DISPLAY_INTERVAL = 1;
	private String mWords[];
	private int mWordCount = 0;
	TextView mDisplay;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Intent myIntent = getIntent();
		int id = myIntent.getIntExtra(Message._ID, 0);
		MessageDBHelper db = new MessageDBHelper(this);
		Message myMessage = db.getMessage(id);
		mWords = myMessage.mText.split("\\s");
		mWordCount = mWords.length;
	}

	@Override
	protected void onResume() {
		super.onResume();
		mDisplay = (TextView) findViewById(R.id.txtDisplay);
		mDisplay.setEnabled(true);
	}

	public void onWindowFocusChanged(boolean hasFocus) {
		setTextSizeForMessage(mDisplay,mWords);
		cycleWords();

	}

	private void setTextSizeForMessage(TextView view, String[] words){
		float newSize = 2000;
		float candidate;
		
		
		//See how wide each word is, scale it to fit
		//Use the smallest scale factor for the whole message
		for (int i = 0; i < words.length; i++){
			candidate = getMaximumTextSize(view,words[i]);
			newSize = Math.min(newSize,candidate);
		}
		view.setTextSize(TypedValue.COMPLEX_UNIT_PX,newSize);		
	}
	
	private float getMaximumTextSize(TextView view, String word){
		float lowFence = 0;
		float highFence = 2000;
		float candidate = lowFence;
		float width = view.getWidth() - view.getPaddingRight() - view.getPaddingLeft();
		
		
		while ((Math.abs(highFence - lowFence) / (highFence + lowFence) > .05) ){
			candidate = (lowFence + highFence) / 2;
			view.setTextSize(TypedValue.COMPLEX_UNIT_PX,candidate);
			if (view.getPaint().measureText(word) > width){
				//too wide
				highFence = candidate;
			} else {
				//not too wide
				lowFence = candidate;
			}
		}
		return lowFence; 
	}
	
	protected void cycleWords() {
		mDisplay = (TextView) findViewById(R.id.txtDisplay);
		new Timer().schedule(new TimerTask(){
			int i = 0;
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					public void run() {
						mDisplay.setText(mWords[i]);
						i = (i+1) % mWordCount;
					}
				});
			};
		}, 0, DISPLAY_INTERVAL * 1000L);
	}
	

}