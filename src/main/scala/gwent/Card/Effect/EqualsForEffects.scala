package cl.uchile.dcc
package gwent.Card.Effect

abstract class EqualsForEffects extends IEffect {
  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      val oEffect = obj.asInstanceOf[IEffect]
      this.get_effect() == oEffect.get_effect()
    } else {
      false
    }
  }
}
