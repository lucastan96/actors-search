package actors.search;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ActorsSearch {

    public static void main(String[] args) {
	ActorsSearch theApp = new ActorsSearch();
	theApp.start();
    }

    private void start() {
	Functions f = new Functions();
	try {
	    f.loadPersons();
	} catch (IOException e) {
	    System.out.println("Error: " + e);
	}

	Scanner s = new Scanner(System.in);
	int option = 0;

	while (option != 7) {
	    System.out.println("**** Menu *******************************");
	    System.out.println("1. Search for Persons");
	    System.out.println("2. List All Persons");
	    System.out.println("3. List Persons Sorted by Person ID");
	    System.out.println("4. List Persons by Rating");
	    System.out.println("5. Edit Person");
	    System.out.println("6. Export Search Results to HTML File");
	    System.out.println("7. Save & Exit");

	    option = getOption();

	    if (option == 1) {
		System.out.print("\nPlease enter a name to search: ");
		String name = s.nextLine();
		f.searchPerson();
	    } else if (option == 2) {
		System.out.println("\nAll saved persons are displayed below:");
		f.getPersonsSortedByName();
	    } else if (option == 3) {
		System.out.println("\nAll saved persons are sorted by Person ID and displayed below:");
		f.getPersonsSortedById();
	    } else if (option == 4) {
		System.out.println("\nAll saved persons are sorted by rating and displayed below:");
		f.getPersonsSortedByRating();
	    } else if (option == 5) {
		System.out.print("\nPlease enter a name to edit: ");
		String name = s.nextLine();
		f.editPerson();
	    } else if (option == 6) {
		System.out.println("\nAll search results have been exported to a HTML file.");
	    } else if (option == 7) {
		System.out.println("\nApp Exited Successfully.");
		break;
	    }
	}
    }

    public static int getOption() {
	Scanner s = new Scanner(System.in);
	System.out.print("\nPlease enter your option: ");
	try {
	    int option = s.nextInt();
	    s.nextLine();
	    return option;
	} catch (InputMismatchException e) {
	    System.out.println("Error: Option input must be an integer.");
	    int option = getOption();
	    return option;
	}
    }
}
