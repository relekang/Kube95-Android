package com.rolflekang.kube95;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PantAdapter extends ArrayAdapter<Pant> {
	private Context context;
	private LayoutInflater mInflater;
	private ArrayList<Pant> pantList;

	public PantAdapter(Context context, int textViewResourceId, ArrayList<Pant> pantList) {
		super(context, textViewResourceId, pantList);
		this.pantList = pantList;
		this.context = context;
		this.mInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		return pantList.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.pantlistrow, parent, false);
			holder = new ViewHolder();
			holder.amount = (TextView) convertView.findViewById(R.id.pantrow_amount);
			holder.date = (TextView) convertView.findViewById(R.id.pantrow_date);
			holder.user = (TextView) convertView.findViewById(R.id.pantrow_user);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// Fill in the actual story info
		Pant p = pantList.get(position);

		holder.amount.setText(p.getValue() + "kr");
		holder.date.setText(p.getDate().toString());
		holder.user.setText(p.getUser());
		return convertView;
	}

	static class ViewHolder {
		TextView amount;
		TextView date;
		TextView user;
	}

}