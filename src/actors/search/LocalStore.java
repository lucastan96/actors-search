package actors.search;

import java.awt.Desktop;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class LocalStore {

    private Map<String, ArrayList<Person>> localStore = new HashMap<>();

    public LocalStore() {
	loadLocalStore();
    }

    public void loadLocalStore() {
	DataInputStream dis;

	try {
	    File f = new File("persons.dat");
	    dis = new DataInputStream(new FileInputStream(f));

	    while (dis.available() > 0) {
		String queryName = readString(dis, 16);
		int size = dis.readInt();

		ArrayList<Person> ps = new ArrayList<>();

		for (int i = 0; i < size; i++) {
		    double score = dis.readDouble();
		    String name = readString(dis, 16);
		    int id = dis.readInt();
		    int flag1 = dis.readInt();
		    ArrayList<String> imageUrls = new ArrayList<>();
		    if (flag1 > 0) {
			for (int j = 0; j < flag1; j++) {
			    imageUrls.add(readString(dis, 100));
			}
		    }
		    String personLink = readString(dis, 100);
		    double myRating = dis.readDouble();
		    int flag2 = dis.readInt();
		    ArrayList<String> myComments = new ArrayList<>();
		    if (flag2 > 0) {
			for (int x = 0; x < flag2; x++) {
			    myComments.add(readString(dis, 100));
			}
		    }

		    Person p = new Person(score, queryName, name, id, imageUrls, personLink, myRating, myComments);
		    ps.add(p);
		}
		this.localStore.put(queryName, ps);
	    }

	    dis.close();
	} catch (IOException e) {
	    System.out.println(e.getMessage());
	    System.out.println("");
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    System.out.println("");
	}
    }

    public void saveLocalStore() {
	// TO DO
    }

    public void searchPerson(String queryName) {
	ArrayList<Person> results = searchLocalStore(queryName);
	System.out.println("");

	if (results.isEmpty()) {
	    System.out.println("No person named " + queryName + " found in the local store. Searching using TVmaze now...");
	    results = searchAPI(queryName);

	    if (!results.isEmpty()) {
		System.out.println("All persons named " + queryName + " found using the TVmaze API are listed down below:\n");
	    }
	} else {
	    System.out.println("All persons named " + queryName + " found in the local store are listed down below:\n");
	}

	for (Person p : results) {
	    System.out.println(p.toString());
	}
    }

    public ArrayList<Person> searchLocalStore(String queryName) {
	ArrayList<Person> results = new ArrayList<>();
	Set<String> queryNames = localStore.keySet();
	if (queryNames.contains(queryName)) {
	    results = localStore.get(queryName);
	}

	return results;
    }

    public ArrayList<Person> searchAPI(String queryName) {
	ArrayList<Person> results = new ArrayList<>();

	try {
	    String link = "http://api.tvmaze.com/search/people?q=";
	    URL url = new URL(link + queryName);
	    InputStream in = url.openStream();

	    JsonReader reader = Json.createReader(in);
	    JsonArray array = reader.readArray();

	    if (array.isEmpty()) {
		System.out.println("No person named " + queryName + " found using TVmaze either. Please try another search.");
	    } else {
		for (int i = 0; i < array.size(); i++) {
		    JsonObject object = array.getJsonObject(i);
		    double score = object.getJsonNumber("score").doubleValue();
		    JsonObject person = object.getJsonObject("person");
		    String name = person.getString("name");
		    int id = person.getJsonNumber("id").intValue();
		    ArrayList<String> imageUrls = new ArrayList<>();
		    if (!person.isNull("image")) {
			JsonObject images = person.getJsonObject("image");
			String medium = images.getString("medium");
			String original = images.getString("original");
			imageUrls.add(medium);
			imageUrls.add(original);
		    }
		    JsonObject links = person.getJsonObject("_links");
		    JsonObject self = links.getJsonObject("self");
		    String personLink = self.getString("href");

		    results.add(new Person(score, queryName, name, id, imageUrls, personLink));
		}

		this.localStore.put(queryName, results);
	    }
	} catch (IOException e) {
	    System.out.println(e.getMessage());
	    System.out.println("");
	}

	return results;
    }

    public void getPersonsSortedByName() {
	if (localStore.isEmpty()) {
	    System.out.println("No person saved yet. Search for one now!");
	} else {
	    Set<Person> personSet = new TreeSet<>();
	    output(personSet);
	}
    }

    public void getPersonsSortedById() {
	if (localStore.isEmpty()) {
	    System.out.println("No person saved yet. Search for one now!");
	} else {
	    Set<Person> personSet = new TreeSet<>(new SortByID());
	    output(personSet);
	}
    }

    public void getPersonsSortedByRating() {
	if (localStore.isEmpty()) {
	    System.out.println("No person saved yet. Search for one now!");
	} else {
	    Set<Person> personSet = new TreeSet<>(new SortByRating());
	    output(personSet);
	}
    }

    public void editPerson(String name) {
	// TO DO
    }

    public void exportPersonsToHTML() {
	Set<String> queryNames = this.localStore.keySet();
	Set<Person> personSet = new TreeSet<>();

	for (String queryName : queryNames) {
	    ArrayList<Person> results = this.localStore.get(queryName);
	    for (Person person : results) {
		personSet.add(person);
	    }
	}

	try {
	    PrintWriter pw = new PrintWriter("persons.html");

	    pw.println("<html>");
	    pw.println("<head>");
	    pw.println("<title>Actors Search</title>");
	    pw.println("<style>body {text-align: center;} .img-container {display:inline-block;} .img-container img:nth-of-type(1) {width: 200px;} .img-container img:nth-of-type(2) {width: 400px;}</style>");
	    pw.println("</head>");
	    pw.println("<body>");

	    for (Person p : personSet) {
		pw.println(p.toHTML());
	    }

	    pw.println("</body>");
	    pw.println("</html>");
	    pw.close();

	    File f = new File("persons.html");
	    Desktop.getDesktop().browse(f.toURI());
	} catch (FileNotFoundException e) {
	    System.out.println(e.getMessage());
	} catch (IOException e) {
	    System.out.println(e.getMessage());
	}
    }

    private static String readString(DataInputStream dis, int size) throws IOException {
	byte[] makeBytes = new byte[size * 2];
	dis.read(makeBytes);
	return depad(makeBytes);
    }

    private static String depad(byte[] read) {
	String word = "";
	for (int i = 0; i < read.length; i += 2) {
	    char c = (char) (((read[i] & 0x00FF) << 8) + (read[i + 1] & 0x00FF));

	    if (c != '*') {
		word += c;
	    }
	}
	return word;
    }

    public void output(Set<Person> personSet) {
	Set<String> queryNames = this.localStore.keySet();

	for (String queryName : queryNames) {
	    ArrayList<Person> results = this.localStore.get(queryName);
	    for (Person person : results) {
		personSet.add(person);
	    }
	}

	for (Person p : personSet) {
	    System.out.println(p.toString());
	}
    }
}
