package com.example.furry_octo_waddle.sql_manager;

import java.util.List;

public interface BD_rw {
	/* Will be deprecated */
	public List<Word_Translation> getRandomEnglishWordNotTranslated(int nombre);
	public List<Word_Translation> getRandomFrenchWordNotTranslated(int nombre);
	public List<Word_Translation> getRandomPairOfWords(int number);
	
	/*and replace by */
	public List<Word_Translation> getWordFromTable(Word_Translation word,boolean random,int nombre);
	
	/* Will be deprecated */
	public void writeEnglishWordWOTranslation(String word);
	public void writeFrenchWordWOTranslation(String word);
	public void writePairOfWords(String frenchWord,String englishWord);
	public void deleteFrenchWord(String frenchWord);
	
	/*and replaced by */
	public void writeWord(Word_Translation word);
	
	/* Will be deprecated */
	public void deleteEnglishWord(String englishWord);
	public void deleteWordbyIndex(int index);
	
	/*and replaced by */	
	public void deleteWord(Word_Translation word);
	
	public void modifyWordbyId(Word_Translation word);
	public void resetTable();
}
