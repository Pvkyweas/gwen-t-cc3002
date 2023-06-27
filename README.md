# Gwen't

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

Context
-------

This project's goal is to create a (simplified) clone of the
[_Gwent_](https://www.playgwent.com/en)card game developed by [_CD PROJEKT RED_](https://cdprojektred.com/en/)

---

## Explicación Tarea 2 entrega final

### Jugador

El jugador tiene un nombre, un contador de gemas (que no puede bajar de 0), un mazo de cartas de tipo Deck, una mano de cartas de tipo HandDeck. Tambien tiene una variable para la sección del tablero y otra para el tablero en si, esto es para usar las funciones de agregar las cartas al tablero.

### Mazos

Decidi hacer clases distintas para representar mazos que contendran las cartas de las manos y las del mazo, esto debido a que presentan las siguientes diferencias:

* En un mazo si importa el orden
* En un mazo la carta que se saca es la primera a diferencia de la mano, en donde se puede sacar de cualquier posición
* Tiene sentido barajar un mazo, pero no una mano (dado el tipo de juego)

Para los mazos normales utilice un Stack y para la mano un ListBuffer ya que cumplen con el requerimiento del orden. Decidi no implementar una interface por que una clase abstracta se ajusta mejor a la situación ambas clases no tendrian exactamente los mismos metodos por lo que, al crear un objeto de tipo mazo, si los metodos que quiera utilizar no estan en la interface, no podria llamarlos (o almenos eso me pasaba) entonces, como de todas formas no iba a crear los objetos como un tipo "el nombre de la interface", la clase abstracta termina funcionando mejor.

### Cartas

Siguiendo el feedback de la tarea 1, cambie como estan implementadas las cartas, ahora estas se separan en cartas de unidad y cartas de clima que no heredan de una misma clase abstracta, si implementan la interfaz ICard.

* Unidad: Estas implementan la interfaz ICardUnity que extiende de ICard e implementa los metodos en cuestión, se divide en 3 tipos de cartas MeleeCard, RangeCard y SiegeCard.

* Clima: Esta hereda de la clase abstracta AbstractCardWeather, no se divide en distintos tipos de cartas de clima como las de unidad, por que este tipo de cartas solo puede ir en una zona y los efectos decidi implementarlos de forma que sean objetos asi que no me hace sentido tener una clase para cada tipo de clima.

Para las cartas de unidad, las funciones que afectan al valor de la fuerza es privada y solo visible en el paquete Card.

### Tablero

Para la implementación del tablero, considere distintas opciones pero decidi que el tablero solo contendria una zona para las cartas de clima y Secciones para cada jugador. Tambien tiene 2 variables de tipo Boolean para saber si una sección esta asignada, estas se asignan utilizando una función del tablero que recibe un objeto de tipo IPlayer, de forma que me soluciona el problema de que 2 jugadores distintos puedan colocar cartas en la misma zona.

* Zonas
   Se pensaron 4 zonas, la de clima y las que contienen cartas de unidad. Para la de clima, como solo puede haber una carta se creo una variable que sea de tipo AbstractWeatherCard, en cuanto a las zonas de unidad (Melee, Range y Siege) estas tendrian un ListBuffer que contenga las cartas. Para las zonas de unidad se creo una clase abstracta AbstracUnityZone que contenga la implementación de los metodos, de esta clase solo heredan MeleeZone, RangeZone y SiegeZone.

* Secciones
   Para la implementación de las secciones, estas tendrian 3 zonas, una para las cartas cuerpo a cuerpo (melee), una para las rango y otra para las de asedio, tiene metodos especificos para agregar cartas a cada zona, esto se hizo por medio de double dispatch.

### Efectos

Para el funcionamiento de los efectos se considero que cada uno de los efectos tendria una expresión de este, esta expresión esta formada por objetos IOperation en donde estas operaciones estan encadenadas una a la otra. Dentro de las operaciones, las que se aplican solo a ciertas lineas especificas fueron hechas siguiendo el patron de diseño de Visitor ya que fue la mejor forma que se me ocurrio de saber si son de una linea o no, para la operación que se aplica a las cartas de la misma linea que la fuente del efecto solo se checkeo que la carta de origen del efecto estuviese en las cartas objetivo puesto que todos los efectos se ejecutan luego de haber añadido la carta.

En cuanto a cuando se aplica el efecto de la carta que se añadió, como mencione antes, este se aplica despues de que la carta es añadida y para que las demás cartas sepan de que una carta fue añadida, se siguio el patrón de diseño Observer, en donde la zona de clima es observada por el tablero, el tablero es observado por las secciones y estas son observadas por las zonas de unidad además estas últimas tambien son observadas por las secciones, de forma que:

* Cuando se añade una carta de clima, la zona de clima le notifica al tablero y este le notifica a las secciones y a su vez, estas notifican a sus zonas de unidad luego cada zona de unidad le dice a sus cartas que ha llegado la hora de aplicar un efecto.
* Cuando se añade una carta de unidad, cada zona de unidad le notifica a su sección y estas le notifican a todas las zonas de unidad que tenga para luego las zonas de unidad le digan a sus cartas que ha llegado la hora de aplicar un efecto

Tanto los efectos como las operaciones tienen un versión que hace nada, NoneEffect para los efectos y NoOperation para las operaciones.

### Como se juegan las cartas.

Cuando un jugador quiere jugar una carta, se siguen los siguiente pasos:
1. El jugador roba una carta y le dice que se juege con la función playYourself de las cartas de tipo ICard y se pasaran a si mismos como input.
2. Las cartas llamaran al jugador y utilizaran el metodo sobrecargado playMe del jugador pasandose a si mismas como input, si la carta es de clima (AbstractCardWeather) entonces el jugador llamara al metodo playOnBoard y pasara como input el parametro Board, si es una carta de unidad (ICardUnity) entonces llamara al metodo playOnSection y pasara su sección como parametro.
3. Las cartas llamaran al metodo correspondiente de la sección o el tablero según corresponda para agregarse, addOnW para las de clima y addOnMelee, addOnRange o addOnSiege para las de unidad.

### Diagrama UML de la implementación

![UML](https://github.com/dcc-cc3002/gwen-t-Pvkyweas/assets/112279911/b3011171-5543-4069-a310-382c606444b4)

### Diagrama de estados

![image](https://github.com/dcc-cc3002/gwen-t-Pvkyweas/assets/112279911/27d3470a-c296-4c52-ab7f-ee2c8ba83cc1)

* InicioPartida: Aqui se realizan las acciones que inician la partida, como crear los mazos y robar las 10 cartas.
* estadoRonda: Se ve si se cumple las condiciones de termino de la partida, indica a cada jugar si perdio la ronda.
* turnos: Según de quien sea el turno, este podra pasar turno o jugar una carta, cada vez que su turno inicie, se le da la posibilidad de robar hasta 3 cartas.
* FinPartida: La partida se termina, se indica al ganador y perdedor.
