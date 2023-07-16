package cl.uchile.dcc
package gwent.Board

import gwent.Card.Unity.RangeCard

/** A class that represent a Zone for range cards
 *
 *
 * @see IZone, AbstractUnityZone
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.0
 */
class RangeZone extends AbstractUnityZone[RangeCard] {

  def Print(): Unit = {
    PrintCards("Zona Rango")
  }
}
