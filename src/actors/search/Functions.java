package actors.search;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Functions {

    private HashMap<String, ArrayList<Person>> personStore;

    public void loadPersons() throws IOException, FileNotFoundException, ClassNotFoundException {
	ObjectInputStream in = new ObjectInputStream(new FileInputStream("persons.dat"));
	ArrayList<Person> persons = (ArrayList<Person>) in.readObject();
	in.close();

	for (Object p : persons) {
	    System.out.println(p);
	}
    }

    public void savePersons() throws IOException {

    }

    public HashMap<String, ArrayList<Person>> searchPerson() {
	return personStore;
    }

    public HashMap<String, ArrayList<Person>> getPersonsSortedByName() {
	return personStore;
    }

    public HashMap<String, ArrayList<Person>> getPersonsSortedById() {
	return personStore;
    }

    public HashMap<String, ArrayList<Person>> getPersonsSortedByRating() {
	return personStore;
    }

    public void editPerson() {

    }

    public void exportPersonsToHTML() {

    }
}
