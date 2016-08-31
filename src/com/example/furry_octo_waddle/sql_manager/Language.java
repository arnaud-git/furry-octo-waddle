package com.example.furry_octo_waddle.sql_manager;

public class Language{
	
	protected int id = 0;
	protected String name;
	protected String code;

	public Language(String  name){
		name = format(name);
		this.code=name.substring(0, 2);
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
		this.code=parent.getCodeLanguage()+"_"+code;
		this.name=name;
	}
	
	public Language(Language parent, String name){
		name = format(name);
		this.code=parent.getCodeLanguage()+"_"+name.substring(0, 2);
		this.name=name;
	}
	
	public Language(String in, String code,String name){
		name = format(name);
		this.id=Integer.parseInt(in);
		this.code=code;
		this.name=name;
	}

	public String getCodeLanguage(){
		return code;
	}

	public String getLanguage(){
		return name;
	}
	
	public int getId(){
		return id;
	}

	@Override
	public boolean equals(Object language){
		return code.equalsIgnoreCase(((Language) language).getCodeLanguage());
	}

	public String[] getArgs() {
		String[] ret =  {Word_Translation.forQuery(getId()),
				getCodeLanguage(),
				getLanguage(),
				} ;
		return ret;
	}

	public void setId(int newRowId) {
		id=newRowId;
		
	}
}
