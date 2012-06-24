package com.virvx.main;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import com.virvx.util.TOTP;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class VirxCalculator extends Activity implements OnClickListener{

	private TextView textView,totp;
	private Counter counter;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        textView=(TextView)findViewById(R.id.timer);
        totp=(TextView)findViewById(R.id.totpview);
        
        counter=new Counter(30000, 1000);
        generateTotp();
        
        
    }
	
	public void generateTotp(){
		String result="";
		String steps="0";
		String seed="3132333435363738393031323334353637383930";
		
		System.out.println("REGID="+((StateHelper)this.getApplication()).getAccounts().getReg_id());
		Log.e("TOTP",((StateHelper)this.getApplication()).getAccounts().getReg_id());
		
		long T0 = 0;
		long X = 30;
		long T = (new Date().getTime() - T0) / X;
		steps = Long.toHexString(T).toUpperCase();
		result=TOTP.generateTOTP(((StateHelper)this.getApplication()).getAccounts().getReg_id(), steps, "8", "HmacSHA1");		
		counter.start();
		totp.setText(result);
	}
	
	public void onClick(View view) {
		counter.cancel();
		Intent intent=new Intent(this, VirxChooseAccount.class);
		this.startActivity(intent);
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// replaces the default 'Back' button action
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// do whatever you want the 'Back' button to do
			// as an example the 'Back' button is set to start a new Activity
			// named 'NewActivity'
			this.startActivity(new Intent(this, VirxChooseAccount.class));
		}
		return true;
	}
	
	public class Counter extends CountDownTimer{

		public Counter(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			// TODO Auto-generated constructor stub
		}

		public void onTick(long millisUntilFinished) {
        	textView.setText(""+millisUntilFinished / 1000);
        }

        public void onFinish() {
        	generateTotp();
        }
		

	}

}
