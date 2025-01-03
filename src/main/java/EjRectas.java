import rectas.*;

import java.util.Optional;

public class EjRectas {
    public static void main(String[] args) {
        try {
            // Cálculo del área de un triángulo conociendo los tres puntos del
            // plano que lo delimitan

            // Coordenadas del Punto p1
            double p1x = Double.parseDouble(args[0]);
            double p1y = Double.parseDouble(args[1]);
            // Coordenadas del punto p2
            double p2x = Double.parseDouble(args[2]);
            double p2y = Double.parseDouble(args[3]);
            // Coordenadas del Punto p3
            double p3x = Double.parseDouble(args[4]);
            double p3y = Double.parseDouble(args[5]);

            // Creamos los tres puntos
            Punto p1 = new Punto(p1x, p1y);
            Punto p2 = new Punto(p2x, p2y);
            Punto p3 = new Punto(p3x, p3y);
            // Calculamos los vectores p1p2 y p1p3
            Vector p1p2 = new Vector(p2x - p1x, p2y - p1y);
            Vector p1p3 = new Vector(p3x - p1x, p3y - p1y);
            // Calculamos el vector perpendicular a p1p2
            Vector perpendicular = p1p2.ortogonal();
            // Calculamos el área del triángulo
            double areaTresPuntos = (perpendicular.x() * p1p3.x() + perpendicular.y() * p1p3.y()) / 2;
            System.out.println("El área del triángulo conociendo tres puntos del plano es: " + areaTresPuntos);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error: debes introducir seis argumentos.");
        } catch (NumberFormatException e) {
            System.err.println("Error: los argumentos deben ser numéricos.");
        }

        // Cálculo del área de un triángulo a partir
        // de la intersección de dos rectas
        // El triángulo tiene su base sobre el eje X
        Punto p4 = new Punto(2, 0);
        Punto p5 = new Punto(5, 6);
        Punto p6 = new Punto(5, 0);
        Punto p7 = new Punto(3, 8);
        // Recta r1: 2x - y - 4 = 0
        Recta r1 = new Recta(new Vector(p4, p5), p4);
        // Recta r2: 4x + y - 20 = 0
        Recta r2 = new Recta(new Vector(p6, p7), p6);
        // Calculamos la base como la distancia entre los puntos de ambas rectas que cortan al eje x
        double base = r1.punto().distancia(r2.punto());
        // Calculamos el punto de intersección entre las dos rectas
        Optional<Punto> interserccion = r1.interseccionCon(r2);
        // La componente y de dicho punto es la altura del triángulo
        double altura = interserccion.get().y();
        // Calculamos el área de dicho triángulo
        double areaTrianguloRectas = base * altura / 2;
        System.out.println("El área del triángulo a partir de la intersección de dos rectas es: " + areaTrianguloRectas);
    }
}