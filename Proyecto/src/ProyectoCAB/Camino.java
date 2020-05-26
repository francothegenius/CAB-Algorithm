package ProyectoCAB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class Camino {
	
	//protected ArrayList<Nodo> camino = new ArrayList<Nodo>();
	protected ArrayList<Integer> camino2 = new ArrayList<Integer>();
	protected int distancia;
	protected int min;
	
	//RANDOM PERMUTTATION
    public void generarPermutacion() {
        // Loop through all our destination cities and add them to our tour
    	int nodo=ControladorNodos.nodos.size();
        /*for (int x = 0; x < ControladorNodos.nodos.size(); x++) {
          camino.set(x, ControladorNodos.getNodo(x));
        }
        Collections.shuffle(camino);*/
       /* ListIterator<Nodo> it = ControladorNodos.nodos.listIterator();
		Nodo nodoactual;
		while(it.hasNext()) {
			nodoactual = it.next();
			camino.add(nodoactual);
		}*/
    	for(int x=0;x<ControladorNodos.adjlista.length;x++) {
    		camino2.add(x);
    	}
		Collections.shuffle(camino2);
		
    }
    
 /*   public int getTotaldistancia(){
    	if (distancia == 0) {
            int tourdistancia = 0;
            // Loop through our tour's cities
            for (int x=0; x < camino.size(); x++) {
                // Get city we're traveling from
                Nodo nodoInicial = camino.get(x);
                // City we're traveling to
                Nodo nodoFinal;
                // Check we're not on our tour's last city, if we are set our
                // tour's final destination city to our starting city
                if(x+1 < camino.size()){
                    nodoFinal =camino.get(x+1);
                }
                else{
                    nodoFinal = camino.get(0);
                }                
                // Get the distancia between the two cities
                for (int i = 0; i < camino.size(); i++) {
                    if (ControladorNodos.adjlista[x][i]==1) {
                    	tourdistancia += distancia(ControladorNodos.getNodo(x),ControladorNodos.getNodo(i));
                    	tourdistancia += distancia(camino.get(x),camino.get(i));
    				}
				}
                tourdistancia += distancia(nodoInicial, nodoFinal); 
            }
            distancia = tourdistancia;
        }
        return distancia;
    }*/
    
    public int getCosto(){
    	if (distancia == 0) {
            int rutaSolucion = 0;
            int min=0;
            // Loop through our tour's cities
            for (int x=0; x < camino2.size(); x++) {
                // Get the distancia between the two cities
                for (int i = 0; i < camino2.size(); i++) {
                    if (ControladorNodos.adjlista[x][i]==1) {
                    	if (x==0 && i==0) {
                        	min= (int) distancia(camino2.get(x),camino2.get(i)); 
						} else {
	                    	rutaSolucion = (int) distancia(camino2.get(x),camino2.get(i));  
	                    	if (rutaSolucion>min) {
	                    		min+=rutaSolucion; 
							}
						}
    				}
				}
            }
            distancia=min;
        }
        return distancia;
    }
    
    public double distancia(Nodo nodoInicial, Nodo nodoFinal) {
    	double f=Math.abs(nodoFinal.etiqueta-nodoInicial.etiqueta);
    	double fs=camino2.size()-Math.abs(nodoFinal.etiqueta-nodoInicial.etiqueta);
    	return Math.min(f, fs);
    }
    
    public double distancia(int n1,int n2) {
    	double f=Math.abs(n2-n1);
    	double fs=camino2.size()-Math.abs(n2-n1);
    	return Math.min(f, fs);
    }
    
    
    public String toString() {
        String s = "";
        for (int i = 0; i < camino2.size(); i++) {
            s += " -> " + camino2.get(i);
        }
        return s;
    }

}
