package com.ustb.utils;

import java.io.InputStream;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class NetTask extends AsyncTask<String, Void, Bitmap> {

	private ImageView imageView;
	
	
	public NetTask(ImageView imageView) {
		super();
		this.imageView = imageView;
	}


	@Override
	protected Bitmap doInBackground(String... arg0) {
		Bitmap bitmap = null;
		try {
			
			URL url = new URL(arg0[0]);
			InputStream inputStream = url.openStream();
			
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds=true;
			
			BitmapFactory.decodeStream(inputStream, null, options);
			int width = options.outWidth;
			int height = options.outHeight;
			
			options.inSampleSize=2;
			int min = Math.min(width, height);
			if (min>100) {
				options.inSampleSize=min/100;
			}
			options.inJustDecodeBounds=false;
			
			URL url2 = new URL(arg0[0]);
			InputStream inputStream2 = url2.openStream();
			bitmap = BitmapFactory.decodeStream(inputStream2, null, options);
			
			inputStream.close();
			inputStream2.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}
	
	@Override
	protected void onPostExecute(Bitmap result) {
		imageView.setImageBitmap(result);
	}
}
