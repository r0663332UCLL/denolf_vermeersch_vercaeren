package view.panels;

import java.util.ArrayList;
import java.util.List;

import domain.model.Question;
import domain.model.Test;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TestPane extends GridPane {
	private Button submitButton;
	private ToggleGroup statementGroup;
	private Test test;
	private ArrayList<String> answers;
	private Question question;
	
	public TestPane (Test test, ArrayList<String> answers){
		super();
		this.test = test;
		this.answers = answers;
        question = test.next();
		this.setPrefHeight(300);
		this.setPrefWidth(750);
		
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

		Label questionField = new Label();
		add(questionField, 0, 0, 1, 1);
		questionField.setText(question.getQuestion());

		statementGroup = new ToggleGroup();
		for (int i = 0; i < question.getStatements().size(); i++) {
		    RadioButton but = new RadioButton(question.getStatements().get(i));
		    but.setToggleGroup(statementGroup);
		    add(but, 0,i+1,1,1);
        }
		submitButton = new Button("Submit");
		add(submitButton,0,9,1,1);
		submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getSelectedStatements();
                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
        });
	}
	
	public void setProcessAnswerAction(EventHandler<ActionEvent> processAnswerAction) {
		submitButton.setOnAction(processAnswerAction);
	}

	public void getSelectedStatements() {
		if(statementGroup.getSelectedToggle()!=null){
			answers.add(statementGroup.getSelectedToggle().getUserData().toString());
		}
	}
}
