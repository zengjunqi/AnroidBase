package com.zengyan.androidbase;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

public class MySensor extends Activity {

	private SensorManager sm;
	MyListener listener;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.sensor_activity);
		Log.i("ZENG", "Light===");
		sm=(SensorManager) getSystemService(SENSOR_SERVICE);
		
		Sensor sensor=sm.getDefaultSensor(Sensor.TYPE_LIGHT);
		 listener = new MyListener();
		sm.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI);
	}
	
	private class MyListener implements SensorEventListener{

		@Override
		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-generated method stub
			
			float light=event.values[0];
			Log.i("ZENG", "Light:"+light);
			
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	protected void onDestroy() {
		sm.unregisterListener(listener);
		listener = null;
		super.onDestroy();
	}
}
