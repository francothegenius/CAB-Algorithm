package ProyectoCAB;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;



public class CAB  {
	protected ArrayList<Nodo> solucion;
	protected int solucionFinal;
	protected float tMin, fD;
	protected int tMax, cont;
	protected ArrayList<Nodo> arrNodo;

	public static double probabilidad(double deltaE, double temperatura) {
		return Math.exp(-(deltaE) /temperatura);
	}
	
	public static double randomDouble()
	{
		Random r = new Random();
		return r.nextInt(1000) / 1000.0;
	}
	
	public void lecturaArchivo(String archivo) throws IOException, FileNotFoundException {
		try {
			BufferedReader lectura = new BufferedReader(new FileReader(archivo));
			String linea= lectura.readLine();
			tMax=Integer.parseInt(linea);
			linea=lectura.readLine();
			tMin=Float.parseFloat(linea);
			linea =lectura.readLine();
			fD = Float.parseFloat(linea);
			linea =lectura.readLine();
			cont=Integer.parseInt(linea);
			arrNodo = new ArrayList<Nodo>();
			StringTokenizer st;
			while((linea=lectura.readLine())!=null) {
				st=new StringTokenizer(linea);
				String nombreNodo = st.nextToken();
				int valorNodo = Integer.parseInt(st.nextToken());
				arrNodo.add(new Nodo(nombreNodo, valorNodo));
			}	
			
		} catch (FileNotFoundException e) {
			System.out.println("No se encontro el archivo");
		} catch (IOException e) {
			System.out.println("Hubo un problema de IO");
		}
	}
	
	
	public static void main(String[] args) throws IOException, FileNotFoundException {
		CAB cab = new CAB();
		Scanner scanner = new Scanner(System.in);
		String entrada = scanner.next();
		cab.lecturaArchivo(entrada+".txt");
		/*
		ControladorNodos.addNodo(new Nodo("Nodo1", 1));
		ControladorNodos.addNodo(new Nodo("Nodo2", 2));
		ControladorNodos.addNodo(new Nodo("Nodo3", 3));
		ControladorNodos.addNodo(new Nodo("Nodo4", 4));
		ControladorNodos.addNodo(new Nodo("Nodo5", 5));
		ControladorNodos.addNodo(new Nodo("Nodo6", 6));
		*/
		
		for(Nodo n: cab.arrNodo) {
			ControladorNodos.addNodo(n);
		}
	
		
		double temperaturaMaxima=cab.tMax;
		double temperaturaMinima=cab.tMin;
		double factorDeclibe=cab.fD;
		
		
		//Generar una permutaci�n aleatoria
		Camino camino= new Camino();
		camino.generateIndividual();
		
		while (temperaturaMaxima>temperaturaMinima) {
			int contador=cab.cont;
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
        
        PrintWriter pw= new PrintWriter(new FileWriter(entrada+"r.txt", true));
		
		pw.println("Camino: " + camino);
		pw.println("Distancia Final: " + camino.getTotaldistancia());
		pw.close();
	}
}
