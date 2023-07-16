package cl.uchile.dcc
package gwent.Game.State

import gwent.Game.GameController

import cl.uchile.dcc.gwent.{Computer, IPlayer}

import scala.io.StdIn

/**
 *  A class that represents the turn state of the controller, this turn belongs to a player
 * @param controller The controller that has this state
 * @param player The player owner of the turn
 */
class TurnState(controller: GameController, private val player: IPlayer) extends GameState(controller) {

  override def getState: String = {s"Turn of: ${player.get_Name()}"}

  /**
   * Method for the states that are reached several times,
   * it serves so that they do the actions that correspond to them
   *
   * TurnState: -Draw 3 cards (or less if the player has over 7 cards)
   *            -Ask the player if wants to pass turn or play a card
   *            -If play a card -> new turn for the same player
   *            -Is pass -> turn of the next player o RoundState,
   *            it depends on whose turn it is
   */
  override def update(): Unit = {

    // Print the cards on board
    controller.Print()

    // shuffle its Deck
    player.shuffle()

    // To draw cards
    drawCardIfCan(player)

    var accion: Int = -1
    while (accion == -1) {
      println("Ingresa la acción que deseas realizar (número entero)")
      player.Print()
      println("0 => Pasar turno")
      val input = StdIn.readLine().toIntOption
      if (input.isDefined) {
        accion = input.get
        if (accion == 0) {
          player.passTurn()
        } else if (accion <= player.numCards_hand()) {
          player.playCard(accion - 1)
        } else {
          accion = -1
        }
      }
    }
  }

  protected def drawCardIfCan(p: IPlayer): Unit = {
    val amount: Int = controller.askCanDraw(player)
    if (amount > 0) {
      // Obtain how many cards the computer has
      val numCards: Int = player.numCards_hand()
      if (10 - numCards >= amount) player.drawCard(amount)
      else if (numCards != 10) player.drawCard(10 - numCards)
    }
  }

  /**
   * Method that transitions to the turn state of the same player,
   * only if the one who played the card is the same one who owns the turn
   * @param p Player who played the card
   */
  override def playCard(p: IPlayer): Unit = { if (p.equals(player)) new TurnState(controller, player)}

  /**
   * Transitions to the next turn that corresponds to the computer
   * @param pNextTurn The next turn's player (computer)
   */
  override def passTurn(pNextTurn: Option[Computer]): Unit = {new ComputerTurnState(controller, pNextTurn.get)}

}
