// CS 0445 Spring 2018
// Read this class and its comments very carefully to make sure you implement
// the class properly.  The data and public methods in this class are identical
// to those MyStringBuilder, with the exception of the two additional methods
// shown at the end.  You cannot change the data or add any instance
// variables.  However, you may (and will need to) add some private methods.
// No iteration is allowed in this implementation. 

// For more details on the general functionality of most of these methods, 
// see the specifications of the similar method in the StringBuilder class.  
public class MyStringBuilder2
{
	// These are the only three instance variables you are allowed to have.
	// See details of CNode class below.  In other words, you MAY NOT add
	// any additional instance variables to this class.  However, you may
	// use any method variables that you need within individual methods.
	// But remember that you may NOT use any variables of any other
	// linked list class or of the predefined StringBuilder or 
	// or StringBuffer class in any place in your code.  You may only use the
	// String class where it is an argument or return type in a method.
	private CNode firstC;	// reference to front of list.  This reference is necessary
							// to keep track of the list
	private CNode lastC; 	// reference to last node of list.  This reference is
							// necessary to improve the efficiency of the append()
							// method
	private int length;  	// number of characters in the list

	// You may also add any additional private methods that you need to
	// help with your implementation of the public methods.

	/**
		Create a new MyStringBuilder2 initialized with the chars in String s.
		@param s A String object that is used to create a new MSB2 object of chars
	*/
	public MyStringBuilder2(String s)
	{
	      if (s != null && s.length() > 0)
	            makeBuilder(s, 0);
	      else  // no String so initialize empty MyStringBuilder2
	      {
	            length = 0;
	            firstC = null;
	            lastC = null;
	      }
	}
	 
	// Recursive method to set up a new MyStringBuilder2 from a String
	private void makeBuilder(String s, int pos)
	{
	      // Recursive case – we have not finished going through the String
	      if (pos < s.length()-1)
	      {
	            // Note how this is done – we make the recursive call FIRST, then
	            // add the node before it.  In this way the LAST node we add is
	            // the front node, and it enables us to avoid having to make a
	            // special test for the front node.  However, many of your
	            // methods will proceed in the normal front to back way.
	            makeBuilder(s, pos+1);
	            firstC = new CNode(s.charAt(pos), firstC);
	            length++;
	      }
	      else if (pos == s.length()-1) // Special case for last char in String
	      {                             // This is needed since lastC must be
	                                    // set to point to this node
	            firstC = new CNode(s.charAt(pos));
	            lastC = firstC;
	            length = 1;
	      }
	      else  // This case should never be reached, due to the way the
	            // constructor is set up.  However, I included it as a
	      {     // safeguard (in case some other method calls this one)
	            length = 0;
	            firstC = null;
	            lastC = null;
	      }
	}

	// Create a new MyStringBuilder2 initialized with the chars in array s
	public MyStringBuilder2(char [] s)
	{
	      if (s != null && s.length > 0)
	            makeBuilder(s, 0);
	      else  // no String so initialize empty MyStringBuilder2
	      {
	            length = 0;
	            firstC = null;
	            lastC = null;
	      }
	}
	 
	// Recursive method to set up a new MyStringBuilder2 from a String
	private void makeBuilder(char [] s, int pos)
	{
	      // Recursive case – we have not finished going through the String
	      if (pos < s.length-1)
	      {
	            // Note how this is done – we make the recursive call FIRST, then
	            // add the node before it.  In this way the LAST node we add is
	            // the front node, and it enables us to avoid having to make a
	            // special test for the front node.  However, many of your
	            // methods will proceed in the normal front to back way.
	            makeBuilder(s, pos+1);
	            firstC = new CNode(s[pos], firstC);
	            length++;
	      }
	      else if (pos == s.length-1) // Special case for last char in String
	      {                             // This is needed since lastC must be
	                                    // set to point to this node
	            firstC = new CNode(s[pos]);
	            lastC = firstC;
	            length = 1;
	      }
	      else  // This case should never be reached, due to the way the
	            // constructor is set up.  However, I included it as a
	      {     // safeguard (in case some other method calls this one)
	            length = 0;
	            firstC = null;
	            lastC = null;
	      }
	}

