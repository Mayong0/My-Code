import java.util.Scanner;

public class Moviebooking {

	public static void main(String[] args) {
		
		final int Ticketprice = 250;
		final int Servicecharge = 20;
		
		Scanner sc = new Scanner(System.in);
		
		for (int i = 1; i <=2; i++) {
		System.out.println("\nBooking for Viewer " + i);
		

		System.out.print("\nEnter your name: ");
		String name = sc.nextLine();
		
		System.out.print("Enter movie title: ");
		String movieTitle = sc.nextLine();
		
		System.out.print("Enter number of tickets: ");
		int numberOfTickets = sc.nextInt();
		sc.nextLine();
		
		int ticketTotal = numberOfTickets * Ticketprice;
		int totalPayment = ticketTotal + Servicecharge;
		
		
		System.out.println("\n--- Movie Booking ---");
		System.out.println("Viewer Name       : " + name);
		System.out.println("Movie Title       : " + movieTitle);
		System.out.println("Tickets Ordered   : " + numberOfTickets);
		System.out.println("Tickets Total     : ₱" + ticketTotal);
		System.out.println("Service Charge    : ₱" + Servicecharge);
		System.out.println("Total Payment     : ₱" + totalPayment);
		System.out.println("-----------------------------");
		}
	}
}