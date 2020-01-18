package com.ustb.utils;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View.OnClickListener;
import android.widget.Toast;

public abstract class BaseActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContent();
		init();
		data();
		
	}

	public abstract void setContent();

	public abstract void init();

	public abstract void data();

	public void showToast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
}
