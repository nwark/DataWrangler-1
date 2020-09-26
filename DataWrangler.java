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
  public static boolean readInputFile(BackEndHash tableName, String fileName) throws FileNotFoundException{
    //Title, Author, Publisher, Publication Year, ISBN
    final int infoTypes = 5;
    
    //If it does not have the right extension change it
    if(fileName.length() < 4 && !fileName.substring(fileName.length() - 4).equals(".csv") && 
        !fileName.substring(fileName.length() - 4).equals(".txt")) {
      fileName += ".csv";
    }
    
    try {
      fileName = System.getProperty("user.dir") + "\\" + fileName;
      File readFile = new File(fileName);
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
      
      //creates the book object one line at a time
      while(scan.hasNextLine()) {
        info = scan.nextLine().split(","); //scans in info
        
        if(info.length > 5) {
          String[] temp = new String[infoTypes];
          temp[0] = "";
          temp[4] = info[info.length - 1];
          temp[3] = info[info.length - 2];
          temp[2] = info[info.length - 3];
          temp[1] = info[info.length - 4];
          
          for(int i = 0; i < info.length - 4; i++) {
            temp[0] += info[i];
            if(i + 1 < info.length - 4) {
              temp[0] += ",";
            }
          }
          
          info = temp;
        }
        
        //creates book
        Book newBook = new Book(info[0], info[1], info[2], Integer.parseInt(info[3]), info[4]);
        //backEndfunction that will add this book to a hash table
        tableName.add(newBook);
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
