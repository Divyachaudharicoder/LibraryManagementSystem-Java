import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookManager bookManager = new BookManager();
        MemeberManager memberManager = new MemeberManager();
        TransactionsManager transactionManager = new TransactionsManager();

        while (true) {
            System.out.println("1. Add Book");
            System.out.println("2. List Books");
            System.out.println("3. Add Member");
            System.out.println("4. List Members");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. List Transactions");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter published year: ");
                    int year = scanner.nextInt();
                    System.out.print("Enter available copies: ");
                    int copies = scanner.nextInt();
                    bookManager.addBook(title, author, year, copies);
                    break;
                case 2:
                    bookManager.listBooks();
                    break;
                case 3:
                    System.out.print("Enter member name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter member email: ");
                    String email = scanner.nextLine();
                    memberManager.addMemeber(name, email);
                    break;
                case 4:
                    memberManager.listMemebers();
                    break;
                case 5:
                    System.out.print("Enter book ID to borrow: ");
                    int bookId = scanner.nextInt();
                    System.out.print("Enter member ID: ");
                    int memberId = scanner.nextInt();
                    transactionManager.borrowBook(bookId, memberId);
                    break;
                case 6:
                    System.out.print("Enter transaction ID to return: ");
                    int transactionId = scanner.nextInt();
                    transactionManager.returnBook(transactionId);
                    break;
                case 7:
                    transactionManager.listTransactions();
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
