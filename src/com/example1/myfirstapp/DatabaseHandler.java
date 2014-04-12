package com.example1.myfirstapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler{

	//Contacts table Columns Name
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "persons_name";
	public static final String KEY_EMAIL = "persons_email";
	
	//Database and Table Name
	private static final String DATABASE_NAME = "Contactsdb";
	private static final String DATABASE_TABLE = "Contacts";
	
	//Database Version
	private static final int DATABASE_VERSION = 1;

	// Context
	private SQLiteDatabase ourDatabase;
	private final Context ourContext;
	private DatabaseHelper ourHelper;
	
	//inner class that helps create and update database
	private static class DatabaseHelper extends SQLiteOpenHelper{
	
		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			String CREATE_CONTACTS_TABLE = "CREATE TABLE " + DATABASE_TABLE + " ("
					+ KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
					+ KEY_NAME + " TEXT NOT NULL, "
					+ KEY_EMAIL + " TEXT NOT NULL" + ");";
			db.execSQL(CREATE_CONTACTS_TABLE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	
	public DatabaseHandler(Context c){
		ourContext = c;
	}
	
	//Creating/opening connection. Returns instance of database
	public DatabaseHandler open() throws SQLException{
		ourHelper = new DatabaseHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	//closes database
	public void close() {
		ourHelper.close();
	}

	public void createEntry(String name, String email) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_EMAIL, email);
		ourDatabase.insert(DATABASE_TABLE, null, cv);
		
	}

	public String getData() {
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_EMAIL};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";
		
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iName = c.getColumnIndex(KEY_NAME);
		int iEmail = c.getColumnIndex(KEY_EMAIL);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = result + c.getString(iRow) + " " + c.getString(iName) + "\t" + c.getString(iEmail) + "\n";
		}
		
		return result;
	}

	public String getName(long l) {
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_EMAIL};
		try{
			Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null, null, null, null);
			c.moveToFirst();
			String name = c.getString(1);
			return name;
		}
		catch(Exception e){
			return "NO EXISTENCE";
		}
	}

	public String getEmail(long l) {
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_EMAIL};
		try{
			Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null, null, null, null);
			c.moveToFirst();
			String email = c.getString(2);
			return email;
		}
		catch(Exception e){
			return "value@doesnotexist.com";
		}
	}

	public void updateEntry(long row, String name, String email) {
		// TODO Auto-generated method stub
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_NAME, name);
		cvUpdate.put(KEY_EMAIL, email);
		ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + row, null);
		
	}

	public void deleteEntry(long row) {
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + row, null);
		
	}
	
	
}
