package ProyectoCAB;

import java.util.ArrayList;

public class ControladorNodos {
	
	protected static ArrayList<Nodo> nodos = new ArrayList<Nodo>();
	protected static int[][] adjlista;
	
	public static void addNodo(Nodo n) {
		nodos.add(n);
	}
	
	public static Nodo getNodo(int i) {
		return nodos.get(i);
	}
	
	public ControladorNodos(int[][] adjlista) {
		this.adjlista=adjlista;
	}
	
	
}
