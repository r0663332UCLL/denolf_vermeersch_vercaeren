package view.panels;

import domain.controller.Controller;
import domain.model.ApplicationService;
import domain.model.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;


public class CategoryOverviewPane extends GridPane {
	private TableView table;
	private Button btnNew;
	Controller controller = new Controller();
	ObservableList<Object> data = FXCollections.observableArrayList(controller.doActionWithReturnValue("GetCategory", new ArrayList<>()));
	public CategoryOverviewPane() {
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
		this.add(new Label("Categories:"), 0, 0, 1, 1);
		
		table = new TableView<>();
		table.setPrefWidth(REMAINING);
        TableColumn nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory("title"));
        table.getColumns().add(nameCol);
        TableColumn descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));
        table.getColumns().add(descriptionCol);
		this.add(table, 0, 1, 2, 6);
		table.setItems(data);
		btnNew = new Button("New");
		this.add(btnNew, 0, 11, 1, 1);
		btnNew.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CategoryDetailPane categoryDetailPane = new CategoryDetailPane();
				final Stage categoryDetail = new Stage();
				categoryDetail.initModality(Modality.APPLICATION_MODAL);
				Group root = new Group();
				Scene scene = new Scene(root);
				categoryDetail.setOnCloseRequest(new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent event) {
						data = FXCollections.observableArrayList(controller.doActionWithReturnValue("GetCategory", new ArrayList<>()));
						table.setItems(data);
						table.refresh();
					}
				});
				root.getChildren().add(categoryDetailPane);
				categoryDetail.setScene(scene);
				categoryDetail.show();
			}
		});
	}

    public void setNewAction(EventHandler<ActionEvent> newAction) {
		btnNew.setOnAction(newAction);
	}
	
	public void setEditAction(EventHandler<MouseEvent> editAction) {
		table.setOnMouseClicked(editAction);
	}

}
