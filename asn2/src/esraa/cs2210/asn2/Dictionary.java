package esraa.cs2210.asn2;
import java.lang.Math;
import java.util.ArrayList;

//* this class implements a dictionary using a hash table //*

//the dictionary class implements the dictionary ADT//
public class Dictionary implements DictionaryADT {
	//creating variables and arraylist to store records//
	private ArrayList<ArrayList<Record>> dict;
	private int N;
	private int count;
	
	//constructor for dictionary class//
	//initializing arraylist, and variables//
	//IPNUT: size of type int
	public Dictionary(int size) {
		this.dict= new ArrayList<ArrayList<Record>>(size);
		this.N = size;
		for(int i = 0; i < N; i++) {
			this.dict.add(new ArrayList<Record>());
		}
		
		this.count= 0;
	}
	//*this method inserts a record pair into the dictionary//
	//exception thrown if pair is already in dictionary//
	//INPUT: pair, of type string//
	//OUTPUT: return 1 if collision, returns 0 otherwise//*
	public int insert(Record pair) throws DictionaryException {
		int key=hashFunction(pair.getConfig());
		if(this.dict.get(key).isEmpty()) {
			this.dict.get(key).add(pair);
			count++;
			return 0;
		}
		else {
			for (int i = 0; i< this.dict.get(key).size(); i++) {
				if(this.dict.get(key).get(i).getConfig().contentEquals(pair.getConfig())) {
					throw new DictionaryException();

				}
			}

			this.dict.get(key).add(pair);
			count++;
			return 1;
		}
		
	}
	//this method hashes the configuration into a key to be inserted into dictionary//
	//INPUT: config of type string//
	//OUTPUT: returns key of type int//
	private int hashFunction(String config) {
		int sum=0;
		for(int i=0; i < config.length(); i++) {
			sum += (config.codePointAt(i))*Math.pow(33, i) % 9973;	
		}
		return sum % this.N;
	}

	//* this method removes the entry with the given configuration//
	//throws exception if configuration is not in dictionary//
	//INPUT: config of type string//
	public void remove(String config) throws DictionaryException{

		for(int i=0; i < N; i++) {
			for(int j=0; j < this.dict.get(i).size(); j++) {
				if(this.dict.get(i).get(j).getConfig().equals(config)) {
					this.dict.get(i).remove(j);
					return;
				}
			}
		}
		throw new DictionaryException();
	}
	//this method gets the specified score//
	//INPUT: config of type string//
	//OUTPUT: returns the score of the specified configuration, and -1 if the configuration is not found//
	public int get(String config) {
		for(int i=0; i < N; i++) {
			for(int j=0; j < this.dict.get(i).size(); j++) {
				if(this.dict.get(i).get(j).getConfig().equals(config)) {
					return this.dict.get(i).get(j).getScore();
				}
			}
		}
		return -1;
		
	}
	//this method has the number of records in the dictionary//
	//OUTPUT: returns the int number of record objects in the dictionary//
	public int numElements() {
		return count;
	}
	
}
