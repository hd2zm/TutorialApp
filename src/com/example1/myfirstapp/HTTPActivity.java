package com.example1.myfirstapp;

import java.io.PrintWriter;
import java.io.StringWriter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class HTTPActivity extends Activity {

	TextView httpTV;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_http);
		httpTV = (TextView)findViewById(R.id.tvHttp);
		GetMethodExample test = new GetMethodExample();
		try{
			httpTV.setText(test.getInternetData());	
		} catch(Exception e){
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			httpTV.setText(sw.toString());
		}
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.htt, menu);
		return true;
	}

}
