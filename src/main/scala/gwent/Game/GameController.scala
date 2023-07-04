package cl.uchile.dcc
package gwent.Game

import gwent.Game.State.{GameState, StartState}

import cl.uchile.dcc.gwent.IPlayer
import cl.uchile.dcc.gwent.Observer_Observable.IObserver
import cl.uchile.dcc.gwent.Observer_Observable.Notifications.INotification

class GameController() extends IObserver{
  var state: GameState = new StartState(this)

  val deckFactory: DeckFactory = new DeckFactory()

  // Player
  // val player: IPlayer

  // Computer
  // val computer: IPlayer

  def getState: String = {state.getState}

  def startGame: Unit = {state.start}

  def playCard: Unit = {state.playCard}

  def passTurn: Unit = {state.passTurn}

  def startTuns: Unit = {state.startTurns}

  def playerLoss(whoLoss: IPlayer): Unit = {}

  // Observer's method
  def getNotification(content: INotification): Unit = {content.readAboutWhoLoss(this)}
}
