import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Banksystem {
	private static double balance = 50000.00;
	private static double amount;
	public static void main(String[] args) {
		while(true) {
			ImageIcon icon = new ImageIcon("C:\\Users\\Almario\\eclipse-workspace\\Activity\\src\\grades\\download.png");
			String choice = (String) JOptionPane.showInputDialog(null
					, "Choose an option \n1. Check balance \n2. Deposit \n3. Withdraw \n4. Exit"
					, "ATM Options"
					, JOptionPane.PLAIN_MESSAGE, icon, null, null);
			if (choice == null) {
				JOptionPane.showMessageDialog(null, "Thank you for using the ATM!");
				break;
			}
		       switch (choice) {
		       case "1":
		    	   checkBalance();
		    	   break;
		       case "2":
		    	   deposit();
		    	   break;
		       case "3":
		    	   withdraw();
		    	   break;
		       case "4":
		    	   return;
		       }
		
		    }

	     }   
	
	private static void checkBalance() {
		
		JOptionPane.showMessageDialog(null, "Your current balance is: ₱"
				+ "" + balance, "Balance", JOptionPane.INFORMATION_MESSAGE);
	}
    private static void deposit() {
    	try {
    		String amountStr = JOptionPane.showInputDialog("Enter amount to deposit:");
    		if (amountStr == null) return;
    		amount = Double.parseDouble(amountStr);
    		if (amount <= 0) {
    		 
    			JOptionPane.showMessageDialog(null, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
    			return;
    			}
    		balance += amount;
    		
    		JOptionPane.showMessageDialog(null, "Deposit successful. New balance: ₱"
    				+ "" + balance, "Success", JOptionPane.INFORMATION_MESSAGE);
    		}catch (NumberFormatException e) {
    			
    			JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
    		}
        
     }
     private static void withdraw() {
    		try { 
    			String amountStr = JOptionPane.showInputDialog("Enter amount to withdraw:");
    			if (amountStr == null) return;
    			double amount = Double.parseDouble(amountStr);
    			if (amount <= 0) {
    				
    				JOptionPane.showMessageDialog(null, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
    				return;
    			}
    			if (amount > balance) {
    				
    				JOptionPane.showMessageDialog(null, "Insufficient balance.", "Error", JOptionPane.ERROR_MESSAGE);	
    				return;
    			}
    			balance -= amount;
    			JOptionPane.showMessageDialog(null, "Withdrawal successful. New Balance: ₱"
    					+ "" + balance,  "Success", JOptionPane.INFORMATION_MESSAGE);
    	} catch (NumberFormatException e) {
    		
    		JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
    		
    		
    	
    		

    			
    		}
    	}
    }