/*
CS 0445 Spring 2018
Read this class and its comments very carefully to make sure you implement
the class properly.  Note the items that are required and that cannot be
altered!  Generally speaking you will implement your MyStringBuilder using
a singly linked list of nodes.  See more comments below on the specific
requirements for the class.

For more details on the general functionality of most of these methods, 
see the specifications of the similar method in the StringBuilder class.
*/
public class MyStringBuilder
{
	/*These are the only three instance variables you are allowed to have.
	See details of CNode class below.  In other words, you MAY NOT add
	any additional instance variables to this class.  However, you may
	use any method variables that you need within individual methods.
	But remember that you may NOT use any variables of any other
	linked list class or of the predefined StringBuilder or 
	or StringBuffer class in any place in your code.  You may only use the
	String class where it is an argument or return type in a method.*/
	private CNode firstC;	// reference to front of list.  This reference is necessary
							// to keep track of the list
	private CNode lastC; 	// reference to last node of list.  This reference is
							// necessary to improve the efficiency of the append()
							// method
	private int length;  	// number of characters in the list

	// You may also add any additional private methods that you need to
	// help with your implementation of the public methods.

	/**
		Create a new MyStringBuilder initialized with the chars in String s
	*/
	public MyStringBuilder(String s)
	{
		if (s == null || s.length() == 0) // Special case for empty String
		{					 			  // or null reference
			firstC = null;
			lastC = null;
			length = 0;
		}
		else
		{
			// Create front node to get started
			firstC = new CNode(s.charAt(0));
			length = 1;
			CNode currNode = firstC;
			/*Create remaining nodes, copying from String.  Note
			how each new node is simply added to the end of the
			previous one.  Trace this to see what is going on.*/
			for (int i = 1; i < s.length(); i++)
			{
				CNode newNode = new CNode(s.charAt(i));
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode;
		}
	}

	/**
		Create a new MyStringBuilder initialized with the chars in array s
	*/
	public MyStringBuilder(char [] s)
	{
		if (s == null || s.length == 0) // Special case for empty array
		{					 			  // or null reference
			firstC = null;
			lastC = null;
			length = 0;
		}
		else
		{
			// Create front node to get started
			firstC = new CNode(s[0]);
			length = 1;
			CNode currNode = firstC;
			/*Create remaining nodes, copying from String.  Note
			how each new node is simply added to the end of the
			previous one.  Trace this to see what is going on.*/
			for (int i = 1; i < s.length; i++)
			{
				CNode newNode = new CNode(s[i]);
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode;
		}
	}

	/**
		Create a new empty MyStringBuilder
	*/
	public MyStringBuilder()
	{
		firstC = null;
		lastC = null;
		length = 0;
	}

	/**
		Append MyStringBuilder b to the end of the current MyStringBuilder, and
		return the current MyStringBuilder.  Be careful for special cases!
	*/
	public MyStringBuilder append(MyStringBuilder b)
	{
		if (b.length > 0) //Do nothing if b is empty
		{
			CNode tempN = null,	//Holds node position in this MSB object 
				  tempNb = null; //holds node position in MSB b

			if (length == 0) 
			{
				firstC = new CNode(b.firstC.data);
				tempN = firstC;
			}
			else 
				tempN = lastC;

			tempNb = b.firstC;
			for (int x = 0; x < b.length; x++)
			{
				tempN.next = new CNode(tempNb.data);
				tempN = tempN.next;
				tempNb = tempNb.next;
			}

			lastC = tempN;

			length += b.length;	
		}
		return this; 
	}

	/**
		Append String s to the end of the current MyStringBuilder, and return
		the current MyStringBuilder.  Be careful for special cases!
	*/
	public MyStringBuilder append(String s)
	{
		if (s.length() >0) //Check for valid string
		{
			if (length == 0) //Appending to an empty object
			{
				firstC = new CNode(s.charAt(0));
				lastC = firstC;
			}

			for (int x = length ==0?1:0; x < s.length(); x++)
			{
				lastC.next = new CNode(s.charAt(x));
				lastC = lastC.next;
			}	
			length += s.length();			
		}
		return this;
	}

	/**
		Append char array c to the end of the current MyStringBuilder, and
		return the current MyStringBuilder.  Be careful for special cases!
	*/
	public MyStringBuilder append(char [] c)
	{
		if (c.length >0)
		{
			if (length == 0) //Appending to an empty object
			{
				firstC = new CNode(c[0]);
				lastC = firstC;
			}

			for (int x = length ==1?1:0; x < c.length; x++)
			{
				lastC.next = new CNode(c[x]);
				lastC = lastC.next;
			}	

			length += c.length;			
		}
		return this;
	}

	/**
		Append char c to the end of the current MyStringBuilder, and
		return the current MyStringBuilder.  Be careful for special cases!
	*/
	public MyStringBuilder append(char c)
	{
		if (length == 0) //Appending to empty object
		{
			firstC = new CNode(c);
			lastC = firstC;
		}
		else
		{
			lastC.next = new CNode(c);
			lastC = lastC.next;
		}
		length ++;

		return this;
	}

	/**
		Return the character at location "index" in the current MyStringBuilder.
		If index is invalid, throw an IndexOutOfBoundsException.
	*/
	public char charAt(int index)
	{
		CNode tempN = firstC;
		if (index >= length || index < 0)
			throw new IndexOutOfBoundsException();
		else if (length > 0) 
		{
			for (int x = 1; x <= index; x++)
				tempN = tempN.next;	
		}
		return tempN.data;
	}

	/**
		Delete the characters from index "start" to index "end" - 1 in the
		current MyStringBuilder, and return the current MyStringBuilder.
		If "start" is invalid or "end" <= "start" do nothing (just return the
		MyStringBuilder as is).  If "end" is past the end of the MyStringBuilder, 
		only remove up until the end of the MyStringBuilder. Be careful for 
		special cases!
	*/
	public MyStringBuilder delete(int start, int end)
	{
		if (end > start && start < length && start >=0 && length > 0 )
		{
			CNode tempN = firstC, tempStart = firstC, tempEnd = firstC;

			for (int x = 0; x < (end<=length? end: length); x++)
			{
				if (x == start -1) //Finds 1 before "start"
					tempStart = tempN;

				if (x == (end <=length? (end-1): length)) 
					tempEnd = tempN;

				tempN = tempN.next;
			}

			if (start == 0) //Delete 1st node
				firstC = tempEnd.next; //Could be null if all links deleted
			else //Delete starting from middle or the end
				tempStart.next = tempEnd.next;

			if (tempEnd.next == null)//Deleted the end node
			{
				if (firstC == null) //Deleted all nodes
					lastC = null;
				else
					lastC = tempStart;
			}

			length -= ((end <=length? (end-1): length) - start + (end <=length? 1: 0));
		}

		return this;
	}

	/**
		Delete the character at location "index" from the current
		MyStringBuilder and return the current MyStringBuilder.  If "index" is
		invalid, do nothing (just return the MyStringBuilder as is).
		Be careful for special cases!
	*/
	public MyStringBuilder deleteCharAt(int index)
	{
		if (length > 0 && index < length && index >=0)
		{
			CNode tempN = firstC, tempStart = firstC;
			if (length == 1) //Deleting 1 & only char
			{
				firstC = null;
				lastC = null;
			}
			else if (index == 0) //Deleting first char but others remain
			{
				firstC = firstC.next;
			}
			else
			{
				int end = index -1;
				for (int x = 0; x < index; x++)
				{
					if (x == end) //Finds 1 before "index"
						tempStart = tempN;

					tempN = tempN.next; //Will be = to index when loop finishes
				}

				tempStart.next = tempN.next;
				
				if (index == length-1)  //Deleted last node
					lastC = tempStart;
			}
			length--;
		}

		return this;
	}

	/**
		Find and return the index within the current MyStringBuilder where
		String str first matches a sequence of characters within the current
		MyStringBuilder.  If str does not match any sequence of characters
		within the current MyStringBuilder, return -1.  Think carefully about
		what you need to do for this method before implementing it.
	*/
	public int indexOf(String str)
	{
		int temp = -1;
		if (length > 0 && str.length() >0)
		{
			boolean found = false; //Tells if the str is found or if end of list reached
			CNode tempN = firstC; //Temporary pointer
			int i = 0; 	//Counter to keep track of index in the list
			int x;		//Counter to keep track of number of characters compared to the 
						// string str.
			while(found!=true && i < length)
			{
				if (tempN.data == str.charAt(0)) //1st char of str found 
				{
					//See if there are enough chars left in the list 
					if(str.length()-1+i<length)
					{
						temp = i; x = 1;
						tempN = tempN.next; //Advance pointer to 2nd character
						i++;
						
						//Loop through list until the characters don't match.
						while(x< str.length() && str.charAt(x) == tempN.data)
						{
							tempN = tempN.next;
							x ++; i++;
						}

						if (x==str.length()) 
							found = true; //Exit loop if word successfully found.
						else //Otherwise continue searching the StringBuilder
							temp = -1;
					}
					else
						found = true; //Str not found, exit loop
				}
				else //Otherwise continue searching the StringBuilder
				{
					tempN = tempN.next;
					i++;
				}
			}
		}
		return temp;
	}

	/**
		Insert String str into the current MyStringBuilder starting at index
		"offset" and return the current MyStringBuilder.  if "offset" == 
		length, this is the same as append.  If "offset" is invalid
		do nothing.
	*/
	public MyStringBuilder insert(int offset, String str)
	{	
		int e = str.length();
		//Check for valid string & offset 
		if (offset <= length && offset >=0 && e > 0) 
		{	
			// Inserting at the end or beginning of empty MSB
			if(offset == length) 
			{
				if (length == 0) //Appending to an empty object
				{
					firstC = new CNode(str.charAt(0));
					lastC = firstC;
				}

				for (int x = length ==1?1:0; x < e; x++)
				{
					lastC.next = new CNode(str.charAt(x));
					lastC = lastC.next;
				}			
			}
			else 
			{
				CNode iPoint = null, iPoint2 = null;

				if (offset == 0) //Inserting at beginning of non-empty MSB
				{
					iPoint2 = firstC;
					firstC = new CNode(str.charAt(0));
					iPoint = firstC;
				}
				else //Not inserting at the beginning
				{
					iPoint = firstC; 
					int stop = offset-1;
					// Move pointer to insertion point
					for (int x = 0; x < stop; x++)
						iPoint = iPoint.next;
					iPoint2 = iPoint.next; //Retain next node after insertion point
				}

				for (int x = (offset == 0? 1: 0); x < e; x++)
				{	
					iPoint.next = new CNode(str.charAt(x));
					iPoint = iPoint.next;
				}	

				iPoint.next = iPoint2; //Join end of str with node after offset
			}
			length += e;	
		}
		return this;
	}

	/**
		Insert character c into the current MyStringBuilder at index
		"offset" and return the current MyStringBuilder.  If "offset" ==
		length, this is the same as append.  If "offset" is invalid, 
		do nothing.
	*/
	public MyStringBuilder insert(int offset, char c)
	{
		if (offset <= length && offset >=0) //Check for valid offset
		{
			if(offset == length) //Insert at the end or to an empty MSB
			{
				if (length == 0) //Appending to empty object
				{
					firstC = new CNode(c);
					lastC = firstC;
				}
				else
				{
					lastC.next = new CNode(c);
					lastC = lastC.next;
				}
			}
			else  
			{
				CNode iPoint = null;

				if (offset == 0) //Inserting at the beginning of non-empty MSB
				{
					iPoint = firstC;
					firstC = new CNode(c);
					firstC.next = iPoint;
				}
				else //Not inserting at the beginning
				{
					iPoint = firstC; 
					CNode iPoint2 = null; //Will need 2nd temporary node

					int stop = offset-1;
					// Move pointer to insertion point
					for (int x = 0; x < stop; x++)
						iPoint = iPoint.next;

					iPoint2 = iPoint.next; //Retain next node after insertion point
					iPoint.next = new CNode(c);
					iPoint = iPoint.next;  // Make pointer the new node
					iPoint.next = iPoint2; //Join end of new node with node after offset 
				}	
			}
			length ++;	
		}
		return this;
	}

	/**
		Insert char array c into the current MyStringBuilder starting at index
		index "offset" and return the current MyStringBuilder.  If "offset" is
		invalid, do nothing.
	*/
	public MyStringBuilder insert(int offset, char [] c)
	{
		int e = c.length;
		//Check for valid array & offset 
		if (offset <= length && offset >=0 && e > 0)
		{ 	
			if(offset == length)//Insert at the end or to an empty MSB
			{
				if (length == 0) //Appending to an empty object
				{
					firstC = new CNode(c[0]);
					lastC = firstC;
				}

				for (int x = length ==1?1:0; x < e; x++)
				{
					lastC.next = new CNode(c[x]);
					lastC = lastC.next;
				}	
			}
			else
			{
				CNode iPoint = null, iPoint2 = null;

				if (offset == 0) //Inserting at beginning of non-empty MSB
				{
					iPoint2 = firstC;
					firstC = new CNode(c[0]);
					iPoint = firstC;
				}
				else //Not inserting at the beginning
				{
					iPoint = firstC; 
					int stop = offset-1;
					// Move pointer to insertion point
					for (int x = 0; x < stop; x++)
						iPoint = iPoint.next;
					iPoint2 = iPoint.next; //Retain next node after insertion point
				}

				for (int x = (offset == 0? 1: 0); x < e; x++)
				{	iPoint.next = new CNode(c[x]);
					iPoint = iPoint.next;
				}	

				iPoint.next = iPoint2; //Join end of str with node after offset	
			}
			length += e;	
		}

		return this;
	}

	/**
		Return the length of the current MyStringBuilder
	*/
	public int length()
	{
		return length;
	}

	/**
		Delete the substring from "start" to "end" - 1 in the current
		MyStringBuilder, then insert String "str" into the current
		MyStringBuilder starting at index "start", then return the current
		MyStringBuilder.  If "start" is invalid or "end" <= "start", do nothing.
		If "end" is past the end of the MyStringBuilder, only delete until the
		end of the MyStringBuilder, then insert.  This method should be done
		as efficiently as possible.  In particular, you may NOT simply call
		the delete() method followed by the insert() method, since that will
		require an extra traversal of the linked list.
	*/
	public MyStringBuilder replace(int start, int end, String str)
	{
		//Check for valid string, start, & end
		if(length > 0 &&  end > start && start < length && start >=0 && str.length() > 0) 
		{
			CNode tempN = firstC, tempStart = firstC, tempEnd = firstC;

			int s = start - 1, //Pointer to node 1 before the "start" 
				e = end <=length? (end-1): length, //Pointer to node 1 before "end"
				e1 = end<=length? end: length; //Signals when to stop the loop

			for (int x = 0; x < e1; x++)
			{
				if (x == start -1) //Finds 1 before "start"
					tempStart = tempN;

				if (x == e) //Find 1 before "end"
					tempEnd = tempN;

				tempN = tempN.next;
			}

			if (start == 0) //Delete 1st node
				firstC = tempEnd.next; //Could be null if all links deleted
			else //Delete starting from middle or the end
				tempStart.next = tempEnd.next;

			if (tempEnd.next == null)//Deleted the end node
			{
				if (firstC == null) //Deleted all nodes
					lastC = null;
				else
					lastC = tempStart;
			}

			length -= ((end <=length? (end-1): length) - start + (end <=length? 1: 0));

			//Delete complete, time to insert

			//If all nodes removed or last node removed, will be append
			if (length == 0 || tempEnd.next == null) 
			{
				if (length == 0) //Appending to an empty object
				{
					firstC = new CNode(str.charAt(0));
					lastC = firstC;
				}

				e = str.length();
				for (int x = length ==0?1:0; x < e; x++)
				{
					lastC.next = new CNode(str.charAt(x));
					lastC = lastC.next;
				}	
			}
			else  //Removed nodes in the middle
			{
				CNode iPoint = null, iPoint2 = null;

				if (start == 0) //Inserting at beginning
				{
					firstC = new CNode(str.charAt(0));
					iPoint = firstC;
				}
				else //Not inserting at the beginning
					iPoint = tempStart; 

				iPoint2 = tempEnd.next; //Retain node at (end - 1)

				e = str.length();
				for (int x = (start == 0? 1: 0); x < e; x++)
				{	iPoint.next = new CNode(str.charAt(x));
					iPoint = iPoint.next;
				}	

				iPoint.next = iPoint2; //Join end of str with node at (end - 1)

				length += str.length();		
			}
		}	

		return this;
	}

	/**
		Reverse the characters in the current MyStringBuilder and then
		return the current MyStringBuilder.
	*/
	public MyStringBuilder reverse()
	{
		if (length > 1)
		{
			CNode prevN = null, tempN = firstC, nextN;
			while (tempN.next != null)
			{
				nextN = tempN.next;
				tempN.next = prevN;
				prevN = tempN;
				tempN = nextN;
			}

			tempN.next = prevN;
			lastC = firstC;
			firstC = tempN;
		}

		return this;
	}
	
	/**
		Return as a String the substring of characters from index "start" to
		index "end" - 1 within the current MyStringBuilder
	*/
	public String substring(int start, int end)
	{
		String substr = "";

		if (end > start && start <= length && start >=0 && length > 0 )
		{
			CNode tempN = firstC;

			//Search list until the desired "start"
			int x = 1;
			while (x <= start)
			{
					tempN = tempN.next;
					x++;
			}

			//Continue in the list until reaching the "end" - 1
			while (x<=(end <=length? end: length)) 
			{
				substr += tempN.data;
				tempN = tempN.next;
				x++;
			}
		}

		return substr;
	}

	/**
		Return the entire contents of the current MyStringBuilder as a String
	*/
	public String toString()
	{
		String output = "";
		CNode tempN = firstC;
		for(int x = 0; x < length; x++)
		{
			output += tempN.data;
			tempN = tempN.next;
		}
		return output;
	}

	/**
		You must use this inner class exactly as specified below.  Note that
		since it is an inner class, the MyStringBuilder class MAY access the
		data and next fields directly.
	*/
	private class CNode
	{
		private char data;
		private CNode next;

		public CNode(char c)
		{
			data = c;
			next = null;
		}

		public CNode(char c, CNode n)
		{
			data = c;
			next = n;
		}
	}
}

