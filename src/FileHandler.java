import java.io.*;
import java.util.*;
 public class FileHandler {
     private static final String FILE_PATH ="books.txt";

     public static void saveBooks(List<Book>  books){
         try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))){
             for (Book book : books){
                 bw.write(book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.isBorrowed());
                 bw.newLine();
             }
          } catch (IOException e){
             System.out.println("Error saving books: " + e.getMessage());
         }
     }

     public static List<Book> loadBooks(){
         List<Book> books = new ArrayList<>();
         try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))){
             String line;
             while ((line = br.readLine()) != null){
                 String[] parts = line.split(",");
                 if (parts.length == 4){
                     Book book = new Book(
                         Integer.parseInt(parts[0]),
                         parts[1],
                                 parts[2]
                     );
                     book.setBorrowed(Boolean.parseBoolean(parts[3]));
                     books.add(book);
                 }
             }
         } catch (IOException e){
             System.out.println("No saved data found!! Starting fresh.");
         }
         return books;
     }

 }