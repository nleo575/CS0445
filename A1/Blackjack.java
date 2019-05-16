/*
	Nicolas Leo, nll21,PS: 3466098
	CS 445 Spring 2018 Assignment 1
*/

/**
	This class by implements a very primitive 2-person version of the card game blackjack.
	@author Nicolas Leo
*/
public class Blackjack 
{
	Card tempC; //Temporary card used throughout game play
	private	int decks,	//Number of decks that will be used in this game
				rounds,	//Number of rounds to be played
				temp;	//Temporary integer
	private RandIndexQueue <Card> shoe,		//Queue to hold cards in the shoe
						  		  discard;	//Queue to hold the discarded cards

	/** 
		A "one line" main here to create a Blackjack object.
		@param Argument[0] Integer: the number of decks that will be used in the game.
		@param Argument[1] Integer: the number of rounds that will be played.
	*/
	public static void main(String [] args)
	{	new Blackjack(args); }

	/**
		Constructor for a Blackjack object. The object is the main driver of the game.
		It will simulate a single player and a dealer. The program will play a simplified
		version of blackjack using a number of decks (specified in the command line) and 
		for a specific number of rounds (also specified in the command line)
		@param args[] Array of arguments passed in from the command line. The first
					  argument args[0] should be the number of decks that will be used
					  in the game. The second argument args[1] should be the number of 
					  rounds that will be played.
	*/
	public Blackjack(String[] args)
	{		
		decks = Integer.parseInt(args[0]); rounds = Integer.parseInt(args[1]);
		shoe = new RandIndexQueue<Card>(decks*52);
		discard = new RandIndexQueue<Card>(decks*15);
		//Discard queue initialized at around 29% of total capacity of the shoe

		//Loads the specified number of decks into the shoe
        for(int x = 0; x < decks; x++)
        {
            for(Card.Suits s: Card.Suits.values())
                for(Card.Ranks r: Card.Ranks.values())
                    shoe.offer(new Card(s, r));
        }

        shoe.shuffle();

        //Creates a dealer & player object from the private inner class Player
        Player dealer = new Player("Dealer");
        Player player = new Player("Player");
 
		System.out.println("Starting Blackjack with " + rounds+" rounds and "+
			decks + " decks in the shoe.\n\n");

		/*
			If there are less than 10 rounds to be played, the program executes a "trace"
			and certain output is displayed on the console. Conditional operators are used
			to keep output from displaying on games with more than 10 rounds.
		*/
		for (int r = 0; r < rounds; r ++)
		{
			System.out.print(rounds < 11 ? "Round " + r + " beginning\n":"");

			//The player & dealer start out with 2 cards
			for (int n = 0; n < 2; n++)
			{	player.draw(shoe.poll()); dealer.draw(shoe.poll()); }

			//The program shows the dealer's and player's hands if rounds < 11.
			System.out.print(rounds < 11?player + ": " + player.viewHand() +" \t: " 
					+ player.getValue() + "\n"
					+ dealer + ": " + dealer.viewHand() +" \t: " 
					+ dealer.getValue()+"\n":"");

			/*
				The following if-else block is the player and dealer decision structure.
				Game play follows the basic strategy set out on the assignment description 
				page. 

				After the initial 2 cards are dealt, if neither the player or the dealer 
				has blackjack (and there isn't a tie), then the player will draw until 
				his/her score is > 16. If the player busts, then the dealer wins. 
				
				If the dealer's score is <17, then the dealer will also draw cards until
				the score >16. If the dealer busts, then the player automatically wins.

				After drawing cards, if scores for both "players" are between [17,21],
				then it will compare their hands' values to determine who the winner is,
				or if there is a tie.
			*/

			//Blackjack
			if (dealer.getValue()  == 21 || player.getValue() == 21)
			{
				System.out.print(rounds < 11?"Result: ":"");

				if(dealer.getValue() == player.getValue())
				{
					System.out.print(rounds < 11?"Push!\n":"");
				}
				else 
				{
					if (dealer.getValue() == 21)
					{
						System.out.print(rounds < 11?"Dealer":"");
						dealer.won(); 
					}
					else
					{
						System.out.print(rounds < 11?"Player":"");
						player.won();
					}
					System.out.print(rounds < 11?" Blackjack wins!\n":"");
				}
			}
			else //No Blackjack, game continues
			{
				while (player.getValue() < 17)
				{
					tempC = shoe.poll(); //Dealer draws a card from the shoe
					System.out.print(rounds < 11?"Player hits: "+ tempC +"\n":"");
					player.draw(tempC); //Card presented to the player
				}

				if (player.getValue() > 21) //Player busts
				{
					System.out.print(rounds < 11?"Player BUSTS: " + player.viewHand()
						+" \t: "+ player.getValue() +"\nResult: Dealer Wins!\n":"");
					dealer.won();
				}
				else if (dealer.getValue() >= 17) //Player stands & dealer check's hand
				{								  //Both stand.
					System.out.print(rounds < 11?"Player STANDS: " + player.viewHand()  
						+" \t: " + player.getValue() + "\nDealer STANDS: "  
						+ dealer.viewHand() +" \t: " + dealer.getValue() + "\nResult: ":"");

					if (player.getValue() == dealer.getValue()) //Tie
					{
						System.out.print(rounds < 11?"Push!\n":"");
					}
					else 
					{
						if (player.getValue()>dealer.getValue()) //Player has larger value
						{	
							System.out.print(rounds < 11?"Player":"");
							player.won();
						}
						else //Dealer value > player's value. Dealer won.	 
						{	 
							System.out.print(rounds < 11?"Dealer":"");
							dealer.won(); 
						}
						System.out.print(rounds < 11?" Wins!\n":"");
					}
				}
				else //Player stands. Dealer's hand < 17, game continues
				{
					System.out.print(rounds < 11?"Player STANDS: " + player.viewHand() 
							+" \t: " + player.getValue()+"\n":"");

					while (dealer.getValue() < 17)
					{
						tempC = shoe.poll(); //Dealer draws a card from the shoe
						System.out.print(rounds < 11?"Dealer hits: "+ tempC+"\n":"");
						dealer.draw(tempC);	//Card added to the dealer's hand.
					}

					if (dealer.getValue() > 21)//Dealer busts
					{
						System.out.print(rounds < 11?"Dealer BUSTS: " + player.viewHand() + 
							" \t: " + dealer.getValue() + "\nResult: Player Wins!\n":"");
						player.won();
					}
					else  //Dealer stands & hands are compared.
					{	
						System.out.print(rounds < 11?"Dealer STANDS: " + dealer.viewHand() +
							" \t: " + dealer.getValue() + "\nResult: ":"");

						if (player.getValue() == dealer.getValue()) //Tie
						{	
							System.out.print(rounds < 11?"Push!\n":"");
						}
						else
						{
							if (dealer.getValue() > player.getValue()) //Dealer wins
							{	
								System.out.print(rounds < 11?"Dealer":"");
								dealer.won(); 
							}
							else //Player wins
							{
								System.out.print(rounds < 11?"Player":"");
								player.won();
							}
							System.out.print(rounds < 11?" Wins!\n":"");
						}
					} 
				} 
			}//End of game decision structure

			System.out.print(rounds < 11?"\n\n":"");

			//Cards are put into the discard pile after each round
			player.returnHand(); dealer.returnHand();

			//If shoe <= 1/4 its original size, discard is put back into shoe and cards
			//are shuffled
			if (shoe.size()<=decks*13)
			{
				System.out.print("Reshuffling the shoe in round "+ r +"\n\n");

				while(!discard.isEmpty())
				{
					shoe.offer(discard.poll());
				}

				shoe.shuffle();
			}
		}

		System.out.println("After " + rounds + " rounds, here are the results:"
							+"\n\tDealer Wins: " + dealer.getWins()
							+"\n\tPlayer Wins: " + player.getWins()
							+"\n\tPushes: " + 
							(rounds - dealer.getWins() - player.getWins()) + "\n");
							//Pushes = rounds - "dealer's" wins - "player's" wins.
	}

