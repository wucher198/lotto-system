package pl.myjava.util.read.htmlpage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.stream.Collectors;

public class URLReaderStrategyImpl implements ReaderStrategy {
	private static final String USER_AGENT = "Mozilla/5.0";
	
	private BufferedReader in;
	private HttpURLConnection httpConnection;	
	private int responseCode = HttpURLConnection.HTTP_NOT_FOUND;
	
	public URLReaderStrategyImpl(URL url, Map<String, String> parameters) throws IOException {
		getConnetion(url);
		sendRequest(createURLParameters(parameters));
	}

	@Override
	public int read() throws IOException {
		return in.read();
	}
	
	public boolean canRead() {
		return responseCode == HttpURLConnection.HTTP_OK ? true : false;
	}
	
	private void getConnetion(URL url) throws IOException {
		httpConnection = (HttpURLConnection) url.openConnection();
		httpConnection.setRequestMethod("POST");
		httpConnection.setRequestProperty("User-Agent", USER_AGENT);
		httpConnection.setRequestProperty("Accept-Language", "pl,en-US;q=0.7,en;q=0.3");
		httpConnection.setDoOutput(true);
	}
	
	private void sendRequest(String encodedParameters) throws IOException {
		if (httpConnection != null) {
			DataOutputStream os = new DataOutputStream(httpConnection.getOutputStream());
			
			if (StringUtils.isNotEmpty(encodedParameters)) {
				os.writeBytes(encodedParameters);
			}
			
			os.flush();
			os.close();
			
			responseCode = httpConnection.getResponseCode();
		}
	}
	
	private String createURLParameters(Map<String, String> parameters) {
		return parameters.keySet().stream()
				.map(parameters::get)
				.map(URLEncoder::encode)
				.collect(Collectors.joining("&"));				
	}

}
