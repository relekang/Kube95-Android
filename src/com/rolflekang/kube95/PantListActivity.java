package com.rolflekang.kube95;

import java.util.Date;

import android.R.layout;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class PantListActivity extends ListActivity implements OnClickListener {
	private Pantekassa pantList;
	private PantAdapter pAdapter;
	private HttpConnector httpCon;
	private TextView sumTextField;
	private Button saveBtn;
	private EditText amountField, userField;
	private DatePicker datePicker;
	private Dialog addDialog;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addDialog = new Dialog(this);
		pantList = new Pantekassa();
		httpCon = new HttpConnector("10.0.1.3");
		pantList.parseStrings(httpCon.getList(httpCon.PANT));
		pAdapter = new PantAdapter(this, R.layout.pantlistrow, pantList);
		setContentView(R.layout.pantlist);
		setListAdapter(pAdapter);
		sumTextField = (TextView) this.findViewById(R.id.sumtext);
		sumTextField.setText("Det er " + pantList.getSum() + "kr i pantekassa!");
//		createTestList();
		ListView list = getListView();
		registerForContextMenu(list);
		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> av, View v, int pos, long id) {

			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.addmenu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.add:
	        	addDialog.setContentView(R.layout.addpant);
	        	addDialog.setTitle("Legg til pant");
	        	saveBtn = (Button) addDialog.findViewById(R.id.savebtn);
	        	amountField = (EditText) addDialog.findViewById(R.id.amountfield);
	        	datePicker = (DatePicker) addDialog.findViewById(R.id.datepicker);
	        	userField = (EditText) addDialog.findViewById(R.id.userfield);
	        	saveBtn.setOnClickListener(this);
	        	addDialog.show();
	        	break;

	    }
	    return true;
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.savebtn:
			if(httpCon.sendPant(new Date(datePicker.getYear(),datePicker.getMonth(), datePicker.getDayOfMonth()), Double.parseDouble(amountField.getText().toString()), userField.getText().toString()))
				Toast.makeText(getApplicationContext(), "Panten ble lagt inn", Toast.LENGTH_SHORT );
			else
				Toast.makeText(getApplicationContext(), "Det oppstod en feil", Toast.LENGTH_SHORT );
			addDialog.dismiss();
			break;
		}
	}
	@SuppressWarnings("unused")
	private void createTestList() {
		for (int i = 0; i < 5; i++) { pantList.add(new Pant(new Date(), 200, "Rofl"));}
		pAdapter.notifyDataSetChanged();
	}
	

}
