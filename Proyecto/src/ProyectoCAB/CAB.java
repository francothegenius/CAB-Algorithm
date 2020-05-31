package ProyectoCAB;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CAB  {
	protected ArrayList<Nodo> grafo;
	protected int solucionFinal;
	protected float tMin, fD;
	protected int tMax, cont, n,cont2;
	protected ArrayList<Nodo> arrNodo;
	protected String[] arrAux;
	protected int[][] adjlista;
	public static double probabilidad(double deltaE, double temperatura) {
		return Math.exp(-(deltaE) /temperatura);
	}
	
	public static double randomDouble()
	{
		Random r = new Random();
		return r.nextInt(1000) / 1000.0;
	}
	
	
 void llenarGrafo(String ID1, String ID2) {
		adjlista[Integer.parseInt(ID1)-1][Integer.parseInt(ID2)-1]=1;
		adjlista[Integer.parseInt(ID2)-1][Integer.parseInt(ID1)-1]=1;
	}
	
	public void llenarControlador(){
		ControladorNodos c=new ControladorNodos(adjlista);
		for (int i = 0; i < adjlista.length; i++) {
			Nodo nodo=new Nodo(String.valueOf(i));
			c.addNodo(nodo);
		}
	}
	
	public boolean busqueda(String []arrAux,String id) {
		for(int x=0;x<arrAux.length;x++) {
			if(arrAux[x]==id) {
				return true;
			}
		}
		return false;
	}
	
	public void lecturaArchivo(String archivo) throws IOException, FileNotFoundException {
		try {
			BufferedReader lectura = new BufferedReader(new FileReader(archivo));
			String linea= lectura.readLine();
			linea= lectura.readLine();
			n=Integer.parseInt(linea.substring(0, linea.indexOf(" ")));
			arrAux=new String[n];
			adjlista=new int[n][n];
			grafo=new ArrayList<Nodo>();
			linea=lectura.readLine();
			StringTokenizer st;
			while((linea=lectura.readLine())!=null) {
				st=new StringTokenizer(linea);
				String ID1 = st.nextToken();
				String ID2 = st.nextToken();
				llenarGrafo(ID1,ID2);
			}
			llenarControlador();
			
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
		String com[]=entrada.split("/");
		cab.lecturaArchivo(com[0]);
		
		
		double temperaturaMaxima=Double.parseDouble(com[1]);
		double temperaturaMinima=Double.parseDouble(com[2]);
		double factorDeclibe=Double.parseDouble(com[3]);
		
		Stopwatch timer = new Stopwatch();
		Solucion s= new Solucion(), 
				bestSolution = new Solucion();
		s.generarPermutacion();
		// agregar metodo para copiar Solucion en bestSolution
		bestSolution.copiarSolucion(s.permutacion);
		int newCost, currentCost = s.getCosto(),
			bestCost = currentCost,
			oldCost=0;

		while (temperaturaMaxima>temperaturaMinima) {
			int contador=Integer.parseInt(com[4]);
			while (contador>0) {
				// Generar un vecino de Solucion
				// generan aleatoriamente dos enteros u,v en [0..n] y u diferente de v
				Random ran=new Random();
				int u= ran.nextInt(ControladorNodos.nodos.size()),
					v=ran.nextInt(ControladorNodos.nodos.size());
				// Intentar un movimiento
				oldCost = currentCost;
				Collections.swap(s.permutacion, u, v);
				newCost = s.getCosto();
				double deltaE = currentCost - newCost;
				if (deltaE<=0) {  // Acepta el movimiento
					if (newCost > bestCost) { 
						// copiar en bestSolution el Solucion con metodo creado en clase Solucion
						bestSolution.copiarSolucion(s.permutacion);
						currentCost = newCost;	
					} 
				} else {   
					double random=randomDouble();
					if (!(random < Math.exp(-(deltaE) / temperaturaMaxima))){   // Rechaza el movimiento
						Collections.swap(s.permutacion, u, v);     // UNDO del movimiento
						currentCost = oldCost; // Regreso al costo anterior
					}
					else { // Acepto un movimiento que empeora por la probabilidad
						currentCost = newCost;
					} 
				}
				contador--;
			}
			temperaturaMaxima *= factorDeclibe;    //0.95
		}
        System.out.println("Mejor Solucion: " + bestSolution);
        System.out.println("Costo: " + bestSolution.getCosto());
        System.out.println("tiempo:"+timer.elapsedTime());
        
        PrintWriter pw= new PrintWriter(new FileWriter(com[0]+"r.txt", true));
		
        pw.println("Mejor Soluci�n: "+ bestSolution);
   		pw.println("Costo: " + bestSolution.getCosto());
   		pw.println("tiempo:"+timer.elapsedTime());
		pw.close();
	}
}
