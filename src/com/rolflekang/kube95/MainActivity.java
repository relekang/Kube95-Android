package com.rolflekang.kube95;



import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
	private Button menuPantBtn, changeCleanerButton, saveUserNameButton;
	private TextView cleanerTextView, nextCleanerTextView;
	private EditText userNameField;
	private Settings settings;
	private Dialog userNameDialog;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        settings = new Settings();
        if(!settings.isUserNameSet()) viewUserNameDialog();
        
        
        CleanGuy cleaner = new CleanGuy();
        
        cleanerTextView = (TextView) this.findViewById(R.id.cleanertextview);
        cleanerTextView.setText(cleaner.getCleaner());
        nextCleanerTextView = (TextView) this.findViewById(R.id.nextcleanertextview);
        nextCleanerTextView.setText(cleaner.getNextCleaner());
        
        menuPantBtn = (Button) this.findViewById(R.id.menupantbtn);
        menuPantBtn.setOnClickListener(this);
        changeCleanerButton = (Button) this.findViewById(R.id.menuchangecleanerbtn);
        changeCleanerButton.setOnClickListener(this);
    }

	private void viewUserNameDialog() {
		userNameDialog = new Dialog(this);
		userNameDialog.setContentView(R.layout.usernamedialog);
		userNameDialog.setTitle("Legg inn navnet ditt");
		userNameField = (EditText) userNameDialog.findViewById(R.id.usernamefield);
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
			settings.setUserName(userNameField.getText().toString());
			userNameDialog.dismiss();
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