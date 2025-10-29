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