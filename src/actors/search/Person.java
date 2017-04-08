package actors.search;

public class Person {

    private int value;
    private String queryName;
    private String name;
    private long id;
    private String[] imageUrls;
    private String personLink;
    private String myRating;
    private String myComments;

    public Person(int value, String queryName, String name, long id, String[] imageUrls, String personLink, String myRating, String myComments) {
	this.value = value;
	this.queryName = queryName;
	this.name = name;
	this.id = id;
	this.imageUrls = imageUrls;
	this.personLink = personLink;
	this.myRating = myRating;
	this.myComments = myComments;
    }

    public int getValue() {
	return value;
    }

    public void setValue(int value) {
	this.value = value;
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

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String[] getImageUrls() {
	return imageUrls;
    }

    public void setImageUrls(String[] imageUrls) {
	this.imageUrls = imageUrls;
    }

    public String getPersonLink() {
	return personLink;
    }

    public void setPersonLink(String personLink) {
	this.personLink = personLink;
    }

    public String getMyRating() {
	return myRating;
    }

    public void setMyRating(String myRating) {
	this.myRating = myRating;
    }

    public String getMyComments() {
	return myComments;
    }

    public void setMyComments(String myComments) {
	this.myComments = myComments;
    }

    @Override
    public String toString() {
	return "Person{" + "value=" + value + ", queryName=" + queryName + ", name=" + name + ", id=" + id + ", imageUrls=" + imageUrls + ", personLink=" + personLink + ", myRating=" + myRating + ", myComments=" + myComments + '}';
    }
}
