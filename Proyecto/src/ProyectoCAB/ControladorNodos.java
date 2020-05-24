package ProyectoCAB;

import java.util.ArrayList;

public class ControladorNodos {
	
	protected static ArrayList<Nodo> nodos = new ArrayList<Nodo>();
	
	public static void addNodo(Nodo n) {
		nodos.add(n);
	}
	
	public static Nodo getNodo(int i) {
		return nodos.get(i);
	}
	
	
}
