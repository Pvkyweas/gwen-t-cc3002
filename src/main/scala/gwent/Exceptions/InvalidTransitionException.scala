package cl.uchile.dcc
package gwent.Exceptions

class InvalidTransitionException(msg: String) extends Exception(s"Start can not transition to $msg")
