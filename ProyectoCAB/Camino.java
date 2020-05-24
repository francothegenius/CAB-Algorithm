package ProyectoCAB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class Camino {
	
	protected ArrayList<Nodo> camino = new ArrayList<Nodo>();
	protected int distancia;
	
    public void generateIndividual() {
        // Loop through all our destination cities and add them to our tour
    	int nodo=ControladorNodos.nodos.size();
        /*for (int x = 0; x < ControladorNodos.nodos.size(); x++) {
          camino.set(x, ControladorNodos.getNodo(x));
        }
        Collections.shuffle(camino);*/
        ListIterator<Nodo> it = ControladorNodos.nodos.listIterator();
		Nodo nodoactual;
		while(it.hasNext()) {
			nodoactual = it.next();
			camino.add(nodoactual);
		}
		Collections.shuffle(camino);
    }
    
    public int getTotaldistancia(){
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
                tourdistancia += distancia(nodoInicial, nodoFinal); 
            }
            distancia = tourdistancia;
        }
        return distancia;
    }
    
    public double distancia(Nodo nodoInicial, Nodo nodoFinal) {
    	double f=Math.abs(nodoFinal.valor-nodoInicial.valor);
    	double fs=camino.size()-Math.abs(nodoFinal.valor-nodoInicial.valor);
    	return Math.min(f, fs);
    }
    
    public String toString() {
        String s = camino.get(0).nombre;
        for (int i = 1; i < camino.size(); i++) {
            s += " -> " + camino.get(i).nombre;
        }
        return s;
    }

}
