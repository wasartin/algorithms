package binaryHeap;

import java.util.Comparator;

public abstract class AbstractPriorityQueue<K,V> implements PriorityQueue<K,V> {

	protected static class PQEntry<K,V> implements Entry<K,V>{
		private K key;
		private V value;
		
		public PQEntry(K key, V value){
			this.key = key;
			this.value = value;
		}
		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}
		
		protected void setKey(K key) {
			this.key = key;
		}
		
		protected void setValue(V value) {
			this.value = value;
		}
	}
	
	private Comparator<K> comp;
	
	protected AbstractPriorityQueue(Comparator<K> c) {
		comp = c;
	}
	
	public int size() {
		return 0;
	}
	
	public boolean isEmpty() {
		return false;
	}
	
	public Entry<K,V> insert(K key, V value) throws IllegalArgumentException{
		return null;
	}
	
	public Entry<K,V> min(){
		return null;
	}
	
	public Entry<K, V> removeMin(){
		return null;
	}
	
}
