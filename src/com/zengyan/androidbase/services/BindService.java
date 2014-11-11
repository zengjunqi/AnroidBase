package com.zengyan.androidbase.services;

import com.zengyan.androidbase.interfaces.IMiddlePerson;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class BindService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i("ZENG", "Bind Service Bind");
		return new MiddlePerson();
		
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i("ZENG", "Bind Service Create");
	}
	
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("ZENG", "Bind Service Destroy");
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i("ZENG", "Bind Service Unbind");
		return super.onUnbind(intent);
		
	}
	
	public void mothodInService() {
		Toast.makeText(this, "你调用到了绑定服务里的方法!", 1000).show();
	}
	
	private class MiddlePerson extends Binder implements IMiddlePerson{
		public void callMethodInService() {
			mothodInService();
		}
	}

}
