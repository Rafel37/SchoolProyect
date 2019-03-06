package utils;

public class Ciclo {

    String nombreCiclo;

    public Ciclo(String nombreCiclo) {
        this.nombreCiclo = nombreCiclo;
    }

    public String getNombreCiclo() {
        return nombreCiclo;
    }

    @Override
    public String toString() {
        return getNombreCiclo();
    }
}
