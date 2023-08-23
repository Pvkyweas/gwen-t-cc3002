package cl.uchile.dcc
package gwent.Card
package Effect

/** A class that represent an effect that does nothing
 *
 * @see IEffect
 * @author Israel Rodriguez
 * @since 1.2.3
 * @version 1.0
 */
class NoneEffect extends Effects with IEffect {

  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      super.equals(obj)
    } else {
      false
    }
  }

}
