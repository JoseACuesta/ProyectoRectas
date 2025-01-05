/// @author: José Antonio Cuesta Burgos
/// @author: Víctor Miguel Gallardo Fuentes

package rectas;

import static java.lang.Math.*;

public record Punto(double x, double y) {

    /// Crea un punto en el origen de coordenadas
    public Punto() {
        this(0, 0);
    }

    /// Calcula la distancia entre el receptor `this` y el punto `p`
    /// @param p El Punto `p`
    public double distancia(Punto p) {
        return sqrt(pow(p.x - this.x, 2) + pow(p.y - this.y, 2));
    }
}
