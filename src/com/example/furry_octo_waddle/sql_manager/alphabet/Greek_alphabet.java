
package com.example.furry_octo_waddle.sql_manager.alphabet;
import java.util.ArrayList;
import java.util.List;

import com.example.furry_octo_waddle.activities.MainActivity;
import com.example.furry_octo_waddle.sql_manager.Extra_Word_Translation;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

public class Greek_alphabet{

	private static final List<Word_Translation> list = new ArrayList<Word_Translation>();

	public static void save_Greek_alphabet(){

		String[] args1= {Extra_Word_Translation.ANCIENT_GREEK_ALPHA,"Α ; α","a","a", Extra_Word_Translation.FRENCH,"alpha" };
		list.add(new Extra_Word_Translation(args1));

		String[] args2= {Extra_Word_Translation.ANCIENT_GREEK_ALPHA,"Β ; β","b (classique) ; v (moderne)","b (classique) ; v (moderne)", Extra_Word_Translation.FRENCH,"bêta" };
		list.add(new Extra_Word_Translation(args2));

		String[] args3= {Extra_Word_Translation.ANCIENT_GREEK_ALPHA,"Γ ; γ","g","g", Extra_Word_Translation.FRENCH,"gamma" };
		list.add(new Extra_Word_Translation(args3));

		String[] args4= {Extra_Word_Translation.ANCIENT_GREEK_ALPHA,"Δ ; δ","d ; parfois dh (moderne)","d ; parfois dh (moderne)", Extra_Word_Translation.FRENCH,"delta" };
		list.add(new Extra_Word_Translation(args4));

		String[] args5= {Extra_Word_Translation.ANCIENT_GREEK_ALPHA,"Ε ; ε","e","e", Extra_Word_Translation.FRENCH,"epsilon" };
		list.add(new Extra_Word_Translation(args5));

		String[] args6= {Extra_Word_Translation.ANCIENT_GREEK_ALPHA,"Ζ ; ζ","z","z", Extra_Word_Translation.FRENCH,"zêta" };
		list.add(new Extra_Word_Translation(args6));

		String[] args7= {Extra_Word_Translation.ANCIENT_GREEK_ALPHA,"Η ; η","ê (classique) ; i (moderne)","ê (classique) ; i (moderne)", Extra_Word_Translation.FRENCH,"êta" };
		list.add(new Extra_Word_Translation(args7));

		String[] args8= {Extra_Word_Translation.ANCIENT_GREEK_ALPHA,"Θ ; θ","th","th", Extra_Word_Translation.FRENCH,"thêta" };
		list.add(new Extra_Word_Translation(args8));

		String[] args9= {Extra_Word_Translation.ANCIENT_GREEK_ALPHA,"Ι ; ι","i","i", Extra_Word_Translation.FRENCH,"iota" };
		list.add(new Extra_Word_Translation(args9));

		String[] args10= {Extra_Word_Translation.ANCIENT_GREEK_ALPHA,"Κ ; κ","k","k", Extra_Word_Translation.FRENCH,"kappa" };
		list.add(new Extra_Word_Translation(args10));

		String[] args11= {Extra_Word_Translation.ANCIENT_GREEK_ALPHA,"Λ ; λ","l","l", Extra_Word_Translation.FRENCH,"lambda" };
		list.add(new Extra_Word_Translation(args11));

		String[] args12= {Extra_Word_Translation.ANCIENT_GREEK_ALPHA,"Μ ; μ","m","m", Extra_Word_Translation.FRENCH,"mu" };
		list.add(new Extra_Word_Translation(args12));

		String[] args13= {Extra_Word_Translation.ANCIENT_GREEK_ALPHA,"Ν ; ν","n","n", Extra_Word_Translation.FRENCH,"nu" };
		list.add(new Extra_Word_Translation(args13));

		String[] args14= {Extra_Word_Translation.ANCIENT_GREEK_ALPHA,"Ξ ; ξ","ks, x","ks, x", Extra_Word_Translation.FRENCH,"xi" };
		list.add(new Extra_Word_Translation(args14));

		String[] args15= {Extra_Word_Translation.ANCIENT_GREEK_ALPHA,"Ο ; ο","o","o", Extra_Word_Translation.FRENCH,"omicron" };
		list.add(new Extra_Word_Translation(args15));

		String[] args16= {Extra_Word_Translation.ANCIENT_GREEK_ALPHA,"Π ; π","p","p", Extra_Word_Translation.FRENCH,"pi" };
		list.add(new Extra_Word_Translation(args16));

		String[] args17= {Extra_Word_Translation.ANCIENT_GREEK_ALPHA,"Ρ ; ρ","r","r", Extra_Word_Translation.FRENCH,"rhô" };
		list.add(new Extra_Word_Translation(args17));

		String[] args18= {Extra_Word_Translation.ANCIENT_GREEK_ALPHA,"Σ ; σ","s","s", Extra_Word_Translation.FRENCH,"sigma" };
		list.add(new Extra_Word_Translation(args18));

		String[] args19= {Extra_Word_Translation.ANCIENT_GREEK_ALPHA,"Τ ; τ","t","t", Extra_Word_Translation.FRENCH,"tau" };
		list.add(new Extra_Word_Translation(args19));

		String[] args20= {Extra_Word_Translation.ANCIENT_GREEK_ALPHA,"Υ ; υ","u (classique) ; y, v, f (moderne, selon contexte)","u (classique) ; y, v, f (moderne, selon contexte)", Extra_Word_Translation.FRENCH,"upsilon" };
		list.add(new Extra_Word_Translation(args20));

		String[] args21= {Extra_Word_Translation.ANCIENT_GREEK_ALPHA,"Φ ; φ/ϕ","ph (classique) ; f (moderne)","ph (classique) ; f (moderne)", Extra_Word_Translation.FRENCH,"phi" };
		list.add(new Extra_Word_Translation(args21));

		String[] args22= {Extra_Word_Translation.ANCIENT_GREEK_ALPHA,"Χ ; χ","kh","kh", Extra_Word_Translation.FRENCH,"khi" };
		list.add(new Extra_Word_Translation(args22));

		String[] args23= {Extra_Word_Translation.ANCIENT_GREEK_ALPHA,"Ψ ; ψ","ps","ps", Extra_Word_Translation.FRENCH,"psi" };
		list.add(new Extra_Word_Translation(args23));

		String[] args24= {Extra_Word_Translation.ANCIENT_GREEK_ALPHA,"Ω ; ω","ô","ô", Extra_Word_Translation.FRENCH,"oméga" };
		list.add(new Extra_Word_Translation(args24));

		for(Word_Translation word : list){
			MainActivity.cbd.writeWord(word);
		}


	}

}