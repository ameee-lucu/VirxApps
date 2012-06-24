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
import android.widget.Button;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

public class VirxChooseAccount extends Activity implements OnClickListener,
		OnItemSelectedListener {

	private Accounts accounts;
	private Spinner availableAccounts;
	private AccountsDataSource dataSource;
	private Button button;

	@Override
	public void onCreate(Bundle icile) {
		super.onCreate(icile);
		setContentView(R.layout.choose_account);
		button = (Button) findViewById(R.id.showcode);
		availableAccounts = (Spinner) findViewById(R.id.available_account);
		availableAccounts.setOnItemSelectedListener(this);

		
		
		dataSource = new AccountsDataSource(this);
		dataSource.open();

		List<Accounts> listAccounts = dataSource.getAllAccounts();
		List<String> values = new ArrayList<String>();
		for (Accounts s : listAccounts) {
			values.add(s.getAccount_name());
		}

		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, values);
		availableAccounts.setAdapter(aa);
	}

	public void onClick(View view) {
		if (view.getId() == R.id.addaccount) {
			Log.w(VirxChooseAccount.class.getName(), "Prepare to add Account");
			Intent intent = new Intent(this, VirxAddAccounts.class);
			this.startActivity(intent);

		} else if (view.getId() == R.id.showcode) {

			// Log.w("TOTP BERFORE", state.getAccounts().getReg_id());
			System.out.println("REGID="
					+ ((StateHelper) this.getApplication()).getAccounts()
							.getReg_id());
			Log.w(VirxChooseAccount.class.getName(),
					"Prepare To the Next Page[Main Page]");
			Intent intent = new Intent(this, VirxCalculator.class);
			this.startActivity(intent);

		} else {
			Log.w(VirxChooseAccount.class.getName(),
					"Prepare To the Back Page[Main Page]");
			Intent intent = new Intent(this, VirxAddAccounts.class);
			this.startActivity(intent);
			dataSource.deleteAll();
		}

	}

	public Accounts getAccounts() {
		return accounts;
	}

	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}

	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {

		accounts = new Accounts();
		accounts.setAccount_name((String) parent.getItemAtPosition(pos));
		Log.w("TESTTTTT", accounts.getAccount_name());
		accounts = dataSource.getAccounts(accounts);
		System.out.println("REGID LOKAL=" + accounts.getReg_id());
		((StateHelper) this.getApplication()).setAccounts(accounts);
		System.out.println("REGID="
				+ ((StateHelper) this.getApplication()).getAccounts()
						.getReg_id());
		// Log.w("TOTP BERFORE", state.getAccounts().getReg_id());

	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	// Override the onKeyDown method
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// replaces the default 'Back' button action
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// do whatever you want the 'Back' button to do
			// as an example the 'Back' button is set to start a new Activity
			// named 'NewActivity'
			this.startActivity(new Intent(this, VirxAddAccounts.class));
		}
		return true;
	}

}
