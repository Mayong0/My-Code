import javax.swing.*;
public class hotelbilling {
	
	static String name;
	    static int age;
	    static int numDays;
	    static String chosenRoom;
	    static String[] rooms = {"Deluxe", "Business", "Large Room", "Small Room"};
	    static double[] roomPrice = {20000, 10000, 5000, 2500};
	    static String phoneNum;
	    static String email;

	    static double totalBill;
	    static double charge;
	    static double payment;
	    static double change;

	    public static void main(String[] args){

	        JComboBox<String> rooms = new JComboBox<>();
	        rooms.addItem(String.valueOf(rooms));

	        JOptionPane.showMessageDialog(null, "Welcome to Sanguine Hotel");

	        name = JOptionPane.showInputDialog("Enter full name: ");
	        askAge();
	        phoneNum = JOptionPane.showInputDialog("Enter contact number: ");
	        email = JOptionPane.showInputDialog("Enter you email: ");

	        chosenRoom = JOptionPane.showInputDialog(null, "Please select a room", "Rooms", JOptionPane.INFORMATION_MESSAGE, null, hotelbilling.rooms, hotelbilling.rooms[0]).toString();
	        askDays();
	        switch (chosenRoom){
	            case "Deluxe":
	                charge = roomPrice[0];
	                break;
	            case "Business":
	                charge = roomPrice[1];
	                break;
	            case "Large Room":
	                charge = roomPrice[2];
	                break;
	            case "Small Room":
	                charge = roomPrice[3];
	        }

	        totalBill = charge * numDays;

	        do {
	            try {
	                payment = Double.parseDouble(JOptionPane.showInputDialog(null, "Please pay your total bill ₱" + totalBill));
	            } catch (NumberFormatException e) {
	                JOptionPane.showMessageDialog(null, "Invalid Input");
	                continue;
	            }
	            if(payment < totalBill){
	                JOptionPane.showMessageDialog(null, "Insufficient payment");
	                continue;
	            }
	            break;
	        }while(true);

	        change = payment - totalBill;

	        JOptionPane.showMessageDialog(null, String.format("Name: %s\nPhone No.: %s\nRoom Type: %s\nNo. of Nights: %d\n------------------\nTotal bill: P %.2f\nPayment: P %.2f\nChange: P %.2f\n------------------\nThank you for choosing Sanguine Hotel", name, phoneNum, chosenRoom, numDays, totalBill, payment, change));
	    }

	    static void askAge(){
	        do {
	            try {
	                age = Integer.parseInt(JOptionPane.showInputDialog("Enter age: "));
	                break;
	            } catch (Exception e) {
	                JOptionPane.showMessageDialog(null, "Invalid Input.");
	            }
	        }while(true);
	    }
	    static void askDays(){
	        do {
	            try {
	                numDays = Integer.parseInt(JOptionPane.showInputDialog("Enter number of days: "));
	                break;
	            } catch (Exception e) {
	                JOptionPane.showMessageDialog(null, "Invalid Input.");
	            }
	        }while(true);
	    }
	
	

	}