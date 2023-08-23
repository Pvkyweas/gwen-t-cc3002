package cl.uchile.dcc
package gwent

import cl.uchile.dcc.gwent.Card.ICard
import gwent.Board.ISection

import cl.uchile.dcc.gwent.Card.Unity.ICardUnity
import cl.uchile.dcc.gwent.Card.Weather.ICardWeather
import cl.uchile.dcc.gwent.Observer_Observable.IObservable

trait IPlayer extends IObservable with IPrintable{
  def playCard(pos_card: Int): Unit
  def drawCard(num_cards: Int): Unit
  def lostRound(): Unit
  def shuffle():Unit
  def numCards_hand(): Int
  def numCards_deck(): Int
  def get_gemCounter(): Int
  def get_Name(): String
  def get_Section(): String
  def get_HandCard(): Option[ICard]
  def passTurn(): Unit
  private[gwent] def set_Section(newSection: ISection): Unit
}
