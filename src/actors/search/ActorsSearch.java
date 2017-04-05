package actors.search;

import java.util.Scanner;

public class ActorsSearch {

    public static void main(String[] args) {
	start();
    }

    private static void start() {
	System.out.println("**** Menu *******************************");
	System.out.println("1. Search for Persons");
	System.out.println("2. List All Persons");
	System.out.println("3. List Persons Sorted by Person ID");
	System.out.println("4. List Persons by Rating");
	System.out.println("5. Edit Person");
	System.out.println("6. Export Search Results to HTML File");
	System.out.println("7. Save & Exit");
	int option = getOption();
    }
    
    public static int getOption() {
	Scanner s = new Scanner(System.in);
	System.out.print("Please enter your option: ");
	int option = s.nextInt();
	return option;
    }
}
