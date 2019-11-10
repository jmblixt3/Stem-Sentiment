package gui;

import java.awt.Color;

import org.python.util.PythonInterpreter;

public class Sentiment {
	private double sentiment;
	private double confidence;
	private String color;
	public Sentiment (String word) {
		//PythonInterpreter interp = new PythonInterpreter();
		//interp.execfile("python/live_sentiment.py");
		setSentiment(0.1);
		setConfidence(0.5);
	}
	public String colors() {
		
		return color;
	}
	
	public String emotion() {
		if(sentiment<0.45) {
			color ="red";
			return "Negative";
		}else if (sentiment>0.55) {
			color ="green";
			return "Positive";
		}else
			return "Neutral";
	}
	
	public double getSentiment() {
		return sentiment;
	}
	public void setSentiment(double sentiment) {
		this.sentiment = sentiment;
	}
	public double getConfidence() {
		return confidence;
	}
	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}
}
