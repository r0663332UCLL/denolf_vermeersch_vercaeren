package view.panels;

import java.util.ArrayList;
import java.util.Observer;

import domain.controller.Controller;
import domain.model.Test;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MessagePane extends GridPane {
    String feedback = "";
    Controller controller = new Controller();
    Test test = (Test) controller.doAction("GenerateTest", new ArrayList<>());
    ArrayList<String> answers = new ArrayList<>();
	public MessagePane (){
	    setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        TextArea textArea = new TextArea();
        if (test.getQuestions() != null) {
            textArea.setPrefRowCount(test.getQuestions().size()-1);
        }
		Button testButton = new Button("Evaluate");
		testButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
			    test = (Test) controller.doAction("GenerateTest", new ArrayList<>());
                while (test.hasQuestions()) {
                    TestPane questionPane = new TestPane(test, answers);
                    final Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    Group root = new Group();
                    Scene scene = new Scene(root);
                    stage.setOnHidden(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent event) {
                            if (answers.size() == test.getQuestions().size()) {
                                ArrayList<String> newAnswers = new ArrayList<>();
                                for (int i = answers.size() - 1; i >= 0; i--) {
                                    newAnswers.add(answers.get(i));
                                }
                                test.setUserAnswers(newAnswers);
                                feedback = test.getFeedback();
                                textArea.setText(feedback);
                                add(textArea, 0, 3,1,1);
                            }
                        }
                    });
                    root.getChildren().add(questionPane);
                    stage.setScene(scene);
                    stage.show();
                }
			}
		});


		add(testButton, 0,1,1,1);
		setHalignment(testButton, HPos.CENTER);
	}
 }