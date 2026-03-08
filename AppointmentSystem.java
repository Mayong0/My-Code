import java.util.ArrayList;
import java.util.Scanner;

class Appointment {
    String id;
    String date;
    String time;
    String purpose;
    String status;

    public Appointment(String id, String date, String time, String purpose, String status) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.purpose = purpose;
        this.status = status;
    }

    public void display() {
        System.out.println("Appointment ID: " + id);
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println("Purpose: " + purpose);
        System.out.println("Status: " + status);
        System.out.println("---------------------------");
    }
}

public class AppointmentSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Appointment> appointments = new ArrayList<>();
        int choice;

        do {
            System.out.println("Appointment System Menu:");
            System.out.println("1. Add Appointment");
            System.out.println("2. Show all appointments");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Appointment ID: ");
                    String id = scanner.nextLine();

                    System.out.print("Enter Date (MM-DD-YY): ");
                    String date = scanner.nextLine();

                    System.out.print("Enter Time: ");
                    String time = scanner.nextLine();

                    System.out.print("Enter Purpose: ");
                    String purpose = scanner.nextLine();

                    System.out.print("Enter Status (Confirmed/Cancelled): ");
                    String status = scanner.nextLine();

                    appointments.add(new Appointment(id, date, time, purpose, status));
                    System.out.println("Appointment added successfully!");
                    break;

                case 2:
                    if (appointments.isEmpty()) {
                        System.out.println("No appointments found.");
                    } else {
                        System.out.println("\nAll Appointments:");
                        for (Appointment appt : appointments) {
                            appt.display();
                        }
                    }
                    break;

                case 3:
                    System.out.println("Exiting Appointment System. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 3);
    }
}