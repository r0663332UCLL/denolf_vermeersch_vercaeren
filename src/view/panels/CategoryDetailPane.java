package view.panels;

import domain.controller.Controller;
import domain.model.Category;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Observable;

public class CategoryDetailPane extends GridPane {
	private Button btnOK, btnCancel;
    private Controller controller = new Controller();
    ArrayList catList = (ArrayList) controller.doAction("GetCategory", null);
    public CategoryDetailPane() {
        super();
		this.setPrefHeight(150);
		this.setPrefWidth(300);
		
		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);

		this.add(new Label("Title:"), 0, 0, 1, 1);
        TextField titleField = new TextField();
		this.add(titleField, 1, 0, 1, 1);

		this.add(new Label("Description:"), 0, 1, 1, 1);
        TextField descriptionField = new TextField();
		this.add(descriptionField, 1, 1, 1, 1);

		this.add(new Label("Main Category:"), 0, 2, 1, 1);
        ComboBox categoryField = new ComboBox<>();
        for (int i = 0; i < catList.size(); i++) {
            Category tmpCat = (Category) catList.get(i);
            if (tmpCat.getMainCategory() == null) {
                categoryField.getItems().add(tmpCat.getTitle());
            }
        }

		this.add(categoryField, 1, 2, 1, 1);

		btnCancel = new Button("Cancel");
		this.add(btnCancel, 0, 3, 1, 1);

		btnOK = new Button("Save");
		btnOK.isDefaultButton();
		this.add(btnOK, 1, 3, 1, 1);
        //voor UI: in parameters eerst naam, dan description en dan de oudercategorie. Indien er geen oudercategorie is, laatste element in lijst null maken
        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<Object> params = new ArrayList<>();
                params.add(titleField.getText());
                params.add(descriptionField.getText());
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

}
