class Descarga implements Runnable {
    private String nombreArchivo;

    public Descarga(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public void run() {
        for (int progreso = 0; progreso <= 100; progreso+=10) {
            System.out.println("Descargando " + nombreArchivo + ": " + progreso + "% completado");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Descarga interrumpida: " + nombreArchivo);
                return;
            }
        }
        System.out.println("Descarga completada: " + nombreArchivo);
    }
}

public class SimuladorDescarga {
    public static void main(String[] args) {
        Thread hilo1 = new Thread(new Descarga("contraseñasFBI.txt"));
        Thread hilo2 = new Thread(new Descarga("AliensReales.mp4"));
        Thread hilo3 = new Thread(new Descarga("GTA V.exe"));

        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}