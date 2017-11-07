package malarze.view;

import javafx.scene.image.Image;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.control.ComboBox;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import malarze.Main;


public class MainWindowController {
	@FXML
	private Label paintingDescription;
	@FXML
	private ImageView painting;
	@FXML
	private ComboBox<String> paintersList;
	private Main mainApp;
	private int paintintgIndex = 0;
	FileInputStream inputstream = null;
	private String path = new String("/Users/deks/Documents/malarze/");

	public MainWindowController() {

	}

	@FXML
	private void initialize() {
		this.paintingDescription.setWrapText(true);
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;

		this.paintersList.setItems(mainApp.getPaintersNameList());
		this.paintersList.getSelectionModel().select(0);
		setPaintingDescription();
		setPainting();

	}

	private void setPainting() {
		try {
			inputstream = new FileInputStream(path
					+ mainApp.getPaintersMap().get(this.paintersList.getSelectionModel().getSelectedItem().toString())
							.getPaintingPath().get(paintintgIndex).get());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Image image = new Image(inputstream);
		this.painting.setImage(image);
	}

	@FXML
	private void onNextButtonClick() {
		if (this.paintintgIndex == (mainApp.getPaintersMap()
				.get(this.paintersList.getSelectionModel().getSelectedItem().toString()).getPaintingInfo().size() - 1))
			this.paintintgIndex = 0;
		else
			this.paintintgIndex++;
		setPaintingDescription();
		setPainting();
	}

	@FXML
	private void onPreviousButtonClick() {
		if (this.paintintgIndex == 0)
			this.paintintgIndex = (mainApp.getPaintersMap()
					.get(this.paintersList.getSelectionModel().getSelectedItem().toString()).getPaintingInfo().size()
					- 1);
		else
			this.paintintgIndex--;
		setPaintingDescription();
		setPainting();
	}

	private void setPaintingDescription() {
		this.paintingDescription.setText(
				mainApp.getPaintersMap().get(this.paintersList.getSelectionModel().getSelectedItem().toString())
						.getPaintingTitle().get(paintintgIndex).get()
						+ "\n"
						+ mainApp.getPaintersMap()
								.get(this.paintersList.getSelectionModel().getSelectedItem().toString())
								.getPaintingInfo().get(paintintgIndex).get());
	}

	@FXML
	private void onPainterSelection() {
		this.paintintgIndex = 0;
		setPaintingDescription();
		setPainting();
	}

}
