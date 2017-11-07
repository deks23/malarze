package malarze;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import malarze.view.MainWindowController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Reader;




import java.io.InputStreamReader;
import model.Painter;

public class Main extends Application {
	private ObservableList<Painter> painters = FXCollections.observableArrayList();
	private ObservableMap<String, Painter> paintersMap = FXCollections.observableHashMap();
	private Stage primaryStage;
	private BorderPane rootLayout;
	private String path = new String("/Users/deks/Documents/malarze");

	public Main() {
		File[] txtFilesList = listOfFiles();
		createListOfPainters(txtFilesList);
		createPaintersMap();

	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Malarze");
		this.primaryStage.setMinWidth(400);
		this.primaryStage.setMinHeight(600);
		initRootLayout();
	}

	public ObservableList<Painter> getPainters() {
		return painters;
	}

	public ObservableMap<String, Painter> getPaintersMap() {
		return this.paintersMap;
	}

	public void createPaintersMap() {
		for (Painter a : this.painters) {
			paintersMap.put(a.getName(), a);
		}
	}

	public ObservableList<String> getPaintersNameList() {
		ObservableList<String> list = FXCollections.observableArrayList();
		for (Painter a : this.painters) {
			list.add(a.getName());
		}
		return list;
	}

	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MainWindow.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);

			primaryStage.setScene(scene);
			primaryStage.show();
			MainWindowController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createListOfPainters(File[] fileList) {
		for (File i : fileList) {
			painters.add(new Painter(splitData(i)));
		}
	}

	public File[] listOfFiles() {

		File folder = new File(path);

		FilenameFilter filterOnlyTxt = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return (name.endsWith(".txt") && !(name.startsWith("&nbsp")));
			}
		};
		File[] fileList = folder.listFiles(filterOnlyTxt);
		return fileList;
	}

	public String splitData(File path) {
		StringBuilder str = new StringBuilder(5000);
		Reader reader = null;
		int i;

		try {
			reader = new InputStreamReader(new FileInputStream(path), "ISO-8859-2");
			while ((i = reader.read()) != -1) {
				if ((char) i == '"')
					continue;
				str.append((char) i);
			}

			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return str.toString();
	}

	public static void main(String[] args) {
		launch(args);

	}
}
