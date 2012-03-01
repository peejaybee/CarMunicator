package com.pjbsoftware.android.CarMunicator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.androidbears.components.ScrollingTextView;

public class DisplayMessage extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
    }

    @Override
    protected void onStart()
    {
	super.onStart();
	Intent myIntent = getIntent();
	int id = myIntent.getIntExtra(Message._ID, 0);
	MessageDBHelper db = new MessageDBHelper(this);
	Message myMessage = db.getMessage(id);
	ScrollingTextView myDisplay = (ScrollingTextView) findViewById(R.id.txtDisplay);
	myDisplay.setText(myMessage.mText);
    }

    @Override
    protected void onResume()
    {
	super.onResume();
	ScrollingTextView myDisplay = (ScrollingTextView) findViewById(R.id.txtDisplay);
	myDisplay.setEnabled(true);	
	
    }
    
}