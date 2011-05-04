package com.rolflekang.kube95;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class PantListActivity extends ListActivity {
	   public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setListAdapter(new ArrayAdapter<String>(this, R.layout.pantlistrow, new String[]{".","..","..."}));
	        ListView list = getListView();
//	        registerForContextMenu(list);
	        list.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
					
				}
			});
	    }
	   
}
