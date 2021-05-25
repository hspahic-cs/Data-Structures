package hashes;

public interface KWHashMap<K, V>
	{
	V get(Object key);
	V put(Object key, Object value);
	V remove(Object key);
	int size();
	boolean isEmpty();
	}
