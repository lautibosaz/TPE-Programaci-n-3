import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fabrica {

    private int piezasObjetivo;
    private List<String> maquina = new ArrayList<>();
    private List<Integer> capacidadMaquina = new ArrayList<>();

    public void leerArchivo(String nombreArchivo) {
        maquina.clear();
        capacidadMaquina.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            int lineaActual = 0;

            while ((linea = br.readLine()) != null) {
                if (lineaActual == 0) {
                    piezasObjetivo = Integer.parseInt(linea.trim());
                } else {
                    String[] partes = linea.split(",");
                    maquina.add(partes[0].trim());
                    capacidadMaquina.add(Integer.parseInt(partes[1].trim()));
                }
                lineaActual++;
            }
            
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public int getPiezasObjetivo() {
        return piezasObjetivo;
    }

    public List<String> getMaquina() {
        return maquina;
    }

    public List<Integer> getCapacidadMaquina() {
        return capacidadMaquina;
    }

    public void mostrarDatos() {
        System.out.println("Piezas a producir: " + piezasObjetivo);
        for (int i = 0; i < maquina.size(); i++) {
            System.out.println(maquina.get(i) + " produce " + capacidadMaquina.get(i));
        }
    }
}