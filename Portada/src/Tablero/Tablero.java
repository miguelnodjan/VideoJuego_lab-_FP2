package Tablero;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tablero extends JFrame {
    private Cuadro[][] cuadros;
    private Cuadro cuadroSeleccionado;
    private JPanel panel;
    private Soldado[] ejercito1;
    private Soldado[] ejercito2;

    public Tablero(Soldado[] ejercito1, Soldado[] ejercito2) {
        super("Tablero");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);

        this.ejercito1 = ejercito1;
        this.ejercito2 = ejercito2;

        panel = new JPanel();
        panel.setLayout(new GridLayout(10, 10));

        cuadros = new Cuadro[10][10];
        DibujarTablero(this.ejercito1);
        DibujarTablero(this.ejercito2);

        for (Cuadro[] fila : cuadros) {
            for (Cuadro cuadro : fila) {
                panel.add(cuadro);
            }
        }

        getContentPane().add(panel);
        panel.repaint();
    }

    private void DibujarTablero(Soldado[] ejercito) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < ejercito.length; k++) {
                    if (ejercito[k] != null && ejercito[k].getFila() == i && ejercito[k].getColumna() == j) {
                        cuadros[i][j] = new Cuadro(i, j, ejercito[k]);
                        cuadros[i][j].addActionListener(new BotonListener());
                        cuadros[i][j].setNombreBoton(ejercito[k].getNombre());
                    }
                }

                if (cuadros[i][j] == null) {
                    cuadros[i][j] = new Cuadro(i, j, null);
                    cuadros[i][j].addActionListener(new BotonListener());
                }
            }
        }
    }

    private class BotonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Cuadro cuadro = (Cuadro) e.getSource();
            accionAlPresionar(cuadro);
            panel.repaint();
        }
    }

    private void accionAlPresionar(Cuadro cuadro) {
        if (cuadro.getSoldado() != null) {
            if (cuadroSeleccionado == null) {
                cuadroSeleccionado = cuadro;
                determinarGanador();
            } else {
                transferirInformacion(cuadro);
                cuadroSeleccionado = null;
                determinarGanador();
            }
        } else if (cuadroSeleccionado != null) {
            moverSoldado(cuadro);
            cuadroSeleccionado = null;
        }
    }

    private void transferirInformacion(Cuadro cuadroDestino) {
        Soldado soldadoOrigen = cuadroSeleccionado.getSoldado();
        Soldado soldadoDestino = cuadroDestino.getSoldado();

        if (soldadoDestino == null || soldadoOrigen.getVida() > soldadoDestino.getVida()) {
            cuadroSeleccionado.getSoldado().setVida(cuadroSeleccionado.getSoldado().getVida() - cuadroDestino.getSoldado().getVida());
            cuadroDestino.setSoldado(soldadoOrigen);
            cuadroDestino.setText(cuadroSeleccionado.getSoldado().getNombre());
            cuadroSeleccionado.setText("");
            cuadroSeleccionado.setSoldado(null);

        } else {
            cuadroDestino.getSoldado().setVida(cuadroDestino.getSoldado().getVida() - cuadroSeleccionado.getSoldado().getVida());
            cuadroSeleccionado.setText("");
            cuadroSeleccionado.setSoldado(null);
            
        }
    }

    private void moverSoldado(Cuadro cuadroDestino) {
        cuadroDestino.setSoldado(cuadroSeleccionado.getSoldado());
        cuadroDestino.setText(cuadroSeleccionado.getSoldado().getNombre());
        cuadroSeleccionado.setText("");
        cuadroSeleccionado.setSoldado(null);

        determinarGanador();

        panel.repaint();
    }

    private void determinarGanador() {
        int contadorEjercito1 = 0;
        int contadorEjercito2 = 0;

        for (int i = 0; i < cuadros.length; i++) {
            for (int j = 0; j < cuadros[i].length; j++) {
                if (cuadros[i][j].getSoldado() != null) {
                    String nombreSoldado = cuadros[i][j].getSoldado().getNombre();
                    if (esSoldadoDeEjercito(nombreSoldado, ejercito1)) {
                        contadorEjercito1++;
                    } else if (esSoldadoDeEjercito(nombreSoldado, ejercito2)) {
                        contadorEjercito2++;
                    }
                }
            }
        }

        if (contadorEjercito1 == 0) {
            JOptionPane.showMessageDialog(this, "¡Ganador: Ejército 2!");
        } else if (contadorEjercito2 == 0) {
            JOptionPane.showMessageDialog(this, "¡Ganador: Ejército 1!");
        }
    }

    private boolean esSoldadoDeEjercito(String nombreSoldado, Soldado[] ejercito) {
        for (Soldado soldado : ejercito) {
            if (soldado != null && soldado.getNombre().equals(nombreSoldado)) {
                return true;
            }
        }
        return false;
    }
}




