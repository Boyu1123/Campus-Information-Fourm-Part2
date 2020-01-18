package com.ustb.school;

import java.util.HashMap;
import java.util.Map;

import com.ustb.application.MyApplication;
import com.ustb.school.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.Toast;

public class LoadActivity extends Activity {
	private ImageView imageView;
	private MyApplication app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load);

		imageView = (ImageView) findViewById(R.id.load_img);

		AlphaAnimation alph = new AlphaAnimation(0, 1);
		alph.setDuration(2000);
		alph.setAnimationListener(listener);
		imageView.setAnimation(alph);

		app = (MyApplication) getApplication();
	}

	private AnimationListener listener = new AnimationListener() {

		@Override
		public void onAnimationStart(Animation arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationRepeat(Animation arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationEnd(Animation arg0) {
			Intent intent = new Intent(LoadActivity.this, MainActivity.class);
			startActivity(intent);
			finish();

		}
	};

}
