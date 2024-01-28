package Final;

public class Arquero extends Soldado{
    private int ataque;
    private int defensa;
    private int vida;
    
    public Arquero(){
        this.ataque = 7;
        this.vida = (int)((Math.random()*3)+3);
        this.defensa = 3;
    }
    public int getAtaque() {
        return ataque;
    }
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
    public int getDefensa() {
        return defensa;
    }
    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }
    public int getVida() {
        return vida;
    }
    public void setVida(int vida) {
        this.vida = vida;
    }

}
