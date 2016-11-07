package com.win.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;

public class CSTSerializable extends Table implements Serializable {
	
	public int idOffset = 0;
	public String cardName = "/0";
    public int maxSearchPage = -1;
    public boolean delFlag = true;
    
    

	public CSTSerializable(String htmlInput){
		super(htmlInput);
		idOffset = Engine.returnNextIdOffset();
	}
	
	public CSTSerializable(String card, String htmlInput){
		super(htmlInput);
		idOffset = Engine.returnNextIdOffset();
		cardName = card;
        checkIfSingle();
	}
	
	public CSTSerializable(String card, String htmlInput, int cardinal){
		super(htmlInput, cardinal);
		idOffset = Engine.returnNextIdOffset();
		cardName = card;
        checkIfSingle();
	}
	
	
	public CSTSerializable(Table inTab, String card){
		super(inTab);
		idOffset = Engine.returnNextIdOffset();
		cardName = card;
        checkIfSingle();
	}

	
	public CSTSerializable(CSTSerializable input){
		super(input.tableInStringForm);
		cardName = input.cardName;
		idOffset = input.idOffset;
        maxSearchPage = input.maxSearchPage;
        delFlag = input.delFlag;
	}
	
	
	
	
	
	//end constructors
    
    public void checkIfSingle(){
        String usage = Parser.returnFirstTag(htmlSourceCode, "<select", "/select>");
        if (usage!=null){
            setMaxPageNumber();
            delFlag = false;
        } else {
            maxSearchPage = 1;
        }
    }
    
    private void setMaxPageNumber(){
        
        	int temp = Integer.parseInt((
        			Parser.killAll(
        					Parser.getHeaderContents(
        							Parser.getHeaderContents(htmlSourceCode, "<div class=\"navBarBottomText\"", "/div>")
        			, "<span class=\"vAlignMiddle\"", "/span>"))).split(" ")[0]);
        	
        	maxSearchPage = (int) Math.ceil(temp/30.0);
        
        
    }

	public  Bundle createNewInfoBundle(){
		Bundle savedInstanceState = new Bundle();
		
		savedInstanceState.putSerializable(SearchFragment.CST_SURVIVAL_GUARANTEE, this);
		savedInstanceState.putInt(SearchFragment.CST_PAGE_NUMBER_SURVIVAL_GUARANTEE, 1);
		savedInstanceState.putSerializable(SearchFragment.CST_PREV_PAGE_SURVIVAL_GUARANTEE, new HashMap<Integer,CSTSerializable>());
		savedInstanceState.putSerializable(SearchFragment.CST_PREV_PAGE_INDICES_SURV_GUARANTEE, new ArrayList<Integer>());
		
		return savedInstanceState;
	}
	
	public  Bundle createNewInfoBundle(int numb){
		Bundle savedInstanceState = new Bundle();
		
		savedInstanceState.putSerializable(SearchFragment.CST_SURVIVAL_GUARANTEE, this);
		savedInstanceState.putInt(SearchFragment.CST_PAGE_NUMBER_SURVIVAL_GUARANTEE, numb);
		savedInstanceState.putSerializable(SearchFragment.CST_PREV_PAGE_SURVIVAL_GUARANTEE, new HashMap<Integer,CSTSerializable>());
		savedInstanceState.putSerializable(SearchFragment.CST_PREV_PAGE_INDICES_SURV_GUARANTEE, new ArrayList<Integer>());
		
		return savedInstanceState;
	}
	
	public static Bundle createNewInfoBundle(CSTSerializable bInfo, int numb, Serializable bData,Serializable bIndex ){
		Bundle savedInstanceState = new Bundle();
		
		savedInstanceState.putSerializable(SearchFragment.CST_SURVIVAL_GUARANTEE, bInfo );
		savedInstanceState.putInt(SearchFragment.CST_PAGE_NUMBER_SURVIVAL_GUARANTEE, numb);
		savedInstanceState.putSerializable(SearchFragment.CST_PREV_PAGE_SURVIVAL_GUARANTEE, bData);
		savedInstanceState.putSerializable(SearchFragment.CST_PREV_PAGE_INDICES_SURV_GUARANTEE, bIndex);
		
		return savedInstanceState;
	}
	
	//onclicklisteners #sound
	
}
