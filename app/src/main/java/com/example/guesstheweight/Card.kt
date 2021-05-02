package com.example.guesstheweight

class Card constructor(face: Int, suit:Int) {
    private var face // face of card ("Ace", "Deuce", ...)
            : Int? = null
    private var suit // suit of card ("Hearts", "Diamonds", ...)
            : Int? = null

    // two-argument constructor initializes card's face and suit

    init {
        this.face = face
        this.suit = suit
    }

    fun getFace(): Int? {
        return face
    }
    fun getSuit(): Int? {
        return suit
    }
}