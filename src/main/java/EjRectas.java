import rectas.*;

public class EjRectas {
    public static void main(String[] args) {
        try{
            double p1x = Double.parseDouble(args[0]);
            double p1y = Double.parseDouble(args[1]);
            double p2x = Double.parseDouble(args[2]);
            double p2y = Double.parseDouble(args[3]);
            double p3x = Double.parseDouble(args[4]);
            double p3y = Double.parseDouble(args[5]);

            Punto p1 = new Punto(p1x, p1y);
            Punto p2 = new Punto(p2x, p2y);
            Punto p3 = new Punto(p3x, p3y);
            Vector p1p2 = new Vector(p2x - p1x, p2y - p1y);
            Vector p1p3 = new Vector(p3x - p1x, p3y - p1y);
            Vector perpendicular = p1p2.ortogonal();
            double area = (perpendicular.x() * p1p3.x() + perpendicular.y() * p1p3.y())/2;
            System.out.println(area);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error: debes introducir seis argumentos.");
        } catch (NumberFormatException e) {
            System.err.println("Error: los argumentos deben ser numéricos.");
        }
    }
}