# 🐞 Práctica: Depuración de código en Eclipse  
## Proyecto: `DepuracionErrores`

---

## 1️⃣ Descripción de los errores encontrados

Durante la ejecución y depuración del código original, se detectaron los siguientes problemas:

| Tipo de error | Descripción | Consecuencia |
|----------------|--------------|---------------|
| **Lógico (bucle)** | El bucle `while (contador <= numAlumnos)` pedía una nota de más ya que contador empezaba desde 0 en vez de 1. | Si el usuario introducía 3 alumnos, el programa pedía 4 notas. |
| **Validación** | Las notas fuera del rango 0..10 se detectaban, pero se sumaban igualmente. | Notas incorrectas afectaban la media final. |
| **Cálculo** | La media se dividía por `numAlumnos`, incluso si se habían introducido notas inválidas o de más. | La media resultante era errónea. |
| **Presentación** | Se mostraba "alumno 0" porque el contador empezaba en 0. | Falta de claridad para el usuario. |

---

## 2️⃣ Cómo se detectaron los errores

Durante la depuración en Eclipse se utilizaron los siguientes **puntos de control (breakpoints)** y vistas:

- **Breakpoint 1:** en la lectura de la nota → permitió comprobar el valor de `contador` y detectar que se pedía una nota extra.
- **Breakpoint 2:** en la línea `suma = suma + nota;` → mostró que las notas inválidas también se sumaban.
- **Vista Variables:** evidenció el crecimiento incorrecto de `contador` y la suma de notas erróneas.
- **Vista Console:** confirmó que se imprimía un alumno adicional y que las notas fuera de rango seguían siendo consideradas.
- **Breakpoint condicional:** se configuró para detener la ejecución **solo cuando la nota fuera inválida**, lo que ayudó a verificar la validación.

---

## 3️⃣ Cambios realizados para corregir los errores

| Problema | Solución aplicada |
|-----------|------------------|
| Bucle pedía una nota de más (`int contador = 0`) | Reescrito para **empezar con el alumno 1** `int contador = 1;`. |
| Se aceptaban notas inválidas | Añadido un **condicional doble `if...else`** que se asegura que la nota es válida para introducirla en suma y aumentar el valor de contador. |
| Media incorrecta | Ahora la suma solo incluye notas válidas y se divide por el número correcto de alumnos. |
| Código poco claro | Simplificado y mejor estructurado. |

---

## 3️⃣.1️⃣ Código corregido

```java
package tema2_debug;

import java.util.Scanner;

public class MediaNotas {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int contador = 1; //DVA20252010: la variable tenía el valor 0 y debe de tener el valor 1 al principio, para poder empezar a contar desde el alumno 1
        double suma = 0;

        System.out.print("Introduce el número de alumnos: ");
        int numAlumnos = sc.nextInt();

        while (contador <= numAlumnos) {
            System.out.print("Introduce la nota del alumno " + contador + ": ");
            double nota = sc.nextDouble();

            if (nota < 0 || nota > 10) {//DVA20252010: Aquí debemos de abrir las llaves del if para luego poner un else if que haga la suma de notas y el aumento del valor de la variable contador, ya que si se introduce un valor inválido, se sigue añadiendo a la suma
                System.out.println("Error: la nota debe estar entre 0 y 10");
            }else { //DVA20252010: Aquí colocamos el else para evitar que valores no deseados se añadan a la nota y haga que el valor de la variable contador aumente
            	suma = suma + nota;
            	contador++;
            }
        }

        double media = suma / numAlumnos;
        System.out.println("La media del grupo es: " + media);

        sc.close();
    }
}
```
## 4️⃣ Breakpoint condicional

- **Ubicación:** línea donde se solicita la nota del alumno.

- **Condición configurada:**
```java
nota < 0 || nota > 10
```

- **Objetivo:** detener la ejecución solo cuando se introduce una nota inválida, permitiendo analizar el flujo y las variables en esos casos.

## 5️⃣ Expresiones en la vista Expressions

Durante la depuración se añadieron las siguientes expresiones para observar la evolución de las variables:

| Expresión | Descripción |
|------------------------|------------------|
| suma / (contador == 0 ? 1 : contador) | Calcula la media parcial en cada iteración. |
| suma | Muestra el acumulado de notas válidas. |
| contador | Muestra el alumno actual del que se está introduciendo ( o ya se ha introducido) la nota. |
	
Estas expresiones permitieron comprobar que la media se calculaba correctamente en cada paso.

## 6️⃣ Plantilla (Template) pedirnota

En Eclipse se creó la plantilla pedirnota desde
*Window → Preferences → Java → Editor → Templates → New...*

**Nombre:** pedirnota
**Contenido:**

```java
double nota;
do {
    System.out.print("Introduce la nota del alumno ${index}: ");
    nota = sc.nextDouble();
} while (nota < 0 || nota > 10);

${cursor}
```

Esta plantilla permite insertar fácilmente el bloque de código para solicitar notas válidas en futuros programas.
