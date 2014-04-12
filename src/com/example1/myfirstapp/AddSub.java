package com.example1.myfirstapp;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.widget.TextView;
import android.view.View;

public class AddSub extends Activity {

	public int counter = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_sub);
		setTextView();
	}
	
	public void setTextView()
	{
		TextView textView = (TextView) findViewById(R.id.textView2);
		textView.setText(counter+"");
		Random r = new Random();
		textView.setTextSize(r.nextInt(75));
		textView.setTextColor(Color.rgb(r.nextInt(265), r.nextInt(265), r.nextInt(265)));
	}
	
	public void add(View view)
	{
		counter = counter + 1;
		setTextView();		
	}
	
	public void subtract(View view)
	{
		counter = counter - 1;
		setTextView();		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_sub, menu);
		return true;
	}

}
