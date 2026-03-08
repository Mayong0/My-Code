import java.util.Scanner;

public class LibrarySystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String allBooks = "";
        int option;

        do {
            System.out.println("--- Library Management System ---");
            System.out.println("1. Add Book\n2. View All Books\n3. Exit\nEnter your choice: ");
            option = sc.nextInt();
            sc.nextLine();

            switch(option) {
                case 1:
                    System.out.println("Enter Book ID: ");
                    String id = sc.nextLine();

                    System.out.println("Enter Title: ");
                    String title = sc.nextLine();

                    System.out.println("Enter Author: ");
                    String author = sc.nextLine();

                    System.out.println("Enter Genre: ");
                    String genre = sc.nextLine();

                    System.out.println("Enter Availability (Available/Checked Out): ");
                    String status = sc.nextLine();

                    allBooks += "\n" +
                            "Book ID: " + id +
                            " | Title: " + title +
                            " | Author: " + author +
                            " | Genre: " + genre +
                            " | Status: " + status + "\n" +
                            "-----------------------------";
                    break;

                case 2:
                    System.out.println("--- All Books ---" + "\n" + allBooks);
                    break;

                default:
                    break;
            }

        } while (option != 3);

        sc.close();
    }
}