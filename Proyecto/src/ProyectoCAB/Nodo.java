package ProyectoCAB;

import java.util.ArrayList;

public class Nodo {
	
	protected String id;
	protected int etiqueta;
	protected ArrayList<Nodo> vecinos;
	
	public Nodo(String n) {
		this.id=n;
		vecinos=new ArrayList<Nodo>();
	}
	
	
	
}
