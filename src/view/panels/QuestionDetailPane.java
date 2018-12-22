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
    ArrayList<Object> catList = controller.doActionWithReturnValue("GetCategory", new ArrayList<>());

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
                for (int i = 0; i < statements.size(); i++) {
                    statementsArea.setText(statementsArea.getText() + statements.get(i));
                }
            }
        });
		addRemove.getChildren().add(btnAdd);

        Button btnRemove = new Button("remove");
		btnRemove.setOnAction(new RemoveStatementListener());
		addRemove.getChildren().add(btnRemove);
		add(addRemove, 1, 8, 2, 1);

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
				params.add(questionField.getText());

				if (categoryField.getSelectionModel().getSelectedItem() == null) {
					params.add(null);
				} else {
					for (int i = 0; i < catList.size(); i++) {
						Category tmpCat = (Category) catList.get(i);
						if (tmpCat.getTitle().equals(categoryField.getSelectionModel().getSelectedItem().toString())) {
							params.add(catList.get(i));
						}
					}
				}
				controller.doAction("AddCategory", params);
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
