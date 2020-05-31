package ProyectoCAB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class Solucion {
	
	protected ArrayList<Integer> permutacion = new ArrayList<Integer>();
	protected int solucion;
	protected int min;
	
	//RANDOM PERMUTTATION
    public void generarPermutacion() {
    	int nodo=ControladorNodos.nodos.size();
    	for(int x=0;x<ControladorNodos.adjlista.length;x++) {
    		permutacion.add(x);
    	}
		Collections.shuffle(permutacion);
		
    }
    
    
    public int getCosto(){
    	if (solucion == 0) {
            int min=0, cab=0;
            for (int x=0; x < permutacion.size(); x++) {
                for (int i = x+1; i < permutacion.size(); i++) {
                    if (ControladorNodos.adjlista[x][i]==1) {
                    	cab= (int) minimo(permutacion.get(x), permutacion.get(i));
                    	if (cab > min) { 
                            min = cab;
                        }
                    }
				}
            }
            solucion=min;
        }
        return solucion;
    }
    
  
    
    public double minimo(int n1,int n2) {
    	double f=Math.abs(n2-n1);
    	double fs=permutacion.size()-Math.abs(n2-n1);
    	return Math.min(f, fs);
    }
    
    public void copiarSolucion(ArrayList<Integer> s) {
    	this.permutacion=new ArrayList<>(s);
    }
    
    public String toString() {
        String s = "";
        for (int i = 0; i < permutacion.size(); i++) {
            s += " -> " + permutacion.get(i);
        }
        return s;
    }

}
