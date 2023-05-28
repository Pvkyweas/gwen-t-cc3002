package cl.uchile.dcc
package gwent.Card.Effect

class NoneEffect extends IEffect{
  def get_effect(): String = "No tiene efecto"

  override def equals(obj: Any): Boolean = {
    if (this.getClass.getName == obj.getClass.getName) {
      val oEffect = obj.asInstanceOf[IEffect]
      this.get_effect() == oEffect.get_effect()
    } else {
      false
    }
  }

}
