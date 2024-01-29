package Tablero;
public class Espadachin extends Soldado {

    private int vida;
    private int ataque; 
    private int defensa;
    public Espadachin() {
        this.vida = (int) ((Math.random() * 3) + 3);
        this.defensa = 8;
        this.ataque = 10;
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
