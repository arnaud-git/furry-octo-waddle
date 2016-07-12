package com.example.furry_octo_waddle.sql_manager;

import java.util.List;

import com.example.furry_octo_waddle.sql_manager.Word_Translation.Extra;
/** BD_rw*/
public interface BD_rw {
	
	//boolean updated = true;
	/* Will be deprecated */
	public List<Word_Translation> getRandomEnglishWordNotTranslated(int nombre);
	public List<Word_Translation> getRandomFrenchWordNotTranslated(int nombre);
	public List<Word_Translation> getRandomPairOfWords(int number);
	public void setUpdateVariable(boolean updated);
	/**Orders*/
	public enum Order {
		/**Randomly arranged*/
		RANDOM,
		/**Alphabetical order (using {@link Word_Translation word.word} for the order )*/
		LANGUAGE_ASC,
		/**Last recent entries*/
		STAMP_DESC, NULL, ID_ASC}
	
	/** @return a list of {@link Word_Translation words} from the table 
	 * @param orderby :the order of the list 
	 * @param nombre : the length of the list, if it equals -1 the longest list will be returned 
	 * @param word : if {@link Word_Translation word.id} is different than 0 the list contains at most only the {@link Word_Translation word} with this id in the table
	 * Otherwise the list contains the words with the same attributes than word (or match the pattern expression in) 
	 * @see  2- {@link Word_Translation word.word} for further information on expression pattern) \n
	 * {@link Order} for the different order parameters
	 * */
	public List<Word_Translation> getWordFromTable(Word_Translation word,Order orderby,int nombre);
	
	/** @deprecated Instantiate a {@link Word_Translation} object and 
	 * Use {@link writeWord(Word_Translation word)}*/
	public void writeEnglishWordWOTranslation(String word);
	/** @deprecated Instantiate a {@link Word_Translation} object and 
	 * Use {@link writeWord(Word_Translation word)}*/
	public void writeFrenchWordWOTranslation(String word);
	/** @deprecated Instantiate a {@link Word_Translation} object and  
	 * Use {@link writeWord(Word_Translation word)}*/
	public void writePairOfWords(String frenchWord,String englishWord);
	/** @deprecated Instantiate a {@link Word_Translation} object and 
	 * Use {@link deleteWord(Word_Translation word) }*/
	public void deleteFrenchWord(String frenchWord);
	
	/**Puts {@link Word_Translation word} into the table (database)*/
	public void writeWord(Word_Translation word);
	
	/** @deprecated Instantiate a {@link Word_Translation} object and  
	 * Use {@link deleteWord(Word_Translation word)}*/
	public void deleteEnglishWord(String englishWord);
	
	/**Erases {@link Word_Translation word} represented by the index from the table (database)
	 * @param index : the index of the word in the table*/
	public void deleteWordbyIndex(int index,Extra type);
		
	/**Erases {@link Word_Translation word} from the table (database)
	 * @param word :
	 *	if {@link Word_Translation word.id} is different than 0, at most only the word with this id in the table will be erased
	 * Otherwise the words with the same attributes than word (or match the pattern expression in ) will be erased
	 * * @see  2- {@link Word_Translation word.word} for further information on expression pattern)
	 *  */
	public void deleteWord(Word_Translation word);
	
	/**Modifies {@link Word_Translation word} in the table (database)
	 * @param word : the {@link Word_Translation word} that would be updated into the table (replaced the {@link Word_Translation word.id}-th value of the table)
	 * */
	public void modifyWordbyId(Word_Translation word);
	
	/**Erases all contents in the table 
	 * (the table still exists after the call)*/
	public void resetTable(Extra type);
	
	public void showTable();
}
