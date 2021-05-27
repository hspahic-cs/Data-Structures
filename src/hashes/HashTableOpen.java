package hashes;

public class HashTableOpen<K, V> implements KWHashMap
	{

	private static class Entry<K, V>
		{
		private K key;
		private V value;
		
		public Entry(K key, V value)
			{
			this.key = key;
			this.value = value;
			}
		
		public K getKey() 
			{return key;}
		public V getValue() 
			{return value;}
		public V setValue(V val) 
			{
			V oldVal = value;
			value = val;
			return oldVal;
			}
		}
	
	//Data Fields
	private Entry<K, V>[] table;
	private static final int START_CAPACITY = 101;
	private double LOAD_THRESHOLD = 0.75;
	
	private int numKeys;
	private int numDeletes;
	private final Entry<K, V> DELETED = new Entry<K, V>(null, null);
	
	//Constructor
	public HashTableOpen()
		{
		table = new Entry[START_CAPACITY];
		}
	
	
	private int find(Object key)
		{
		// TODO
		int hash = key.hashCode();
		hash = hash % table.length;
		if (hash < 0) {
			hash += table.length;
		}
		
		while(table[hash] != null && !key.equals(table[hash].key)) {
			hash++;
			if(hash >= table.length) 
			hash = 0;
		}
		
		return hash;
		}
	
	public V get(Object key)
		{
		int index = find(key);
		
		Entry<K, V> result = table[index];
		
		if (result != null){
			return result.getValue();
		}
		
		return null;		
		}
	
	
	public V put(Object key, Object value)
		{
		int index = key.hashCode() % table.length;
		Entry<K, V> temp = new Entry<K,V>((K)key, (V)value);
		
		if (table[index] == null) {
			table[index] = temp;
			numKeys++;
			
			if((numKeys + numDeletes) >= table.length * LOAD_THRESHOLD) {
				rehash();
			}
			
			return null;
		}
		
		else {
			V oldVal = table[index].value;
			table[index].value = (V)value;
			return oldVal;
		}
		
		}
	
	public V remove(Object key)
		{
		int index = find(key);
		if(table[index] == null) {
			return null;
		}
		V oldValue = table[index].value;
		table[index] = null;
		numKeys--;

		return oldValue;
		}
	
	private void rehash()
		{
		Entry<K, V>[] oldTable = table;
		int len = oldTable.length;
		table = new Entry[2*len + 1];
		
		numKeys = 0; numDeletes = 0;
		
		for(int i = 0; i < len; i++)
			{
			Entry<K, V> entry= oldTable[i];
			if(entry != null && entry != DELETED)
				{
				put(entry.key, entry.value);
				}
			}
		}

	public int size()
		{
		return numKeys;
		}


	
	public boolean isEmpty()
		{
		return numKeys == 0;
		}
	
	
	}
