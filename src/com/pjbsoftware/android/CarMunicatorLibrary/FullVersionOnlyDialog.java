package com.pjbsoftware.android.CarMunicatorLibrary;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FullVersionOnlyDialog extends DialogFragment {

	public FullVersionOnlyDialog() {
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		View view = inflater.inflate(R.layout.fullversiononlydialog, container);
//		TextView mPitch = (TextView) view.findViewById(R.id.txtPitch);
		getDialog().setTitle(R.string.strBuyDialogTitle);
		
		Button btnCancel = (Button) view.findViewById(R.id.btnCancel);
		btnCancel.setOnClickListener(new OnClickListener() {
			public void onClick(View v){
				dismiss();
			}
		});
		
		Button btnBuy = (Button) view.findViewById(R.id.btnBuy);
		btnCancel.setOnClickListener(new OnClickListener() {
			public void onClick(View v){
				try{
					startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=com.pjbsoftware.carmunicatorfull")));
				}finally{
					startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=com.pjbsoftware.carmunicatorfull")));					
				}
				dismiss();
			}
		});
		
		return view;
	}
}
