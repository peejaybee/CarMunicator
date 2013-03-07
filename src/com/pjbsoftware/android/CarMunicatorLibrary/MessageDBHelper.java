package com.pjbsoftware.android.CarMunicatorLibrary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.ContentValues;

import com.pjbsoftware.android.CarMunicatorLibrary.Message;

public class MessageDBHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "messages.db";
	private static final String TABLE_NAME = "messages";
	private static final int DB_VERSION = 1;
	public static final String CREATE_STRING = "CREATE TABLE " + TABLE_NAME
			+ "( " + Message._ID + " INTEGER PRIMARY KEY" + ", "
			+ Message.CAPTION + " TEXT" + ", " + Message.TEXT + " TEXT" + ");";

	public MessageDBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(CREATE_STRING);
		// load the new table
		ContentValues val = new ContentValues();
		for (int i = 0; i < Message.NUMBER_OF_MESSAGES; i++) {
			val.put(Message._ID, i);
			val.put(Message.CAPTION, Message.mInitialCaptions[i]);
			val.put(Message.TEXT, Message.mInitialTexts[i]);
			db.insert(TABLE_NAME, Message.mInitialCaptions[i], val);
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public void saveMessage(Message msg) {
		SQLiteDatabase db = getWritableDatabase();

		db.replace(TABLE_NAME, null, msg.toContentValues());
	}

	public Message getMessage(int messageID) {
		String strSelection = "_ID = " + messageID;
		String[] cols = new String[] { Message._ID, Message.CAPTION,
				Message.TEXT };

		SQLiteDatabase db = getReadableDatabase();

		Cursor rs = db.query(TABLE_NAME, cols, strSelection, null, null, null,
				null);

		Message msg = new Message();
		rs.moveToFirst();
		msg.mID = rs.getInt(0);
		msg.mCaption = rs.getString(1);
		msg.mText = rs.getString(2);

		db.close();

		return msg;

	}

	public String[] getCaptions() {
		String[] captions = new String[Message.NUMBER_OF_MESSAGES];
		String[] cols = new String[] { Message._ID, Message.CAPTION,
				Message.TEXT };

		SQLiteDatabase db = getReadableDatabase();

		Cursor rs = db.query(TABLE_NAME, cols, null, null, null, null,
				Message._ID);

		while (rs.moveToNext()) {
			int id = rs.getInt(0);
			captions[id] = rs.getString(1);
		}
		return captions;
	}
}
