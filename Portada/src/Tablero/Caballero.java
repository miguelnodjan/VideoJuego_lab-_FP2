package Tablero;

public class Caballero extends Soldado {

    private int vida;
    private int ataque; 
    private int defensa;
    private int fila;
    private int columna;
    
    public int getColumna() {
        return columna;
    }
    public void setColumna(int columna) {
        this.columna = columna;
    }
    public int getFila() {
        return fila;
    }
    public void setFila(int fila) {
        this.fila = fila;
    }
    public Caballero() {
        this.vida = (int) ((Math.random() * 3) + 10);
        this.defensa = 7;
        this.ataque = 13;
    
    }
    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }
    public void setAtaque(int ataque){
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }
}
