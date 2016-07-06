
package com.example.furry_octo_waddle.sql_manager.alphabet;
import java.util.ArrayList;
import java.util.List;

import com.example.furry_octo_waddle.activities.MainActivity;
import com.example.furry_octo_waddle.sql_manager.Extra_Word_Translation;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

public class Greek_alphabet{

private static final List<Word_Translation> list = new ArrayList<Word_Translation>();

public Greek_alphabet(){

String[] args1= {"@gr","Α ; α","a","a", "@fr","alpha" };
list.add(new Extra_Word_Translation(args1));

String[] args2= {"@gr","Β ; β","b (classique) ; v (moderne)","b (classique) ; v (moderne)", "@fr","bêta" };
list.add(new Extra_Word_Translation(args2));

String[] args3= {"@gr","Γ ; γ","g","g", "@fr","gamma" };
list.add(new Extra_Word_Translation(args3));

String[] args4= {"@gr","Δ ; δ","d ; parfois dh (moderne)","d ; parfois dh (moderne)", "@fr","delta" };
list.add(new Extra_Word_Translation(args4));

String[] args5= {"@gr","Ε ; ε","e","e", "@fr","epsilon" };
list.add(new Extra_Word_Translation(args5));

String[] args6= {"@gr","Ζ ; ζ","z","z", "@fr","zêta" };
list.add(new Extra_Word_Translation(args6));

String[] args7= {"@gr","Η ; η","ê (classique) ; i (moderne)","ê (classique) ; i (moderne)", "@fr","êta" };
list.add(new Extra_Word_Translation(args7));

String[] args8= {"@gr","Θ ; θ","th","th", "@fr","thêta" };
list.add(new Extra_Word_Translation(args8));

String[] args9= {"@gr","Ι ; ι","i","i", "@fr","iota" };
list.add(new Extra_Word_Translation(args9));

String[] args10= {"@gr","Κ ; κ","k","k", "@fr","kappa" };
list.add(new Extra_Word_Translation(args10));

String[] args11= {"@gr","Λ ; λ","l","l", "@fr","lambda" };
list.add(new Extra_Word_Translation(args11));

String[] args12= {"@gr","Μ ; μ","m","m", "@fr","mu" };
list.add(new Extra_Word_Translation(args12));

String[] args13= {"@gr","Ν ; ν","n","n", "@fr","nu" };
list.add(new Extra_Word_Translation(args13));

String[] args14= {"@gr","Ξ ; ξ","ks, x","ks, x", "@fr","xi" };
list.add(new Extra_Word_Translation(args14));

String[] args15= {"@gr","Ο ; ο","o","o", "@fr","omicron" };
list.add(new Extra_Word_Translation(args15));

String[] args16= {"@gr","Π ; π","p","p", "@fr","pi" };
list.add(new Extra_Word_Translation(args16));

String[] args17= {"@gr","Ρ ; ρ","r","r", "@fr","rhô" };
list.add(new Extra_Word_Translation(args17));

String[] args18= {"@gr","Σ ; σ","s","s", "@fr","sigma" };
list.add(new Extra_Word_Translation(args18));

String[] args19= {"@gr","Τ ; τ","t","t", "@fr","tau" };
list.add(new Extra_Word_Translation(args19));

String[] args20= {"@gr","Υ ; υ","u (classique) ; y, v, f (moderne, selon contexte)","u (classique) ; y, v, f (moderne, selon contexte)", "@fr","upsilon" };
list.add(new Extra_Word_Translation(args20));

String[] args21= {"@gr","Φ ; φ/ϕ","ph (classique) ; f (moderne)","ph (classique) ; f (moderne)", "@fr","phi" };
list.add(new Extra_Word_Translation(args21));

String[] args22= {"@gr","Χ ; χ","kh","kh", "@fr","khi" };
list.add(new Extra_Word_Translation(args22));

String[] args23= {"@gr","Ψ ; ψ","ps","ps", "@fr","psi" };
list.add(new Extra_Word_Translation(args23));

String[] args24= {"@gr","Ω ; ω","ô","ô", "@fr","oméga" };
list.add(new Extra_Word_Translation(args24));

for(Word_Translation word : list){
	MainActivity.cbd.writeWord(word);
}


}

}