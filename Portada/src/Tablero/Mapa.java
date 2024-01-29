package Tablero;

import java.util.*;

public class Mapa {
    
    public static Soldado[] SeleccionarMapa(Soldado[]ejercito1,String nombre1, String mapa){
        int vida;

        Scanner sc = new Scanner(System.in);
        String nombre = mapa + nombre1;
        switch (nombre) {
            case "BosqueInglaterra":
                for (int i = 0; i < ejercito1.length; i++) {
                    vida = ejercito1[i].getVida() +1;
                    ejercito1[i].setVida(vida);
                }   
                break;
                case "CampoFrancia":
                for (int i = 0; i < ejercito1.length; i++) {
                    vida = ejercito1[i].getVida() +1;
                    ejercito1[i].setVida(vida);
                }
                
                break;
                case "MontañaCastilla-Aragon":
                for (int i = 0; i < ejercito1.length; i++) {
                    vida = ejercito1[i].getVida() +1;
                    ejercito1[i].setVida(vida);
                }
                
                break;
                case "DesiertoMoros":
                for (int i = 0; i < ejercito1.length; i++) {
                    vida = ejercito1[i].getVida() +1;
                    ejercito1[i].setVida(vida);
                }
                
                break;
                case "PlayaSacroImperioRomano":
                for (int i = 0; i < ejercito1.length; i++) {
                    vida = ejercito1[i].getVida() +1;
                    ejercito1[i].setVida(vida);
                }
                
                break;
                case "BosqueSacroImperioRomano":
                for (int i = 0; i < ejercito1.length; i++) {
                    vida = ejercito1[i].getVida() +1;
                    ejercito1[i].setVida(vida);
                }
                
                break;
                case "CampoSacroImperioRomano":
                for (int i = 0; i < ejercito1.length; i++) {
                    vida = ejercito1[i].getVida() +1;
                    ejercito1[i].setVida(vida);
                }
                break;
        
            default:
                break;
        }
        return ejercito1;

    }
}
