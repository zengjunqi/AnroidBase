package com.zengyan.androidbase;

import java.io.IOException;
import java.util.List;

import com.zengyan.androidbase.model.Student;
import com.zengyan.androidbase.services.XmlParser;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class XmlParserAct extends Activity implements OnClickListener {
	EditText edtext;
	Button btnParser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xmlparseractivity);

		edtext = (EditText) findViewById(R.id.etText);
		btnParser = (Button) findViewById(R.id.btnParser);
		btnParser.setOnClickListener(this);


		//this.getResources().getAssets().open(fileName)
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnParser: getParserData();
			
			break;

		default:
			break;
		}
	}
	
	private void getParserData() {
		
		 try {
			 StringBuilder sBuilder=new StringBuilder();
			List<Student> ls=XmlParser.PullParseXML(getAssets().open("student.xml"));
			for (Student student : ls) {
				
				sBuilder.append("name:"+student.getName()+"id:"+student.getId()+"group:"+student.getGroup()+"sex:"+student.getSex()+"age:"+student.getAge()+"\n");
			}
			
			edtext.setText(sBuilder.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
	}
	
	
}
