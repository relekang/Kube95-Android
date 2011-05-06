package com.rolflekang.kube95;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
		
		String week = "Bytt fra uke " + cleanGuy.getNextCleanWeek(settings.getUserName()) + " til";
		curentWeekView.setText(week);
		
		nextList = cleanGuy.getNextCleanersList(cleanGuy.getNextCleanWeek(settings.getUserName()));
		spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item , nextList);
		newWeekSelector.setAdapter(spinnerAdapter);
		
		saveButton.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
			//TODO:
			switch (v.getId()) {
			case R.id.changecleaner_savebtn:
				String selected = (String) newWeekSelector.getSelectedItem();
				String[] week = selected.split("\\:");
				int newWeekNr = Integer.parseInt(week[0]);
				cleanGuy.swap(cleanGuy.getNextCleanWeek(settings.getUserName()), newWeekNr);
				Toast.makeText(getApplicationContext(), "Du byttet uke "+cleanGuy.getNextCleanWeek(settings.getUserName())+" med uke "+newWeekNr, Toast.LENGTH_LONG).show();
				finish();
				break;

			default:
				break;
			}
	}
}
