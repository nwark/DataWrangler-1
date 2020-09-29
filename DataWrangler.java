// --== CS400 File Header Information ==--
// Name: Nolan Wark
// Email: nwark@wisc.edu
// Team: AD
// Role: DataWrangler #1
// TA: Sophie Stephenson
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataWrangler {
  public static boolean readInputFile(HashTableMap<String, Book> tableName, String fileName) throws FileNotFoundException{
    //Title, Author, Publisher, Publication Year, ISBN
    final int infoTypes = 5;
    
    //If it does not have the right extension change it
    if(fileName.length() < 4 && !fileName.substring(fileName.length() - 4).equals(".csv") && 
        !fileName.substring(fileName.length() - 4).equals(".txt")) {
      fileName += ".csv";
    }
    
    String filePath = System.getProperty("user.dir");
    
    try {
      filePath = System.getProperty("user.dir");
      for(int i = 0; i < filePath.length(); i++) {
        if(filePath.charAt(i) == '/') {
          filePath += filePath.charAt(i) + fileName;
          break;
        }
        if(filePath.charAt(i) == '\\') {
          filePath += filePath.charAt(i) + fileName;
          break;
        }
        if(i == filePath.length() - 1) {
          filePath += '/' + fileName;
          break;
        }
      }
      File readFile = new File(filePath);
      Scanner scan = new Scanner(readFile);
      String[] variables = new String[infoTypes];
      String[] info = new String[infoTypes];
      
      //looks to see it there is any content
      if(!scan.hasNextLine()) {
        System.out.println("The File " + fileName + " is empty.");
        scan.close();
        return false;
      }
      variables = scan.nextLine().split(",");
      
      //looks to see if the correct number of variables are there
      if(variables.length != 5) {
        System.out.println("The number of variables are not correct . The first line of the File should be \"Title,Author,Publisher,Publication Year,ISBN\"");
        scan.close();
        return false;
      }
      
      //looks to see if the correct variables are there and in the correct order
      if(variables[0].subSequence(0, variables[0].length()).equals("Title") || !variables[1].toLowerCase().equals("author") ||
          !variables[2].toLowerCase().equals("publisher") || !variables[3].toLowerCase().equals("publication year") ||
          !variables[4].toLowerCase().equals("isbn")) {
        System.out.println(variables[0] + "," + variables[1] + "," + variables[2] + "," + variables[3] + "," + variables[4]);
        System.out.println("The variables are not correct. The first line of the File should be \"Title,Author,Publisher,Publication Year,ISBN\"");
        scan.close();
        return false;
      }
      
      int cnt = 1;
      //creates the book object one line at a time
      while(scan.hasNextLine()) {
        info = scan.nextLine().split(","); //scans in info
        cnt++;
        
        if(info.length < 5) {
          System.out.println("line: " + cnt + " does not have the required infomation for the book.");
          System.out.println("The required infomation is title, author, publisher, publication year, and ISBN and in that order");
          return false;
        }
        
        if(info.length > 5) {
          String[] temp = new String[infoTypes];
          //if the title has a comma in it
            String titleDif = "";
          if(info[0].contains("\"")) {
            int find = 1;
            while(find < info.length) {
              if(info[find].contains("\"")) {
                break;
              }
              if(find == info.length - 1) {
                break;
              }
              find++;
            }
            if(info[find].contains("\"")) {
              for(int i = 0; i <= find; i++) {
                titleDif += info[i];
                if(i < find) {
                  titleDif += ",";
                }
              }
            }
          }
          //if the publisher has a comma in it
            String pubDif = "";
          if(info[info.length - 3].contains("\"")) {
            pubDif = info[info.length - 4] + "," + info[info.length - 3];
          }
          //put it together
          temp[4] = info[info.length - 1];
          temp[3] = info[info.length - 2];
          if(pubDif.length() > 0) {//if publisher had comma
            temp[2] = pubDif;
            temp[1] = info[info.length - 5];
            if(titleDif.length() > 0) {//if the publisher and title had commas
              temp[0] = titleDif;
            } else {//if publisher had comma but the title did not
              temp[0] = info[0];
            }
          } else {//if publisher did not have a comma
            temp[2] = info[info.length - 3];
            temp[1] = info[info.length - 4];
            temp[0] = titleDif;
          }
          
          info = temp;
        }
        
        //creates book
        Book newBook = new Book(info[0], info[1], info[2], Integer.parseInt(info[3]), info[4]);
        //backEndfunction that will add this book to a hash table
        tableName.put(newBook.getISBN(), newBook);
      }
    
      scan.close();
      return true;
      
    }catch (NumberFormatException e) {
      System.out.println("The Publication Year format was not correct. Make sure there are no letters in the Publication Year column");
      return false;
    }catch(FileNotFoundException e) {
      System.out.println("File not found at " + fileName);
      return false;
    }
  }

}
