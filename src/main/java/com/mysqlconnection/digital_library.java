package com.mysqlconnection;
import java.sql.*;
import java.util.Scanner;
class library{
    public void searchBook(Connection connection,int bookid) throws Exception{
            String query1 = "select * from books where book_id = "+bookid;
            Statement st1 = connection.createStatement();
            ResultSet res1 = st1.executeQuery(query1);
            while (res1.next()){
                System.out.println(res1.getInt("book_id")+" | "+res1.getString("book_name")+" | "+res1.getString("author_name"));
            }
    }
    public void insertBook(Connection connection,int bookid,String bookname,String authorname) throws Exception{
            String query2 = "insert into books values(?,?,?)";
            PreparedStatement ps1 = connection.prepareStatement(query2);
            ps1.setInt(1,bookid);
            ps1.setString(2,bookname);
            ps1.setString(3,authorname);
            int res2 = ps1.executeUpdate();
            if(res2>0){
                System.out.println("insertion successful");
            }
            else{
                System.out.println("insertion failed");
            }
    }
    public void deleteBook(Connection connection,int bookid)throws Exception{
            String query3 = "delete from books where book_id ="+bookid;
            Statement st2 = connection.createStatement();
            int res3 = st2.executeUpdate(query3);
            if(res3>0){
                System.out.println("deletion successfull");
            }
            else {
                System.out.println("no book found with id "+bookid);
            }
    }
    public void displayBook(Connection connection)throws Exception{
        String query = "select * from books";
        Statement st = connection.createStatement();
        ResultSet res = st.executeQuery(query);
        while(res.next()){
            System.out.println(res.getInt("book_id")+" | "+res.getString("book_name")+" | "+res.getString("author_name"));
        }
    }
}
public class digital_library {
    public static void main(String[] args) throws Exception {
        while (true) {
            Scanner sc = new Scanner(System.in);
            String url = "jdbc:mysql://localhost:3306/digital_library";
            String user = "root";
            String password = "root";
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("select the operation to perform");
            System.out.println("1 to display all books");
            System.out.println("2 to insert a new book");
            System.out.println("3 to delete a book");
            System.out.println("4 to search for a book by its id");
            System.out.println("0 to exit");
            int operation = sc.nextInt();
            library l = new library();
            switch (operation) {
                case 1:
                    l.displayBook(connection);
                    break;
                case 2:
                    System.out.println("enter the book id");
                    System.out.println("enter the book name");
                    System.out.println("enter the book author name");
                    int newbookid = sc.nextInt();
                    String newbookname = sc.next();
                    String newauthorname = sc.next();
                    l.insertBook(connection, newbookid, newbookname, newauthorname);
                    break;
                case 3:
                    System.out.println("enter the book id to be deleted");
                    int oldbookid = sc.nextInt();
                    l.deleteBook(connection, oldbookid);
                    break;
                case 4:
                    System.out.println("enter the book id to search");
                    int bookid = sc.nextInt();
                    l.searchBook(connection, bookid);
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }
}