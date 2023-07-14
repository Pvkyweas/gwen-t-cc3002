package cl.uchile.dcc
package gwent.Game.State

import gwent.Game.GameController

import cl.uchile.dcc.gwent.Deck.Deck
import cl.uchile.dcc.gwent.{IPlayer, Player}

/** A class that represent the initial state, the start state.
 *
 * @param controller whose is the state
 */
class StartState(controller: GameController) extends GameState(controller){

  override def getState: String = {"Start"}

  /**
   *  Method that starts the game, creates the computer player, and a new player if the player 1 is not defined
   *  also, tell to all player to draw the initial 10 cards. Finally transitions to RoundState
   */
  override def start: Unit = {

    // Create the player 1 if the it doesn't exist
    if (!controller.isPlayer1()) controller.addPlayer(createPlayer())

    // Create a new computer player
    controller.addIA(createIA())

    // The initial draw of 10 cards
    controller.initialDraw()

    // Starts rounds
    controller.startTurns
  }

  override def startTurns(p: IPlayer): Unit = {new TurnState(controller, p)}

  /**
   * Private method to create a computer player, use the deckfactory to create it's deck
   *
   * @return Computer player
   */
  private def createIA(): IPlayer = {
    val iaDeck: Deck = controller.createStandardDeck()
    new Player("Computadora", 2, iaDeck)
  }

  /**
   *  Private method to create the player 1 when this is not defined
   * @return The player 1
   */
  private def createPlayer(): IPlayer = {
    val playerDeck: Deck = controller.createStandardDeck()
    new Player("player", 2, playerDeck)
  }
}
