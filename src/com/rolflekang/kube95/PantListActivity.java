package com.rolflekang.kube95;

import java.util.Date;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class PantListActivity extends ListActivity {
	private Pantekassa pantList;
	private PantAdapter pAdapter;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pantList = new Pantekassa();
		pAdapter = new PantAdapter(this, R.layout.pantlistrow, pantList);
		setListAdapter(pAdapter);
		createTestList();
		ListView list = getListView();
		registerForContextMenu(list);
		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> av, View v, int pos, long id) {

			}
		});
	}
	private void createTestList() {
		for (int i = 0; i < 5; i++) { pantList.add(new Pant(new Date(), 200, "Rofl"));}
		pAdapter.notifyDataSetChanged();
	}

}
