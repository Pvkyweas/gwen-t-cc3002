package cl.uchile.dcc
package gwent.Board

import gwent.Card.Unity.MeleeCard

/** A class that represent a Zone for melee cards
 *
 *
 * @see IZone, AbstractUnityZone
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.0
 */
class MeleeZone extends AbstractUnityZone[MeleeCard]{

  def Print(): Unit = {
    PrintCards("Zona Melee")
  }
}
