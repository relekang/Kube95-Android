package com.rolflekang.kube95;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class ChangeCleanerActivity extends Activity implements OnClickListener{
	private CleanGuy cleanGuy;
	private Settings settings;
	private TextView curentWeekView;
	private Spinner newWeekSelector;
	private Button saveButton;
	private ArrayAdapter<String> spinnerAdapter;
	private ArrayList<String> nextList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.changecleaner);
		
		cleanGuy = new CleanGuy();
		settings = new Settings(getApplicationContext());
		curentWeekView = (TextView) this.findViewById(R.id.changecleaner_week);
		newWeekSelector = (Spinner) this.findViewById(R.id.changecleaner_newweekselector);
		saveButton = (Button) this.findViewById(R.id.changecleaner_savebtn);
		
//		Calendar cal = Calendar.getInstance();
//		String week = "Bytt fra uke " + cal.get(Calendar.WEEK_OF_YEAR) + " til";
		String week = "Bytt fra uke " + cleanGuy.getNextCleanWeek(settings.getUserName()) + " til";
		curentWeekView.setText(week);
		
//		nextList = cleanGuy.getNextCleanersList(cal.get(Calendar.WEEK_OF_YEAR));
		nextList = cleanGuy.getNextCleanersList(cleanGuy.getNextCleanWeek(settings.getUserName()));
		spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item , nextList);
		newWeekSelector.setAdapter(spinnerAdapter);
		
		saveButton.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
			//TODO:
	}
}
