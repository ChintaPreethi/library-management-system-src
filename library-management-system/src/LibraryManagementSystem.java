import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem {

    static ArrayList<Book> books = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        addSampleBooks();

        int choice;

        do {

            System.out.println("\n================================");
            System.out.println("       LIBRARY MANAGEMENT");
            System.out.println("================================");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Remove Book");
            System.out.println("7. Exit");
            System.out.println("================================");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addBook();
                    break;

                case 2:
                    viewBooks();
                    break;

                case 3:
                    searchBook();
                    break;

                case 4:
                    borrowBook();
                    break;

                case 5:
                    returnBook();
                    break;

                case 6:
                    removeBook();
                    break;

                case 7:
                    System.out.println(
                            "Thank you for using the library system!"
                    );
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 7);

        sc.close();
    }

    // Add sample books
    static void addSampleBooks() {

        books.add(
                new Book(
                        1,
                        "Java Programming",
                        "James Gosling"
                )
        );

        books.add(
                new Book(
                        2,
                        "Python Basics",
                        "Guido van Rossum"
                )
        );

        books.add(
                new Book(
                        3,
                        "Clean Code",
                        "Robert Martin"
                )
        );
    }

    // Add a new book
    static void addBook() {

        System.out.print("Enter book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        // Check duplicate ID
        for (Book book : books) {

            if (book.getId() == id) {
                System.out.println(
                        "Book ID already exists!"
                );
                return;
            }
        }

        System.out.print("Enter book title: ");
        String title = sc.nextLine();

        System.out.print("Enter author name: ");
        String author = sc.nextLine();

        books.add(
                new Book(id, title, author)
        );

        System.out.println(
                "Book added successfully!"
        );
    }

    // View all books
    static void viewBooks() {

        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\n========== BOOKS ==========");

        for (Book book : books) {
            book.displayBook();
        }
    }

    // Search book
    static void searchBook() {

        System.out.print("Enter book title to search: ");
        String search = sc.nextLine();

        boolean found = false;

        for (Book book : books) {

            if (book.getTitle()
                    .toLowerCase()
                    .contains(search.toLowerCase())) {

                book.displayBook();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Book not found.");
        }
    }

    // Borrow book
    static void borrowBook() {

        System.out.print("Enter book ID: ");
        int id = sc.nextInt();

        for (Book book : books) {

            if (book.getId() == id) {

                if (book.isAvailable()) {

                    book.borrowBook();

                    System.out.println(
                            "Book borrowed successfully!"
                    );

                } else {

                    System.out.println(
                            "Book is already borrowed."
                    );
                }

                return;
            }
        }

        System.out.println("Book not found.");
    }

    // Return book
    static void returnBook() {

        System.out.print("Enter book ID: ");
        int id = sc.nextInt();

        for (Book book : books) {

            if (book.getId() == id) {

                if (!book.isAvailable()) {

                    book.returnBook();

                    System.out.println(
                            "Book returned successfully!"
                    );

                } else {

                    System.out.println(
                            "This book was not borrowed."
                    );
                }

                return;
            }
        }

        System.out.println("Book not found.");
    }

    // Remove book
    static void removeBook() {

        System.out.print("Enter book ID: ");
        int id = sc.nextInt();

        for (Book book : books) {

            if (book.getId() == id) {

                if (!book.isAvailable()) {

                    System.out.println(
                            "Cannot remove a borrowed book."
                    );

                    return;
                }

                books.remove(book);

                System.out.println(
                        "Book removed successfully!"
                );

                return;
            }
        }

        System.out.println("Book not found.");
    }
}