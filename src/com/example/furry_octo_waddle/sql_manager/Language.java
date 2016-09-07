package com.example.furry_octo_waddle.sql_manager;

import java.util.ArrayList;

import com.example.furry_octo_waddle.activities.MainActivity;

public class Language{

	protected int id = 0;
	protected String name;
	protected String code;

	public Language(String  name){
		name = format(name);
		this.code=name;//.substring(0, 2);
		this.name=name;
	}

	private String format(String name2) {
		Word_Translation.formatString(name2);
		while(name2.length()<2){
			name2= name2+"x";
		}
		return name2;
	}

	public Language(String code,String name){
		name = format(name);
		this.code=code;
		this.name=name;
	}

	public Language(Language parent, String code,String name){
		name = format(name);
		this.code=parent.getCode()+"_"+code;
		this.name=name;
	}

	public Language(Language parent, String name){
		name = format(name);
		this.code=parent.getCode()+"_"+name.substring(0, 2);
		this.name=name;
	}

	public Language(String in, String code,String name){
		name = format(name);
		this.id=Integer.parseInt(in);
		this.code=code;
		this.name=name;
	}

	public String getCode(){
		return code;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public boolean parentOf(Language langue){
		return langue.getCode().contains(code+"_");
	}
	
	@Override
	public boolean equals(Object language){
		return code.equalsIgnoreCase(((Language) language).getCode());
	}

	public String[] getArgsDB() {
		String[] ret =  {Word_Translation.forQuery(getId()),
				getCode(),
				getName(),
		} ;
		return ret;
	}

	public void setId(int newRowId) {
		id=newRowId;

	}

	public void print(){
		MainActivity.printDebug(0, code +" = "+ name);
	}

	public static class Languages_List extends ArrayList<Language>{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Languages_List(){
			super();
		}

		public boolean isDisplayed(Language langue){
			try{
				for (Language l : toArray(new Language[0])){
					if(l.parentOf(langue)||l.equals(langue)){
						return true;
					}	
				}
			}catch(Exception e){
				e.printStackTrace();
				MainActivity.printDebug(12, "isDisplayed" );

			}
			return false;
		}
		
		public String getCode(){
			MainActivity.printDebug(12, "list code");
			String ret = "";
			try{
				/*for (Object l : toArray()){
					MainActivity.printDebug(24,"kii");

					MainActivity.printDebug(24,l.getClass().getName());
				}*/
				for (Language l : toArray(new Language[0])){
					ret= ret+l.getCode()+"%";	
				}
			}catch(Exception e){
				e.printStackTrace();
				MainActivity.printDebug(12, "oorf" );

			}
			MainActivity.printDebug(12, "list code 32" + ret);
			return ret;
		}

	}
}