	// Create a new empty MyStringBuilder2
	public MyStringBuilder2()
	{
        length = 0;
        firstC = null;
        lastC = null;
	}

	/**
		Appends a MyStringBuilder2 object to the end of the current MyStringBuilder2.
		@param b Another MyStringBuilder2
		@return The current MyStringBuilder2
	*/
	public MyStringBuilder2 append(MyStringBuilder2 b)
	{
		if (b.length > 0) //Do nothing if b is empty
		{
			CNode tempN;	//holds node position in MSB2 b 

			if (length == 0) 
			{
				firstC = new CNode(b.firstC.data);
				lastC = firstC;
				tempN = b.firstC.next; //Will be null if b.length == 1
			}
			else
				tempN = b.firstC;

			// If MSB2 was empty and b was only 1 node long, then done
			if (tempN != null)
				appendRec(tempN);				

			length += b.length;	
		}
		return this; 
	}

	private void appendRec (CNode  tempN)
	{
		lastC.next = new CNode(tempN.data);
		lastC = lastC.next;
		
		if (tempN.next != null) 
			appendRec(tempN.next);
	}


	/**
		Appends a String object to the end of the current MyStringBuilder2.
		@param s A String object
		@return The current MyStringBuilder2
	*/
	public MyStringBuilder2 append(String s)
	{
		if (s.length() > 0) //Do nothing if s is 0 length
		{
			int x;	//holds position of the next char in the string

			if (length == 0) 
			{
				firstC = new CNode(s.charAt(0));
				lastC = firstC;
				x = 1;
			}
			else
				x = 0;

			// If MSB2 was empty and s was only 1 character long, then done
			if (length !=0 || s.length() != 1)
				appendRec(s, x);				

			length += s.length();	
		}
		return this; 
	}

	private void appendRec (String s, int x)
	{
		lastC.next = new CNode(s.charAt(x));
		lastC = lastC.next;
		
		if (x < s.length() -1) 
			appendRec(s, ++x);
	}

	/**
		Appends an array of characters to the end of the current MyStringBuilder2.
		@param c An array of characters
		@return The current MyStringBuilder2
	*/
	public MyStringBuilder2 append(char [] c)
	{
		if (c.length > 0) //Do nothing if c is 0 length
		{
			int x;	//holds position of the next char in the array

			if (length == 0) 
			{
				firstC = new CNode(c[0]);
				lastC = firstC;
				x = 1;
			}
			else
				x = 0;

			// If MSB2 was empty and c was only 1 character long, then done
			if (length !=0 || c.length != 1)
				appendRec(c, x);				

			length += c.length;	
		}
		return this; 
	}

	private void appendRec (char [] c, int x)
	{
		lastC.next = new CNode(c[x]);
		lastC = lastC.next;
		
		if (x < c.length -1) 
			appendRec(c, ++x);
	}

	/**
		Appends a single character to the end of the current MyStringBuilder2.
		@param c A character
		@return The current MyStringBuilder2
	*/
	public MyStringBuilder2 append(char c)
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
		Gets the character at a given index in the MyStringBuilder2.
		@param index Integer of the index of interest in the MyStringBuilder2
		@return Returns the character at location "index" in the current MyStringBuilder2.
		If index is invalid, throw an IndexOutOfBoundsException.
	*/
	public char charAt(int index)
	{
		CNode tempN = firstC;
		if (index >= length || index < 0)
			throw new IndexOutOfBoundsException();
		else 
			tempN = charAtRec(tempN, 0, index);

		return tempN.data;
	}

	private CNode charAtRec(CNode tempN, int x, int index)
	{
		if (x == index) 
			return tempN;
		else
			return charAtRec(tempN.next, ++x, index);
	}

