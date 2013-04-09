
public class HashTable {
	public Entry[] table;
	public int collisions;
	/// empty position, table[i] = null
	// removed position, table[i] will refer to Entry object whose removed field equals true
	// occupied position, table[i] refer to Entry object whose removed field equals false
	private int tableSize;
	// constructor - initializes HashTable
	public HashTable(int size){
		table = new Entry[size];
		tableSize = size;
	}
	// first hash function returns <character encoding of first char> - <encoding of 'a'>
	private int h1(String key){
		return key.charAt(0) - 'a';
	}
	private int h2(String key){
		return key.length();
	}
	// finding an open position. returns the open position
	private int probe(String key){
		int i = h1(key);
		int j = h2(key);
		int iterations = 0;
		if(i < 0){
			i = -i;
		}
		// keep probing until we get an empty or removed position
		while((table[i] != null) && (!table[i].removed)){
			i = (i + j) % tableSize;
			iterations++;
			if(iterations > tableSize){
				return -1;
			}
		}
		this.collisions = iterations; 
		return i;
	}
	// returns the position of the key, not the available position
	private int findKey(String key){
		int i = h1(key);
		int j = h2(key);
		int iterations = 0;
		if(i < 0){
			i = -i;
		}
		// keeps probing while the entry is not empty
		while(table[i] != null){
			// return if key is found, otherwise continue
			if((!table[i].removed) && table[i].key.equals(key)){
				return i;
			}
			i = (i+j)%tableSize;
			iterations++;
			if(iterations > tableSize){
				return -1;
			}
		}
		return -1;
	}
	public int search(String key){
		int i = findKey(key);
		if(i == -1){
			return (Integer) null;
		}
		else{
			return table[i].occurrences;
		}
	}
	// searches the hash table and delete the key if found
	public void remove(String key){
		int i = findKey(key);
		if(i==-1){
			return;
		}
		table[i].removed = true;
	}
	public void insert(String key){
		// first probe for the key to find an empty position
		int i = probe(key);
		if(i == -1){
			// throw exception 
		}
		// case 2: the key is already found in the hash table, increase occurrences by 1
		else if((findKey(key) != -1) && (table[findKey(key)].key.equals(key))){
			table[findKey(key)].occurrences++;
			//System.out.println(table[findKey(key)].key);
			//System.out.println(table[findKey(key)].occurrences);
		}	
		// case 1: reach an empty position
		else if(table[i] == null){
			table[i] = new Entry(key, 1);
			//System.out.println(table[i].key);
			//System.out.println(table[i].occurrences);
		}
		// case 3: encounter a removed position while probing
		else if(table[i].removed == true){
			table[i] = new Entry(key, 1);
		}
		// case 4: no removed position or empty position encountered
		else{
			// throw exception: overflow
		}
		
		
	}
}
