package Tablero;

import javax.swing.JButton;

public class Cuadro extends JButton{
       
    private Soldado soldado;
    private final int x;
    private final int y;
    public Cuadro(int x, int y, Soldado soldado) {
        this.soldado = soldado;
        this.x = x;
        this.y = y;   
    }

    public int getXPos() {
        return x;
    }
    public int getYPos() {
        return y;
    }

    public void setFicha(Soldado soldado){
        this.soldado = soldado;
        repaint();
    }
    public Soldado getSoldado() {
        return soldado;
    }
    public void setSoldado(Soldado soldado){
        this.soldado= soldado;
        repaint();
    }
    public boolean estaVacio() {
        return soldado == null;
    }
    public void setNombreBoton(String nombre) {
        setText(nombre);
    }  
}
