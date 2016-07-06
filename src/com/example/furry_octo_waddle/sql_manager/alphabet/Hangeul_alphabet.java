package com.example.furry_octo_waddle.sql_manager.alphabet;
import java.util.ArrayList;
import java.util.List;

import com.example.furry_octo_waddle.activities.MainActivity;
import com.example.furry_octo_waddle.sql_manager.Extra_Word_Translation;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

public class Hangeul_alphabet{

	private static final List<Word_Translation> list = new ArrayList<Word_Translation>();

	public Hangeul_alphabet(){

		String[] args0= {"@kr","ㄱ","g,gu ; [g-]", "@fr","기역 ; giyeok" };
		list.add(new Extra_Word_Translation(args0));

		String[] args1= {"@kr","ㅏ","a,a ; [-a-]", "@fr","아 ; a" };
		list.add(new Extra_Word_Translation(args1));

		String[] args2= {"@kr","ㄴ","n,n ; [n-]/[-n]", "@fr","니은 ; nieun" };
		list.add(new Extra_Word_Translation(args2));

		String[] args3= {"@kr","ㅑ","ya,ya ; [-ja-]", "@fr","야 ; ya" };
		list.add(new Extra_Word_Translation(args3));

		String[] args4= {"@kr","ㄷ","d,d ; [d-]", "@fr","디귿 ; digeut" };
		list.add(new Extra_Word_Translation(args4));

		String[] args5= {"@kr","ㅓ","eo,o (o ouvert) ; [-ʌ-]", "@fr","어 ; eo" };
		list.add(new Extra_Word_Translation(args5));

		String[] args6= {"@kr","ㄹ","r (devant une voyelle), l","r / l ; [ɾ-]/[-ʎ]", "@fr","리을 ; rieul" };
		list.add(new Extra_Word_Translation(args6));

		String[] args7= {"@kr","ㅕ","yeo,yo (o ouvert) ; [-jʌ-]", "@fr","여 ; yeo" };
		list.add(new Extra_Word_Translation(args7));

		String[] args8= {"@kr","ㅁ","m,m ; [m-]/[-m]", "@fr","미음 ; mieum" };
		list.add(new Extra_Word_Translation(args8));

		String[] args9= {"@kr","ㅗ","o,ô ; [-o-]", "@fr","오 ; o" };
		list.add(new Extra_Word_Translation(args9));

		String[] args10= {"@kr","ㅂ","b,b ; [b-]", "@fr","비읍 ; bieup" };
		list.add(new Extra_Word_Translation(args10));

		String[] args11= {"@kr","ㅛ","yo,yô ; [-jo-]", "@fr","요 ; yo" };
		list.add(new Extra_Word_Translation(args11));

		String[] args12= {"@kr","ㅅ","s,s ; [s-]", "@fr","시옷 ; siot" };
		list.add(new Extra_Word_Translation(args12));

		String[] args13= {"@kr","ㅜ","u,ou ; [-u-]", "@fr","우 ; u" };
		list.add(new Extra_Word_Translation(args13));

		String[] args14= {"@kr","ㅇ","ng,ng ; [-ŋ]", "@fr","이응 ; ieung" };
		list.add(new Extra_Word_Translation(args14));

		String[] args15= {"@kr","ㅠ","yu,you ; [-ju-]", "@fr","유 ; yu" };
		list.add(new Extra_Word_Translation(args15));

		String[] args16= {"@kr","ㅈ","j,dj ; [tɕ-]", "@fr","지읒 ; jieut" };
		list.add(new Extra_Word_Translation(args16));

		String[] args17= {"@kr","ㅡ","eu,eu ; [-ɯ-]", "@fr","으 ; eu" };
		list.add(new Extra_Word_Translation(args17));

		String[] args18= {"@kr","ㅊ","ch,tch ; [tɕʰ-]", "@fr","치읓 ; chieut" };
		list.add(new Extra_Word_Translation(args18));

		String[] args19= {"@kr","ㅣ","i,i ; [-i-]", "@fr","이 ; i" };
		list.add(new Extra_Word_Translation(args19));

		String[] args20= {"@kr","ㅋ","k,kh ; [kʰ-]/[-k̚]", "@fr","키읔 ; kieuk" };
		list.add(new Extra_Word_Translation(args20));

		String[] args21= {"@kr","ㅌ","t,th ; [tʰ-]/[-t̚]", "@fr","티읕 ; tieut" };
		list.add(new Extra_Word_Translation(args21));

		String[] args22= {"@kr","ㅍ","p,ph ; [pʰ-]/[-p̚]", "@fr","피읖 ; pieup" };
		list.add(new Extra_Word_Translation(args22));

		String[] args23= {"@kr","ㅎ","h,h ; [h-]", "@fr","히읗 ; hieut" };
		list.add(new Extra_Word_Translation(args23));

		String[] args24= {"@kr","ㄲ","kk,k appuyé ; [kʼ-]/[-k̚]", "@fr","쌍기역 ; ssanggiyeok" };
		list.add(new Extra_Word_Translation(args24));

		String[] args25= {"@kr","ㅐ","ae,è ; [-ɛ-]", "@fr","애 ; ae" };
		list.add(new Extra_Word_Translation(args25));

		String[] args26= {"@kr","ㄸ","tt,t appuyé ; [tʼ-]/[-t̚]", "@fr","쌍디귿 ; ssangdigeut" };
		list.add(new Extra_Word_Translation(args26));

		String[] args27= {"@kr","ㅒ","yae,yè ; [-jɛ-]", "@fr","얘 ; yae" };
		list.add(new Extra_Word_Translation(args27));

		String[] args28= {"@kr","ㅃ","pp,p appuyé ; [pʼ-]/[-p̚]", "@fr","쌍비읍 ; ssangbieup" };
		list.add(new Extra_Word_Translation(args28));

		String[] args29= {"@kr","ㅔ","e,é ; [-e-]", "@fr","에 ; e" };
		list.add(new Extra_Word_Translation(args29));

		String[] args30= {"@kr","ㅆ","ss,s appuyé ; [sʼ-]/[-t̚]", "@fr","쌍시옷 ; ssangsiot" };
		list.add(new Extra_Word_Translation(args30));

		String[] args31= {"@kr","ㅖ","ye,yé ; [-je-]", "@fr","예 ; ye" };
		list.add(new Extra_Word_Translation(args31));

		String[] args32= {"@kr","ㅉ","jj,tch appuyé ; [tɕʼ-]/[-t̚]", "@fr","쌍지읒 ; ssangjieut" };
		list.add(new Extra_Word_Translation(args32));

		String[] args33= {"@kr","ㅘ","wa,wa ; [-wa-]", "@fr","와 ; wa" };
		list.add(new Extra_Word_Translation(args33));

		String[] args34= {"@kr","ㅙ","wae,wè ; [-wɛ-]", "@fr","왜 ; wae" };
		list.add(new Extra_Word_Translation(args34));

		String[] args35= {"@kr","ㅚ","oe,wi ; [-wi-]", "@fr","외 ; oe" };
		list.add(new Extra_Word_Translation(args35));

		String[] args36= {"@kr","ㄳ","ks,x ; [-k̚]/[-ks-]", "@fr","기역 시옷 ; giyeok siot" };
		list.add(new Extra_Word_Translation(args36));

		String[] args37= {"@kr","ㅝ","weo,wo (o ouvert) ; [-wʌ-]", "@fr","워 ; weo" };
		list.add(new Extra_Word_Translation(args37));

		String[] args38= {"@kr","ㄵ","nj,ndj ; [-n]/[-ntɕ-]", "@fr","니은 지읒 ; nieun jieut" };
		list.add(new Extra_Word_Translation(args38));

		String[] args39= {"@kr","ㅞ","we,wé ; [-we-]", "@fr","웨 ; we" };
		list.add(new Extra_Word_Translation(args39));

		String[] args40= {"@kr","ㄶ","nh,nh ; [-n]/[-nx-]", "@fr","니은 히읗 ; nieun hieut" };
		list.add(new Extra_Word_Translation(args40));

		String[] args41= {"@kr","ㅟ","wi,wi ; [-wi-]", "@fr","위 ; wi" };
		list.add(new Extra_Word_Translation(args41));

		String[] args42= {"@kr","ㄺ","lg,lg ; [-ʎ]/[-ʎg-]", "@fr","리을 기역 ; rieul giyeok" };
		list.add(new Extra_Word_Translation(args42));

		String[] args43= {"@kr","ㅢ","eui,euil ; [-ɰi-]", "@fr","의 ; ui" };
		list.add(new Extra_Word_Translation(args43));

		String[] args44= {"@kr","ㄻ","lm,lm ; [-ʎ]/[-ʎm-]", "@fr","리을 미음 ; rieul mieum" };
		list.add(new Extra_Word_Translation(args44));

		String[] args45= {"@kr","ㄼ","lb,lb ; [-ʎ]/[-ʎb-]", "@fr","리을 비읍 ; rieul bieup" };
		list.add(new Extra_Word_Translation(args45));

		String[] args46= {"@kr","ㄽ","ls,ls ; [-ʎ]/[-ʎs-]", "@fr","리을 시옷 ; rieul siot" };
		list.add(new Extra_Word_Translation(args46));

		String[] args47= {"@kr","ㄾ","lt,lt ; [-ʎ]/[-ʎtʰ-]", "@fr","리을 티읕 ; rieul tieut" };
		list.add(new Extra_Word_Translation(args47));

		String[] args48= {"@kr","ㄿ","lp,lph ; [-ʎ]/[-ʎpʰ-]", "@fr","리을 피읖 ; rieul pieup" };
		list.add(new Extra_Word_Translation(args48));

		String[] args49= {"@kr","ㅀ","lh,lh ; [-ʎ]/[-ʎx-]", "@fr","리을 히읗 ; rieul hieut" };
		list.add(new Extra_Word_Translation(args49));

		String[] args50= {"@kr","ㅄ","bs,ps ; [-p̚]/[-ps-]", "@fr","비읍 시옷 ; bieup siot" };
		list.add(new Extra_Word_Translation(args50));

		for(Word_Translation word : list){
			MainActivity.cbd.writeWord(word);
		}
	}
}