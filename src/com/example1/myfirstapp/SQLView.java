package com.example1.myfirstapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class SQLView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlview);
		TextView tv = (TextView) findViewById(R.id.tvSQLinfo);
		DatabaseHandler dh = new DatabaseHandler(this);
		dh.open();
		String data = dh.getData();
		dh.close();
		tv.setText(data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sqlview, menu);
		return true;
	}

}
