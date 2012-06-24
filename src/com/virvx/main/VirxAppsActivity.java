package com.virvx.main;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;


public class VirxAppsActivity extends Activity implements OnItemSelectedListener{
	
	private AccountsDataSource dataSource;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        dataSource=new AccountsDataSource(this);
        dataSource.open();
        
        List<Accounts> listAccounts=dataSource.getAllAccounts();        
        if(listAccounts!=null&&listAccounts.isEmpty()==false){
        	Log.w(VirxAppsActivity.class.getSimpleName(), "Account Found, Now Prepare to List Available Account To Redirect to Choose Account Intent");
        	Intent intent=new Intent(this, VirxChooseAccount.class);
        	this.startActivity(intent);
        }else{
        	Log.w(VirxAppsActivity.class.getSimpleName(), "Account Not Found, Ready to add Account");
        	Intent intent=new Intent(this, VirxAddAccounts.class);
        	this.startActivity(intent);
        }
        
        
    }

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
    
    
	
	
	
}