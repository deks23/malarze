package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Scanner;

public class Painter {
	private final StringProperty name;
	private final StringProperty info;
	private final ObservableList<StringProperty> paintingPath = FXCollections.observableArrayList();
	private final ObservableList<StringProperty> paintingInfo = FXCollections.observableArrayList();
	private final ObservableList<StringProperty> paintingTitle = FXCollections.observableArrayList();

	public Painter() {
		this.name = new SimpleStringProperty("");
		this.info = new SimpleStringProperty("");
	}

	public Painter(String data) {
		Scanner scan = new Scanner(data);
		this.name = new SimpleStringProperty(scan.nextLine());
		this.info = new SimpleStringProperty(scan.nextLine());

		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			String lines[] = line.split("\t");
			paintingPath.add(new SimpleStringProperty(lines[0]));
			paintingTitle.add(new SimpleStringProperty(lines[1]));
			paintingInfo.add(new SimpleStringProperty(lines[2]));
		}
		scan.close();
	}

	public String getName() {
		return name.get();
	}

	public String getInfo() {
		return info.get();
	}

	public ObservableList<StringProperty> getPaintingPath() {
		return this.paintingPath;
	}

	public ObservableList<StringProperty> getPaintingInfo() {
		return this.paintingInfo;
	}

	public ObservableList<StringProperty> getPaintingTitle() {
		return this.paintingTitle;
	}
}
