package pl.myjava.util.read.htmlpage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class ReadHTMLLottoPage {
	private static final String TAG_THAT_WE_SEEK = "div";
	private static final String PAGE_URL = "http://www.lotto.pl/lotto/wyniki-i-wygrane";
	private static final String USER_AGENT = "Mozilla/5.0";
	
	private static final String[] searchString = {"lotto", "lottoPlus", "lottoSzansa"};
	
	public static void main(String[] args) throws Exception {
		URL url = new URL(PAGE_URL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setRequestMethod("POST");
		connection.setRequestProperty("User-Agent", USER_AGENT);
		connection.setRequestProperty("Accept-Language", "pl,en-US;q=0.7,en;q=0.3");
		
		String postParameters = "data_losowania%5Bdate%5D=2018-03-06&op=&form_build_id=form-bed0685ead3b2f77ac99ed687f49542b&form_id=lotto_wyszukaj_form";
		
		connection.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.writeBytes(postParameters);
		wr.flush();
		wr.close();
		
		int responseCode = connection.getResponseCode();
		System.out.println("Response Code: " + responseCode);
		
		String testString = "<td>hshshshshs</td><div class=\"lotto\">Some Content<div>Inside content</div></div><p>Paragraph</p>";
		
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//		BufferedReader in = new BufferedReader(new StringReader(testString));
		int input = 0;
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
			
			if (((char) input) == '>') {
				if (searchForClosing) {
					if (closingTag) {
						if (checkIfEndContains(tagName.toString(), TAG_THAT_WE_SEEK)) {
						// sprawdzamy czy nie ma jakichkolwiek wewnetrznych tagow i czy dany tag jest zamykajacym
							if (innerTagCounter == 0) {
								System.out.println(wholeTag);						
								wholeTag = new StringBuilder();
								searchForClosing = false;
							}
						}
						
						innerTagCounter--;
					} else {
						innerTagCounter++;
					}
				} else if (checkIfContains(tagName.toString(), TAG_THAT_WE_SEEK, "class", searchString)) { // jesli znalezlismy tag ktorego szukamy
					searchForClosing = true;
					wholeTag.append(tagName);
				}
				
				closingTag = false;
				insideTag = false;
				tagName = new StringBuilder();
			} else {
				if (((char) input) == '<') { // sprawdzamy czy to poczatek znacznika
					int secondInput = 0;
						
					if ((secondInput = in.read()) != -1 && ((char) secondInput) == '/') { // jesli tak to musimy sprawdzić czy nie znalezlismy koncowego
						closingTag = true;
					}
					
					if (!insideTag) {
						tagName.append((char) input);
					}
					
					tagName.append((char) secondInput);
					
					if (searchForClosing) {
						wholeTag.append((char) secondInput);
					}
					
					insideTag = true;
				}
			}
		}
		
		in.close();
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
			result = !tag.contains("/") && tag.contains(searchTag); 
		} else {
			result = tag.contains("/") && tag.contains(searchTag);
		}
		
		return result;
	}
	
	private static boolean ifTagContains(String tag, String searchField, String... searchValue) throws IOException {
		boolean result = false;
		
		if (tag.contains(searchField)) {
			StringReader reader = new StringReader(tag.substring(tag.indexOf(searchField) + searchField.length() + 2));
			int input = 0;
			boolean finish = false;
			StringBuilder builder = new StringBuilder();
			
			while ((input = reader.read()) != -1 && !finish) {
				if (((char) input) != '"') {
					builder.append((char) input);
				} else {
					finish = true;
				}
			}
			
			for (String search : searchValue) {				
				if (builder.toString().length() > 0 && !builder.toString().equals("") && builder.toString().contains(search) ) {
					result = true;
				}
			}
		}
		
		return result;
	}
}

//Szukaj<img src="/sites/all/themes/basic/images/svg/searchWyniki.svg" alt="" class="img-fluid">
//<input maxlength="30" name="data_losowania[date]" id="wyszukaj-wyniki-form-datepicker-popup-1" size="20" value="2018-03-03" class="form-text inputCalendar hasDatepicker date-popup-init" placeholder="data losowania" type="text">