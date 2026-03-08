import java.util.ArrayList;
import javax.swing.*;

public class ordersystem {
    static Item[] foods = {
            new Item("Sushi", 120.00),
            new Item("Teriyaki", 150.00),
            new Item("Wagyu Beef", 350.00),
            new Item("Salmon Caviar", 700.00),
            new Item("King Crab", 5000.00),
            new Item("Beef Wellington", 2000.00)
    };
    static Item[] drinks = {
            new Item("Diet Soda", 120.00),
            new Item("Monster (White)", 100.00),
            new Item("Soda Pop", 200.00),
            new Item("Virgin Mojito", 400.00),
            new Item("Mulled Wine", 1000.00),
            new Item("Cucumber Mint Cooler", 1300.00)
    };

    static double payment;
    static double totalBill;
    static double change;
    static ArrayList<Item> orderedItem = new ArrayList<>();

    public static void main(String[] args){
        do {
            ImageIcon menuIcon = new ImageIcon("C:\\Users\\admin\\IdeaProjects\\lesson\\src\\e2679876e23516651886a416e64f9840.png");
            String input = (String) JOptionPane.showInputDialog(null, "Welcome to Noir et Blanc\n1. Order \n2. Check Cart\n3. Payment", "Noir et Blanc", JOptionPane.INFORMATION_MESSAGE, menuIcon, null, null);
            switch (input) {
                case "1":
                    menu();
                    break;
                case "2":
                    cart();
                    break;
                case "3":
                    payment();
                    break;
                case null:
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid Input. Please try again.");
            }
        }while(true);
    }


    static void menu(){
        if(orderedItem.size() >= 5){
            JOptionPane.showMessageDialog(null, "You've reached the maximum number of orders placed!");
            return;
        }
        do {
            String input = JOptionPane.showInputDialog(null, "Welcome to Noir et Blanc\n1. Foods \n2. Drinks", "Menu", JOptionPane.INFORMATION_MESSAGE);
            switch (input) {
                case "1":
                    Item chosenFood = (Item) JOptionPane.showInputDialog(null, "Please select your food.", "Order", JOptionPane.PLAIN_MESSAGE, null, foods, foods[0]);
                    if (chosenFood == null) break;
                    orderedItem.add(chosenFood);
                    JOptionPane.showMessageDialog(null, "Successfully added to cart!");
                    break;
                case "2":
                    Item chosenDrink = (Item) JOptionPane.showInputDialog(null, "Please select your food.", "Order", JOptionPane.PLAIN_MESSAGE, null, drinks, drinks[0]);
                    if (chosenDrink == null) break;
                    orderedItem.add(chosenDrink);
                    JOptionPane.showMessageDialog(null, "Successfully added to cart!");
                    break;
                case null:
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Please select an option");
            }
        }while(orderedItem.size() < 5);
        JOptionPane.showMessageDialog(null, "You've reached the maximum number of orders placed!");
    }

    static void cart(){
        StringBuilder sb = new StringBuilder();
        sb.append("Ordered Items:");
        for (Item food : orderedItem){
            String name = food.getName();
            double price = food.getPrice();
            sb.append("\n").append(name).append(" --- $ ").append(price);
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    static void payment(){
        if(orderedItem.isEmpty()){
            JOptionPane.showMessageDialog(null, "Your cart is empty!");
            return;
        }
        for (Item item : orderedItem){
            totalBill += item.getPrice();
        }
        do {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("Ordered Items:\n-------------");
                for (Item food : orderedItem){
                    String name = food.getName();
                    double price = food.getPrice();
                    sb.append("\n").append(name).append(" --- $ ").append(price);
                }
                payment = Double.parseDouble(JOptionPane.showInputDialog(null, sb + "\n-------------\nPlease pay a total of $ " + totalBill));
                if(payment < totalBill){
                    JOptionPane.showMessageDialog(null, "Insufficient payment!");
                    continue;
                }
                change = Math.abs(payment - totalBill);
                JOptionPane.showMessageDialog(null, "Successfully ordered!\nYour change is $ " + change);
                orderedItem.clear();
                return;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid Input. Please try again.");
            }
        }while(true);
    }
}

class Item {
    String name;
    double price;

    Item(String name, double price){
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString(){
        return name + " - $" + price;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
	}

}