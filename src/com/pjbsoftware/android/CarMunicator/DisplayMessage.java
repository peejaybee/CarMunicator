package com.pjbsoftware.android.CarMunicator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextPaint;
import android.widget.TextView;

import java.lang.String;
import java.util.Timer;
import java.util.TimerTask;

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
//		mDisplay.setText(mWords[0]);
		mDisplay.setEnabled(true);
	}

	public void onWindowFocusChanged(boolean hasFocus) {
		setTextSizeForMessage(mDisplay,mWords);
		cycleWords();

	}

	private void setTextSizeForMessage(TextView view, String[] words){
		float currentSize = view.getTextSize();
		float newSize = 20000F;
		float candidate = 0;
		
		TextPaint viewPaint = view.getPaint();
		Rect box = new Rect();
		float viewWidth = (float) view.getWidth();
		float viewHeight = (float) view.getHeight();
		
		for (int i = 0; i < words.length; i++){
			viewPaint.getTextBounds(words[i],0, words[i].length(), box);
			float sX = box.width();
			float sY = box.height();
			candidate = currentSize * Math.min(viewWidth / sX, viewHeight / sY);
			newSize = Math.min(newSize,candidate);
		}
		
		view.setTextSize(newSize);
		
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