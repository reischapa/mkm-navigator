package com.win.test;

import java.io.Serializable;
import java.util.ArrayList;

public class Table implements Serializable{

	public String htmlSourceCode;
	public ArrayList<ArrayList<String>> tableInStringForm  = new ArrayList<ArrayList<String>>();
	
	//start constructors
	
	public Table(String s){ // constructor with formatted string, <head, etc
		htmlSourceCode = s;
		constructTable();
	}
	
	
	
	public Table(String s, int cardinal){ // constructor with formatted string, <head, etc
		htmlSourceCode = s;
		constructTable(cardinal);
	}
	
	
	public Table(ArrayList<ArrayList<String>> in){
		tableInStringForm = in;
    }
	
	public Table(Table input){
		htmlSourceCode = input.htmlSourceCode;
		tableInStringForm = input.tableInStringForm;
	}
	//end constructors
    
    
    private void constructTable(int cardinal){
        tableInStringForm =  Parser.returnParsedTable(Parser.returnNthTagDebug(htmlSourceCode, "<tbody", "/tbody>", cardinal), "<tr", "/tr>", "<td", "/td>");
    }
	
    private void constructTable(){
		tableInStringForm =  Parser.returnParsedTable(Parser.returnFirstTag(htmlSourceCode, "<tbody", "/tbody>"), "<tr", "/tr>", "<td", "/td>");
    }
	
	
	
	
	

	
	
	
	
}
