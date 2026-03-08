import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class deptprac {
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();
	static String[] householdNames;
	static double[] consumption;
	static double[] billAmount;
	static int numHouseHold;
	
	public static void main(String[] args) {
		print("How many households? ");
		numHouseHold = sc.nextInt(); sc.nextLine();
		
		householdNames = new String[numHouseHold];
		consumption = new double[numHouseHold];
		billAmount = new double[numHouseHold];
		
		for (int i = 0; i < numHouseHold; i++) {
			print("Enter household name " + (i + 1) + ": ");
			householdNames[i] = sc.nextLine();
			print("Enter monthly consumption (in kWh): ");
			consumption[i] = sc.nextDouble(); sc.nextLine();	
			
			billAmount[i] = computeBill(consumption[i]);
			
			
			buildReport(householdNames[i], consumption[i], billAmount[i]);
	}
		
	try (FileWriter writer = new FileWriter("ElectricityBillingReport.txt"); 
			BufferedWriter buffer = new BufferedWriter(writer)) {
			buffer.write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void buildReport(String householdName, double consumption, double billAmount) {
		String category = getUsageCategory(consumption);
		double discountedBill = applyDiscount(billAmount, category);
		
		sb.append("\n-Household: ").append(householdName);
		sb.append("\nConsumption: ").append(consumption);
		sb.append("\nCategory: ").append(category);
		sb.append("\nOriginal Bill: ").append(billAmount);
		if (discountedBill != billAmount) sb.append("\nDiscounted Bill: ").append(discountedBill);
		sb.append("\n-----------------------------------");
		
	}
	
	public static String getUsageCategory(double consumption) {
		if(consumption < 200)
			return "Low Usage";
		else if(consumption >= 200 && consumption <= 499) 
			return "Average Usage";
		else 
			return "High Usage";
	}
	
	public static double computeBill(double consumption) {
		if(consumption < 200) 
			return consumption * 8;
		else if(consumption >= 200 && consumption <= 499) 
			return consumption *10;
		else 
			return consumption * 12;
	}
	
	public static double applyDiscount(double bill, String category) {
		if(category.equalsIgnoreCase("Low Usage")) 
			return bill - (bill * 0.10);
					return bill;
	}
	static void print(Object txt) {
		System.out.print(txt);
	}

}
