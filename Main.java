public class Main {
    public static void main(String[] args) {
        Fabrica fabrica = new Fabrica();
        fabrica.leerArchivo("especificaciones.txt");

        fabrica.mostrarDatos();

        System.out.println("\n=== BACKTRACKING ===");
        SolucionBacktrack solucionBacktrack = new SolucionBacktrack(fabrica);
        solucionBacktrack.encontrarSolucion();

        System.out.println("\n=== GREEDY ===");
        SolucionGreedy solucionGreedy = new SolucionGreedy(fabrica);
        solucionGreedy.encontrarSolucion();
    }
}
