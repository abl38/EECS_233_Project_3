public class Entry{
	protected String key;
	protected boolean removed;
	protected int occurrences;
	// constructor - initializes entry before insertion
	protected Entry(String key, int occurrences){
		this.key = key;
		this.occurrences = occurrences;
		removed = false;
	}
}