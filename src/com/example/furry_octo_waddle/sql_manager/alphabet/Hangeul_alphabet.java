package com.example.furry_octo_waddle.sql_manager.alphabet;
import java.util.ArrayList;
import java.util.List;

import com.example.furry_octo_waddle.activities.MainActivity;
import com.example.furry_octo_waddle.sql_manager.Extra_Word_Translation;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

public class Hangeul_alphabet{

	private static final List<Word_Translation> list = new ArrayList<Word_Translation>();

	public static void save_Hangeul_alphabet(){

		String[] args0= {Extra_Word_Translation.HANGEUL_ALPHA,"ㄱ","g","gu ; [g-]", Extra_Word_Translation.FRENCH,"기역 ; giyeok" };
		list.add(new Extra_Word_Translation(args0));

		String[] args1= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅏ","a","a ; [-a-]", Extra_Word_Translation.FRENCH,"아 ; a" };
		list.add(new Extra_Word_Translation(args1));

		String[] args2= {Extra_Word_Translation.HANGEUL_ALPHA,"ㄴ","n","n ; [n-]/[-n]", Extra_Word_Translation.FRENCH,"니은 ; nieun" };
		list.add(new Extra_Word_Translation(args2));

		String[] args3= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅑ","ya","ya ; [-ja-]", Extra_Word_Translation.FRENCH,"야 ; ya" };
		list.add(new Extra_Word_Translation(args3));

		String[] args4= {Extra_Word_Translation.HANGEUL_ALPHA,"ㄷ","d","d ; [d-]", Extra_Word_Translation.FRENCH,"디귿 ; digeut" };
		list.add(new Extra_Word_Translation(args4));

		String[] args5= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅓ","eo","o (o ouvert) ; [-ʌ-]", Extra_Word_Translation.FRENCH,"어 ; eo" };
		list.add(new Extra_Word_Translation(args5));

		String[] args6= {Extra_Word_Translation.HANGEUL_ALPHA,"ㄹ","r (devant une voyelle)/l","r/l ; [ɾ-]/[-ʎ]", Extra_Word_Translation.FRENCH,"리을 ; rieul" };
		list.add(new Extra_Word_Translation(args6));

		String[] args7= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅕ","yeo","yo (o ouvert) ; [-jʌ-]", Extra_Word_Translation.FRENCH,"여 ; yeo" };
		list.add(new Extra_Word_Translation(args7));

		String[] args8= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅁ","m","m ; [m-]/[-m]", Extra_Word_Translation.FRENCH,"미음 ; mieum" };
		list.add(new Extra_Word_Translation(args8));

		String[] args9= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅗ","o","ô ; [-o-]", Extra_Word_Translation.FRENCH,"오 ; o" };
		list.add(new Extra_Word_Translation(args9));

		String[] args10= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅂ","b","b ; [b-]", Extra_Word_Translation.FRENCH,"비읍 ; bieup" };
		list.add(new Extra_Word_Translation(args10));

		String[] args11= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅛ","yo","yô ; [-jo-]", Extra_Word_Translation.FRENCH,"요 ; yo" };
		list.add(new Extra_Word_Translation(args11));

		String[] args12= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅅ","s","s ; [s-]", Extra_Word_Translation.FRENCH,"시옷 ; siot" };
		list.add(new Extra_Word_Translation(args12));

		String[] args13= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅜ","u","ou ; [-u-]", Extra_Word_Translation.FRENCH,"우 ; u" };
		list.add(new Extra_Word_Translation(args13));

		String[] args14= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅇ","ng","ng ; [-ŋ]", Extra_Word_Translation.FRENCH,"이응 ; ieung" };
		list.add(new Extra_Word_Translation(args14));

		String[] args15= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅠ","yu","you ; [-ju-]", Extra_Word_Translation.FRENCH,"유 ; yu" };
		list.add(new Extra_Word_Translation(args15));

		String[] args16= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅈ","j","dj ; [tɕ-]", Extra_Word_Translation.FRENCH,"지읒 ; jieut" };
		list.add(new Extra_Word_Translation(args16));

		String[] args17= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅡ","eu","eu ; [-ɯ-]", Extra_Word_Translation.FRENCH,"으 ; eu" };
		list.add(new Extra_Word_Translation(args17));

		String[] args18= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅊ","ch","tch ; [tɕʰ-]", Extra_Word_Translation.FRENCH,"치읓 ; chieut" };
		list.add(new Extra_Word_Translation(args18));

		String[] args19= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅣ","i","i ; [-i-]", Extra_Word_Translation.FRENCH,"이 ; i" };
		list.add(new Extra_Word_Translation(args19));

		String[] args20= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅋ","k","kh ; [kʰ-]/[-k̚]", Extra_Word_Translation.FRENCH,"키읔 ; kieuk" };
		list.add(new Extra_Word_Translation(args20));

		String[] args21= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅌ","t","th ; [tʰ-]/[-t̚]", Extra_Word_Translation.FRENCH,"티읕 ; tieut" };
		list.add(new Extra_Word_Translation(args21));

		String[] args22= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅍ","p","ph ; [pʰ-]/[-p̚]", Extra_Word_Translation.FRENCH,"피읖 ; pieup" };
		list.add(new Extra_Word_Translation(args22));

		String[] args23= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅎ","h","h ; [h-]", Extra_Word_Translation.FRENCH,"히읗 ; hieut" };
		list.add(new Extra_Word_Translation(args23));

		String[] args24= {Extra_Word_Translation.HANGEUL_ALPHA,"ㄲ","kk","k appuyé ; [kʼ-]/[-k̚]", Extra_Word_Translation.FRENCH,"쌍기역 ; ssanggiyeok" };
		list.add(new Extra_Word_Translation(args24));

		String[] args25= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅐ","ae","è ; [-ɛ-]", Extra_Word_Translation.FRENCH,"애 ; ae" };
		list.add(new Extra_Word_Translation(args25));

		String[] args26= {Extra_Word_Translation.HANGEUL_ALPHA,"ㄸ","tt","t appuyé ; [tʼ-]/[-t̚]", Extra_Word_Translation.FRENCH,"쌍디귿 ; ssangdigeut" };
		list.add(new Extra_Word_Translation(args26));

		String[] args27= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅒ","yae","yè ; [-jɛ-]", Extra_Word_Translation.FRENCH,"얘 ; yae" };
		list.add(new Extra_Word_Translation(args27));

		String[] args28= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅃ","pp","p appuyé ; [pʼ-]/[-p̚]", Extra_Word_Translation.FRENCH,"쌍비읍 ; ssangbieup" };
		list.add(new Extra_Word_Translation(args28));

		String[] args29= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅔ","e","é ; [-e-]", Extra_Word_Translation.FRENCH,"에 ; e" };
		list.add(new Extra_Word_Translation(args29));

		String[] args30= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅆ","ss","s appuyé ; [sʼ-]/[-t̚]", Extra_Word_Translation.FRENCH,"쌍시옷 ; ssangsiot" };
		list.add(new Extra_Word_Translation(args30));

		String[] args31= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅖ","ye","yé ; [-je-]", Extra_Word_Translation.FRENCH,"예 ; ye" };
		list.add(new Extra_Word_Translation(args31));

		String[] args32= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅉ","jj","tch appuyé ; [tɕʼ-]/[-t̚]", Extra_Word_Translation.FRENCH,"쌍지읒 ; ssangjieut" };
		list.add(new Extra_Word_Translation(args32));

		String[] args33= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅘ","wa","wa ; [-wa-]", Extra_Word_Translation.FRENCH,"와 ; wa" };
		list.add(new Extra_Word_Translation(args33));

		String[] args34= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅙ","wae","wè ; [-wɛ-]", Extra_Word_Translation.FRENCH,"왜 ; wae" };
		list.add(new Extra_Word_Translation(args34));

		String[] args35= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅚ","oe","wi ; [-wi-]", Extra_Word_Translation.FRENCH,"외 ; oe" };
		list.add(new Extra_Word_Translation(args35));

		String[] args36= {Extra_Word_Translation.HANGEUL_ALPHA,"ㄳ","ks","x ; [-k̚]/[-ks-]", Extra_Word_Translation.FRENCH,"기역 시옷 ; giyeok siot" };
		list.add(new Extra_Word_Translation(args36));

		String[] args37= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅝ","weo","wo (o ouvert) ; [-wʌ-]", Extra_Word_Translation.FRENCH,"워 ; weo" };
		list.add(new Extra_Word_Translation(args37));

		String[] args38= {Extra_Word_Translation.HANGEUL_ALPHA,"ㄵ","nj","ndj ; [-n]/[-ntɕ-]", Extra_Word_Translation.FRENCH,"니은 지읒 ; nieun jieut" };
		list.add(new Extra_Word_Translation(args38));

		String[] args39= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅞ","we","wé ; [-we-]", Extra_Word_Translation.FRENCH,"웨 ; we" };
		list.add(new Extra_Word_Translation(args39));

		String[] args40= {Extra_Word_Translation.HANGEUL_ALPHA,"ㄶ","nh","nh ; [-n]/[-nx-]", Extra_Word_Translation.FRENCH,"니은 히읗 ; nieun hieut" };
		list.add(new Extra_Word_Translation(args40));

		String[] args41= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅟ","wi","wi ; [-wi-]", Extra_Word_Translation.FRENCH,"위 ; wi" };
		list.add(new Extra_Word_Translation(args41));

		String[] args42= {Extra_Word_Translation.HANGEUL_ALPHA,"ㄺ","lg","lg ; [-ʎ]/[-ʎg-]", Extra_Word_Translation.FRENCH,"리을 기역 ; rieul giyeok" };
		list.add(new Extra_Word_Translation(args42));

		String[] args43= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅢ","eui","euil ; [-ɰi-]", Extra_Word_Translation.FRENCH,"의 ; ui" };
		list.add(new Extra_Word_Translation(args43));

		String[] args44= {Extra_Word_Translation.HANGEUL_ALPHA,"ㄻ","lm","lm ; [-ʎ]/[-ʎm-]", Extra_Word_Translation.FRENCH,"리을 미음 ; rieul mieum" };
		list.add(new Extra_Word_Translation(args44));

		String[] args45= {Extra_Word_Translation.HANGEUL_ALPHA,"ㄼ","lb","lb ; [-ʎ]/[-ʎb-]", Extra_Word_Translation.FRENCH,"리을 비읍 ; rieul bieup" };
		list.add(new Extra_Word_Translation(args45));

		String[] args46= {Extra_Word_Translation.HANGEUL_ALPHA,"ㄽ","ls","ls ; [-ʎ]/[-ʎs-]", Extra_Word_Translation.FRENCH,"리을 시옷 ; rieul siot" };
		list.add(new Extra_Word_Translation(args46));

		String[] args47= {Extra_Word_Translation.HANGEUL_ALPHA,"ㄾ","lt","lt ; [-ʎ]/[-ʎtʰ-]", Extra_Word_Translation.FRENCH,"리을 티읕 ; rieul tieut" };
		list.add(new Extra_Word_Translation(args47));

		String[] args48= {Extra_Word_Translation.HANGEUL_ALPHA,"ㄿ","lp","lph ; [-ʎ]/[-ʎpʰ-]", Extra_Word_Translation.FRENCH,"리을 피읖 ; rieul pieup" };
		list.add(new Extra_Word_Translation(args48));

		String[] args49= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅀ","lh","lh ; [-ʎ]/[-ʎx-]", Extra_Word_Translation.FRENCH,"리을 히읗 ; rieul hieut" };
		list.add(new Extra_Word_Translation(args49));

		String[] args50= {Extra_Word_Translation.HANGEUL_ALPHA,"ㅄ","bs","ps ; [-p̚]/[-ps-]", Extra_Word_Translation.FRENCH,"비읍 시옷 ; bieup siot" };
		list.add(new Extra_Word_Translation(args50));

		for(Word_Translation word : list){
			MainActivity.cbd.writeWord(word);
		}
	}
}