package com.example.blani.higherorlowerdrinkingapp
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import java.util.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var deckOfCards = mutableListOf<String>()
    private var numberOfShots = 0
    private var numberOfCardsLeft = 52


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startGame(view: View){

        //reset number of shots
        numberOfShots = 0
        textView2.text = "Shots to drink: " + numberOfShots

        //inicialize deck of cards and shuffle them
        deckOfCards = mutableListOf("C2", "C3", "C4", "C5", "C6", "C7", "C7", "C8", "C9", "C10", "C11", "C12", "C13", "C14",
        "D2", "D3", "D4", "D5", "D6", "D7", "D7", "D8", "D9", "D10", "D11", "D12", "D13", "D14",
        "H2", "H3", "H4", "H5", "H6", "H7", "H7", "H8", "H9", "H10", "H11", "H12", "H13", "H14",
        "S2", "S3", "S4", "S5", "S6", "S7", "S7", "S8", "S9", "S10", "S11", "S12", "S13", "S14")
        deckOfCards.shuffle()

        //get first card from the deck

        var card = deckOfCards.removeAt(0)
        var suit = card.substring(0,1)
        var number = card.substring(1)

        //show it in the app
        var drawableInt = stringSuitToSymbol(suit)
        number = cardNumberToCardName(number)

        imageView2.setImageResource(drawableInt)
        textView.text = number

        //update number of cards
        numberOfCardsLeft = 51
        textView3.text = "Number of cards left: " + numberOfCardsLeft

    }

    fun checkIfLower(view: View) {
        if(deckOfCards.isEmpty()){
            //when all cards are used, the game is finished
            Toast.makeText(this, "End of deck! End of game!", Toast.LENGTH_SHORT).show()
        }
        else{
            //let's draw next card
            var newCard = deckOfCards.removeAt(0)
            var newCardNo = newCard.substring(1)
            var newCardSuit = newCard.substring(0,1)
            var existingCardNo = textView.text.toString()
            existingCardNo = cardNameToCardNumber(existingCardNo)

            //compare existing and new card
            if(newCardNo.toInt() > existingCardNo.toInt()){
                //if it was higher - player made a wrong guess - is drinking
                Toast.makeText(this, "You're drinking!", Toast.LENGTH_SHORT).show()
                //we control number of drunk shots
                ++numberOfShots
                textView2.text = "Shots to drink: " + numberOfShots
            }
            else{
                //if it was lower or equal - player made good guess - no punishment
                Toast.makeText(this, "Sorry! No shot for you!", Toast.LENGTH_SHORT).show()
            }

            //display the card and update number of cards
            imageView2.setImageResource(stringSuitToSymbol(newCardSuit))
            textView.text = cardNumberToCardName(newCardNo)
            --numberOfCardsLeft
            textView3.text = "Number of cards left: " + numberOfCardsLeft


        }
    }

    fun checkIfHigher(view: View) {
        if(deckOfCards.isEmpty()){
            //when all cards are used, the game is finished
            Toast.makeText(this, "End of deck! End of game!", Toast.LENGTH_SHORT).show()
        }
        else{
            //let's draw next card
            var newCard = deckOfCards.removeAt(0)
            var newCardNo = newCard.substring(1)
            var newCardSuit = newCard.substring(0,1)
            var existingCardNo = textView.text.toString()
            existingCardNo = cardNameToCardNumber(existingCardNo)

            //compare existing and new card
            if(newCardNo.toInt() < existingCardNo.toInt()){
                //if it was lower - player made a wrong guess - is drinking
                Toast.makeText(this, "You're drinking!", Toast.LENGTH_SHORT).show()
                //we control number of drunk shots
                ++numberOfShots
                textView2.text = "Shots to drink: " + numberOfShots
            }
            else{
                //if it was higher or equal - player made good guess - no punishment
                Toast.makeText(this, "Sorry! No shot for you!", Toast.LENGTH_SHORT).show()
            }

            //display the card and update number of cards
            imageView2.setImageResource(stringSuitToSymbol(newCardSuit))
            textView.text = cardNumberToCardName(newCardNo)
            --numberOfCardsLeft
            textView3.text = "Number of cards left: " + numberOfCardsLeft

        }
    }


    private fun stringSuitToSymbol(suit: String) : Int{
        var drawableInt = 0
        when(suit){
            "C" -> drawableInt = R.drawable.club
            "D" -> drawableInt = R.drawable.diamond
            "H" -> drawableInt = R.drawable.hearts
            "S" -> drawableInt = R.drawable.spade
        }
        return drawableInt
    }

    private fun cardNumberToCardName(stringNumber: String) : String {
        when(stringNumber){
            "11" -> return "W"
            "12" -> return "Q"
            "13" -> return "K"
            "14" -> return "A"
            else -> return stringNumber
        }
    }

    private fun cardNameToCardNumber(name: String) : String{
        when(name){
            "W" -> return "11"
            "Q" -> return "12"
            "K" -> return "13"
            "A" -> return "14"
            else -> return name
        }
    }

}

