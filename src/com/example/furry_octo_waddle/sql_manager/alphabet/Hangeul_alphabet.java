package com.example.furry_octo_waddle.sql_manager.alphabet;
import java.util.ArrayList;
import java.util.List;

import com.example.furry_octo_waddle.activities.MainActivity;
import com.example.furry_octo_waddle.sql_manager.Extra_Word_Translation;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

public class Hangeul_alphabet{

	private static final List<Word_Translation> list = new ArrayList<Word_Translation>();

	public static void save_Hangeul_alphabet(){

		String[] args0= {Languages_ISO.HANGEUL_ALPHA,"ㄱ","g","gu ; [g-]", Languages_ISO.FRENCH,"기역 ; giyeok" };
		list.add(new Extra_Word_Translation(args0));

		String[] args1= {Languages_ISO.HANGEUL_ALPHA,"ㅏ","a","a ; [-a-]", Languages_ISO.FRENCH,"아 ; a" };
		list.add(new Extra_Word_Translation(args1));

		String[] args2= {Languages_ISO.HANGEUL_ALPHA,"ㄴ","n","n ; [n-]/[-n]", Languages_ISO.FRENCH,"니은 ; nieun" };
		list.add(new Extra_Word_Translation(args2));

		String[] args3= {Languages_ISO.HANGEUL_ALPHA,"ㅑ","ya","ya ; [-ja-]", Languages_ISO.FRENCH,"야 ; ya" };
		list.add(new Extra_Word_Translation(args3));

		String[] args4= {Languages_ISO.HANGEUL_ALPHA,"ㄷ","d","d ; [d-]", Languages_ISO.FRENCH,"디귿 ; digeut" };
		list.add(new Extra_Word_Translation(args4));

		String[] args5= {Languages_ISO.HANGEUL_ALPHA,"ㅓ","eo","o (o ouvert) ; [-ʌ-]", Languages_ISO.FRENCH,"어 ; eo" };
		list.add(new Extra_Word_Translation(args5));

		String[] args6= {Languages_ISO.HANGEUL_ALPHA,"ㄹ","r (devant une voyelle)/l","r/l ; [ɾ-]/[-ʎ]", Languages_ISO.FRENCH,"리을 ; rieul" };
		list.add(new Extra_Word_Translation(args6));

		String[] args7= {Languages_ISO.HANGEUL_ALPHA,"ㅕ","yeo","yo (o ouvert) ; [-jʌ-]", Languages_ISO.FRENCH,"여 ; yeo" };
		list.add(new Extra_Word_Translation(args7));

		String[] args8= {Languages_ISO.HANGEUL_ALPHA,"ㅁ","m","m ; [m-]/[-m]", Languages_ISO.FRENCH,"미음 ; mieum" };
		list.add(new Extra_Word_Translation(args8));

		String[] args9= {Languages_ISO.HANGEUL_ALPHA,"ㅗ","o","ô ; [-o-]", Languages_ISO.FRENCH,"오 ; o" };
		list.add(new Extra_Word_Translation(args9));

		String[] args10= {Languages_ISO.HANGEUL_ALPHA,"ㅂ","b","b ; [b-]", Languages_ISO.FRENCH,"비읍 ; bieup" };
		list.add(new Extra_Word_Translation(args10));

		String[] args11= {Languages_ISO.HANGEUL_ALPHA,"ㅛ","yo","yô ; [-jo-]", Languages_ISO.FRENCH,"요 ; yo" };
		list.add(new Extra_Word_Translation(args11));

		String[] args12= {Languages_ISO.HANGEUL_ALPHA,"ㅅ","s","s ; [s-]", Languages_ISO.FRENCH,"시옷 ; siot" };
		list.add(new Extra_Word_Translation(args12));

		String[] args13= {Languages_ISO.HANGEUL_ALPHA,"ㅜ","u","ou ; [-u-]", Languages_ISO.FRENCH,"우 ; u" };
		list.add(new Extra_Word_Translation(args13));

		String[] args14= {Languages_ISO.HANGEUL_ALPHA,"ㅇ","ng","ng ; [-ŋ]", Languages_ISO.FRENCH,"이응 ; ieung" };
		list.add(new Extra_Word_Translation(args14));

		String[] args15= {Languages_ISO.HANGEUL_ALPHA,"ㅠ","yu","you ; [-ju-]", Languages_ISO.FRENCH,"유 ; yu" };
		list.add(new Extra_Word_Translation(args15));

		String[] args16= {Languages_ISO.HANGEUL_ALPHA,"ㅈ","j","dj ; [tɕ-]", Languages_ISO.FRENCH,"지읒 ; jieut" };
		list.add(new Extra_Word_Translation(args16));

		String[] args17= {Languages_ISO.HANGEUL_ALPHA,"ㅡ","eu","eu ; [-ɯ-]", Languages_ISO.FRENCH,"으 ; eu" };
		list.add(new Extra_Word_Translation(args17));

		String[] args18= {Languages_ISO.HANGEUL_ALPHA,"ㅊ","ch","tch ; [tɕʰ-]", Languages_ISO.FRENCH,"치읓 ; chieut" };
		list.add(new Extra_Word_Translation(args18));

		String[] args19= {Languages_ISO.HANGEUL_ALPHA,"ㅣ","i","i ; [-i-]", Languages_ISO.FRENCH,"이 ; i" };
		list.add(new Extra_Word_Translation(args19));

		String[] args20= {Languages_ISO.HANGEUL_ALPHA,"ㅋ","k","kh ; [kʰ-]/[-k̚]", Languages_ISO.FRENCH,"키읔 ; kieuk" };
		list.add(new Extra_Word_Translation(args20));

		String[] args21= {Languages_ISO.HANGEUL_ALPHA,"ㅌ","t","th ; [tʰ-]/[-t̚]", Languages_ISO.FRENCH,"티읕 ; tieut" };
		list.add(new Extra_Word_Translation(args21));

		String[] args22= {Languages_ISO.HANGEUL_ALPHA,"ㅍ","p","ph ; [pʰ-]/[-p̚]", Languages_ISO.FRENCH,"피읖 ; pieup" };
		list.add(new Extra_Word_Translation(args22));

		String[] args23= {Languages_ISO.HANGEUL_ALPHA,"ㅎ","h","h ; [h-]", Languages_ISO.FRENCH,"히읗 ; hieut" };
		list.add(new Extra_Word_Translation(args23));

		String[] args24= {Languages_ISO.HANGEUL_ALPHA,"ㄲ","kk","k appuyé ; [kʼ-]/[-k̚]", Languages_ISO.FRENCH,"쌍기역 ; ssanggiyeok" };
		list.add(new Extra_Word_Translation(args24));

		String[] args25= {Languages_ISO.HANGEUL_ALPHA,"ㅐ","ae","è ; [-ɛ-]", Languages_ISO.FRENCH,"애 ; ae" };
		list.add(new Extra_Word_Translation(args25));

		String[] args26= {Languages_ISO.HANGEUL_ALPHA,"ㄸ","tt","t appuyé ; [tʼ-]/[-t̚]", Languages_ISO.FRENCH,"쌍디귿 ; ssangdigeut" };
		list.add(new Extra_Word_Translation(args26));

		String[] args27= {Languages_ISO.HANGEUL_ALPHA,"ㅒ","yae","yè ; [-jɛ-]", Languages_ISO.FRENCH,"얘 ; yae" };
		list.add(new Extra_Word_Translation(args27));

		String[] args28= {Languages_ISO.HANGEUL_ALPHA,"ㅃ","pp","p appuyé ; [pʼ-]/[-p̚]", Languages_ISO.FRENCH,"쌍비읍 ; ssangbieup" };
		list.add(new Extra_Word_Translation(args28));

		String[] args29= {Languages_ISO.HANGEUL_ALPHA,"ㅔ","e","é ; [-e-]", Languages_ISO.FRENCH,"에 ; e" };
		list.add(new Extra_Word_Translation(args29));

		String[] args30= {Languages_ISO.HANGEUL_ALPHA,"ㅆ","ss","s appuyé ; [sʼ-]/[-t̚]", Languages_ISO.FRENCH,"쌍시옷 ; ssangsiot" };
		list.add(new Extra_Word_Translation(args30));

		String[] args31= {Languages_ISO.HANGEUL_ALPHA,"ㅖ","ye","yé ; [-je-]", Languages_ISO.FRENCH,"예 ; ye" };
		list.add(new Extra_Word_Translation(args31));

		String[] args32= {Languages_ISO.HANGEUL_ALPHA,"ㅉ","jj","tch appuyé ; [tɕʼ-]/[-t̚]", Languages_ISO.FRENCH,"쌍지읒 ; ssangjieut" };
		list.add(new Extra_Word_Translation(args32));

		String[] args33= {Languages_ISO.HANGEUL_ALPHA,"ㅘ","wa","wa ; [-wa-]", Languages_ISO.FRENCH,"와 ; wa" };
		list.add(new Extra_Word_Translation(args33));

		String[] args34= {Languages_ISO.HANGEUL_ALPHA,"ㅙ","wae","wè ; [-wɛ-]", Languages_ISO.FRENCH,"왜 ; wae" };
		list.add(new Extra_Word_Translation(args34));

		String[] args35= {Languages_ISO.HANGEUL_ALPHA,"ㅚ","oe","wi ; [-wi-]", Languages_ISO.FRENCH,"외 ; oe" };
		list.add(new Extra_Word_Translation(args35));

		String[] args36= {Languages_ISO.HANGEUL_ALPHA,"ㄳ","ks","x ; [-k̚]/[-ks-]", Languages_ISO.FRENCH,"기역 시옷 ; giyeok siot" };
		list.add(new Extra_Word_Translation(args36));

		String[] args37= {Languages_ISO.HANGEUL_ALPHA,"ㅝ","weo","wo (o ouvert) ; [-wʌ-]", Languages_ISO.FRENCH,"워 ; weo" };
		list.add(new Extra_Word_Translation(args37));

		String[] args38= {Languages_ISO.HANGEUL_ALPHA,"ㄵ","nj","ndj ; [-n]/[-ntɕ-]", Languages_ISO.FRENCH,"니은 지읒 ; nieun jieut" };
		list.add(new Extra_Word_Translation(args38));

		String[] args39= {Languages_ISO.HANGEUL_ALPHA,"ㅞ","we","wé ; [-we-]", Languages_ISO.FRENCH,"웨 ; we" };
		list.add(new Extra_Word_Translation(args39));

		String[] args40= {Languages_ISO.HANGEUL_ALPHA,"ㄶ","nh","nh ; [-n]/[-nx-]", Languages_ISO.FRENCH,"니은 히읗 ; nieun hieut" };
		list.add(new Extra_Word_Translation(args40));

		String[] args41= {Languages_ISO.HANGEUL_ALPHA,"ㅟ","wi","wi ; [-wi-]", Languages_ISO.FRENCH,"위 ; wi" };
		list.add(new Extra_Word_Translation(args41));

		String[] args42= {Languages_ISO.HANGEUL_ALPHA,"ㄺ","lg","lg ; [-ʎ]/[-ʎg-]", Languages_ISO.FRENCH,"리을 기역 ; rieul giyeok" };
		list.add(new Extra_Word_Translation(args42));

		String[] args43= {Languages_ISO.HANGEUL_ALPHA,"ㅢ","eui","euil ; [-ɰi-]", Languages_ISO.FRENCH,"의 ; ui" };
		list.add(new Extra_Word_Translation(args43));

		String[] args44= {Languages_ISO.HANGEUL_ALPHA,"ㄻ","lm","lm ; [-ʎ]/[-ʎm-]", Languages_ISO.FRENCH,"리을 미음 ; rieul mieum" };
		list.add(new Extra_Word_Translation(args44));

		String[] args45= {Languages_ISO.HANGEUL_ALPHA,"ㄼ","lb","lb ; [-ʎ]/[-ʎb-]", Languages_ISO.FRENCH,"리을 비읍 ; rieul bieup" };
		list.add(new Extra_Word_Translation(args45));

		String[] args46= {Languages_ISO.HANGEUL_ALPHA,"ㄽ","ls","ls ; [-ʎ]/[-ʎs-]", Languages_ISO.FRENCH,"리을 시옷 ; rieul siot" };
		list.add(new Extra_Word_Translation(args46));

		String[] args47= {Languages_ISO.HANGEUL_ALPHA,"ㄾ","lt","lt ; [-ʎ]/[-ʎtʰ-]", Languages_ISO.FRENCH,"리을 티읕 ; rieul tieut" };
		list.add(new Extra_Word_Translation(args47));

		String[] args48= {Languages_ISO.HANGEUL_ALPHA,"ㄿ","lp","lph ; [-ʎ]/[-ʎpʰ-]", Languages_ISO.FRENCH,"리을 피읖 ; rieul pieup" };
		list.add(new Extra_Word_Translation(args48));

		String[] args49= {Languages_ISO.HANGEUL_ALPHA,"ㅀ","lh","lh ; [-ʎ]/[-ʎx-]", Languages_ISO.FRENCH,"리을 히읗 ; rieul hieut" };
		list.add(new Extra_Word_Translation(args49));

		String[] args50= {Languages_ISO.HANGEUL_ALPHA,"ㅄ","bs","ps ; [-p̚]/[-ps-]", Languages_ISO.FRENCH,"비읍 시옷 ; bieup siot" };
		list.add(new Extra_Word_Translation(args50));

		for(Word_Translation word : list){
			MainActivity.cbd.writeWord(word);
		}
	}
}