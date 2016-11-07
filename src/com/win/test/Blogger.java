package com.win.test;

import android.widget.TextView;



public abstract class Blogger { //error handling and debugging, also other things
	
	public static boolean displayToSelectedView(String dispped, TextView hook){
		if (!hook.equals(null)){
			String snickers = (String) hook.getText();
			if (!snickers.isEmpty()){
				hook.setText(snickers + "\n" + dispped);
				return true;
			} else {
				hook.setText(dispped);
				return true;
			}
	
		}
		return false;
	}
	
	
	public static boolean displayToStartActivity(String dispped){
		return displayToSelectedView(dispped, (TextView) StartActivity.potatoes.findViewById(R.id.testing));
	}

}

