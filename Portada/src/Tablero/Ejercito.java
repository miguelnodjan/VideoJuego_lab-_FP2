package Tablero;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
public class Ejercito {
    

    public static void AsignarSoldados(Soldado[] ejercito, String reino) {
       int controlador = ejercito.length;

    while (controlador > 0) {
        JOptionPane.showMessageDialog(null, "La cantidad de soldados de " + reino+ " es: " + ejercito.length, "Cantidad de soldados", JOptionPane.INFORMATION_MESSAGE);
        String eleccion = JOptionPane.showInputDialog("Ingrese la clase de soldado que desee (Caballero, Arquero, Lancero y Espadachin)");
        
        if (eleccion != null) { 
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de " + eleccion));

            if (cantidad <= controlador) {
                for (int i = 0; i < cantidad; i++) {
                    switch (eleccion) {
                        case "Caballero":
                            ejercito[ejercito.length - controlador + i] = new Caballero();
                            ejercito[ejercito.length - controlador + i].setNombre(reino + " Caballero" + (ejercito.length - controlador + i));
                            break;
                        case "Arquero":
                            ejercito[ejercito.length - controlador + i] = new Arquero();
                            ejercito[ejercito.length - controlador + i].setNombre(reino + " Arquero" + (ejercito.length - controlador + i));
                            break;
                        case "Espadachin":
                            ejercito[ejercito.length - controlador + i] = new Espadachin();
                            ejercito[ejercito.length - controlador + i].setNombre(reino + " Espadachin" + (ejercito.length - controlador + i));
                            break;
                        case "Lancero":
                            ejercito[ejercito.length - controlador + i] = new Lancero();
                            ejercito[ejercito.length - controlador + i].setNombre(reino + " Lancero" + (ejercito.length - controlador + i));
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Clase de soldado no reconocida.");
                    }
                }

                controlador = controlador - cantidad;
            } else {
                JOptionPane.showMessageDialog(null, "Supero el límite de soldados disponibles.");
                break;
            }
        } else {
            break;
        }
       }
   }
    

    public static Soldado[] FilayColumna(Soldado[] ejercito) {
       
    
        for (int i = 0; i < ejercito.length; i++) {
            int fila, columna;
            do {
                fila = (int) (Math.random() * 10);
                columna = (int) (Math.random() * 10);
            } while (posicionOcupada(ejercito, fila, columna));
            if (ejercito[i] != null) {
                ejercito[i].setFila(fila);
                ejercito[i].setColumna(columna);
            }
            
        }
    
        return ejercito;
    }
    public static Soldado[] FilayColumna(Soldado[] ejercito, Soldado[] ejercito2) {
       
    
        for (int i = 0; i < ejercito2.length; i++) {
            int fila, columna;
            do {
                fila = (int) (Math.random() * 10);
                columna = (int) (Math.random() * 10);
            } while (posicionOcupada(ejercito, fila, columna));
            if (ejercito2[i] != null) {
                ejercito2[i].setFila(fila);
                ejercito2[i].setColumna(columna);
            }
            
        }
    
        return ejercito;
    }
    
    private static boolean posicionOcupada(Soldado[] ejercito, int fila, int columna) {
        for (Soldado soldado : ejercito) {
            if (soldado != null && soldado.getFila() == fila && soldado.getColumna() == columna) {
                return true;
            }
        }
        return false;
    }
    
    public static void SoldadoMayorVida(Soldado[] army, JPanel panel){
        int cantidad =0;
        int numero = 0;
        for (int i = 0; i < army.length; i++) {
            if(army[i].getVida() > cantidad){
                cantidad = army[i].getVida();
                numero = i;
            }
        }
        JLabel label1 = new JLabel("Soldado con mayor vida: ");
        JLabel label2 = new JLabel("Nombre: " + army[numero].getNombre());
        JLabel label3 = new JLabel("Vida: " + army[numero].getVida());
        JLabel label4 = new JLabel("Fila: " + army[numero].getFila());
        JLabel label5 = new JLabel("Columna: " + army[numero].getColumna());
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
    }

    public static void mostrarSoldados(Soldado[] ejercito) {
        JFrame frame = new JFrame("Información del Ejército");
        frame.setSize(400, 300);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        for (int i = 0; i < ejercito.length; i++) {
            textArea.append("Soldado " + (i + 1) + "\n");
            textArea.append("Vida: " + ejercito[i].getVida() + "\n");
            textArea.append("Nombre: " + ejercito[i].getNombre() + "\n");
            textArea.append("Fila: " + ejercito[i].getFila() + "\n");
            textArea.append("Columna: " + ejercito[i].getColumna() + "\n");
            textArea.append("\n");
        }

        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel panel = new JPanel();
        panel.add(scrollPane);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
   
    public static void MostrarResumen(String reino, Soldado[] ejercito, JPanel panel) {
        int caballero = 0;
        int arquero = 0;
        int lancero = 0;
        int espadachin = 0;

        for (Soldado soldado : ejercito) {
            if (soldado instanceof Caballero) {
                caballero++;
            } else if (soldado instanceof Arquero) {
                arquero++;
            } else if (soldado instanceof Espadachin) {
                espadachin++;
            } else if (soldado instanceof Lancero) {
                lancero++;
            } else {
                System.out.println(soldado.getNombre() + " es de una clase no reconocida.");
            }
        }

        JLabel label1 = new JLabel("Ejército: " + reino);
        JLabel label2 = new JLabel("Cantidad total de soldados creados: " + ejercito.length);
        JLabel label3 = new JLabel("Espadachines: " + espadachin);
        JLabel label4 = new JLabel("Arqueros: " + arquero);
        JLabel label5 = new JLabel("Caballeros: " + caballero);
        JLabel label6 = new JLabel("Lanceros: " + lancero);
        
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);
    }
    
}


