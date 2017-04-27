package generator;

import java.time.LocalDate;
import java.util.GregorianCalendar;

import org.json.JSONException;

import generator.model.Generator;
import generator.model.TouristicItemType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class Controller {
		
	@FXML
	private ComboBox<TouristicItemType> chooseItemTypeComboBox;
	@FXML
	private TextField nameTxtField;
	@FXML
	private DatePicker eventDatePicker;
	@FXML
	private ChoiceBox<String> eventHoursChoiceBox;
	@FXML
	private ChoiceBox<String> eventMinutesChoiceBox;
	@FXML
	private HBox eventDateHBox;
	@FXML
	private TextArea descriptionTxtArea;
	@FXML
	private TextField wwwTxtField;
	@FXML
	private TextField gpsLatTxtField;
	@FXML
	private TextField gpsLonTxtField;
	@FXML
	private TextField likesTxtField;
	@FXML
	private Label itemCountNumberLabel;
	@FXML
	private ComboBox<Float> ratingComboBox;
	@FXML
	private Button genJsonBtn;
	
	// Generator object (the model)
	private Generator model;
	
	// controller
	public Controller(){
		model = new Generator();
	}
		
	// initialize variables when view is loaded
	@FXML
	private void initialize(){
		chooseItemTypeComboBox.setItems(FXCollections.observableArrayList(TouristicItemType.values()));
		chooseItemTypeComboBox.setValue(chooseItemTypeComboBox.getItems().get(0));
		chooseItemTypeComboBox.valueProperty().addListener(new TypeChangeListener());
		
		eventHoursChoiceBox.setItems(FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15",
				"16","17","18","19","20","21","22","23"));
		eventHoursChoiceBox.setValue("17");
		
		eventMinutesChoiceBox.setItems(FXCollections.observableArrayList("00","05","10","15","20","25","30","35","40","45","50","55"));
		eventMinutesChoiceBox.setValue("45");
		
		ratingComboBox.setItems(FXCollections.observableArrayList(1.0f,1.5f,2.0f,2.5f,3.0f,3.5f,4.0f,4.5f,5.0f));
		ratingComboBox.setValue(ratingComboBox.getItems().get(0));
		
		eventDatePicker.setValue(LocalDate.now());
		eventDatePicker.valueProperty().addListener(new DateChangeListener());
		
		gpsLatTxtField.textProperty().addListener(new OnlyDigitListener(gpsLatTxtField, true));
		gpsLonTxtField.textProperty().addListener(new OnlyDigitListener(gpsLonTxtField, true));
		
		likesTxtField.textProperty().addListener(new OnlyDigitListener(likesTxtField,false));
	}
	
	
	// add item do JSON (when "Add" button will be pressed)
	@FXML
	private void addItemToJson(){
		
		model.setType(chooseItemTypeComboBox.getValue());
		model.setName(nameTxtField.getText());
		LocalDate date = eventDatePicker.getValue();
		if (!eventDateHBox.isDisable() && date != null) {
			model.setDate(new GregorianCalendar(date.getYear(),date.getMonthValue()-1,date.getDayOfMonth(),Integer.parseInt(eventHoursChoiceBox.getValue()),Integer.parseInt(eventMinutesChoiceBox.getValue())));
		}else{
			model.setDate(null);
		}
		model.setDescription(descriptionTxtArea.getText());
		model.setWww(wwwTxtField.getText());
		model.setGpsLat(Float.parseFloat(getStringValueCheckIfNotNULL(gpsLatTxtField)));
		model.setGpsLon(Float.parseFloat(getStringValueCheckIfNotNULL(gpsLonTxtField)));
		model.setRating(ratingComboBox.getValue());
		model.setLikes(Long.parseLong(getStringValueCheckIfNotNULL(likesTxtField)));
		
		model.addToJson();
		
		clearAllFields();
		
		itemCountNumberLabel.setText(""+model.getCount());
		genJsonBtn.setDisable(false);	
	}
	
	// generates JSON file when "Generate JSON" button was pressed
	@FXML
	private void generateJson(){
		model.generateJson();
	}
	
	// clear all fields on the view
	private void clearAllFields(){
		
		nameTxtField.clear();
		eventDatePicker.setValue(LocalDate.now());
		descriptionTxtArea.clear();
		wwwTxtField.setText("http://");
		gpsLatTxtField.clear();
		gpsLonTxtField.clear();
		ratingComboBox.setValue(ratingComboBox.getItems().get(0));
		likesTxtField.clear();
		itemCountNumberLabel.setText(""+model.getCount());
	}
		
		
	// method checks TextField value, if it is empty it puts "0" (for parsing to Integer or Float)
	private String getStringValueCheckIfNotNULL(TextField text){
		
		String string = text.getText();
		if (string.length() > 0 ) return string;
		else return "0";
	}

	
	// type change listener
	class TypeChangeListener implements ChangeListener<TouristicItemType>{
		@Override
		public void changed(ObservableValue<? extends TouristicItemType> observable, TouristicItemType oldValue, TouristicItemType newValue) {
			// disable/enable date and time HBox on the view
			if (!newValue.equals(TouristicItemType.EVENT)) eventDateHBox.setDisable(true);
			else eventDateHBox.setDisable(false);
			// set count number depending of type
			itemCountNumberLabel.setText(""+model.getCount());
			// change model type parameter
			model.setType(chooseItemTypeComboBox.getValue());
			// clear all fields of the view
			clearAllFields();
		}
	}
	
	// date change listener
	class DateChangeListener implements ChangeListener<LocalDate>{
		@Override
		public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
			
			// if no date specified, disable time options on the view
			if (newValue == null) {
				eventHoursChoiceBox.setDisable(true);
				eventMinutesChoiceBox.setDisable(true);
			}
			else{
				eventHoursChoiceBox.setDisable(false);
				eventMinutesChoiceBox.setDisable(false);
			}			
		}
	}
	
	// input of TextField only digits (with or without .)
	class OnlyDigitListener implements ChangeListener<String>{
		
		TextField tf;
		boolean acceptComma;
		
		public OnlyDigitListener(TextField tf, boolean acceptComma){ 
			this.tf = tf;
			this.acceptComma = acceptComma;
		}
		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			// only digits without "."
			if (!acceptComma){
				if (!newValue.matches("[1-9][0-9]*") && !newValue.equals("")) tf.setText(oldValue);				
			}
			// only digits and "."
			else{
				if (!newValue.matches("\\d+(\\.)?(\\d+)?") && !newValue.equals("")) tf.setText(oldValue);
			}			
		}
	}	
} 
