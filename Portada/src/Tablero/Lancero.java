package Final;
public class Lancero extends Soldado {

    private int vida;
    private int ataque; 
    private int defensa;
    public Lancero() {
        this.vida = (int) ((Math.random() * 4) + 5);
        this.defensa = 5;
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
