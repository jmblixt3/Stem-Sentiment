package gui;



import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;


public class BasicFXMLController {

	@FXML
	private Button calculate;
	@FXML
	private TextField inputWord;
	@FXML
	private Label sentiment;
	@FXML
	private Label emotion;
	@FXML
	private Label confidence;
	@FXML
	private Pane emotionPane;
	
	Sentiment words;
	//private final int wait = 10000;


	// Add a public no-args constructor

	public BasicFXMLController() {
		
	}

	@FXML
	private void Calculate() {
		
		words = new Sentiment(inputWord.getText());
		
		emotionPane.setStyle("-fx-border-color:black; -fx-background-color:"+ words.colors()+";");
		sentiment.setText(""+words.getSentiment());
		confidence.setText(""+words.getConfidence());
		emotion.setText(words.emotion());
		emotion.setTextAlignment(TextAlignment.CENTER);
		
		//System.out.println("Yeet");
	}




	@FXML
	public void Quit() {
		System.out.println("CLOSING");
		System.exit(0);
	}
}