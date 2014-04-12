package com.example1.myfirstapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class CountriesActivity extends Activity {

	private static final String[] COUNTRIES = new String[] {
        "Belgium", "France", "Italy", "Germany", "Spain", "Switzerland", "Ireland", "Brazil", "Bogota", "India",
        "Great Britain", "Colombia", "Mexico", "USA", "Canada"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_countries);
		
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.Countries);
        textView.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.countries, menu);
		return true;
	}

}
