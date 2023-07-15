package cl.uchile.dcc
package gwent.Game.State

import gwent.Game.GameController
import gwent.IPlayer

/**
 * A class that represents the turn state of the controller, this turn belongs to a computer
 * @param controller The controller that has this state
 * @param computer The computer owner of the turn
 */
class ComputerTurnState(controller: GameController, private val computer: IPlayer) extends TurnState(controller, computer){
  /**
   * Method for the states that are reached several times,
   * it serves so that they do the actions that correspond to them
   *
   * TurnState: -Draw 3 cards (or less if the player has over 7 cards)
   * -Ask the player if wants to pass turn or play a card
   * -If play a card -> new turn for the same player
   * -Is pass -> turn of the next player o RoundState,
   * it depends on whose turn it is
   */
  override def update(): Unit = {}

  /**
   * Method that transitions to the turn state of the same player, 
   * only if the one who played the card is the same one who owns the turn
   *
   * @param p Player who played the card
   */
  override def playCard(p: IPlayer): Unit = {
    if (p.equals(computer)) new ComputerTurnState(controller, computer)
  }

  /**
   * Transitions to the next state, since this is the computer's turn, it transitions to RoundState
   * @param pNextTurn The next turn's player (computer)
   */
  override def passTurn(pNextTurn: Option[IPlayer]): Unit = {
    new RoundState(controller)
  }
}
