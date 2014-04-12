package com.example1.myfirstapp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SerializeActivity extends Activity implements OnClickListener{

	Vector<Integer> vector;
	Button submitValue;
	Button serialize;
	Button deserialize;
	EditText cell;
	EditText file;
	String filename;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_serialize);
		submitValue = (Button) findViewById(R.id.arrayButton);
		submitValue.setOnClickListener(this);
		serialize = (Button) findViewById(R.id.serializeButton);
		serialize.setOnClickListener(this);
		deserialize = (Button) findViewById(R.id.deserializeButton);
		deserialize.setOnClickListener(this);
		vector = new Vector<Integer>();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.serialize, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.arrayButton:{
			cell = (EditText) findViewById(R.id.inputArray);
			String text = cell.getText().toString();
			if(text.equals("NA")){
				TextView tv = (TextView)findViewById(R.id.viewArray);
				tv.setText(vector.toString());
			}
			else {
				try{
					Integer i = Integer.parseInt(text);
					vector.add(i);
					Toast.makeText(SerializeActivity.this, "VALUE ENTERED", Toast.LENGTH_LONG).show();
					cell.setText("");
				}
				catch(NumberFormatException e) {
					Toast.makeText(SerializeActivity.this, "ERROR: NOT VALID INPUT", Toast.LENGTH_LONG).show();
				}
			}
			break;
		}
		case R.id.serializeButton:{
			file = (EditText) findViewById(R.id.fileName);
			filename = file.getText().toString();
			FileOutputStream fos = null;
	        BufferedOutputStream bos = null;
	        ObjectOutputStream output = null;	        
	        if (!filename.trim().equals(""))
	        {
	        	filename+=".ser";
	            try
	            {
	            	fos = this.openFileOutput(filename,Context.MODE_PRIVATE); //part of Context class
	                bos = new BufferedOutputStream(fos);
	                output = new ObjectOutputStream(bos);
	                output.writeObject(vector);
	                
	                Toast.makeText(SerializeActivity.this, "SERIALIZED VECTOR", Toast.LENGTH_LONG).show();
	                
	                output.close();
	                bos.close();
	                fos.close();
	            }
	            catch (Exception err)
	            {
	            	Toast.makeText(SerializeActivity.this, "ERROR: CANNOT CREATE FILE", Toast.LENGTH_LONG).show();
	            };
	        }
	        else
	        {
	        	  Toast.makeText(SerializeActivity.this, "ERROR: MUST PUT IN NAME", Toast.LENGTH_LONG).show();
	        }
	        break;
		}
		 
		case R.id.deserializeButton:{
			file = (EditText) findViewById(R.id.fileName);
			filename = file.getText().toString() + ".ser";
			
			Serializable serial = null;
			FileInputStream fis = null;
	        BufferedInputStream bis = null;
	        ObjectInputStream input = null;

	        try
	        {   
	        	fis = this.openFileInput(filename);
	            bis = new BufferedInputStream(fis);
	            input = new ObjectInputStream(bis);
	            serial = (Serializable) input.readObject();
	            
				TextView tv = (TextView)findViewById(R.id.viewArray);
				tv.setText("Deserialized Version: " + serial.toString());
				
				vector = (Vector<Integer>) serial;
				
				input.close();
				bis.close();
				fis.close();
	        }
	        catch(Exception e)
	        {
	        	Toast.makeText(SerializeActivity.this, "ERROR: FILE DOESN'T EXIST", Toast.LENGTH_LONG).show();
	        }
	        break;
		}
			
	        
		}
	}

}
