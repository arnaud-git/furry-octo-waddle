
package com.example.furry_octo_waddle.sql_manager.alphabet;
import java.util.ArrayList;
import java.util.List;

import com.example.furry_octo_waddle.activities.MainActivity;
import com.example.furry_octo_waddle.sql_manager.Extra_Word_Translation;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

public class Greek_alphabet{

	private static final List<Word_Translation> list = new ArrayList<Word_Translation>();

	public static void save_Greek_alphabet(){

		String[] args1= {Languages_ISO.ANCIENT_GREEK_ALPHA,"Α ; α","a","a", Languages_ISO.FRENCH,"alpha" };
		list.add(new Extra_Word_Translation(args1));

		String[] args2= {Languages_ISO.ANCIENT_GREEK_ALPHA,"Β ; β","b (classique) ; v (moderne)","b (classique) ; v (moderne)", Languages_ISO.FRENCH,"bêta" };
		list.add(new Extra_Word_Translation(args2));

		String[] args3= {Languages_ISO.ANCIENT_GREEK_ALPHA,"Γ ; γ","g","g", Languages_ISO.FRENCH,"gamma" };
		list.add(new Extra_Word_Translation(args3));

		String[] args4= {Languages_ISO.ANCIENT_GREEK_ALPHA,"Δ ; δ","d ; parfois dh (moderne)","d ; parfois dh (moderne)", Languages_ISO.FRENCH,"delta" };
		list.add(new Extra_Word_Translation(args4));

		String[] args5= {Languages_ISO.ANCIENT_GREEK_ALPHA,"Ε ; ε","e","e", Languages_ISO.FRENCH,"epsilon" };
		list.add(new Extra_Word_Translation(args5));

		String[] args6= {Languages_ISO.ANCIENT_GREEK_ALPHA,"Ζ ; ζ","z","z", Languages_ISO.FRENCH,"zêta" };
		list.add(new Extra_Word_Translation(args6));

		String[] args7= {Languages_ISO.ANCIENT_GREEK_ALPHA,"Η ; η","ê (classique) ; i (moderne)","ê (classique) ; i (moderne)", Languages_ISO.FRENCH,"êta" };
		list.add(new Extra_Word_Translation(args7));

		String[] args8= {Languages_ISO.ANCIENT_GREEK_ALPHA,"Θ ; θ","th","th", Languages_ISO.FRENCH,"thêta" };
		list.add(new Extra_Word_Translation(args8));

		String[] args9= {Languages_ISO.ANCIENT_GREEK_ALPHA,"Ι ; ι","i","i", Languages_ISO.FRENCH,"iota" };
		list.add(new Extra_Word_Translation(args9));

		String[] args10= {Languages_ISO.ANCIENT_GREEK_ALPHA,"Κ ; κ","k","k", Languages_ISO.FRENCH,"kappa" };
		list.add(new Extra_Word_Translation(args10));

		String[] args11= {Languages_ISO.ANCIENT_GREEK_ALPHA,"Λ ; λ","l","l", Languages_ISO.FRENCH,"lambda" };
		list.add(new Extra_Word_Translation(args11));

		String[] args12= {Languages_ISO.ANCIENT_GREEK_ALPHA,"Μ ; μ","m","m", Languages_ISO.FRENCH,"mu" };
		list.add(new Extra_Word_Translation(args12));

		String[] args13= {Languages_ISO.ANCIENT_GREEK_ALPHA,"Ν ; ν","n","n", Languages_ISO.FRENCH,"nu" };
		list.add(new Extra_Word_Translation(args13));

		String[] args14= {Languages_ISO.ANCIENT_GREEK_ALPHA,"Ξ ; ξ","ks, x","ks, x", Languages_ISO.FRENCH,"xi" };
		list.add(new Extra_Word_Translation(args14));

		String[] args15= {Languages_ISO.ANCIENT_GREEK_ALPHA,"Ο ; ο","o","o", Languages_ISO.FRENCH,"omicron" };
		list.add(new Extra_Word_Translation(args15));

		String[] args16= {Languages_ISO.ANCIENT_GREEK_ALPHA,"Π ; π","p","p", Languages_ISO.FRENCH,"pi" };
		list.add(new Extra_Word_Translation(args16));

		String[] args17= {Languages_ISO.ANCIENT_GREEK_ALPHA,"Ρ ; ρ","r","r", Languages_ISO.FRENCH,"rhô" };
		list.add(new Extra_Word_Translation(args17));

		String[] args18= {Languages_ISO.ANCIENT_GREEK_ALPHA,"Σ ; σ","s","s", Languages_ISO.FRENCH,"sigma" };
		list.add(new Extra_Word_Translation(args18));

		String[] args19= {Languages_ISO.ANCIENT_GREEK_ALPHA,"Τ ; τ","t","t", Languages_ISO.FRENCH,"tau" };
		list.add(new Extra_Word_Translation(args19));

		String[] args20= {Languages_ISO.ANCIENT_GREEK_ALPHA,"Υ ; υ","u (classique) ; y, v, f (moderne, selon contexte)","u (classique) ; y, v, f (moderne, selon contexte)", Languages_ISO.FRENCH,"upsilon" };
		list.add(new Extra_Word_Translation(args20));

		String[] args21= {Languages_ISO.ANCIENT_GREEK_ALPHA,"Φ ; φ/ϕ","ph (classique) ; f (moderne)","ph (classique) ; f (moderne)", Languages_ISO.FRENCH,"phi" };
		list.add(new Extra_Word_Translation(args21));

		String[] args22= {Languages_ISO.ANCIENT_GREEK_ALPHA,"Χ ; χ","kh","kh", Languages_ISO.FRENCH,"khi" };
		list.add(new Extra_Word_Translation(args22));

		String[] args23= {Languages_ISO.ANCIENT_GREEK_ALPHA,"Ψ ; ψ","ps","ps", Languages_ISO.FRENCH,"psi" };
		list.add(new Extra_Word_Translation(args23));

		String[] args24= {Languages_ISO.ANCIENT_GREEK_ALPHA,"Ω ; ω","ô","ô", Languages_ISO.FRENCH,"oméga" };
		list.add(new Extra_Word_Translation(args24));

		for(Word_Translation word : list){
			MainActivity.cbd.writeWord(word);
		}


	}

}