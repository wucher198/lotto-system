package pl.myjava.util.read.htmlpage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.myjava.util.read.htmlpage.consts.LiteralConsts;

public class ReadHTMLLottoPage {
	private static final String TAG_THAT_WE_SEEK = "div";
	private static final String PAGE_URL = "https://www.lotto.pl/lotto/wyniki-i-wygrane";
	private static final String USER_AGENT = "Mozilla/5.0";
	
	private static final String[] searchString = {"resultsItem lotto"};//, "lotto", "lottoPlus", "lottoSzansa"};
	
	public static void main(String[] args) throws Exception {
		URL url = new URL(PAGE_URL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setRequestMethod("POST");
		connection.setRequestProperty("User-Agent", USER_AGENT);
		connection.setRequestProperty("Accept-Language", "pl,en-US;q=0.7,en;q=0.3");

		String postParameters = "data_losowania%5Bdate%5D=2018-03-06&op=&form_build_id=form-7da30f6f3a3b46cd8e8dd1f72f74f39c&form_id=lotto_wyszukaj_form";
		
		connection.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.writeBytes(postParameters);
		wr.flush();
		wr.close();
		
		int responseCode = connection.getResponseCode();
		System.out.println("Response Code: " + responseCode);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		List<String> result = searchForTags(in, TAG_THAT_WE_SEEK, "class", Arrays.asList(searchString));
		result.forEach(System.out::println);
		
		in.close();
	}

	private static ArrayList<String> searchForTags(BufferedReader in, String tagWhatWeSeek, String attribute, List<String> searchStrings) throws IOException {
		ArrayList<String> result = new ArrayList<>();
		int input;
		StringBuilder tagName = new StringBuilder();
		StringBuilder wholeTag = new StringBuilder();
		boolean searchForClosing = false;
		boolean closingTag = false;
		boolean insideTag = false;
		int innerTagCounter = 0;
		
		while ((input = in.read()) != -1) {
			if (searchForClosing) { 
				wholeTag.append((char) input); // Zapamietywanie calego znacznika od <div> do </div> ze wszystkimi tagami wewnatrz
			}
			
			if (insideTag) {
				tagName.append((char) input); // <div class="gvhjvh jkjhgkj ljkhkj">
			}
			
			if (ifInputEquals(input, '>')) {
				if (searchForClosing) {
					if (closingTag) {
						if (checkIfEndContains(tagName.toString(), tagWhatWeSeek)) {
						// sprawdzamy czy nie ma jakichkolwiek wewnetrznych tagow i czy dany tag jest zamykajacym
							if (innerTagCounter == 0) {
								result.add(wholeTag.toString());						
								wholeTag = new StringBuilder();
								searchForClosing = false;
							}
						}
						
						innerTagCounter--;
					} else {
						innerTagCounter++;
					}
				} else if (checkIfContains(tagName.toString(), tagWhatWeSeek, attribute, searchStrings.toArray(new String[0]))) { // jesli znalezlismy tag ktorego szukamy
					searchForClosing = true;
					wholeTag.append(tagName);
				}
				
				closingTag = false;
				insideTag = false;
				tagName = new StringBuilder();
				
				// sprawdzamy czy to poczatek znacznika
			} else {
				// Sprawdzamy czy nastepny symbol to znak otwierajacy znacznik <
				if (ifInputEquals(input, '<')) {
					int secondInput = 0;
						
					// jesli tak to musimy sprawdzić czy nie znalezlismy koncowego
					if ((secondInput = in.read()) != -1 && ((char) secondInput) == '/') {
						closingTag = true;
					}
					
					// Sprawdzamy czy nie jestesmy w srodku znacznika pomiedzy np. <div>...</div>
					if (!insideTag) {
						tagName.append((char) input);
					}
					
					// Musimy pamietac aby rowniez dodac drugi odczytany znak
					tagName.append((char) secondInput);
					
					// Jesli szukamy znacznika zamykajacego dodajemy tylko drugi oddczytany znak do calego tekstku
					if (searchForClosing) {
						wholeTag.append((char) secondInput);
					}
					
					insideTag = true;
				}
			}
		}
		
		return result;
	}

	private static boolean ifInputEquals(int input, char symbol) {
		return ((char) input) == symbol;
	}
	
	// czy konczacy tag to jest ten ktory szukamy
	// </div>
	private static boolean checkIfEndContains(String tag, String searchTag) {
		return ifTag(tag, searchTag, false); 
	}
	
	//if begin tag equals
	//<div class="resultsItem lottoPlus dymek_kulki">
	private static boolean checkIfContains(String tag, String searchTag, String searchField, String... searchValue) throws IOException {
		boolean result = false;
		
		if (ifTag(tag, searchTag, true)) {
			result = ifTagContains(tag, searchField, searchValue);
		}
		
		return result;
	}

	private static boolean ifTag(String tag , String searchTag, boolean startTag) {
		boolean result = false;
		
		if (startTag) {
			result = !tag.contains(LiteralConsts.SLASH) && tag.contains(searchTag); 
		} else {
			result = tag.contains(LiteralConsts.SLASH) && tag.contains(searchTag);
		}
		
		return result;
	}
	
	private static boolean ifTagContains(String tag, String searchField, String... searchValue) throws IOException {
		boolean result = false;
		
		if (tag.contains(searchField)) {
//			StringReader reader = new StringReader(tag.substring(tag.indexOf(searchField) + searchField.length() + 2));
//			int input = 0;
//			boolean finish = false;
//			StringBuilder builder = new StringBuilder();
//			
//			while ((input = reader.read()) != -1 && !finish) {
//				if (((char) input) != LiteralConsts.DOUBLE_QUOTE) {
//					builder.append((char) input);
//				} else {
//					finish = true;
//				}
//			}
			
			String tagName = tag.substring(tag.indexOf(searchField) + searchField.length() + 2);
			
			for (String search : searchValue) {				
				if (StringUtils.isNotEmpty(tagName) && tagName.toString().contains(search) ) {
					result = true;
				}
			}
		}
		
		return result;
	}
}