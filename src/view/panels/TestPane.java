package view.panels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	private ArrayList<Integer> rowindexes = new ArrayList<>();
	
	public TestPane (Test test, ArrayList<String> answers){
		super();
		this.test = test;
		this.answers = answers;
        question = this.test.next();
		this.setPrefHeight(300);
		this.setPrefWidth(750);
		
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

		Label questionField = new Label();
		add(questionField, 0, 0, 1, 1);
		questionField.setText(question.getQuestion());

		statementGroup = new ToggleGroup();
		ArrayList<String> statements = question.getStatements();
        Random r = new Random();
        int curRow = 0;
        rowindexes.add(0);
		for (int i = 0; i < question.getStatements().size(); i++) {
		    RadioButton but = new RadioButton(statements.get(i));
		    but.setToggleGroup(statementGroup);
		    while(rowindexes.contains(curRow)) {
                curRow = r.nextInt((question.getStatements().size() - 1) + 1) + 1;

            }
            rowindexes.add(curRow);
            add(but, 0, curRow,1,1);
        }
		submitButton = new Button("Submit");
		add(submitButton,0,9,1,1);
		submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getSelectedStatements();
                if (statementGroup.getSelectedToggle() != null) {
                    final Node source = (Node) event.getSource();
                    final Stage stage = (Stage) source.getScene().getWindow();
                    stage.close();
                }
            }
        });
	}
	
	public void setProcessAnswerAction(EventHandler<ActionEvent> processAnswerAction) {
		submitButton.setOnAction(processAnswerAction);
	}

	public void getSelectedStatements() {
		if(statementGroup.getSelectedToggle()!=null){
            Pattern pattern = Pattern.compile("'.*'");
            Matcher matcher = pattern.matcher(statementGroup.getSelectedToggle().toString());
            if (matcher.find()) {
                String str = matcher.group(0);
                str = str.substring(1,str.length()-1);
                answers.add(str);
            }
		}
	}
}
