package com.virvx.main;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SLiteHelper extends SQLiteOpenHelper{
	
	public SLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	public static final String TABLE_ACCOUNTS="accounts";
	public static final String COLUMN_ID="_id";
	public static final String COLUMN_ACCOUNT_NAME="account_name";
	public static final String COLUMN_REG_ID="reg_id";
	
	
	private static final String DATABASE_NAME="accounts.db";
	private static final int DATABASE_VERSION=1;
	
	//database creation sql
	
	private static final String DATABASE_CREATE="create table "+TABLE_ACCOUNTS+"("
			+COLUMN_ID+" integer primary key autoincrement,"
			+COLUMN_ACCOUNT_NAME+" text not null,"
			+COLUMN_REG_ID+" text not null);";

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE);
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(SLiteHelper.class.getName(), "Upgrading database from version "+oldVersion+" to "
				+newVersion+", which will destroy all old");
		
		db.execSQL("DROP TABLE IF EXIST "+TABLE_ACCOUNTS);
		onCreate(db);		
	}
	
	
			

}