	/**
		Deletes the characters from index "start" to index "end" - 1 in the
		current MyStringBuilder2.
		@param start Integer of starting index to begin the deletion
		@param end Integer of the end index to delete up to (not inclusive) 
		@return The current MyStringBuilder2.
	*/
	public MyStringBuilder2 delete(int start, int end)
	{
		if (end > start && start < length && start >=0 && length > 0 )
		{
			CNode tempN = firstC, tempStart = firstC, tempEnd = firstC;

			deleteRec(0, start, (end<length? end: length), tempN, tempStart, tempEnd);
			length -= ((end <=length? (end-1): length) - start + (end <=length? 1: 0));
		}

		return this;
	}

	private void deleteRec (int pos, int start, int end, CNode tempN, CNode tempStart, 
		CNode tempEnd)
	{
		if (pos < end) 
		{
			if (pos == start -1) //Finds 1 before "start"
				tempStart = tempN;

			if (pos == end-1) 
				tempEnd = tempN;

			deleteRec(++pos, start, end, tempN.next, tempStart, tempEnd);
		}
		else 
		{ 
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
		}
	}

	/**
		Deletes the character at location "index" from the current
		MyStringBuilder2.
		@param index Integer of the index of the character to delete.
		@return The current MyStringBuilder2 object.  If "index" is
		invalid, no deletion will occur.
	*/
	public MyStringBuilder2 deleteCharAt(int index)
	{
		if (length > 0 && index < length && index >=0)
		{
			if (length == 1) //Deleting 1 & only char
			{
				firstC = null;
				lastC = null;
			}
			else if (index == 0) //Deleting first char but others remain
				firstC = firstC.next;
			else //Deleting from the middle or the end character
				deleteCharAtRec(index -1, 0, firstC);

			length--;
		}

		return this;
	}

	private void deleteCharAtRec(int index, int pos, CNode tempN)
	{
		if (pos < index) 
			deleteCharAtRec(index, ++pos, tempN.next);
		else 
		{
			tempN.next = tempN.next.next;
			if (tempN.next == null)  //Deleted last node
					lastC = tempN;
		}
	}

	/**
		Finds and returns the index within the current MyStringBuilder2 where
		the given string first matches a sequence of characters within the current
		MyStringBuilder2.
		@param str A string object
		@return Integer representing the index where the string begins. 
				If the string is not found, returns -1
	*/
	public int indexOf(String str)
	{
		if (length > 0 && str.length() >0)
			return indexOfRec(str, firstC, firstC, 0, 0);
		else
			return -1;
	}
	/**
		@param str String object from the constructor
		@param tempN Temporary node used for forward recursion
		@param failure Temporary node pointer used in case current recursion doesn't work
		@param i Current index in the MSB
		@param x Current index in the string
	*/
	private int indexOfRec(String str, CNode tempN, CNode failure, int i, int x)
	{
		if (tempN.data == str.charAt(x)) //1st char of str found 
		{

			if (x==str.length()-1) 	//reached the end of the string
				return i - x; //Exit loop if word successfully found.
			else if (tempN.next != null)//If there's another node, check it
				return indexOfRec(str, tempN.next, failure, ++i, ++x);	
			else
				return -1;
		}
		else if (failure.next != null) //Backtrack and use the starting index + 1
			return indexOfRec(str,failure.next, failure.next, i - x + 1, 0);
		else //Reached end of string w/out matching
			return -1;
	}


