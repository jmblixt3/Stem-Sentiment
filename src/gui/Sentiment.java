package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import java.net.URL;
import java.text.DecimalFormat;


public class Sentiment {
	private double sentiment;
	private double confidence;
	private double pos,neg,neutral;
	private String confidencepr;
	private String color;
	private String emotion;
	private DecimalFormat df;


	public Sentiment(String word) {
		df = new DecimalFormat("##.#%");
		try {
			PostHandle(sendPOST(word));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(confidence+color+emotion);
		
	}


	public String colors() {

		return color;
	}


	public double getSentiment() {
		return sentiment;
	}

	public void setSentiment(double sentiment) {
		this.sentiment = sentiment;
	}

	public String getConfidencepr() {
		return confidencepr;
	}
	public double getConfidence() {
		return confidence;
	}

	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}
	private static String sendPOST(String word) throws IOException {
		URL obj = new URL("http://text-processing.com/api/sentiment/");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(("text="+word).getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			
			
			System.out.println(response.toString());
			return response.toString();
		} else {
			System.out.println("POST request not worked");
			return null;
		}
	}

	public void PostHandle(String eval) {
		System.out.println(eval.split("\"label\": \"")[1]);
		neutral= Double.parseDouble(eval.split("\"neutral\": ")[1].split(",")[0]);
		pos = Double.parseDouble(eval.split("\"pos\": ")[1].split("}")[0]);
		neg = Double.parseDouble(eval.split("\"neg\": ")[1].split(",")[0]);
		switch(eval.split("\"label\": \"")[1]) {
		case "neutral\"}":
			color = "gray";
			setEmotion("Neutral");
			break;
		case "pos\"}":
			color = "green";
			setEmotion("Positive");
			break;
		case "neg\"}":
			color = "red";
			setEmotion("Negative");
			break;
		}
		if(neutral<0.5&&(pos>0.6||neg>0.6)) {
			if(pos>neg) 
				confidence = pos;
			else
				confidence =neg;
		}else {
			color = "gray";
			setEmotion("Neutral");
			confidence = neutral;
		}
		confidencepr = df.format((double)confidence);
	}

	public String getEmotion() {
		return emotion;
	}

	public void setEmotion(String emotion) {
		this.emotion = emotion;
	}


}
