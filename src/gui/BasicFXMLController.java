package gui;



import javafx.event.ActionEvent;
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
	/*
	 * @FXML private Label sentiment;
	 */
	@FXML
	private Label emotion;
	@FXML
	private Label confidence;
	@FXML
	private AnchorPane emotionPane;
	
	Sentiment words;
	String color;
	int pr;
	//private final int wait = 10000;


	// Add a public no-args constructor

	public BasicFXMLController() {
		
	}

	@FXML
	private void Calculate() {
		
		words = new Sentiment(inputWord.getText());
		pr = 100-(int)(words.getConfidence()*100);
		if(words.colors() =="red") {
			color = "rgb("+(pr)+"%,0%,0%)";
		}else if(words.colors() =="green") {
			color = "rgb(0%,"+(pr)+"%,0%)";
		}else {
			color =words.colors();
		}
		System.out.println(color);
		emotionPane.setStyle("-fx-border-color:black; -fx-background-color:"+ color+";");
		//sentiment.setText(""+words.getSentiment());
		confidence.setText(words.getConfidencepr());
		emotion.setText(words.getEmotion());
		emotion.setTextAlignment(TextAlignment.CENTER);
		//System.out.println("Yeet");
		// This works
		Thread thread = new Thread(new Runnable() {

		    @Override
		    public void run() {
		         try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        emotionPane.setStyle("-fx-border-color:black; -fx-background-color:"+ "white"+";");
		 		//sentiment.setText(""+words.getSentiment());
		 		confidence.setText(""+0.0);
		 		emotion.setText("Unknown");
		 		inputWord.clear();
		    }
		            
		});
		        
		thread.start();
	}

	@FXML
	public void onEnter(ActionEvent ae){
	   Calculate();
	}


	@FXML
	public void Quit() {
		System.out.println("CLOSING");
		System.exit(0);
	}
}