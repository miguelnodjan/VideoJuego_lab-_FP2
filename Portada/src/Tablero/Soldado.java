package Final;

public class Soldado {
    private int fila;
    private int columna;
    private int defensa;
    private int vida;
    private int ataque;
    private String nombre;
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getAtaque() {
        return ataque;
    }
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
    public int getVida() {
        return vida;
    }
    public void setVida(int vida) {
        this.vida = vida;
    }
   
    public int getDefensa() {
        return defensa;
    }
    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }
    public void setFila( int n){
        this.fila = n;
    }
    public void setColumna(int n){
        this.columna = n;
    }
   public int getFila(){
      return fila;
   }
   public int getColumna(){
      return columna;
   }


}
