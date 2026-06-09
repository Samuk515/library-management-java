import java.util.*;

public class Library {
    private List<Book> books;

    public Library() {
        this.books = FileHandler.loadBooks();
    }

    public void addBook(String title, String author) {
        int id = books.size() + 1;
        books.add(new Book(id, title, author));
        FileHandler.saveBooks(books);
        System.out.println("Book added successfully.");
    }

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in library.");
            return;
        }
        System.out.println("\n--- All Books ---");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void searchBook(String keyword) {
        System.out.println("\n--- Search Results ---");
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) System.out.println("No books found for: " + keyword);
    }

    public void borrowBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                if (book.isBorrowed()) {
                    System.out.println("Book is already borrowed.");
                } else {
                    book.setBorrowed(true);
                    FileHandler.saveBooks(books);
                    System.out.println("You borrowed: " + book.getTitle());
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void returnBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                if (!book.isBorrowed()) {
                    System.out.println("Book was not borrowed.");
                } else {
                    book.setBorrowed(false);
                    FileHandler.saveBooks(books);
                    System.out.println("You returned: " + book.getTitle());
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }
}