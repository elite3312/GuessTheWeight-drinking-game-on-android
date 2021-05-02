package com.example.guesstheweight

import android.content.Intent
import android.content.pm.ActivityInfo
import android.hardware.biometrics.BiometricPrompt
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text


class Game_Activity : AppCompatActivity() {
    lateinit var guess_prompt: TextView
    lateinit var deck_size: TextView
    lateinit var drawn_face: TextView
    lateinit var stake_size: TextView
    lateinit var drawn_img:ImageView
    lateinit var stake1_img:ImageView
    lateinit var stake2_img:ImageView
    lateinit var stake3_img:ImageView
    lateinit var stake4_img:ImageView
    lateinit var stake5_img:ImageView
    lateinit var stake6_img:ImageView
    lateinit var deck_img:ImageView
    lateinit var bigger: Button
    lateinit var smaller: Button
    lateinit var player_turn:TextView
    lateinit var deck:Deck
    lateinit var card:Card
    lateinit var cardTemp:Card/*temporary card holder*/
    var stakeCount :Int = 0/*number of cards in stake*/
    var resId=resId()/*to access picture resource*/
    var playerNum:Int=4/*number of players*/
    var currentTurn:Int=1/*whose turn*/
    var guessCorrectCombo:Int=0/*must reach 2 to switch player*/

    companion object {
        val EXTRA_IDS = "EXTRA_IDS"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        deck= Deck()
        deck.shuffle()/*shuffle initializes deck*/
        deck_size = findViewById(R.id.deck_size)
        guess_prompt = findViewById(R.id.guess_prompt)
        drawn_face=findViewById(R.id.face)
        stake_size=findViewById(R.id.stake_txt)
        drawn_img=findViewById(R.id.drawn_img)
        stake1_img=findViewById(R.id.stake1_img)
        stake2_img=findViewById(R.id.stake2_img)
        stake3_img=findViewById(R.id.stake3_img)
        stake4_img=findViewById(R.id.stake4_img)
        stake5_img=findViewById(R.id.stake5_img)
        stake6_img=findViewById(R.id.stake6_img)
        deck_img=findViewById(R.id.deck_img)

        bigger=findViewById(R.id.bigger)
        bigger.setOnClickListener{ bigger() }

        smaller=findViewById(R.id.smaller)
        smaller.setOnClickListener { smaller() }
        player_turn=findViewById(R.id.player)
        player_turn.setText("player:1")
        drawFirstCard()/*ever turn requires drawing a card first*/

    }
    fun compareCards(card1:Card,card2:Card):Boolean?{
        if(card1.getFace()==card2.getFace())return true
        else return card1.getFace()!! <card2.getFace()!!/*if card 2's face is bigger, then return true*/
    }

    fun drawFirstCard(){

        bigger.setEnabled(false)
        smaller.setEnabled(false)
        guess_prompt.setText("drawing first card")
        guess_prompt.setVisibility(android.view.View.VISIBLE)
        object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000)
            }

            override fun onFinish() {
                //mTextField.setText("done!")
                card= deck.dealCard()!!
                if(deck.isEmpty()){deck.shuffle()}
                stakeCount=0
                updateStake()
                updateDeckSize()
                updateDrawn()
                guess_prompt.setVisibility(android.view.View.INVISIBLE)
                bigger.setEnabled(true)
                smaller.setEnabled(true)
            }
        }.start()

    }

    fun updateDeckSize(){/*update textview of decksize*/
        deck_size.setText("decksize="+(52-deck.getCurrentCard()).toString())
        deck_size.invalidate()
    }
    fun updateStake(){/*adds 1 to stake, shows up to 6 cards*/
        stakeCount+=1
        stake_size.setText("stake="+(stakeCount).toString())
        if (stakeCount==1){stake1_img.setImageResource(resId.getID(card.getFace()!!,card.getSuit()!!))
        }
        else if(stakeCount==2){stake2_img.setImageResource(resId.getID(card.getFace()!!,card.getSuit()!!))}

        else if(stakeCount==3)stake3_img.setImageResource(resId.getID(card.getFace()!!,card.getSuit()!!))

        else if(stakeCount==4)stake4_img.setImageResource(resId.getID(card.getFace()!!,card.getSuit()!!))

        else if(stakeCount==5)stake5_img.setImageResource(resId.getID(card.getFace()!!,card.getSuit()!!))

        else if(stakeCount==6)stake6_img.setImageResource(resId.getID(card.getFace()!!,card.getSuit()!!))


    }
    fun clearStake(){
        stakeCount=0
        stake1_img.setImageResource(android.R.color.transparent)
        stake2_img.setImageResource(android.R.color.transparent)
        stake3_img.setImageResource(android.R.color.transparent)
        stake4_img.setImageResource(android.R.color.transparent)
        stake5_img.setImageResource(android.R.color.transparent)
        stake6_img.setImageResource(android.R.color.transparent)
    }
    fun updateDrawn(){
        drawn_img.setImageResource(resId.getID(card.getFace()!!,card.getSuit()!!))
        drawn_face.setText(card.getFace().toString())
        drawn_face.invalidate()
    }
    fun startPenalty(stakeCount:Int){

        var intent: Intent = Intent(this@Game_Activity, penalty::class.java)
        intent.putExtra(EXTRA_IDS, stakeCount!!)
        startActivity(intent)
    }


    fun bigger(){
        takeGuess(false)
    }
    fun smaller(){
        takeGuess(true)
    }
    fun takeGuess(mode:Boolean){
        cardTemp=card/*hold a copy of current card on table*/
        card= deck.dealCard()!!/*peek the card at the top of the deck*/
        if(deck.isEmpty())deck.shuffle()
        updateDrawn()//put next card on table(revealing it to player)
        updateDeckSize()
        updateStake()

        if(compareCards(cardTemp,card)==mode){/*guess wrong*/
            guessCorrectCombo=0
            guess_prompt.setText("guess wrong")
            guess_prompt.setVisibility(android.view.View.VISIBLE)
            bigger.setEnabled(false)
            smaller.setEnabled(false)

            object : CountDownTimer(500, 500) {
                override fun onTick(millisUntilFinished: Long) {
                    //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000)
                }

                override fun onFinish() {
                    guess_prompt.setVisibility(android.view.View.INVISIBLE)
                    startPenalty(stakeCount)
                    clearStake()
                    drawFirstCard()

                }
            }.start()

        }
        else{
            guessCorrectCombo+=1
            if(guessCorrectCombo>=2){
                toNextPlayer()
                guessCorrectCombo=0
                guess_prompt.setText("guess correct, move to next player")
            }
            else guess_prompt.setText("guess correct")//one more correct guess to go
            guess_prompt.setVisibility(android.view.View.VISIBLE)
            object : CountDownTimer(1000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000)
                }

                override fun onFinish() {
                    guess_prompt.setVisibility(android.view.View.INVISIBLE)

                }
            }.start()
        }}

   fun toNextPlayer(){
       if(currentTurn<playerNum){
           currentTurn+=1
       }
       else if(currentTurn>=playerNum)currentTurn=1
       player_turn.setText("player:"+currentTurn.toString())
   }
}