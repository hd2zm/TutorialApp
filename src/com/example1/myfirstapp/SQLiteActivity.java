package com.example1.myfirstapp;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLiteActivity extends Activity implements OnClickListener{

	Button sqlUpdate, sqlView, sqlGetInfo, sqlEdit, sqlDelete;
	EditText sqlName, sqlEmail, sqlRowID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite);
		sqlUpdate = (Button) findViewById(R.id.sqliteB);
		sqlView = (Button) findViewById(R.id.viewB);
		sqlName = (EditText) findViewById(R.id.nameEditText);
		sqlEmail = (EditText) findViewById(R.id.emailEditText);
		sqlGetInfo = (Button) findViewById(R.id.getInfoB);
		sqlEdit = (Button) findViewById(R.id.editEntryB);
		sqlDelete = (Button) findViewById(R.id.deleteEntryB);
		sqlRowID = (EditText) findViewById(R.id.rowIDEText);
		
		sqlUpdate.setOnClickListener(this);
		sqlView.setOnClickListener(this);
		sqlGetInfo.setOnClickListener(this);
		sqlEdit.setOnClickListener(this);
		sqlDelete.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sqlite, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.sqliteB:{
			
			boolean didItWork = true;
			try{
				String name = sqlName.getText().toString();
				String email = sqlEmail.getText().toString();
			
				DatabaseHandler entry = new DatabaseHandler(SQLiteActivity.this);
				entry.open();
				entry.createEntry(name, email);
				entry.close();
			}catch (Exception e){
				didItWork = false;
			}finally{
				Dialog d = new Dialog(this);
				TextView tv = new TextView(this);
				if(didItWork){
					d.setTitle("UPDATED DATA");
					tv.setText("Your input is recorded");
				}
				else{
					d.setTitle("ERROR");
					tv.setText("Cannot upload data");
				}
				d.show();
			}
			break;
		}
		case R.id.viewB:{
			//Intent i = new Intent("com.example1.myfirstapp.SQLView");
			Intent i = new Intent(this, SQLView.class);
			startActivity(i);
			break;
		}
		case R.id.getInfoB:{
			String s = sqlRowID.getText().toString();
			long l = Long.parseLong(s);
			
			DatabaseHandler dh = new DatabaseHandler(this);
			dh.open();
			String returnName = dh.getName(l);
			String returnEmail = dh.getEmail(l);
			dh.close();
			
			sqlName.setText(returnName);
			sqlEmail.setText(returnEmail);

			break;
		}
		case R.id.editEntryB:{
			
			String s = sqlRowID.getText().toString();
			long row = Long.parseLong(s);
			String name = sqlName.getText().toString();
			String email = sqlEmail.getText().toString();
			
			DatabaseHandler dh = new DatabaseHandler(this);
			dh.open();
			dh.updateEntry(row, name, email);
			dh.close();
			break;
		}
		case R.id.deleteEntryB:{
			
			String s = sqlRowID.getText().toString();
			long row = Long.parseLong(s);

			DatabaseHandler dh = new DatabaseHandler(this);
			dh.open();
			dh.deleteEntry(row);
			dh.close();
			
			break;
		}
		}
	}

}
