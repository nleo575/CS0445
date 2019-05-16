/*
	Nicolas Leo, nll21,PS: 3466098
	CS 445 Spring 2018 Assignment 1
*/

import java.util.*; //For Random

/**
	This class acts as a simple data structure for accessing Java Objects. 
	It primarily implement 3 interfaces – MyQ<T>, Indexable<T>, and Shufflable.  
	The details of these interfaces are explained in the files MyQ.java, Indexable.java,
	and Shufflable.java. 
	@author Nicolas Leo
*/
public class RandIndexQueue <T> implements MyQ<T>, Indexable<T>, Shufflable
{
	private int moves,	//Keeps track of number of moves in the array
				front,	//Keeps track of the logical front of the queue
				back, 	//Keeps track of the logical end of the queue
				position, //Current position from the front of the queue, use in shuffle
				randPosition; //Keeps track of a randomly generated position from the front
	private T queue [],	//Array used as a queue
			  tempT; 	//Used to temporarily store objects of type T.
	private Random rand = new Random();	//Random number generator used to shuffle 

	/**
		Constructor method for creating a RandIndexQueue object
		@param initSize Integer to set the initial size of the queue
	*/
	public RandIndexQueue (int initSize)
	{
		front = 0; back = initSize;
		@SuppressWarnings("unchecked")
		T[] tempQueue = (T[]) new Object[initSize + 1];
		queue = tempQueue;
	}

	/**
		Method for returning a formatting string of the queue's contents.
		@return Formatted string of the queue's contents.
	*/
    public String toString()
    {
    	if (isEmpty())
    	{
    		return "Contents: empty";
    	}
    	else
    	{
	        StringBuilder sb = new StringBuilder("Contents: ");
	        for(int i = 0; i <size(); i++)
	        { sb.append(queue[(front + i) % queue.length]).append(" ");}
	        return sb.toString();
    	}

    }

	/**
		Doubles the size of the array if it becomes full and copies all of references
	*/
	private void resize ()
	{
		@SuppressWarnings("unchecked")
		T [] tempArray = (T[]) new Object [(queue.length*2)];
		//Copies the queue starting at the "front" and continuing until the "back"
		for (int n = 0; n < queue.length - 1 ; n++) 
		{
			tempArray[n] = queue[front];
			front = (front+1)% queue.length;
		}

		front = 0; back = queue.length - 2; 
		queue = tempArray;
	}

	/**
		Add a new Object to the queue in the next available location. 
		@param item Item (generic type T) being added at location i. 
		@return Always returns true since the queue is dynamically resized 
				to accommodate an arbitrary number of items.
	*/
	public boolean offer(T item)
	{
		if (item != null)
		{
			//Checks to see if the queue is full. If full, resize array.
			if (front == ((back + 2) % queue.length)) 
			{
				resize();
			}

			back = (back + 1 )% queue.length;
			queue[back] = item; moves++; 
		}
		return true;
	}
	
	/**
		Removes the logical front item in the queue. 
		@return Returns the logical front item (generic type T) in the queue. 
				If the queue is empty, returns null.
	*/
	public T poll()
	{
		if (isEmpty())
		{
			return null;
		}
		else
		{
			tempT = queue[front];
			queue[front] = null;
			front = (front + 1)%queue.length;

			return tempT;
		}

	}
	
	/**
		Gets and returns the logical front item in the queue without removing it.
		@return Returns the logical front item (generic type T). If the queue is empty, 
				returns null.
	*/
	public T peek()
	{
		if (size()> 0)
			return queue[front];
		else
			return null;
	}
	
	/**
		Tells if the queue is full. 
		@return Always returns false since the queue is dynamically resized as needed. 
	*/
	public boolean isFull()
	{
		return false;

	}
	
	/**
		Tells if the queue is empty. 
		@return Returns true if the queue is empty, and false otherwise
	*/
	public boolean isEmpty()
	{
		return front == ((back + 1) % queue.length);
	}
	
	/**
		Returns the logical size of the array
		@return Returns an integer containing the number of items in the queue
	*/
	public int size()
	{
		return front>back?(queue.length - front + back + 1)%queue.length:(back - front + 1);
	}

	/**
		Resets the queue to empty status by reinitializing the variables appropriately
	*/
	public void clear()
	{
		for (int x = 0; x < size(); x++)
			queue[(x+front)%queue.length] = null;
		front = 0; back = queue.length-1;
	}
	
	/**
		Returns the number of moves made in the array.
		@return Returns an integer with the number of moves made in the array. Moves are 
				made during poll and offer.
	*/
	public int getMoves()
	{
		return moves;
	}

	/**
		Sets the number of moves made in the array.
		@param moves Integer to set the number of moves to any arbitrary value
	*/
	public void setMoves(int moves)
	{
		this.moves = moves;
	}

	//______________________End of MyQ<T> methods____________________________

	/**
		Gets and returns the value located at logical location i in the implementing
		collection, where location 0 is the logical beginning of the collection.
		@param i Integer of the position where the user want to view the stored object.
		@return The object (generic type T) located at the logical location i. If the 
				collection has fewer than (i+1) items, throws an IndexOutOfBoundsException.
	*/
	public T get(int i)
	{
		if (size() < i + 1)
			throw new IndexOutOfBoundsException();
		else
			return queue[(front+i)%queue.length];
	}
	
	/**
		Assign item to logical location i in the implementing collection, where location
		0 is the logical beginning of the collection.  If the collection has fewer than
		(i+1) items, throw an IndexOutOfBoundsException
		@param i Integer of the logical location i in the implementing collection
		@param item Item (generic type T) being added at location i. 
	*/
	public void set(int i, T item)	
	{
		if (size() < i + 1)
			throw new IndexOutOfBoundsException();
		else
			queue[(front+i)%queue.length] = item;
	}

	//______________________End of Indexable<T> methods____________________________

	/**
		Reorganizes the items in the queue in a pseudo-random way by iterating through the
		logical order of the array from the front to the back. At each position, a random
		number is generated and the object at the current position is swapped with the 
		object at the position specified by the random number.
	*/
	public void shuffle()
	{
		if (size ()> 1)
		{
			for (int i = 0; i < size(); i++)
			{
				position = (i + front)%queue.length; //current position from the front
				randPosition =(rand.nextInt(size())+ front) % queue.length;

				if (randPosition != position)
				{
					tempT = queue[randPosition];
					queue[randPosition] = queue[position];
					queue[position] = tempT;
				}
			}	
			tempT = null; //Reinitialize the variable
		}
	}
}