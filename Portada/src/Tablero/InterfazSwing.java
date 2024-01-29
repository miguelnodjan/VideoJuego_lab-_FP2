package Tablero;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazSwing {

    public static void main(String[] args) {
        int soldados1 = (int)((Math.random()*10)+1);
        Soldado [] ejercito1 = new Soldado[soldados1];
        int soldados2 = (int)((Math.random()*10)+1);
        Soldado [] ejercito2 = new Soldado[soldados2];

        JFrame frame = new JFrame("Videojuego");

        JButton boton = new JButton("Nuevo Juego");

 
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                JFrame ventana2 = new JFrame();
                ventana2.setSize(580,500);
                JLabel mapa = new JLabel("Ingrese mapa (Bosque, Campo, Montaña, Desierto)");
                JTextField mapa1 = new JTextField();
                JLabel reinos = new JLabel("Tipos de reinos (Inglaterra, Francia, Castilla-Aragon, Moros, SacroImperioRomano)");
                JLabel ejercito1JLabel = new JLabel("Ingrese reino para ejercito 1");
                JTextField ejercito1Field = new JTextField();
                JLabel ejercito2JLabel = new JLabel("Ingrese reino para ejercito 2");
                JTextField ejercito2Field = new JTextField();
                JButton boton2 = new JButton("Asignar valores");
                boton2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String campo = mapa1.getText();
                        String reino1 = ejercito1Field.getText();
                        String reino2 = ejercito2Field.getText();
                        
                        Ejercito.AsignarSoldados(ejercito1,reino1);
                        Ejercito.AsignarSoldados(ejercito2,reino2);
                        Ejercito.FilayColumna(ejercito1);
                        Ejercito.FilayColumna(ejercito1, ejercito2);
                        Mapa.SeleccionarMapa(ejercito1, reino1, campo);
                        Mapa.SeleccionarMapa(ejercito1, reino2, campo);
                        Tablero tabla = new Tablero(ejercito1, ejercito2);
                        tabla.setVisible(true);

                        JFrame ventana3 = new JFrame();
                        ventana3.setSize(400, 400);
                        ventana3.setVisible(true);

                        JButton mostrar1Button = new JButton("Soldados ejercito 1");
                        mostrar1Button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Ejercito.mostrarSoldados(ejercito1);
                            }
                        });
                       

                        JButton mostrar2Button = new JButton("Soldados ejercito 2");
                        mostrar2Button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Ejercito.mostrarSoldados(ejercito2);
                            }
                        });
                        
    

                        JButton resumenButton = new JButton("Resumen 1 ejercito");
                         
                        resumenButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JFrame resumenFrame = new JFrame("Resumen del Ejército");
                                JPanel panel = new JPanel();
                                Ejercito.MostrarResumen(reino1, ejercito1, panel);
                                JPanel panel2 = new JPanel();
                                Ejercito.SoldadoMayorVida(ejercito1, panel2);
                                JPanel mainPanel = new JPanel(new FlowLayout());
                                mainPanel.add(panel);
                                mainPanel.add(panel2);

                                resumenFrame.getContentPane().add(mainPanel);
                                resumenFrame.setSize(700, 200   );
                                resumenFrame.setVisible(true);
                            }
                        });
                        
                        
                        JButton resumen2Button = new JButton("Resumen 2 ejercito");
                        resumen2Button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JFrame resumen2Frame = new JFrame("Resumen del Ejército");
                                JPanel panel2 = new JPanel();
                                Ejercito.MostrarResumen(reino2, ejercito2, panel2);
                                JPanel panel3 = new JPanel();
                                Ejercito.SoldadoMayorVida(ejercito2, panel3);
                                JPanel mainPanel = new JPanel(new FlowLayout());
                                mainPanel.add(panel2);
                                mainPanel.add(panel3);

                                resumen2Frame.getContentPane().add(mainPanel);
                                resumen2Frame.setSize(700, 200);
                                resumen2Frame.setVisible(true);
                            }
                        });

                        mostrar1Button.setBounds(50, 50, 300, 30);
                        mostrar2Button.setBounds(50, 100, 300, 30);
                        resumenButton.setBounds(50, 150, 300, 30);
                        resumen2Button.setBounds(50, 200, 300, 30);

                        
                        ventana3.getContentPane().setLayout(null);  
                        ventana3.getContentPane().add(mostrar1Button);
                        ventana3.getContentPane().add(resumen2Button);
                        ventana3.getContentPane().add(resumenButton);
                        ventana3.getContentPane().add(mostrar2Button);



                    }
                });

            
                    
                
                mapa.setBounds(50, 50, 300, 30);
                mapa1.setBounds(50, 100, 150, 30);
                reinos.setBounds(50, 150, 500, 30);
                ejercito1JLabel.setBounds(50,200,350,30);
                ejercito1Field.setBounds(50, 250, 350, 30);       
                ejercito2JLabel.setBounds(50,300,350,30);        
                ejercito2Field.setBounds(50, 350, 350, 30);       
                boton2.setBounds(250, 400, 150, 30);

                ventana2.setVisible(true);
                ventana2.getContentPane().setLayout(null);
                ventana2.getContentPane().add(mapa);
                ventana2.getContentPane().add(mapa1);
                ventana2.getContentPane().add(reinos);
                ventana2.getContentPane().add(ejercito1JLabel);
                ventana2.getContentPane().add(ejercito1Field);
                ventana2.getContentPane().add(ejercito2JLabel);
                ventana2.getContentPane().add(ejercito2Field);
                ventana2.getContentPane().add(boton2);

              
            }
        };
        boton.setBounds(50, 50, 150, 30);

        boton.addActionListener(actionListener);
        
        frame.getContentPane().setLayout(null); 
        frame.getContentPane().add(boton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(300, 200);

        frame.setVisible(true);
    }
}
