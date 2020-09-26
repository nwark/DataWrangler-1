// --== CS400 File Header Information ==--
// Name: Nolan Wark
// Email: nwark@wisc.edu
// Team: AD
// Role: DataWrangler #1
// TA: Sophie Stephenson
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

public class Book {
    
    private String title;
    private String author;
    private String publisher;
    private int publication_year;
    private String isbn;
    
    //No-argument constructor
    public Book() {
        title = "";
        author = "";
        publisher = "";
        publication_year = 0;
        isbn = "";
    }
    //Parameterized constructor
    public Book(String title, String author, String publisher, int publication_year, String isbn) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publication_year = publication_year;
        this.isbn = isbn;
    }
    //Copy constructor
    public Book(Book c) {
        this.title = c.title;
        this.author = c.author;
        this.publisher = c.publisher;
        this.publication_year = c.publication_year;
        this.isbn = c.isbn;
    }
    
    //Getter methods
    /**
     * @return String of the title of the book
     */
    public String getTitle() { 
      return title; 
    }
    /**
     * @return String of the author of the book
     */
    public String getAuthor() { 
      return author; 
    }
    /**
     * @return String of the publisher of the book
     */
    public String getPublisher() { 
      return publisher; 
    }
    /**
     * @return int of the year the book got published
     */
    public int getPublicationYear() { 
      return publication_year; 
    }
    /**
     * @return String of the ISBN of the book
     */
    public String getISBN() { 
      return isbn; //decided on String and not long because some of the numbers were x's
    }
    
    //Setter methods
    /** sets the title of the book
     * @param title
     */
    public void setTitle(String title) { 
      this.title = title; 
    }
    /** sets the author of the book
     * @param title
     */
    public void setAuthor(String author) { 
      this.author = author; 
    }
    /** sets the publisher of the book
     * @param title
     */
    public void setPublisher(String publisher) { 
      this.publisher = publisher; 
    }
    /** sets the publication year of the book
     * @param title
     */
    public void setPublicationYear(int publication_year) { 
      this.publication_year = publication_year; 
    }
    /** sets the ISBN of the book
     * @param title
     */
    public void setISBN(String isbn) {
      this.isbn = isbn; 
    }
    
    public String getCitation() {
      //WIP
      //MLA format- Last Name, First Name. Title of Book. Publisher, Publication
      String toReturn = "";
      String firstName = "";
      String lastName = "";
      String[] lastFirst = new String[10];
      lastFirst = author.split(" ");
      
      if(!author.isEmpty()) {
        //last name
        int lastNameIndex = 0;
        
        for(int i = lastFirst.length - 1; i >= 0; i--) {
          if(!lastFirst[i].equals("")) {
            //two worded last names TODO add more names
            if((i - 1) >= 0 && lastFirst[i - 1].toUpperCase().equals("VON")) {
              lastNameIndex = i - 1;
              lastName = lastFirst[i - 1] + lastFirst[i];
            } else {
              lastNameIndex = i;
              lastName = lastFirst[i];
            }
            
          }
        }
        
        //first name
        for(int i = 0; i < lastNameIndex; i++) {
          firstName = firstName + lastFirst[i];
        }
        
        lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
        firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
        if(firstName.charAt(firstName.length() - 1) == '.') {
          firstName = firstName.substring(0, firstName.length() - 1);
        }
      }
      
      if(firstName.isEmpty()) {
        toReturn = lastName + ". ";
      } else {
        toReturn = lastName + ", " + firstName + ". ";
      }
      
      if(!title.equals("")) {
        toReturn += title + ". ";
      }
      
      if(!publisher.equals("")) {
        toReturn += publisher;
      }
      
      if(publication_year > 0) {
        if(!publisher.equals("")) {
          toReturn += ", ";
        }
        toReturn += publication_year;
      }
      toReturn += ".";
      return toReturn;
    }
}










