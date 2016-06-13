package com.example.furry_octo_waddle.sql_manager;

import java.util.List;
/** BD_rw*/
public interface BD_rw {
	/* Will be deprecated */
	public List<Word_Translation> getRandomEnglishWordNotTranslated(int nombre);
	public List<Word_Translation> getRandomFrenchWordNotTranslated(int nombre);
	public List<Word_Translation> getRandomPairOfWords(int number);
	
	/** @return a list of words from the table
	 * @param random : if false the list is in alphabetical order otherwise in a random one
	 * @param nombre : the length of the list, if it equals -1 the longest list will be returned 
	 * @param word : if word.id is different than 0 the list contains at most only the word with this id in the table
	 * Otherwise the list contains the words with the same attributes than word
	 * except if an attribute equals to '*' then this attribute is not considerated in the query
	 * */
	public List<Word_Translation> getWordFromTable(Word_Translation word,boolean random,int nombre);
	
	/** @deprecated Instantiate a Word_Translation object and 
	 * Use writeWord(Word_Translation word)*/
	public void writeEnglishWordWOTranslation(String word);
	/** @deprecated Instantiate a Word_Translation object and 
	 * Use writeWord(Word_Translation word)*/
	public void writeFrenchWordWOTranslation(String word);
	/** @deprecated Instantiate a Word_Translation object and  
	 * Use writeWord(Word_Translation word)*/
	public void writePairOfWords(String frenchWord,String englishWord);
	/** @deprecated Instantiate a Word_Translation object and 
	 * Use deleteWord(Word_Translation word)*/
	public void deleteFrenchWord(String frenchWord);
	
	/**Puts word into the table (database)*/
	public void writeWord(Word_Translation word);
	
	/** @deprecated Instantiate a Word_Translation object and  
	 * Use deleteWord(Word_Translation word)*/
	public void deleteEnglishWord(String englishWord);
	
	/**Erases word represented by the index from the table (database)
	 * @param index : the index of the word in the table*/
	public void deleteWordbyIndex(int index);
		
	/**Erases word from the table (database)
	 * @param word :
	 *	if word.id is different than 0, at most only the word with this id in the table will be erased
	 * Otherwise the words with the same attributes than word will be erased
	 * except if an attribute equals to '*' then this attribute is not considerated in the query
	 *  */
	public void deleteWord(Word_Translation word);
	
	/**Modifies word in the table (database)
	 * @param word : the word that would be updated into the table (replaced the word.id-th value of the table)
	 * */
	public void modifyWordbyId(Word_Translation word);
	
	/**Erases all contents in the table 
	 * (the table still exists after the call)*/
	public void resetTable();
}
