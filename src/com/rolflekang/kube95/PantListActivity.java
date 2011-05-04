package com.rolflekang.kube95;

import java.util.Date;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class PantListActivity extends ListActivity {
	private Pantekassa pantList;
	private PantAdapter pAdapter;
	private HttpConnector httpCon;
	private TextView sumTextField;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
	@SuppressWarnings("unused")
	private void createTestList() {
		for (int i = 0; i < 5; i++) { pantList.add(new Pant(new Date(), 200, "Rofl"));}
		pAdapter.notifyDataSetChanged();
	}

}
