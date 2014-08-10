package interview.practice.phoneNumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class PhoneNumberFinder {
	String url;
	ArrayList<String> phoneNumbers;

	public ArrayList<String> findPhoneNumbers(String url) {
		//
		this.url = url;
		this.phoneNumbers = new ArrayList<String>();

		ArrayList<String> response = this.getResponse();

		Pattern pattern = Pattern
				.compile("(((\\d-?)?\\d{3}-?)?(\\d{3}-?\\d{4}))");

		ListIterator<String> iter = response.listIterator();
		while (iter.hasNext()) {
			// Deal with the line
			String line = iter.next();
			Matcher matcher = pattern.matcher(line);
			while (matcher.find())
				this.phoneNumbers.add(matcher.group());
		}

		return this.phoneNumbers;
	}

	private ArrayList<String> getResponse() {

		ArrayList<String> returnValue = new ArrayList<String>();
		//
		HttpClient client = new HttpClient();

		// Create a method instance.
		GetMethod method = new GetMethod(url);

		// Provide custom retry handler is necessary
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3, false));

		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					method.getResponseBodyAsStream(), Charset.forName("UTF-8")));

			// break the html input into lines
			String line = "";
			try {
				while ((line = br.readLine()) != null) {
					returnValue.add(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (HttpException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		return returnValue;
	}

}
