import java.util.ArrayList;
import java.util.List;

/* 
 * Estrategia de resolución con Greedy:
 * 
 * En este algoritmo se ordenan las máquinas de mayor a menor en base a su capacidad de producción,
 * y siempre que no se haya alcanzado la cantidad de piezas requeridas, selecciona de forma secuencial 
 * las que más producen.
 * 
 * Candidatos: todas las máquinas disponibles.
 * Estrategia de selección: Se eligen primero las máquinas que producen más piezas.
 * 
 * El agloritmo es eficiente en costo computacional, ya que recorre las máquinas una sola vez.
 * Sin embargo, no garantiza encontrar la mejor solución en todos los casos, pero si una aproximación
 * válida cercana a la mejor solución.
 */

public class SolucionGreedy {

    private Fabrica fabrica;
    private int estadosGenerados = 0;

    public SolucionGreedy(Fabrica fabrica) {
        this.fabrica = fabrica;
    }

    public void encontrarSolucion() {

        List<Integer> capacidades = fabrica.getCapacidadMaquina();
        List<String> maquinas = fabrica.getMaquina();

        int objetivo = fabrica.getPiezasObjetivo();
        int produccion = 0;

        List<Integer> indicesOrdenados = ordenarIndices();

        System.out.println("Mejor solución encontrada con Greedy:");

        for (int i = 0; i < indicesOrdenados.size(); i++) {
            int index = indicesOrdenados.get(i);
            int capacidad = capacidades.get(index);

            while (produccion + capacidad <= objetivo) {
                System.out.print(maquinas.get(index) + " ");
                produccion += capacidad;
                estadosGenerados++;
            }

            if (produccion == objetivo)
                break;
        }

        System.out.println("\nPiezas producidas: " + produccion);
        System.out.println("Máquinas utilizadas: " + estadosGenerados);
        System.out.println("Estados generados: " + estadosGenerados);

        if (produccion != objetivo) {
            System.out.println("¡¡¡No se pudo alcanzar el objetivo exacto con estrategia Greedy!!!");
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
}