	/**
		The player class is a private inner class designed to simulate player and dealers
		of a card game. Each "player" has a hand of cards which he/she will draw from a 
		common "shoe" and return to a discard pile at the end of a round. The player
		object will also keep track of the value of the player's hand as well as basic 
		attributes, like wins/losses and their name.
	*/
	private class Player
	{	
		//Queue to hold the player's hand
		private RandIndexQueue <Card> hand = new RandIndexQueue<Card>(4);
		private String name; //The player's name or designation.
		private int aces=0, //keeps track of the number of aces in the player's hand.
					wins, 	//Number of times the player won a game. 
					value = 0;//Value is the points of the cards in the player's hand.
					//This value is based on the number of aces in the player's hand

		/**
			Constructor for the player object.
			@param name String containing the name distinction for this player object.
		*/
		public Player(String name)
		{ 	this.name = name; }

		/**
			Method to get the number value of the player's hand.
			@return Integer of the value of the player's hand.
		*/
		public int getValue()
		{	return value; }

		/**
			Method to get the number of rounds that the player won.
			@return Integer of the number of wins.
		*/
		public int getWins()
		{	return wins; }

		/**
			After a round is completed, this method will return all of the player's cards
			to the discard pile.
		*/
		public void returnHand()
		{
			temp = hand.size();
			for (int x = 0; x < temp; x++)
			{ discard.offer(hand.poll());}
			value = 0; aces = 0;
		}

		/**
			Method for drawing a card from the shoe/receiving a card from the dealer.
			The method will update the total value of the player's hand as well. The value
			will try to give the maximum value without going over 21.
		*/
		public void  draw(Card c)
		{
			if (1 == c.value2())
				aces++;
			hand.offer(c);
			
			//1st calculate hand total minus aces and store in temp.
			temp = 0; 
			for(int h = 0; h < hand.size(); h ++)
				temp += hand.get(h).value2(); 
			temp -= aces; //Subtracted out the value of any potential aces.
			//Temp will be 0 if all cards in the hand are aces

			if (aces == 0) //No aces
			{ 	
				value = temp;
			}
			else if(temp + 11 + (aces - 1) <=21) //Keep 1 ace high & see if it causes bust
			{
				value = temp + 11 + (aces - 1); //Temp = 0 if all cards are aces
			}
			else //Keep all aces low, regardless if it causes the hand to bust
			{
				value = temp + aces; 
			}
		}

		/**
			Method to return a string of the player's name. 
		*/
		public String toString()
		{	return name; }

		/**
			Method to update the count of rounds that the player won.
		*/
		public void won()
		{	wins++; }

		/**
			Method to view the player's current hand.
			@return RandIndexQueue <Card> containing the player's current hand.
		*/
		public RandIndexQueue <Card> viewHand()
		{	return hand; }
	}
}