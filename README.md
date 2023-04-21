# Gwen't

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

Context
-------

This project's goal is to create a (simplified) clone of the
[_Gwent_](https://www.playgwent.com/en)card game developed by [_CD PROJEKT RED_](https://cdprojektred.com/en/)

---

## Explicación Tarea 1 entrega final

### Implementación efectos de las cartas

Como mencioné en los commits, decidí cambiar cómo pensaba implementar el funcionamiento de los efectos de las cartas. Originalmente, iba a hacer que comparara el nombre del efecto y el requerimiento correspondiente, por lo que definí un método get_Requirement, pero lo pensé mejor y decidí que la mejor forma de implementarlo era codificando los efectos siguiendo la siguiente estructura:

* Tipo de requisito-Requisito-Como afecta-Cuanto afecta

Por ejemplo, para el efecto de Escarcha mordiente, el **requisito a cumplir** es ser de una **clasificación especifica**, específicamente, **requiere** que la carta sea **cuerpo a cuerpo** y el **como afecta** a la carta es **estableciendo el valor de fuerza** a **1**, por lo que el código sería cla-c-sf-1.

Creo que hacerlo de esta forma es mejor, ya que en vez de colocar un "if" para cada efecto, coloco uno por cada característica en común que tienen. Si nos fijamos en cómo funcionan los efectos de las cartas de clima, todas (menos Clima despejado) tienen como requisito la clasificación de la carta y el cómo afectan es el mismo. Incluso los efectos de cartas de unidad siguen esta estructura, por lo que no es necesario crear métodos para los efectos de clima y de unidad por separado.

Pero el mayor punto a favor es que es mucho más escalable que la implementación que tenía pensada anteriormente, ya que si quiero añadir un efecto que haga lo mismo que Refuerzo moral pero, en vez de añadir 1, añada 2, el código sigue funcionando sin requerir modificaciones. En caso de querer añadir un efecto que aplique una operación distinta a las implementadas, solo se necesita añadir dentro del "if" correspondiente un bloque que tenga la siguiente forma:
```
if (effect_code(2) == "operación deseada"){
  // Operación en cuestión
}
```

La codificación que estoy utilizando ahora mismo es:

**Tipo de requisito**
* cla: Clasificación de la carta
* nam: Nombre de la carta
* all: Afecta a todas las cartas

**Requisito**
* c: Cuerpo a cuerpo
* d: Distancia
* a: Asedio
* Si el requisito es de tipo nam, aqui iria el nombre de la carta

**Como afecta**
* sd: Establece el valor de fuerza a un valor especifico
* addf: Le añade una cantidad a la fuerza
* rf: Reestablece el valor de fuerza al anterior afectado por una carta de clima
* multf: Multiplica el valor de la fuerza por una cantidad

No esta demás mencionar que, cuando evaluo el tipo de requisito de clasificación, lo hago de la siguiente forma:
```
if (effect_code(0) == "cla" && effect_code(1).contains(classification))
```
Es por la misma idea de escalabilidad, ya que si quisiese añadir un efecto con un requisito que afecte a 2 tipos de clasificaciones, solo tendria que poner ambas codificaciones, por ejemplo, si quiero afectar tanto a distancia como asedio, el requisito seria "dc".

pd: perdón por el mucho texto, pero queria que quedara lo más claro posible unu, espero haberlo logrado.

### Implementación Mazos

Decidi hacer clases distintas para representar mazos que contendran las cartas de las manos y las del mazo, esto debido a que presentan las siguientes diferencias:

* En un mazo si importa el orden
* En un mazo la carta que se saca es la primera a diferencia de la mano, en donde se puede sacar de cualquier posición
* Tiene sentido barajar un mazo, pero no una mano (dado el tipo de juego)

Para los mazos normales utilice un Stack y para la mano un ListBuffer ya que cumplen con el requerimiento del orden. Decidi no implementar una interface por que una clase abstracta se ajusta mejor a la situación ambas clases no tendrian exactamente los mismos metodos por lo que, al crear un objeto de tipo mazo, si los metodos que quiera utilizar no estan en la interface, no podria llamarlos (o almenos eso me pasaba) entonces, como de todas formas no iba a crear los objetos como un tipo "el nombre de la interface", la clase abstracta termina funcionando mejor.
