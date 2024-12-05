import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
// Vehicle class
class Vehicle {
 String make;
 String model;
 int year;
 ArrayList<ServiceRecord> serviceRecords;
 Vehicle(String make, String model, int year) {
 this.make = make;
 this.model = model;
 this.year = year;
 this.serviceRecords = new ArrayList<>();
 }
 void addServiceRecord(ServiceRecord record) {
 serviceRecords.add(record);
 }
 ArrayList<ServiceRecord> getServiceRecords() {
 return serviceRecords;
 }
}
// ServiceRecord class
class ServiceRecord {
 Date serviceDate;
 String description;
 double cost;
 ServiceRecord(Date serviceDate, String description, double cost) {
 this.serviceDate = serviceDate;
 this.description = description;this.cost = cost;
 }
 public String toString() {
 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
 return "Date: " + sdf.format(serviceDate) + ", Description: " + description 
+ ", Cost: $" + cost;
 }
}
// MaintenanceManager class
class MaintenanceManager {
 ArrayList<Vehicle> vehicles;
 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
 MaintenanceManager() {
 vehicles = new ArrayList<>();
 }
 void addVehicle(String make, String model, int year) {
 vehicles.add(new Vehicle(make, model, year));
 }
 void logService(String make, String model, int year, String dateString, String 
description, double cost) throws ParseException {
 Date serviceDate = dateFormat.parse(dateString);
 for (Vehicle vehicle : vehicles) {
 if (vehicle.make.equalsIgnoreCase(make) && 
vehicle.model.equalsIgnoreCase(model) && vehicle.year == year) {
 vehicle.addServiceRecord(new ServiceRecord(serviceDate, 
description, cost));
 return;
 }
 }
 System.out.println("Vehicle not found.");
 }
 void viewMaintenanceHistory(String make, String model, int year) {
 for (Vehicle vehicle : vehicles) {
 if (vehicle.make.equalsIgnoreCase(make) && 
vehicle.model.equalsIgnoreCase(model) && vehicle.year == year) {
 System.out.println("Maintenance History for " + make + " " + model 
+ " (" + year + "):");for (ServiceRecord record : vehicle.getServiceRecords()) {
 System.out.println(record);
 }
 return;
 }
 }
 System.out.println("Vehicle not found.");
 }
 void calculateTotalCost(String make, String model, int year) {
 double totalCost = 0;
 for (Vehicle vehicle : vehicles) {
 if (vehicle.make.equalsIgnoreCase(make) && 
vehicle.model.equalsIgnoreCase(model) && vehicle.year == year) {
 for (ServiceRecord record : vehicle.getServiceRecords()) {
 totalCost += record.cost;
 }
 System.out.println("Total Maintenance Cost for " + make + " " + 
model + " (" + year + "): $" + totalCost);
 return;
 }
 }
 System.out.println("Vehicle not found.");
 }
}
// MaintenanceApp class
public class MaintenanceApp {
 static MaintenanceManager manager = new MaintenanceManager();
 public static void main(String[] args) {
 Frame frame = new Frame("Vehicle Maintenance System");
 frame.setSize(400, 400);
 frame.setLayout(new GridLayout(6, 1));
 
 Button addVehicleButton = new Button("Add New Vehicle");
 Button logServiceButton = new Button("Log Service Record");
 Button viewHistoryButton = new Button("View Maintenance History");
 Button calculateCostButton = new Button("Calculate Total Maintenance 
Cost");
 Button exitButton = new Button("Exit");
 addVehicleButton.addActionListener(new ActionListener() {@Override
 public void actionPerformed(ActionEvent e) {
 Dialog dialog = new Dialog(frame, "Add New Vehicle", true);
 dialog.setLayout(new GridLayout(4, 2));
 TextField makeField = new TextField();
 TextField modelField = new TextField();
 TextField yearField = new TextField();
 
 dialog.add(new Label("Make:"));
 dialog.add(makeField);
 dialog.add(new Label("Model:"));
 dialog.add(modelField);
 dialog.add(new Label("Year:"));
 dialog.add(yearField);
 Button saveButton = new Button("Save");
 dialog.add(saveButton);
 saveButton.addActionListener(new ActionListener() {
 @Override
 public void actionPerformed(ActionEvent e) {
 String make = makeField.getText();
 String model = modelField.getText();
 int year = Integer.parseInt(yearField.getText());
 manager.addVehicle(make, model, year);
 dialog.setVisible(false);
 System.out.println("Vehicle added successfully!");
 }
 });
 dialog.setSize(300, 200);
 dialog.setVisible(true);
 }
 });
 logServiceButton.addActionListener(new ActionListener() {
 @Override
 public void actionPerformed(ActionEvent e) {
 Dialog dialog = new Dialog(frame, "Log Service Record", true);
 dialog.setLayout(new GridLayout(7, 2));
 TextField makeField = new TextField();
 TextField modelField = new TextField();
 TextField yearField = new TextField();
 TextField dateField = new TextField();
 TextField descField = new TextField();
 TextField costField = new TextField();dialog.add(new Label("Make:"));
 dialog.add(makeField);
 dialog.add(new Label("Model:"));
 dialog.add(modelField);
 dialog.add(new Label("Year:"));
 dialog.add(yearField);
 dialog.add(new Label("Service Date (dd-MM-yyyy):"));
 dialog.add(dateField);
 dialog.add(new Label("Description:"));
 dialog.add(descField);
 dialog.add(new Label("Cost:"));
 dialog.add(costField);
 Button saveButton = new Button("Save");
 dialog.add(saveButton);
 saveButton.addActionListener(new ActionListener() {
 @Override
 public void actionPerformed(ActionEvent e) {
 String make = makeField.getText();
 String model = modelField.getText();
 int year = Integer.parseInt(yearField.getText());
 String date = dateField.getText();
 String description = descField.getText();
 double cost = Double.parseDouble(costField.getText());
 try {
 manager.logService(make, model, year, date, description, 
cost);
 dialog.setVisible(false);
 System.out.println("Service record logged successfully!");
 } catch (ParseException ex) {
 System.out.println("Invalid date format. Please enter the date 
in dd-MM-yyyy format.");
 }
 }
 });
 dialog.setSize(400, 300);
 dialog.setVisible(true);
 }
 });
 viewHistoryButton.addActionListener(new ActionListener() {
 @Override
 public void actionPerformed(ActionEvent e) {Dialog dialog = new Dialog(frame, "View Maintenance History", 
true);
 dialog.setLayout(new GridLayout(4, 2));
 TextField makeField = new TextField();
 TextField modelField = new TextField();
 TextField yearField = new TextField();
 
 dialog.add(new Label("Make:"));
 dialog.add(makeField);
 dialog.add(new Label("Model:"));
 dialog.add(modelField);
 dialog.add(new Label("Year:"));
 dialog.add(yearField);
 Button viewButton = new Button("View");
 dialog.add(viewButton);
 viewButton.addActionListener(new ActionListener() {
 @Override
 public void actionPerformed(ActionEvent e) {
 String make = makeField.getText();
 String model = modelField.getText();
 int year = Integer.parseInt(yearField.getText());
 manager.viewMaintenanceHistory(make, model, year);
 dialog.setVisible(false);
 }
 });
 dialog.setSize(300, 200);
 dialog.setVisible(true);
 }
 });
 calculateCostButton.addActionListener(new ActionListener() {
 @Override
 public void actionPerformed(ActionEvent e) {
 Dialog dialog = new Dialog(frame, "Calculate Total Maintenance 
Cost", true);
 dialog.setLayout(new GridLayout(4, 2));
 TextField makeField = new TextField();
 TextField modelField = new TextField();
 TextField yearField = new TextField();
 
 dialog.add(new Label("Make:"));
 dialog.add(makeField);
 dialog.add(new Label("Model:"));dialog.add(modelField);
 dialog.add(new Label("Year:"));
 dialog.add(yearField);
 Button calcButton = new Button("Calculate");
 dialog.add(calcButton);
 calcButton.addActionListener(new ActionListener() {
 @Override
 public void actionPerformed(ActionEvent e) {
 String make = makeField.getText();
 String model = modelField.getText();
 int year = Integer.parseInt(yearField.getText());
 manager.calculateTotalCost(make, model, year);
 dialog.setVisible(false);
 }
 });
 dialog.setSize(300, 200);
 dialog.setVisible(true);
 }
 });
 exitButton.addActionListener(new ActionListener() {
 @Override
 public void actionPerformed(ActionEvent e) {
 System.out.println("Exiting...");
 System.exit(0);
 }
 });
 frame.add(addVehicleButton);
 frame.add(logServiceButton);
 frame.add(viewHistoryButton);
 frame.add(calculateCostButton);
 frame.add(exitButton);
 frame.setVisible(true);
 }
}
