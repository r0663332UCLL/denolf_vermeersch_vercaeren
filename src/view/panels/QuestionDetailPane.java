package view.panels;

import domain.controller.Controller;
import domain.controller.ControllerException;
import domain.model.Category;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class QuestionDetailPane extends GridPane {
	private Button btnOK, btnCancel;
	private int rowIndex = 2;
	private Controller controller = new Controller();
	ArrayList<String> statements = new ArrayList<>();
    ArrayList catList = (ArrayList) controller.doAction("GetCategory", null);

    public QuestionDetailPane() {
		this.setPrefHeight(300);
		this.setPrefWidth(320);
		
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        
		add(new Label("Question: "), 0, 0, 1, 1);
        TextField questionField = new TextField();
		add(questionField, 1, 0, 2, 1);
		
		add(new Label("Statement: "), 0, 1, 1, 1);
        TextField statementField = new TextField();
		add(statementField, 1, 1, 2, 1);

		add(new Label("Statements: "), 0, 2, 1, 1);
        TextArea statementsArea = new TextArea();
		statementsArea.setPrefRowCount(5);
		statementsArea.setEditable(false);
		add(statementsArea, 1, 2, 2, 5);

		Pane addRemove = new HBox();
        Button btnAdd = new Button("add");
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                statements.add(statementField.getText());
                statementsArea.setText(statementsArea.getText() + statementField.getText() + System.getProperty("line.separator"));
            }
        });
		addRemove.getChildren().add(btnAdd);

        Button btnRemove = new Button("remove");
		btnRemove.setOnAction(new RemoveStatementListener());
		addRemove.getChildren().add(btnRemove);
		add(addRemove, 1, 8, 2, 1);
        btnRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                statementsArea.setText("");
                for (int i = 0; i < statements.size(); i++) {
                    if (statements.get(i).equals(statementField.getText())) {
                        statements.remove(i);
                        continue;
                    }
                    statementsArea.setText(statementsArea.getText() + statements.get(i) + System.getProperty("line.separator"));
                }
            }
        });
		add(new Label("Category: "), 0, 9, 1, 1);
        ComboBox categoryField = new ComboBox();
        for (int i = 0; i < catList.size(); i++) {
            Category tmpCat = (Category) catList.get(i);
            categoryField.getItems().add(tmpCat.getTitle());
        }
		add(categoryField, 1, 9, 2, 1);

		add(new Label("Feedback: "), 0, 10, 1, 1);
        TextField feedbackField = new TextField();
		add(feedbackField, 1, 10, 2, 1);

		btnCancel = new Button("Cancel");
		btnCancel.setText("Cancel");
		add(btnCancel, 0, 11, 1, 1);

		btnOK = new Button("Save");
		btnOK.isDefaultButton();
		btnOK.setText("Save");
		add(btnOK, 1, 11, 2, 1);
		//voor UI: eerst het type vraag, dan de vraag, dan een arraylist van Strings met statements, dan feedback en dan de categorie
		btnOK.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ArrayList<Object> params = new ArrayList<>();
				if (statements.size() <= 2) {
				    if (statements.get(0).equals("yes")) {
                        params.add("YesNoQuestion");
                    } else {
				        params.add("MultipleChoiceQuestion");
                    }
                } else {
				    params.add("MultipleChoiceQuestion");
				}
				params.add(questionField.getText());
				params.add(statements);
				params.add(feedbackField.getText());
				params.add(categoryField.getSelectionModel().getSelectedItem().toString());
				controller.doAction("AddQuestion", params);
			}
		});
	}

	public void setSaveAction(EventHandler<ActionEvent> saveAction) {
		btnOK.setOnAction(saveAction);
	}

	public void setCancelAction(EventHandler<ActionEvent> cancelAction) {
		btnCancel.setOnAction(cancelAction);
	}

	class AddStatementListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
		}
	}

	class RemoveStatementListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
		}
	}
}
