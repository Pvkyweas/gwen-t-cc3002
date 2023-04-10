package cl.uchile.dcc
package gwent

import munit.FunSuite

class CardUnityTest extends FunSuite {
  var carta_prueba: ICard = _
  var carta_prueba2: ICard = _
  var cMele_prueba: ICard = _
  var cDistancia_prueba: ICard = _
  var cAsedio_prueba: ICard = _

  override def beforeEach(context: BeforeEach): Unit = {
    carta_prueba = new CardUnity("Prueba","tipoPrueba",3, "Refuerzo moral")
    carta_prueba2 = new CardUnity("Prueba2","tipoPrueba2",1, "Vinculo estrecho")
    cMele_prueba = new CardUnity("meleprueba","cuerpo a cuerpo",2)
    cDistancia_prueba = new CardUnity("distanciaprueba", "distancia", 2)
    cAsedio_prueba = new CardUnity("asedioprueba", "asedio", 2)
  }
  
  test("Deberia poder obtenerse el nombre"){
    assertEquals(carta_prueba.get_Name(),"Prueba")
  }

  test("Deberia poder obtenerse el valor de fuerza de la carta") {
    assertEquals(carta_prueba.get_Force(),3)
  }

  test("Deberia poder obtenerse el efecto de la carta") {
    assertEquals(carta_prueba.get_Effect(), "Refuerzo moral")
  }

  test("Deberia poder obtenerse la clasificacion de la carta") {
    assertEquals(carta_prueba.get_Classification(), "tipoPrueba")
  }

  test("Deberia poder obtenerse el requisito correcto según el efecto") {
    assertEquals(carta_prueba.get_Requirement(), "tipoPrueba")
    assertEquals(carta_prueba2.get_Requirement(), "Prueba2")
  }

  test("Se les puede aplicar el efecto: Refuerzo moral si son de la misma fila") {
    carta_prueba.effectApply("Refuerzo moral", "tipoPrueba")
    carta_prueba2.effectApply("Refuerzo moral", "tipoPrueba")

    assertEquals(carta_prueba.get_Force(),4)
    assertEquals(carta_prueba2.get_Force(),1)
  }

  test("Se les puede aplicar el efecto: Vinculo estrecho si tienen el mismo nombre") {
    carta_prueba.effectApply("Vinculo estrecho", "Prueba")
    carta_prueba2.effectApply("Vinculo estrecho", "Prueba")

    assertEquals(carta_prueba.get_Force(), 6)
    assertEquals(carta_prueba2.get_Force(), 1)
  }

  test("Se les puede aplicar los efectos de las cartas de clima") {
    cMele_prueba.effectApply("Escarcha mordiente", "Clima")
    cDistancia_prueba.effectApply("Escarcha mordiente", "Clima")
    cAsedio_prueba.effectApply("Escarcha mordiente", "Clima")

    assertEquals(cMele_prueba.get_Force(),1)
    assertEquals(cDistancia_prueba.get_Force(),2)
    assertEquals(cAsedio_prueba.get_Force(),2)

    cMele_prueba.effectApply("Niebla impenetrable", "Clima")
    cDistancia_prueba.effectApply("Niebla impenetrable", "Clima")
    cAsedio_prueba.effectApply("Niebla impenetrable", "Clima")

    assertEquals(cMele_prueba.get_Force(), 2)
    assertEquals(cDistancia_prueba.get_Force(), 1)
    assertEquals(cAsedio_prueba.get_Force(), 2)

    cMele_prueba.effectApply("Lluvia torrencial", "Clima")
    cDistancia_prueba.effectApply("Lluvia torrencial", "Clima")
    cAsedio_prueba.effectApply("Lluvia torrencial", "Clima")

    assertEquals(cMele_prueba.get_Force(), 2)
    assertEquals(cDistancia_prueba.get_Force(), 2)
    assertEquals(cAsedio_prueba.get_Force(), 1)

    cMele_prueba.effectApply("Clima despejado", "Clima")
    cDistancia_prueba.effectApply("Clima despejado", "Clima")
    cAsedio_prueba.effectApply("Clima despejado", "Clima")

    assertEquals(cMele_prueba.get_Force(), 2)
    assertEquals(cDistancia_prueba.get_Force(), 2)
    assertEquals(cAsedio_prueba.get_Force(), 2)
  }

}
