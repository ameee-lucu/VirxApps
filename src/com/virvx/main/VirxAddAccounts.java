package com.virvx.main;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class VirxAddAccounts extends Activity implements OnClickListener,
		OnItemSelectedListener {

	private AccountsDataSource dataSource;
	private Spinner spinner;
	private Spinner availableAccounts;
	private EditText editText;
	private String accountName;
	private String availableName;
	static final String[] accounts = { "BackOffice", "Facebook", "textAll" };
	private List<String> available;

	@Override
	public void onCreate(Bundle icile) {
		super.onCreate(icile);
		setContentView(R.layout.add_account);

		dataSource = new AccountsDataSource(this);
		dataSource.open();

		List<Accounts> listAccounts = dataSource.getAllAccounts();
		if (listAccounts != null && listAccounts.isEmpty() == false) {
			available = new ArrayList<String>();
			List<String> listAva = new ArrayList<String>();

			for (Accounts s : listAccounts) {

				listAva.add(s.getAccount_name() + "-" + s.getReg_id());

			}

			

			availableAccounts = (Spinner) findViewById(R.id.alreadyregistered);
			availableAccounts.setOnItemSelectedListener(this);
			ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, listAva);
			availableAccounts.setAdapter(aa);

			spinner = (Spinner) findViewById(R.id.spinner);
			spinner.setOnItemSelectedListener(this);
			ArrayAdapter<String> aas = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, accounts);
			spinner.setAdapter(aas);

			editText = (EditText) findViewById(R.id.regid);

		} else {
			Log.w(VirxAppsActivity.class.getSimpleName(),
					"Account Not Found, Ready to add Account");
			spinner = (Spinner) findViewById(R.id.spinner);
			editText = (EditText) findViewById(R.id.regid);
			spinner.setOnItemSelectedListener(this);
			ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, accounts);
			spinner.setAdapter(aa);
		}

	}

	public void onClick(View view) {
		Log.w("this", "on Method onClick");
		if (!editText.getText().equals(null)&&!(editText.getText().toString().equalsIgnoreCase(""))) {
			System.out.println("ada pertama");
			Accounts accounts = new Accounts();
			accounts.setReg_id(editText.getText().toString());
			accounts.setAccount_name(getAccountName());
			accounts = dataSource.addNewAccounts(accounts);
			//((StateHelper) this.getApplication()).setAccounts(accounts);
			Intent intent = new Intent(this, VirxChooseAccount.class);
			this.startActivity(intent);
		}else{
			//System.out.println("availableName name="+availableName==null?"null":availableName.equals("")?"kosong":availableName);
			if(availableName!=null&&!(availableName.trim().equals(""))){
				System.out.println("ada kedua");
				Intent intent = new Intent(this, VirxChooseAccount.class);
				this.startActivity(intent);
			}
		}
	}

	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {

		int parentId = parent.getId();
		switch (parentId) {
		case R.id.alreadyregistered:
			setAvailableName((String) availableAccounts.getItemAtPosition(pos));
			break;

		case R.id.spinner:
			setAccountName((String) spinner.getItemAtPosition(pos));
			break;

		default:
			break;
		}

	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	        moveTaskToBack(true);
	        return true;
	    }
	    this.finish();
	    return super.onKeyDown(keyCode, event);
	    
	}

	public String getAvailableName() {
		return availableName;
	}

	public void setAvailableName(String availableName) {
		this.availableName = availableName;
	}
	
	
	

}
