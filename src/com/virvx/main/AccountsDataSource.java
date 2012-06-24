package com.virvx.main;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Comment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.SyncStateContract.Columns;
import android.util.Log;

public class AccountsDataSource {
	
	private SQLiteDatabase database;
	private SLiteHelper dbHelper;
	private String[] allColumns={SLiteHelper.COLUMN_ID,SLiteHelper.COLUMN_ACCOUNT_NAME,SLiteHelper.COLUMN_REG_ID};

	public AccountsDataSource(Context context){
		dbHelper=new SLiteHelper(context);
	}
	
	public void open()throws SQLException{
		database=dbHelper.getWritableDatabase();
	}
	
	public void close(){
		dbHelper.close();
	}
	
	
	
	public List<Accounts> getAllAccounts(){
		List<Accounts> listAccount=new ArrayList<Accounts>();
		Cursor cursor=database.query(SLiteHelper.TABLE_ACCOUNTS, allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		
			Log.w("Cursor", "Cursor more than 0");
			while (!cursor.isAfterLast()) {
				Accounts accounts=cursorToAccounts(cursor);
				listAccount.add(accounts);
				cursor.moveToNext();
			}
		

		//make sure to close the cursor
		
		cursor.close();
		return listAccount;
	}
	
	public Accounts addNewAccounts(Accounts accounts){
		ContentValues values=new ContentValues();
		values.put(SLiteHelper.COLUMN_ACCOUNT_NAME, accounts.getAccount_name());
		values.put(SLiteHelper.COLUMN_REG_ID, accounts.getReg_id());
		long insertId=database.insert(SLiteHelper.TABLE_ACCOUNTS, null, values);
		Cursor cursor=database.query(SLiteHelper.TABLE_ACCOUNTS, allColumns, SLiteHelper.COLUMN_ID+"="+insertId, null, null, null, null);
		cursor.moveToFirst();
		Accounts newAcc=cursorToAccounts(cursor);
		cursor.close();
		return newAcc;
	}

	public void deleteAll(){
		database.delete(SLiteHelper.TABLE_ACCOUNTS, null, null);
	}
	
	public Accounts getAccounts(Accounts accounts){		
		Cursor cursor=database.query(SLiteHelper.TABLE_ACCOUNTS, allColumns, "account_name like '"+accounts.getAccount_name()+"'",null, null,null,null,null);
		cursor.moveToFirst();
		
		System.out.println("Cursor size="+cursor.getCount());
		System.out.println("Cursor item="+cursor.getLong(0));
		System.out.println("Cursor item="+cursor.getString(1));
		
		accounts.setId(cursor.getLong(0));
		accounts.setAccount_name(cursor.getString(1));
		accounts.setReg_id(cursor.getString(2));
		cursor.close();
		System.out.println("ON GET ACCPNTS="+accounts.getReg_id());
		return accounts;
	}
	
	public Accounts cursorToAccounts(Cursor cursor){
		Accounts accounts=new Accounts();
		accounts.setId(cursor.getLong(0));
		accounts.setAccount_name(cursor.getString(1));
		accounts.setReg_id(cursor.getString(2));
		return accounts;
	}
	
	
	
}
