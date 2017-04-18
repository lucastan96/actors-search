package actors.search;

import java.util.ArrayList;
import java.util.Objects;

public class Person implements Comparable<Person> {

    private double score;
    private String queryName;
    private String name;
    private int id;
    private ArrayList<String> imageUrls = new ArrayList<>();
    private String personLink;
    private double myRating;
    private ArrayList<String> myComments = new ArrayList<>();

    public Person() {

    }

    public Person(double score, String queryName, String name, int id, ArrayList<String> imageUrls, String personLink) {
	this.score = score;
	this.queryName = queryName;
	this.name = name;
	this.id = id;
	this.imageUrls = imageUrls;
	this.personLink = personLink;
    }

    public Person(double score, String queryName, String name, int id, ArrayList<String> imageUrls, String personLink, double myRating, ArrayList<String> myComments) {
	this.score = score;
	this.queryName = queryName;
	this.name = name;
	this.id = id;
	this.imageUrls = imageUrls;
	this.personLink = personLink;
	this.myRating = myRating;
	this.myComments = myComments;
    }

    public double getScore() {
	return score;
    }

    public void setScore(double score) {
	this.score = score;
    }

    public String getQueryName() {
	return queryName;
    }

    public void setQueryName(String queryName) {
	this.queryName = queryName;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public ArrayList<String> getImageUrls() {
	return imageUrls;
    }

    public void setImageUrls(ArrayList<String> imageUrls) {
	this.imageUrls = imageUrls;
    }

    public String getPersonLink() {
	return personLink;
    }

    public void setPersonLink(String personLink) {
	this.personLink = personLink;
    }

    public double getMyRating() {
	return myRating;
    }

    public void setMyRating(double myRating) {
	this.myRating = myRating;
    }

    public ArrayList<String> getMyComments() {
	return myComments;
    }

    public void setMyComments(ArrayList<String> myComments) {
	this.myComments = myComments;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final Person other = (Person) obj;
	if (this.id != other.id) {
	    return false;
	}
	if (!Objects.equals(this.name, other.name)) {
	    return false;
	}
	if (!Objects.equals(this.personLink, other.personLink)) {
	    return false;
	}
	return true;
    }

    @Override
    public int hashCode() {
	int hash = 5;
	hash = 29 * hash + Objects.hashCode(this.name);
	hash = 29 * hash + this.id;
	hash = 29 * hash + Objects.hashCode(this.personLink);
	return hash;
    }

    @Override
    public int compareTo(Person o) {
	return this.name.compareTo(o.getName());
    }

    @Override
    public String toString() {
	return "Person{" + "score=" + score + ", queryName=" + queryName + ", name=" + name + ", id=" + id + ", imageUrls=" + imageUrls + ", personLink=" + personLink + ", myRating=" + myRating + ", myComments=" + myComments + '}';
    }
}
