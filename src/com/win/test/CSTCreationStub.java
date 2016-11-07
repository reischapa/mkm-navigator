package com.win.test;

import java.security.PublicKey;

import android.app.Activity;

public class CSTCreationStub extends TableCreationStub {
	
	protected Activity mRefActivity;
	
	protected int pageNumber = 1;
	
	protected final CardSearchTable inputCST;
	protected CSTSerializable outputCST = null;
	
	
	public CSTCreationStub(Activity inHook,  CardSearchTable foo, int numericInput){
		super(Engine.returnSearchUrlObject(foo.cardName, numericInput));
		pageNumber = numericInput;
		mRefActivity = inHook;
		inputCST = foo;
	}
	
	public CSTCreationStub(Activity inHook, CardSearchTable foo){
		super(Engine.returnSearchUrlObject(foo.cardName));
		
		mRefActivity = inHook;
		inputCST = foo;
	}
	
	public CSTCreationStub(CardSearchTable foo){
		super(Engine.returnSearchUrlObject(foo.cardName));
		inputCST = foo;
	}
	
	
	public CSTCreationStub(CardSearchTable foo, int numericInput){
		super(Engine.returnSearchUrlObject(foo.cardName, numericInput));
		pageNumber = numericInput;
		
		inputCST = foo;
	}
	
	
	@Override
	protected Void doInBackground(Void... params) {
			super.doInBackground();
			
			if (mContentsTableForm!=null){
				outputCST = new CSTSerializable(mContentsTableForm, inputCST.cardName);
				outputCST.maxSearchPage = inputCST.maxSearchPage;
			}
			publishProgress("Search for page " + pageNumber + " complete.");
			return null;
			
		
	}
	
	
	
	

}