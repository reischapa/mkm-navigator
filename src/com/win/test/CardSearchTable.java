package com.win.test;

import java.util.ArrayList;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CardSearchTable extends CSTSerializable{
	
	
	
	

	//start constructors
	
	public CardSearchTable(String card, String input){
		super(card, input);
		
	}
	
	public CardSearchTable(String card, String input, int cardinal){
		super(card,input,cardinal);
		
	}
	
	
	public CardSearchTable(Table input, String cardName){
		super(input, cardName);
		
	}
	
	public CardSearchTable(CSTSerializable input){
		super(input);
	}
	
	//end constructors
	
	
	public CSTSerializable returnSerializableSelf(){
		return new CSTSerializable(this);
	}
	
	
	public LinearLayout createViews(RelativeLayout mTableHook){ //creates linear layout of relativelayouts that are attached to mTableHook, whatever it may be, as long as it is a Relative Layout
		return createViews(mTableHook, true);
	}
	
	
public LinearLayout createViews(RelativeLayout mTableHook, final boolean autoAttach){ //creates linear layout of relativelayouts that are attached to mTableHook, whatever it may be, as long as it is a Relative Layout
		LinearLayout buttonRows ;
		if (tableInStringForm!=null){
			
			buttonRows = new LinearLayout(mTableHook.getContext());
			buttonRows.setOrientation(LinearLayout.VERTICAL);
			buttonRows.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
			for (int i = 0; i<tableInStringForm.size(); i++){
				RelativeLayout.LayoutParams rowLayoutParams = new RelativeLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
				
				
				ArrayList<String> arr = tableInStringForm.get(i);
				
				RelativeLayout rel = new RelativeLayout(buttonRows.getContext());
				
				rel.setPadding(0, 0, 0, 3 );
				
				
				
				rel.setLayoutParams(rowLayoutParams);
				
				for (int f = 0; f < arr.size(); f++){
					RelativeLayout.LayoutParams columnLayoutParams = new RelativeLayout.LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
					ImageView im = new ImageView(mTableHook.getContext());
					im.setId(-1000-i);
					if (f==1){

						
						columnLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, -1);
						im.setLayoutParams(columnLayoutParams);
						
					
						
						im.setPadding(0, 0, 5 , 0);			
						//String wut = Engine.returnExpansionName((String ) arr.get(f));					
						im.setImageDrawable((mTableHook.getResources()).getDrawable(Engine.returnExResID("Future Sight")));					
						rel.addView(im);					
					} else if (f==4){
						TextView but = new TextView(mTableHook.getContext());
						
						//but.setTextColor(Color.parseColor("#000000"));
						columnLayoutParams.addRule(RelativeLayout.RIGHT_OF , im.getId());
						but.setLayoutParams(columnLayoutParams);
						but.setText(Parser.killAll(arr.get(f)));
						rel.addView(but);
						
					} else if (f==7){
						TextView but = new TextView(mTableHook.getContext());
						
						//but.setTextColor(Color.parseColor("#000000"));
						columnLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, -1);
						but.setLayoutParams(columnLayoutParams);
						but.setText(Parser.priceFormatter(Parser.killAll(arr.get(f))));
						rel.addView(but);
					}		
				}
				
				buttonRows.addView(rel);
			}
			if (autoAttach){
				mTableHook.addView(buttonRows);
			}
			
		} else {
			
			return null;
			
		}		
		return buttonRows;
	}
	
	
	
	
	
	
	//end non static methods
	

}
