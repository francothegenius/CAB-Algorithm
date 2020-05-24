package ProyectoCAB;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import com.sun.corba.se.impl.orbutil.graph.Graph;


public class CAB  {
	protected ArrayList<Nodo> solucion;
	protected int solucionFinal;

	public static double probabilidad(double deltaE, double temperatura) {
		return Math.exp(-(deltaE) /temperatura);
	}
	
	public static double randomDouble()
	{
		Random r = new Random();
		return r.nextInt(1000) / 1000.0;
	}

	
	public static void main(String[] args) {
		ControladorNodos.addNodo(new Nodo("Nodo1", 1));
		ControladorNodos.addNodo(new Nodo("Nodo2", 2));
		ControladorNodos.addNodo(new Nodo("Nodo3", 3));
		ControladorNodos.addNodo(new Nodo("Nodo4", 4));
		ControladorNodos.addNodo(new Nodo("Nodo5", 5));
		ControladorNodos.addNodo(new Nodo("Nodo6", 6));

		
		double temperaturaMaxima=10;
		double temperaturaMinima=0.001;
		double factorDeclibe=0.95;
		
		//Generar una permutación aleatoria
		Camino camino= new Camino();
		camino.generateIndividual();
		
		while (temperaturaMaxima>temperaturaMinima) {
			int contador=100;
			while (contador>0) {
				Camino nuevoCamino=new Camino();
				nuevoCamino.generateIndividual();
				double distanciaCamino=camino.getTotaldistancia();
				double distancianNuevoCamino=nuevoCamino.getTotaldistancia();
				double deltaE=distancianNuevoCamino-distanciaCamino;
				if (deltaE<=0) {
					camino=nuevoCamino;
				} else {
					double random=randomDouble();
					if (probabilidad(deltaE, temperaturaMaxima)>random) {
						camino=nuevoCamino;
					}
				}
				contador--;
			}
			temperaturaMaxima*=factorDeclibe;
		}
		
        System.out.println("Distancia Final: " + camino.getTotaldistancia());
        System.out.println("Camino: " + camino);	
	}
}