	/**
		Inserts a string into the current MyStringBuilder2 starting at index
		index "offset."  If "offset" is invalid, the method does nothing.
		@param offset Integer where the insertion begins
		@param str A string object
		@return The current MyStringBuilder2
	*/
	public MyStringBuilder2 insert(int offset, String str)
	{
		//Check for valid string & offset 
		if (offset <= length && offset >=0 && str.length() > 0) 
		{	
			// Inserting at the end of MSB2 with nodes or to the beginning of empty MSB2
			if(offset == length) 
			{
				if (length == 0) //Appending to an empty object
				{
					firstC = new CNode(str.charAt(0));
					lastC = firstC;
				}

				insertRec(str, (length ==1?1:0), str.length());		
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
					// Move pointer to insertion point
					iPoint = insertRec(iPoint, 0, offset-1);
					iPoint2 = iPoint.next; //Retain next node after insertion point
				}

				iPoint = insertRec(str, iPoint, (offset == 0? 1: 0), str.length());
				iPoint.next = iPoint2; //Join end of str with node after offset
			}
			length += str.length();	
		}
		return this;
	}

	//Appends to the end
	private void insertRec(String str, int pos, int stop)
	{
		if (pos < stop) 
		{
			lastC.next = new CNode(str.charAt(pos));
			lastC = lastC.next;	
			insertRec(str, ++pos, stop);
		}
	}

	//Moves the pointer to an insertion point and returns the pointer.
	private CNode insertRec(CNode iPoint, int pos, int stop)
	{	
		if (pos < stop)
			return	insertRec(iPoint.next, ++pos, stop);
		else
			return iPoint; 
	}

	//Inserts the string at a given position
	private CNode insertRec(String str, CNode iPoint, int pos, int stop)
	{	
		if (pos < stop) 
		{
			iPoint.next = new CNode(str.charAt(pos));
			return insertRec(str, iPoint.next, ++pos, stop);
		}
		else
			return iPoint;
	}


	/**
		Inserts a character into the current MyStringBuilder2 starting at index
		index "offset."  If "offset" is invalid, the method does nothing.
		@param offset Integer where the insertion begins
		@param c A character
		@return The current MyStringBuilder2
	*/
	public MyStringBuilder2 insert(int offset, char c)
	{
		//Check for valid string & offset 
		if (offset <= length && offset >=0) 
		{	
			// Inserting at the end of MSB2 with nodes or to the beginning of empty MSB2
			if(offset == length) 
			{
				if (length == 0) //Appending to an empty object
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
					// Move pointer to insertion point
					//Calls same method from above in the string version
					iPoint = insertRec(iPoint, 0, offset-1);
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
		Inserts char array into the current MyStringBuilder2 starting at index
		index "offset."  If "offset" is invalid, the method does nothing.
		@param offset Integer where the insertion begins
		@param c An array of characters
		@return The current MyStringBuilder2
	*/
	public MyStringBuilder2 insert(int offset, char [] c)
	{
		//Check for valid string & offset 
		if (offset <= length && offset >=0 && c.length > 0) 
		{	
			// Inserting at the end of MSB2 with nodes or to the beginning of empty MSB2
			if(offset == length) 
			{
				if (length == 0) //Appending to an empty object
				{
					firstC = new CNode(c[0]);
					lastC = firstC;
				}

				insertRec(c, (length ==1?1:0), c.length);		
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
					// Move pointer to insertion point
					//Calls same method from above in the string version
					iPoint = insertRec(iPoint, 0, offset-1);
					iPoint2 = iPoint.next; //Retain next node after insertion point
				}

				iPoint = insertRec(c, iPoint, (offset == 0? 1: 0), c.length);
				iPoint.next = iPoint2; //Join end of str with node after offset
			}
			length += c.length;	
		}
		return this;
	}

	//Appends the char array at the end
	private void insertRec(char [] c, int pos, int stop)
	{
		if (pos < stop) 
		{
			lastC.next = new CNode(c[pos]);
			lastC = lastC.next;	
			insertRec(c, ++pos, stop);
		}
	}


	//Inserts the char array at a given position
	private CNode insertRec(char [] c, CNode iPoint, int pos, int stop)
	{	
		if (pos < stop) 
		{
			iPoint.next = new CNode(c[pos]);
			return insertRec(c, iPoint.next, ++pos, stop);
		}
		else
			return iPoint;
	}

	/**
		Returns the length of the current MyStringBuilder2
		@return Returns an integer the length of the current MyStringBuilder2
	*/
	public int length()
	{
		return length;
	}

	//This method should be done as efficiently as possible.  In particular, you may 
	//NOT simply call the delete() method followed by the insert() method, 
	// since that will require an extra traversal of the linked list.
	/**
		Deletes the substring from "start" to "end" - 1 in the current
		MyStringBuilder2, then inserts String "str" into the current
		MyStringBuilder2 starting at index "start."   
		If "start" is invalid or "end" <= "start", do nothing.
		If "end" is past the end of the MyStringBuilder2, only delete until the
		end of the MyStringBuilder2, then insert.  This method should be done
		as efficiently as possible.  In particular, you may NOT simply call
		the delete() method followed by the insert() method, since that will
		require an extra traversal of the linked list.
		@param start Integer of the starting index to replace
		@param end Integer of the end index to replace up until
		@param str String object to replace the characters within [start, end)
		@return The current MyStringBuilder2.
	*/
	public MyStringBuilder2 replace(int start, int end, String str)
	{
				//Check for valid string, start, & end
		if(length > 0 &&  end > start && start < length && start >=0 && str.length() > 0) 
		{
			CNode tempN = firstC, tempStart = firstC, tempEnd = firstC;
			replaceRec(str, 0, 0, start, (end<length? end: length), tempN, 
				tempStart, tempEnd);
		}
		return this;
	}


	/**
		@param pos Integer of the position/index in the MSB2
		@parm start Integer of the starting index to begin replacing

	*/
	public void replaceRec(String str, int posMSB, int posStr, int start, int end, 
		CNode tempN, CNode tempStart, CNode tempEnd)
	{
		if (posMSB < end) 
		{
			if (posMSB == start -1) //Finds 1 before "start"
				tempStart = tempN;

			if (posMSB == end-1) 
				tempEnd = tempN;

			replaceRec(str, ++posMSB, posStr, start, end, tempN.next, tempStart, tempEnd);
		}
		else 
		{ 
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

				replaceRec2(length ==0?1:0, str);
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
				replaceRec3((start == 0? 1: 0), str, iPoint, iPoint2);
			}
			length += str.length();	
		}
	}

	private void replaceRec2(int pos, String str)
	{
		lastC.next = new CNode(str.charAt(pos));
		lastC = lastC.next;
		
		if (pos < str.length() -1) 
			replaceRec2(++pos, str);
	}

	private void replaceRec3(int pos, String str, CNode iPoint, CNode iPoint2)
	{
		iPoint.next = new CNode(str.charAt(pos));
		iPoint = iPoint.next;
		if (pos < str.length() -1) 
			replaceRec3(++pos, str, iPoint, iPoint2);
		else
			iPoint.next = iPoint2; //Join end of str with node at (end - 1)
	}

	// Reverse the characters in the current MyStringBuilder2 and then
	// return the current MyStringBuilder2.
	public MyStringBuilder2 reverse()
	{
		if (length > 1)
		{
			CNode prevN = null, tempN = firstC, nextN = tempN.next;
			reverseRec(prevN, tempN, nextN);
		}
		return this;
	}

	private void reverseRec(CNode prevN, CNode tempN, CNode nextN)
	{
		if (nextN != null) //Not at last node
		{
			tempN.next = prevN;
			reverseRec(tempN, nextN, nextN.next);		
		}
		else
		{
			tempN.next = prevN;
			lastC = firstC;
			firstC = tempN;
		}
	}
	
	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder2
	public String substring(int start, int end)
	{
		char [] c = new char[(end >= length? length-1: end) - start + 1];

		if (end > start && start <= length && start >=0 && length > 0 )
		{
			CNode tempN = firstC;

			int pos = 1, //Tracks index in array & compares it against the start value
				index = 0; //Used for index value of the char array c. 
			substringRec (c, index, pos, start, end, tempN);
		}

		return (new String(c));
	}

	private void substringRec(char [] c, int index, int pos, int start, int end, 
		CNode tempN)
	{
		if (pos <= start)
			substringRec (c, index, ++pos, start, end, tempN.next);
		else if (pos<=(end >length? length: end)) 
		{
			c[index] = tempN.data;
			substringRec (c, ++index, ++pos, start, end, tempN.next);
		}
	}

	/**
		Return the entire contents of the current MyStringBuilder as a String
	*/
	public String toString()
	{
		char [] c = new char[length];
		getString(c, 0, firstC);
		return (new String(c));
	}

	private void getString(char [] c, int pos, CNode curr)
	{
	      if (curr != null)
	      {
	            c[pos] = curr.data;
	            getString(c, pos+1, curr.next);
	      }
	}

	// Find and return the index within the current MyStringBuilder2 where
	// String str LAST matches a sequence of characters within the current
	// MyStringBuilder2.  If str does not match any sequence of characters
	// within the current MyStringBuilder2, return -1.  Think carefully about
	// what you need to do for this method before implementing it.  For some
	// help with this see the Assignment 3 specifications.
	public int lastIndexOf(String str)
	{
		int ans = -1;
		if (length > 0 && str.length() >0)
		{
			CNode tempN = firstC; //Temporary pointer
			ans = lastIndexOfRec(str, tempN, 0, str.length() - 1);
		}
		return ans;
	}

	/**
		@param str String of interest
		@param tempN CNode used to traverse the linked list
		@param ans Integer representing the answer. Assume not found (-1)
		@param i Current index in the list
		@param x Tracks which index of the string is being compared against the current 
		node
	*/
	private int lastIndexOfRec(String str, CNode tempN, int i, int x)
	{
		int ans; 
		if (tempN != lastC) //Recurse until the end of the array
			ans = lastIndexOfRec(str, tempN.next, ++i, x);
		else
			ans = -1;

		if (ans != -1 ) //Part of string was found at the next node in the list
			if(ans - i < str.length()) // Need to check this node
				x = str.length() - 1 - (ans - i); //decrement x 
			else  // Else ans - i >= str.length() i.e. str was found
				return tempN != firstC?ans:(ans - str.length());	
			//If tempN == firstC, calculate correct answer, 
			//otherwise return index of last char of str and keep moving down the stack

		// Checks if there are enough nodes remaining on the stack in order to check the
		// remainder of the string & if the current node's data == a particular char in 
		//str
		if (i - x >= 0 && tempN.data == str.charAt(x)) 
			if ( x == str.length() -1)
				return i;
			else
				return tempN != firstC?ans:0;
			/*If tempN != firstC, return index of last char of str otherwise 
			firstC == 1st 
			char of str, in which case lastIndexOf == 0*/
		else
			return -1;
	}
	
	// Find and return an array of MyStringBuilder2, where each MyStringBuilder2
	// in the return array corresponds to a part of the match of the array of
	// patterns in the argument.  If the overall match does not succeed, return
	// null.  For much more detail on the requirements of this method, see the
	// Assignment 3 specifications.
	public MyStringBuilder2 [] regMatch(String [] pats)
	{	//Check if valid array passed in & msb not empty
		if (pats.length > 0 && length > 0) 
		{
			MyStringBuilder2 [] msb = new MyStringBuilder2[pats.length];

			msb = regMatchInitialize(msb, 0); //Initialize array with MSB2 objects
			if (regMatchRec(firstC, msb, pats, 0, 0)) 
				return msb;
			else 
				return null;
		}
		else
			return null;
	}

	private MyStringBuilder2 [] regMatchInitialize(MyStringBuilder2 [] msb, int pos)
	{
		msb[pos] = new MyStringBuilder2();
		if (pos < msb.length-1)
			msb = regMatchInitialize(msb, pos+1);
		return msb;
	}

	/**
		@param str String object of the pattern
		@param c A char from the original node
		@param x Current index in the string
	*/
	private boolean patContains(String str, char c, int x)
	{
		if (c == str.charAt(x)) //Char is within this pat
			return true;
		else if (x<str.length()-1) // Char not found, check next index of the string
			return patContains(str,c, x+1);
		else //Reached end of string w/out matching
			return false;
	}

	private void res(MyStringBuilder2 [] msb, int level)
	{
		for (MyStringBuilder2 m: msb ) 
		{
			levspace(level);
			System.out.println("Res: "+m);
		}
	}

	private void levspace(int level)
	{
		for (int x = 0; x < level; x++ ) 
			System.out.print("     ");
	}
	/**
		@param tempN A CNode used to keep track of the nodes in the MSB2 object
		@param msb2 An array of MyStringBuilder 2 objects that will be used for output
		@param pats String array of the patterns to match
		@param indexPats Integer of the index of the current pattern
		@return Method returns a boolean indicating whether the pattern(s) were matched
	*/
	private boolean regMatchRec(CNode tempN, MyStringBuilder2 [] msb, String[] pats,
	 int indexPats, int level)
	{	
		levspace(level);
		boolean ans = false;
		System.out.println("Level " + level);
		levspace(level);
		System.out.println("Pat "+ indexPats +" contains "+ tempN.data + " ? "+patContains(pats[indexPats], tempN.data, 0));
		// Check the node to see if it matches the current pattern
		if (patContains(pats[indexPats], tempN.data, 0)) 
		{
			//levspace(level);
			// System.out.print("Match. ");
			msb[indexPats].append(tempN.data);
			res(msb, level);
			//If next node exists, then recurse forward using the same pat
			if(tempN != lastC)
			{
				levspace(level);
				System.out.println("Recursing to next node");
				ans = regMatchRec(tempN.next, msb, pats, indexPats, level+1);
			}
			else if (msb[msb.length-1].length() >0) 
			{	
				//Else then this is last node. Check if all pats matched
				return true;
			}

				levspace(level);
				System.out.println("Backtracked to level " + level);
			//If recursion failed, try same char w/next pat (if 1 exists) & curr pat has >=1 match
			if (!ans && indexPats < pats.length-1) 
			{
				if (msb[indexPats].length() >1) 
				{
					levspace(level);
					System.out.println("Pat " +indexPats +" has "+ msb[indexPats].length() + " matches");
					res(msb, level);
					levspace(level);
					System.out.println("Unmatch " +tempN.data +" from Pat " + indexPats);
					msb[indexPats].deleteCharAt(msb[indexPats].length()-1);
					res(msb, level);
					levspace(level);
					System.out.println("Check " +tempN.data +" w/Pat " + (indexPats+1));


					ans = regMatchRec(tempN, msb, pats, indexPats+1, level+1);
				}
				else
				{
					levspace(level);
					System.out.println("Unmatch " +tempN.data +" from Pat " + indexPats);
					msb[indexPats].deleteCharAt(msb[indexPats].length()-1);
					res(msb, level);

				}

				//If got back to first char then proceed forward if another char exists
				if (msb[0].length() == 0) //IndexPats must == 0 for this to be true
				{
					levspace(level);
					System.out.println("!Match, indexPats = 0, try next char if != lastC");
					if(tempN != lastC)
						ans = regMatchRec(tempN.next, msb, pats, indexPats, level+1);
					else
					{
						levspace(level);
						System.out.println("!Match, @ lastC, return false");
						return false;
					}
				}
			}
			
		}
		else if (indexPats == 0 && msb[0].length() == 0) //IndexPats must == 0 for this to be true
		{
			levspace(level);
			System.out.println("!Match, indexPats = 0, try next char if != lastC");
			if(tempN != lastC)
				ans = regMatchRec(tempN.next, msb, pats, indexPats, level+1);
			else
			{
				levspace(level);
				System.out.println("!Match, @ lastC, return false");
				return false;
			}
		}
		else if (msb[indexPats].length() >0 &&  indexPats < pats.length-1) 
		{
			levspace(level);
			System.out.println("!Match. Checking next Pat");
			//Pat has >= 1 match & there are more pats, check curr char w/next pat	
			ans = regMatchRec(tempN, msb, pats, indexPats+1, level+1);

		}
		else if (indexPats == pats.length-1 && msb[msb.length-1].length() >0) 
		{
			return true; //Char !match pat, but pat is LAST pat & has @ least 1 match
		}
		else if (msb[indexPats].length() == 0 && indexPats !=0) 
		{
			levspace(level);
			System.out.println("!Match. Pat "+ indexPats+" has 0 matches. Returning false");
			res(msb, level);
			return false; //Char !match pat & pat doesn't already have matches
		}
		levspace(level);
		System.out.println("Level " + level + " returns: " + ans);
		return ans;
	}
	
	// You must use this inner class exactly as specified below.  Note that
	// since it is an inner class, the MyStringBuilder2 class MAY access the
	// data and next fields directly.
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