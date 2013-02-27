package com.pjbsoftware.android.CarMunicator;

import android.provider.BaseColumns;
import android.content.ContentValues;

public class Message implements BaseColumns {
	public static final int NUMBER_OF_MESSAGES = 6;
	public static final String mInitialCaptions[] = { "Thanks", "Thank yoooo",
			"Signal", "Too Close", "Sorry!", "Now You Find It" };

	public static final String mInitialTexts[] = { "Thanks!",
			"All I wanna do is to thank youuuu",
			"That flippy bit on the steering column is the TURN SIGNAL",
			"I\'m sorry, have we been introduced?",
			"Sorry about that -- my fault!",
			"Now that I've passed you, you find the accelerator!" };

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
