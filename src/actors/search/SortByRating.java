package actors.search;

import java.util.Comparator;

public class SortByRating implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
	double r1 = o1.getMyRating();
	double r2 = o2.getMyRating();

	if (r1 > r2) {
	    return 1;
	} else if (r1 < r2) {
	    return -1;
	} else {
	    return 0;
	}
    }
}
