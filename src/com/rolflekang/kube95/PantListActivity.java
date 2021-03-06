package com.rolflekang.kube95;

import java.util.ArrayList;
import java.util.Date;

import com.rolflekang.kube95.util.HttpConnector;
import com.rolflekang.kube95.util.Settings;

import android.app.Dialog;
import android.app.ListActivity;
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
	private Settings settings;
	private TextView sumTextField;
	private Button saveBtn;
	private EditText amountField, userField;
	private DatePicker datePicker;
	private Dialog addDialog;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addDialog = new Dialog(this);
		pantList = new Pantekassa();
		httpCon = new HttpConnector(getApplicationContext(),1);
		pantList.update(httpCon.getPant());
		pAdapter = new PantAdapter(this, R.layout.pantlistrow, pantList);
		settings = new Settings(getApplicationContext());
		setContentView(R.layout.pantlist);
		setListAdapter(pAdapter);
		sumTextField = (TextView) this.findViewById(R.id.sumtext);
		sumTextField.setText("Det er " + pantList.getSum() + "kr i pantekassa!");
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
//	        	addDialog.setContentView(R.layout.addpant);
//	        	addDialog.setTitle("Legg til pant");
//	        	saveBtn = (Button) addDialog.findViewById(R.id.savebtn);
//	        	amountField = (EditText) addDialog.findViewById(R.id.amountfield);
//	        	datePicker = (DatePicker) addDialog.findViewById(R.id.datepicker);
//	        	userField = (EditText) addDialog.findViewById(R.id.userfield);
//	        	if(settings.getUserName() != null) userField.setVisibility(8);
//	        	saveBtn.setOnClickListener(this);
//	        	addDialog.show();
	        	Toast.makeText(this, "This functionality is currently not available", Toast.LENGTH_LONG).show();
	        	break;

	    }
	    return true;
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.savebtn:
			String user = "";
			if(settings.getUserName() != null) user = settings.getUserName();
			else user = userField.getText().toString();
			
			if(httpCon.sendPant(new Date(datePicker.getYear(),datePicker.getMonth(), datePicker.getDayOfMonth()), Double.parseDouble(amountField.getText().toString()), user )) {
				pantList.update(httpCon.getPant());
				pAdapter.notifyDataSetChanged();
				Toast.makeText(getApplicationContext(), "Panten ble lagt inn", Toast.LENGTH_SHORT ).show();
			}
			else
				Toast.makeText(getApplicationContext(), "Det oppstod en feil", Toast.LENGTH_SHORT ).show();
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
