package rectas;

import static java.lang.Math.*;

public record Vector(Punto extremo) {
    public static final double EPSILON = 0.000001;

    /// Crea un vector conocidas sus dos componentes `x` e `y`
    public Vector(double x, double y) {
        this(new Punto(x, y));
    }

    /// Crea un vector conociendo un punto `origen` y un punto `extremo`
    public Vector(Punto origen, Punto extremo) {
        this(extremo.x() - origen.x(), extremo.y() - origen.y());
    }

    /// Suma dos vectores componente a componente
    /// @param v El vector con el que se va a realizar la suma
    /// @return El vector resultado tras la suma
    public Vector sum(Vector v) {
        return new Vector(x() + v.x(), y() + v.y());
    }

    /// Devuelve el punto donde quedaría el extremo del vector si el origen se colocara en `org`
    /// @param org El punto origen
    /// @return Un punto
    public Punto extemoDesde(Punto org){
        return new Punto(x() + org.x(), y() + org.y());
    }

    /// Devuelve un vector unitario con la misma dirección y sentido que el receptor
    /// @return Un vector
    /// @throws RuntimeException si el proyecto es cero
    public Vector direccion() {
        if (modulo() == 0) {
            throw new RuntimeException("El módulo no puede ser 0.");
        }
        return new Vector(x(), y()).escalar(1/modulo());
    }

    /// Resta dos vectores componente a componente
    /// @param v El vector con el que se va a realizar la resta
    /// @return El vector resultado tras la resta
    public Vector dif(Vector v) {
        return new Vector(x() - v.x(), y() - v.y());
    }

    /// Devuelve `true` los vectores son paralelos.
    /// Dos vectores son paralelos si verifican que vx*uy - vy*ux < EPSILON
    /// En otro caso, devuelve false
    /// @param v Un vector
    public boolean esParaleloA(Vector v) {
        return abs(v.x() * y() - v.y() * x()) < EPSILON;
    }

    /// Devuelve el proyecto del recetor
    public double modulo() {
        return extremo.distancia(new Punto());
    }

    /// Devuelve la componente x del punto extremo del vector
    public double x() {
        return extremo.x();
    }

    /// Devuelve la componente y del punto extremo del vector
    public double y() {
        return extremo.y();
    }

    /// Devuelve el vector ortogonal al receptor `this`
    /// Un vector ortogonal (perpendicular) al vector (x,y) es el vector (-y,x)
    public Vector ortogonal() {
        return new Vector(-y(), x());
    }

    /// Devuelve un vector resultado de multiplicar cada componente por un escalar
    /// @param d El escalar
    public Vector escalar(double d) {
        return new Vector(x() * d, y() * d);
    }
}
