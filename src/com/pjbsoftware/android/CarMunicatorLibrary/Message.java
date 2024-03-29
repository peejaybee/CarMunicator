package com.pjbsoftware.android.CarMunicatorLibrary;

import android.provider.BaseColumns;
import android.content.ContentValues;

public class Message implements BaseColumns {
	public static final int NUMBER_OF_MESSAGES = 6;
	public static final String mInitialCaptions[] = { "HELP", "Low Tire", 
			"Brake Lights", "Sorry!", "Gas Cap Open", "Turn Signal On" };

	public static final String mInitialTexts[] = { "CALL 911 SEND HELP", "You have a low tire!",
			 "Check your brake lights!", "I'm sorry about that!",
			"Your gas cap is open!", "Your turn signal is still on!" };

	public static final String CAPTION = "MessageCaption";
	public static final String TEXT = "MessageText";

	public String mCaption = "MessageCaption";
	public String mText = "MessageText";

	public int mID = 0;

	public ContentValues toContentValues() {
		ContentValues cv = new ContentValues();

		cv.put(_ID, mID);
		cv.put(CAPTION, mCaption);
		cv.put(TEXT, mText);

		return cv;

	}

}
