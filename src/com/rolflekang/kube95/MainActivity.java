package com.rolflekang.kube95;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	private Button menuPantBtn;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        menuPantBtn = (Button) this.findViewById(R.id.menupantbtn);
        menuPantBtn.setOnClickListener(this);
    }

	public void onClick(View v) {
		switch(v.getId()){
		case R.id.menupantbtn:
			Intent i = new Intent(this, PantListActivity.class);
			startActivityForResult(i, 0);
			break;
//		case R.id.feedbackbtn:
//			Toast.makeText(this, "...", Toast.LENGTH_SHORT);
////			Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
////			emailIntent.setType("plain/text");
////			emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"kube95app@rolflekang.com"});
////			emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "[Feedback]");
////			startActivity(emailIntent);
//			break;
		}
	}
}