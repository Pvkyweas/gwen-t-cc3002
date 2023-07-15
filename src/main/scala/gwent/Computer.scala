package cl.uchile.dcc
package gwent

import gwent.Deck.Deck

class Computer(private val name: String,
               private var gem_counter: Int = 2,
               private val deck_cards: Deck) extends Player(name, gem_counter, deck_cards){

}
