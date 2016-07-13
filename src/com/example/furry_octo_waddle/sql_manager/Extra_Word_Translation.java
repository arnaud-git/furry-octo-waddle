package com.example.furry_octo_waddle.sql_manager;

import com.example.furry_octo_waddle.activities.MainActivity;

public class Extra_Word_Translation extends Word_Translation {

	private String pronunciation ="";
	private String romanization ="";
	public static final String HANGEUL_ALPHA = "@kr&";
	public static final String HIRAGANA_ALPHA = "@ja_hi&";
	public static final String KATAKANA_ALPHA = "@ja_ka&";
	public static final String ANCIENT_GREEK_ALPHA = "@gr&";

	public Extra_Word_Translation(String index, String[] args){
		super(index,args[0],args[1], args[4],args[5]);
		if(args.length!=6){
			//erreur
		}else{
			setRomanization(args[2]);
			setPronunciation(args[3]);
			setType(Extra.EXTRA);
		}

	}
	
	public Extra_Word_Translation(Word_Translation word){
		super(String.valueOf(word.getId()),word.getLanguage(),word.getWord(),word.getTargeted_language(),word.getTraduction_of_word(),word.getTime());
		if(word.getType()==Extra.NORMAL){
			romanization="";
			pronunciation="";
		}else{
			romanization = ((Extra_Word_Translation)word).getRomanization();			
			pronunciation = ((Extra_Word_Translation)word).getPronunciation();
		}
		MainActivity.printDebug(1, "Debug");
	}

	public Extra_Word_Translation(String index, String[] args,String time){
		super(index,args[0],args[1], args[4],args[5],time);
		if(args.length!=6){
			//erreur
		}else{
			setRomanization(args[2]);
			setPronunciation(args[3]);
			setType(Extra.EXTRA);
		}

	}

	public Extra_Word_Translation( String[] args){
		super(args[0],args[1], args[4],args[5]);
		if(args.length!=6){
			//erreur
		}else{
			setRomanization(args[2]);
			setPronunciation(args[3]);
			setType(Extra.EXTRA);
		}

	}


	public String getRomanization(){
		return romanization;
	}

	public void setRomanization(String rom ){
		if(rom!=null)
			romanization=formatString(rom);
		else 
			romanization="";
	}

	public void setPronunciation(String pro){
		if(pro!=null)
			pronunciation=formatString(pro);
		else
			pronunciation="";
	}

	public String getPronunciation(){
		return pronunciation;
	}

	@Override
	public String[] getArgs(){
		String[] ret =  {forQuery(getId()),
				getLanguage(),
				getWord(),
				romanization,
				pronunciation,
				getTargeted_language(),
				getTraduction_of_word(),
				forQuery(getTime())
		} ;
		return ret;
	}
	
	public final String[] getLanguages() {
		String args[] = super.getLanguage().split("(?<=%)");
		return args;
	}
	
	
	public final String[] getTargeted_languages() {
		String args[] = super.getTargeted_language().split("(?<=%)");
		return args;
	}

}
