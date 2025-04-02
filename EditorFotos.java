package tap;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class EditorFotos extends JFrame {
    
    private JLabel JlabelFoto;
    private BufferedImage imagenActual;
    private BufferedImage imagenOriginal;
    
    public EditorFotos() {

        setTitle("Editor de Fotos");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        JMenu ArcMenu = new JMenu("Archivo");
        JMenuItem AbrirFoto = new JMenuItem("Abrir Foto");
        JMenuItem GuardarFoto = new JMenuItem("Guardar Foto");
        JMenuItem CrearFoto = new JMenuItem("Crear Foto");
        JMenuItem CerrarFoto = new JMenuItem("Cerrar Foto");
        JMenuItem exitApp = new JMenuItem("Salir");

        ArcMenu.add(AbrirFoto);
        ArcMenu.add(GuardarFoto);
        ArcMenu.add(CrearFoto);
        ArcMenu.add(CerrarFoto);
        ArcMenu.addSeparator();
        ArcMenu.add(exitApp);

        JMenu editMenu = new JMenu("Editar");
        JMenuItem RecortarFoto = new JMenuItem("Recortar Foto");
        JMenuItem RotarFoto = new JMenuItem("Rotar Foto");
        JMenuItem PixelarFoto = new JMenuItem("Pixelar Foto");
        JMenuItem QuitarPixel = new JMenuItem("Quitar Pixelado");

        editMenu.add(RecortarFoto);
        editMenu.add(RotarFoto);
        editMenu.add(PixelarFoto);
        editMenu.add(QuitarPixel);

        JMenu ayudaMenu = new JMenu("Ayuda");
        JMenuItem Acercade = new JMenuItem("Acerca de");
        JMenuItem Consejos = new JMenuItem("Consejos de Uso");

        ayudaMenu.add(Acercade);
        ayudaMenu.add(Consejos);

        menuBar.add(ArcMenu);
        menuBar.add(editMenu);
        menuBar.add(ayudaMenu);

        setJMenuBar(menuBar);

        JlabelFoto = new JLabel("No se ha cargado ninguna foto", SwingConstants.CENTER);
        JlabelFoto.setFont(new Font("Arial", Font.PLAIN, 18));
        add(JlabelFoto);

        AbrirFoto.addActionListener(e -> AbrirF());
        GuardarFoto.addActionListener(e -> GuardarF());
        CrearFoto.addActionListener(e -> CrearF());
        CerrarFoto.addActionListener(e -> CerrarF());
        exitApp.addActionListener(e -> System.exit(0));

        RecortarFoto.addActionListener(e -> RecortarF());
        RotarFoto.addActionListener(e -> RotarF());
        PixelarFoto.addActionListener(e -> PixelarF());
        QuitarPixel.addActionListener(e -> QuitarPixelF());

        Acercade.addActionListener(e -> JOptionPane.showMessageDialog(this, "Editor de Fotos versión 1.0\nCreado por: \nEdgar Avila\nVictor Granados\nJardel Contreras"));
        Consejos.addActionListener(e -> JOptionPane.showMessageDialog(this, "Consejos: Usa 'Abrir' para cargar una foto y 'Recortar' para editar."));
    }
    
    private void AbrirF() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                imagenActual = ImageIO.read(file);
                imagenOriginal = new BufferedImage(imagenActual.getWidth(), imagenActual.getHeight(), imagenActual.getType());
                Graphics2D g2d = imagenOriginal.createGraphics();
                g2d.drawImage(imagenActual, 0, 0, null);
                g2d.dispose();
                ImageIcon icon = new ImageIcon(imagenActual.getScaledInstance(JlabelFoto.getWidth(), JlabelFoto.getHeight(), Image.SCALE_SMOOTH));
                JlabelFoto.setIcon(icon);
                JlabelFoto.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No se pudo abrir la foto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void GuardarF() {
        if (imagenActual == null) {
            JOptionPane.showMessageDialog(this, "No hay foto para guardar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Foto");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos JPG", "jpg", "jpeg"));
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                if (!file.getName().endsWith(".jpg")) {
                    file = new File(file.getParent(), file.getName() + ".jpg");
                }
                ImageIO.write(imagenActual, "jpg", file);
                JOptionPane.showMessageDialog(this, "Foto guardada con éxito.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No se pudo guardar la foto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void CrearF() {
        String widthInput = JOptionPane.showInputDialog(this, "Ingrese el ancho de la foto:");
        String heightInput = JOptionPane.showInputDialog(this, "Ingrese la altura de la foto:");
        String colorInput = JOptionPane.showInputDialog(this, "Ingrese el color de fondo (en formato HEX, ej: #000000 para negro):");

        try {

            int width = Integer.parseInt(widthInput);
            int height = Integer.parseInt(heightInput);
            
            Color backgroundColor = Color.decode(colorInput);

            imagenActual = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = imagenActual.createGraphics();
            g2d.setColor(backgroundColor);
            g2d.fillRect(0, 0, width, height);
            g2d.dispose();

            
            imagenOriginal = new BufferedImage(imagenActual.getWidth(), imagenActual.getHeight(), imagenActual.getType());
            g2d = imagenOriginal.createGraphics();
            g2d.drawImage(imagenActual, 0, 0, null);
            g2d.dispose();

            ImageIcon icon = new ImageIcon(imagenActual.getScaledInstance(JlabelFoto.getWidth(), JlabelFoto.getHeight(), Image.SCALE_SMOOTH));
            JlabelFoto.setIcon(icon);
            JlabelFoto.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido para las dimensiones.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "El formato de color es incorrecto. Asegúrese de usar un formato HEX válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void CerrarF() {
        if (imagenActual == null) {
            JOptionPane.showMessageDialog(this, "No hay foto para cerrar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        imagenActual = null;
        imagenOriginal = null;
        JlabelFoto.setIcon(null);
        JlabelFoto.setText("Foto cerrada.");
    }

    private void RecortarF() {
        if (imagenActual == null) {
            JOptionPane.showMessageDialog(this, "No hay foto para recortar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int width = imagenActual.getWidth() / 2;
            int height = imagenActual.getHeight() / 2;
            int x = (imagenActual.getWidth() - width) / 2;
            int y = (imagenActual.getHeight() - height) / 2;
            imagenActual = imagenActual.getSubimage(x, y, width, height);
            ImageIcon icon = new ImageIcon(imagenActual.getScaledInstance(JlabelFoto.getWidth(), JlabelFoto.getHeight(), Image.SCALE_SMOOTH));
            JlabelFoto.setIcon(icon);
            JOptionPane.showMessageDialog(this, "Foto recortada.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudo recortar la foto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void RotarF() {
    if (imagenActual == null) {
        JOptionPane.showMessageDialog(this, "No hay foto para rotar.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    try {

        int width = imagenActual.getWidth();
        int height = imagenActual.getHeight();

        BufferedImage rotatedImage = new BufferedImage(height, width, imagenActual.getType());
        Graphics2D g2d = rotatedImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.translate(height / 2, width / 2);
        g2d.rotate(Math.toRadians(90)); 

        g2d.drawImage(imagenActual, -width / 2, -height / 2, null);
        g2d.dispose();

        imagenActual = rotatedImage;

        ImageIcon icon = new ImageIcon(imagenActual.getScaledInstance(JlabelFoto.getWidth(), JlabelFoto.getHeight(), Image.SCALE_SMOOTH));
        JlabelFoto.setIcon(icon);

        JOptionPane.showMessageDialog(this, "Foto rotada con éxito.");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "No se pudo rotar la foto.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    private void PixelarF() {
        if (imagenActual == null) {
            JOptionPane.showMessageDialog(this, "No hay foto para pixelar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {

            BufferedImage tempImage = new BufferedImage(imagenActual.getWidth() / 10, imagenActual.getHeight() / 10, imagenActual.getType());
            Graphics2D g2d = tempImage.createGraphics();
            g2d.drawImage(imagenActual, 0, 0, tempImage.getWidth(), tempImage.getHeight(), null);
            g2d.dispose();

            imagenActual = new BufferedImage(imagenActual.getWidth(), imagenActual.getHeight(), imagenActual.getType());
            g2d = imagenActual.createGraphics();
            g2d.drawImage(tempImage, 0, 0, imagenActual.getWidth(), imagenActual.getHeight(), null);
            g2d.dispose();

            ImageIcon icon = new ImageIcon(imagenActual.getScaledInstance(JlabelFoto.getWidth(), JlabelFoto.getHeight(), Image.SCALE_SMOOTH));
            JlabelFoto.setIcon(icon);
            JOptionPane.showMessageDialog(this, "Foto pixelada.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudo pixelar la foto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void QuitarPixelF() {
        if (imagenActual == null) {
            JOptionPane.showMessageDialog(this, "No hay foto para quitar el pixelado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {

            imagenActual = new BufferedImage(imagenOriginal.getWidth(), imagenOriginal.getHeight(), imagenOriginal.getType());
            Graphics2D g2d = imagenActual.createGraphics();
            g2d.drawImage(imagenOriginal, 0, 0, null);
            g2d.dispose();

            ImageIcon icon = new ImageIcon(imagenActual.getScaledInstance(JlabelFoto.getWidth(), JlabelFoto.getHeight(), Image.SCALE_SMOOTH));
            JlabelFoto.setIcon(icon);
            JOptionPane.showMessageDialog(this, "El pixelado ha sido eliminado.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudo quitar el pixelado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditorFotos().setVisible(true));
    }
    
}
