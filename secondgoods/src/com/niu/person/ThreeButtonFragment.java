package com.niu.person;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

public class ThreeButtonFragment extends Fragment implements OnClickListener{
	Activity activity;//主类上下文
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(com.niu.secondgoods.R.layout.three_button_person, container, false);
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
	}
	public void onAttach(Activity activity) {
    	super.onAttach(activity);
    	this.activity = activity;
    }
	//点击方法
	@Override
	public void onClick(View arg0) {
		
	}

}