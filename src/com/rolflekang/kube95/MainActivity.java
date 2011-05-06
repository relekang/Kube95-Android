package com.rolflekang.kube95;



import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	private Button menuPantBtn, changeCleanerButton, saveUserNameButton;
	private TextView cleanerTextView, nextCleanerTextView, nextCleanWeekTextView;
	private EditText userNameField;
	private Settings settings;
	private Dialog userNameDialog;
	private CleanGuy cleanGuy;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        settings = new Settings(getApplicationContext());
        if(!settings.isUserNameSet()) viewUserNameDialog();
        
        
        cleanGuy = new CleanGuy();
        
        cleanerTextView = (TextView) this.findViewById(R.id.cleanertextview);
        cleanerTextView.setText(cleanGuy.getCleaner());
        nextCleanerTextView = (TextView) this.findViewById(R.id.nextcleanertextview);
        nextCleanerTextView.setText(cleanGuy.getNextCleaner());
        nextCleanWeekTextView = (TextView) this.findViewById(R.id.nextcleanweektextview);
        if(settings.isUserNameSet()) nextCleanWeekTextView.setText(Integer.toString(cleanGuy.getNextCleanWeek(settings.getUserName())));
        
        menuPantBtn = (Button) this.findViewById(R.id.menupantbtn);
        menuPantBtn.setOnClickListener(this);
        changeCleanerButton = (Button) this.findViewById(R.id.menuchangecleanerbtn);
        changeCleanerButton.setOnClickListener(this);
    }

	private void viewUserNameDialog() {
		userNameDialog = new Dialog(this);
		userNameDialog.setContentView(R.layout.usernamedialog);
		userNameDialog.setTitle("Legg inn navnet ditt");
		userNameDialog.setCancelable(false);
		userNameField = (EditText) userNameDialog.findViewById(R.id.usernamefield);
		userNameField.setInputType(EditorInfo.TYPE_TEXT_FLAG_CAP_WORDS);
		saveUserNameButton = (Button) userNameDialog.findViewById(R.id.usernamesavebtn);
		saveUserNameButton.setOnClickListener(this);
		userNameDialog.show();
		
	}

	public void onClick(View v) {
		switch(v.getId()){
		case R.id.menupantbtn:
			Intent i = new Intent(this, PantListActivity.class);
			startActivityForResult(i, 0);
			break;
		case R.id.menuchangecleanerbtn:
			Intent i1 = new Intent(this, ChangeCleanerActivity.class);
			startActivityForResult(i1, 0);
			break;
		case R.id.usernamesavebtn:
			if(userNameField.getText().toString() == ""){
				Toast.makeText(getApplicationContext(), "Du m� skrive inn navn", Toast.LENGTH_LONG);
				break;
			}
			settings.setUserName(userNameField.getText().toString());
			nextCleanWeekTextView.setText(Integer.toString(cleanGuy.getNextCleanWeek(userNameField.getText().toString())));
			userNameDialog.dismiss();
			break;
		case R.id.menu_deleteusernamebtn:
			settings.deleteUserName();
			viewUserNameDialog();
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