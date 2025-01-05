/// @author: José Antonio Cuesta Burgos
/// @author: Víctor Miguel Gallardo Fuentes

package rectas;

import java.util.Optional;

public record Recta(Vector vectorDirector, Punto punto) {

    // record Implicita anidado
    record Implicita(double a, double b, double c){};

    /// Crea una recta conocienco dos puntos por donde pasa
    public Recta(Punto p1, Punto p2) {
        this(new Vector(p1, p2), p1);
    }

    /// Dos rectas son paralelas si lo son sus vectores de dirección
    /// @param r Una recta
    public boolean esParalelaA(Recta r) {
        return r.vectorDirector.esParaleloA(vectorDirector);
    }

    /// Una recta pasa por un punto `p` si el vector formado por `p` y un punto de la recta
    /// es paralelo al 'vectorDirector` de esta
    /// @param p Un punto
    public boolean contieneA(Punto p) {
        Vector v = new Vector(p, punto);
        return v.esParaleloA(vectorDirector);
    }

    /// Devuelve un record Implicita con los coeficientes a, b y c de la recta en forma implícita
    public Implicita implicita() {

        return new Implicita(vectorDirector.y(), -vectorDirector.x(), vectorDirector.x()*punto.y() - vectorDirector.y()*punto.x());
    }

    /// Devuelve el determinante de una matriz dados los elementos aij que la conforman
    /// @param a11 Elemento de la matriz
    /// @param a12 Elemento de la matriz
    /// @param a21 Elemento de la matriz
    /// @param a22 Elemento de la matriz
    private double determinante(double a11, double a12, double a21, double a22) {
        return a11 * a22 - a12 * a21;
    }

    /// Devuelve el `vectorDirector` de la recta
    public Vector vectorDirector() {
        return vectorDirector;
    }

    /// Devuelve el punto por donde pasa la recta
    public Punto punto() {
        return punto;
    }

    /// Devuelve una recta cuyo `vectorDirector` es el mismo que el de la recta actual y que pase por `p`
    /// @param p Un punto
    public Recta paralelaPor(Punto p) {
        return new Recta(vectorDirector, p);
    }

    /// Devuelve una recta cuyo `vectorDirector` sea perpendicular al actual y que pase por `p`
    public Recta perpendicularPor(Punto p) {
        return new Recta(vectorDirector.ortogonal(), p);
    }

    /// Calcula el punto de intersección de dos rectas.
    /// Si no existe ese punto se devuelve un opcional vacío.
    public Optional<Punto> interseccionCon(Recta r){
        if (esParalelaA(r)){
            return Optional.empty();
        }
        double xNumerador = determinante(-implicita().c(), implicita().b(), -r.implicita().c(), r.implicita().b());
        double yNumerador = determinante(implicita().a(), -implicita().c(), r.implicita().a(), -r.implicita().c());
        double denominador = determinante(implicita().a(), implicita().b(), r.implicita().a(), r.implicita().b());
        double x = xNumerador/denominador;
        double y = yNumerador/denominador;
        if (denominador != 0) return Optional.of(new Punto(x, y));
        else return Optional.empty();
    }

    /// Devuelve la distancia entre la recta y el punto que se pasa como parámetro
    /// @param p Un punto
    public double distanciaDesde(Punto p) {
        Recta r = new Recta(vectorDirector, punto);
        Recta perpendicular = r.perpendicularPor(p);
        Optional<Punto> punto = r.interseccionCon(perpendicular);
        return punto.get().distancia(p);
    }
}