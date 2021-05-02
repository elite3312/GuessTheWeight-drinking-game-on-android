package com.example.guesstheweight

import java.security.SecureRandom

class Deck {

    //var suits = arrayOf("Hearts", "Diamonds", "Clubs", "Spades")

    private lateinit var deck // array of Card objects
            : Array<Card?>
    private var currentCard // index of next Card to be dealt (0-51)
            = 0
    private val NUMBER_OF_CARDS = 52 // constant # of Cards

    // random number generator
    private val randomNumbers: SecureRandom = SecureRandom()

    // constructor fills deck of Cards
    init{
        deck = arrayOfNulls<Card>(NUMBER_OF_CARDS) // create array of Card objects

        currentCard = 0 // first Card dealt will be deck[0]

        // populate deck with Card objects
        for (count in deck.indices) deck[count] = Card((count % 13+1), (count / 13+1))//1-13,1-4,
    }



    // shuffle deck of Cards with one-pass algorithm
    fun shuffle() {
        // next call to method dealCard should start at deck[0] again
        currentCard = 0

        // for each Card, pick another random Card (0-51) and swap them
        for (first in deck.indices) {
            // select a random number between 0 and 51
            val second: Int = randomNumbers.nextInt(NUMBER_OF_CARDS)

            // swap current Card with randomly selected Card
            val temp = deck[first]
            deck[first] = deck[second]
            deck[second] = temp
        }
    }

    // deal one Card
    fun dealCard(): Card? {
        // determine whether Cards remain to be dealt
        return if (currentCard < deck.size) deck[currentCard++] // return current Card in array
        else null // return null to indicate that all Cards were dealt
    }
    fun isEmpty():Boolean{
        return currentCard>=deck.size
    }
    fun getCurrentCard():Int{return currentCard}
}