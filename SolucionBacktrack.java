import java.util.ArrayList;
import java.util.List;

/*
 * Estrategia de resolución con Backtracking:
 * 
 * En este algoritmo cada nodo del árbol de exploración representa un estado en el que se elige una 
 * secuencia parcial de máquinas, con el objetivo de llegar a un estado final donde la suma de piezas
 * producidas sea igual a la requerida.
 * 
 * Se utiliza poda para evitar continuar la exploración en los siguientes casos:
 * - Si la cantidad de piezas acumuladas supera la requerida.
 * - Si la cantidad de máquinas utilizadas ya es igual o mayor a la mejor solución encontrada.
 * 
 * El algoritmo garantiza encontrar la mejor solución, que sería la menor cantidad de máquinas, aunque el costo
 * computacional podria ser elevado si la cantidad de máquinas es grande o si hay muchas combinaciones posibles.
 */

public class SolucionBacktrack {

    private Fabrica fabrica;
    private int estadosGenerados = 0;
    private List<Integer> mejorSolucion = new ArrayList<>();

    public SolucionBacktrack(Fabrica fabrica) {
        this.fabrica = fabrica;
    }

    public void encontrarSolucion() {
        List<Integer> resultadoParcial = new ArrayList<>();
        List<Integer> indicesOrdenados = ordenarIndices();

        backtrack(0, resultadoParcial, indicesOrdenados);

        if (mejorSolucion.isEmpty()) {
            System.out.println("¡¡¡No se encontró ninguna combinación válida!!!");
        } else {
            System.out.println("Mejor solución encontrada con Backtracking:");
            int suma = 0;

            for (int indice : mejorSolucion) {
                String nombre = fabrica.getMaquina().get(indice);
                int piezas = fabrica.getCapacidadMaquina().get(indice);
                suma += piezas;
                System.out.print(nombre + " ");
            }

            System.out.println("\nPiezas producidas: " + suma);
            System.out.println("Máquinas utilizadas: " + mejorSolucion.size());
            System.out.println("Estados generados: " + estadosGenerados);
        }
    }

    private List<Integer> ordenarIndices() {
        List<Integer> indicesOrdenados = new ArrayList<>();
        List<Integer> capacidades = fabrica.getCapacidadMaquina();

        for (int i = 0; i < capacidades.size(); i++) {
            indicesOrdenados.add(i);
        }

        for (int i = 0; i < indicesOrdenados.size() - 1; i++) {
            for (int j = i + 1; j < indicesOrdenados.size(); j++) {
                int index1 = indicesOrdenados.get(i);
                int index2 = indicesOrdenados.get(j);
                if (capacidades.get(index1) < capacidades.get(index2)) {
                    int temp = indicesOrdenados.get(i);
                    indicesOrdenados.set(i, indicesOrdenados.get(j));
                    indicesOrdenados.set(j, temp);
                }
            }
        }
        return indicesOrdenados;
    }

    private void backtrack(int sumaActual, List<Integer> resultadoParcial, List<Integer> indicesOrdenados) {
        estadosGenerados ++;
        int objetivo = fabrica.getPiezasObjetivo();

        if (sumaActual > objetivo) return;
        if (!mejorSolucion.isEmpty() && resultadoParcial.size() >= mejorSolucion.size()) return;

        if (sumaActual == objetivo) {
            mejorSolucion.clear();
            mejorSolucion.addAll(resultadoParcial);
            return;
        }

        List<Integer> capacidades = fabrica.getCapacidadMaquina();

        for (int i = 0; i < indicesOrdenados.size(); i++) {
            int index = indicesOrdenados.get(i);
            resultadoParcial.add(index);
            backtrack(sumaActual + capacidades.get(index), resultadoParcial, indicesOrdenados);
            resultadoParcial.remove(resultadoParcial.size() - 1);
        }
    }
}