package com.ustb.school;

import com.ustb.application.MyApplication;
import com.ustb.demo.PushService;
import com.ustb.entity.E_User;
import com.ustb.page.Fragment_1;
import com.ustb.page.Fragment_2;
import com.ustb.page.Fragment_3;
import com.ustb.page.Fragment_4;
import com.ustb.school.R;
import com.ustb.utils.SPUtils;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class MainActivity extends FragmentActivity implements OnClickListener {
	private LinearLayout l1, l2, l3, l4;
	private Fragment_1 f1;
	private Fragment_2 f2;
	private Fragment_3 f3;
	private Fragment_4 f4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_main);

		init();
		
	}

	private void init() {
		l1 = (LinearLayout) findViewById(R.id.bottom1);
		l2 = (LinearLayout) findViewById(R.id.bottom2);
		l3 = (LinearLayout) findViewById(R.id.bottom3);
		l4 = (LinearLayout) findViewById(R.id.bottom4);
		l1.setOnClickListener(this);
		l2.setOnClickListener(this);
		l3.setOnClickListener(this);
		l4.setOnClickListener(this);
		changeBottom(1);

		// 进入判断用户是否登录
		loginTest();
	}

	private void loginTest() {
		MyApplication app = (MyApplication) getApplication();
		boolean logincode = SPUtils.contains(MainActivity.this, "logincode");
//		Log.d("test", logincode + "");
		if (!logincode) {
			app.setLoginCode(false);
		} else {
			int id = (Integer) SPUtils.get(this, "id", 0);
			String account = (String) SPUtils.get(this, "account", "0");
			String name = (String) SPUtils.get(this, "name", "0");
			String pass = (String) SPUtils.get(this, "pass", "0");
			String email = (String) SPUtils.get(this, "email", "0");
			String info = (String) SPUtils.get(this, "info", "0");
			String photourl = (String) SPUtils.get(this, "photourl", "0");
			String sex = (String) SPUtils.get(this, "sex", "0");
			int admin = (Integer) SPUtils.get(this, "admin", 0);

			E_User user = new E_User();
			user.setId(id);
			user.setAccount(account);
			user.setName(name);
			user.setPass(pass);
			user.setEmail(email);
			user.setInfo(info);
			user.setPhotourl(photourl);
			user.setSex(sex);
			user.setAdmin(admin);

			app.setUser(user);
			app.setLoginCode(true);
			
			//启动MQTT服务
			startMqttService();
		}

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.bottom1:
			changeBottom(1);
			break;
		case R.id.bottom2:
			changeBottom(2);
			break;
		case R.id.bottom3:
			changeBottom(3);
			break;
		case R.id.bottom4:
			changeBottom(4);
			break;
		}

	}

	/**
	 * 碎片替换
	 * 
	 * @param frag
	 * @param id
	 */
	public void replaceFragment(Fragment frag, int id) {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction tx = fm.beginTransaction();
		tx.replace(id, frag);
		tx.commit();
		// tx.commitAllowingStateLoss();
	}

	/**
	 * 更改底部样式并且更改界面
	 * 
	 * @param i
	 */
	private void changeBottom(int i) {
		String basecolor = "#ffffff";
		String selectcolor = "#F2F2F2";
		switch (i) {
		case 1:
			l1.setBackgroundColor(Color.parseColor(selectcolor));
			l2.setBackgroundColor(Color.parseColor(basecolor));
			l3.setBackgroundColor(Color.parseColor(basecolor));
			l4.setBackgroundColor(Color.parseColor(basecolor));
			l1.setSelected(true);
			l2.setSelected(false);
			l3.setSelected(false);
			l4.setSelected(false);
			if (f1 == null) {
				f1 = new Fragment_1();
			}
			replaceFragment(f1, R.id.main_fragment);
			break;
		case 2:
			l2.setBackgroundColor(Color.parseColor(selectcolor));
			l1.setBackgroundColor(Color.parseColor(basecolor));
			l3.setBackgroundColor(Color.parseColor(basecolor));
			l4.setBackgroundColor(Color.parseColor(basecolor));
			l1.setSelected(false);
			l2.setSelected(true);
			l3.setSelected(false);
			l4.setSelected(false);
			if (f2 == null) {
				f2 = new Fragment_2();
			}
			replaceFragment(f2, R.id.main_fragment);
			break;
		case 3:
			l3.setBackgroundColor(Color.parseColor(selectcolor));
			l2.setBackgroundColor(Color.parseColor(basecolor));
			l1.setBackgroundColor(Color.parseColor(basecolor));
			l4.setBackgroundColor(Color.parseColor(basecolor));
			l1.setSelected(false);
			l2.setSelected(false);
			l3.setSelected(true);
			l4.setSelected(false);
			if (f3 == null) {
				f3 = new Fragment_3();
			}
			replaceFragment(f3, R.id.main_fragment);
			break;
		case 4:
			l4.setBackgroundColor(Color.parseColor(selectcolor));
			l2.setBackgroundColor(Color.parseColor(basecolor));
			l3.setBackgroundColor(Color.parseColor(basecolor));
			l1.setBackgroundColor(Color.parseColor(basecolor));
			l1.setSelected(false);
			l2.setSelected(false);
			l3.setSelected(false);
			l4.setSelected(true);
			if (f4 == null) {
				f4 = new Fragment_4();
			}
			replaceFragment(f4, R.id.main_fragment);
			break;

		}

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		f2.onActivityResult(requestCode, resultCode, data);
	}
	
	private void startMqttService() {

		MyApplication app = (MyApplication) getApplication();
		int id = app.getUser().getId();
		Editor editor = getSharedPreferences(PushService.TAG, MODE_PRIVATE)
				.edit();
		editor.putString(PushService.PREF_DEVICE_ID, String.valueOf(id));
		editor.commit();
		PushService.actionStart(getApplicationContext());

	}
}
