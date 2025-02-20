import java.util.Scanner;
public class matriz {
	
	    public static void main(String[] args) {
	    	
	        Scanner leer = new Scanner(System.in);
	        
	        int filas, columnas;
	        
	        System.out.print("filas: ");
	         filas = leer.nextInt();
	        System.out.print("columnas: ");
	         columnas = leer.nextInt();

	        char[][] matriz = new char[filas][columnas];
	        
	        for (int i = 0; i < filas; i++) {
	        	
	            for (int j = 0; j < columnas; j++) {
	                matriz[i][j] = 'x';
	                
	            }
	        }

	      
	        int fila = 0, col = 0;
	        
	        while (fila < filas && col < columnas) {
	            matriz[fila][col] = '1';
	            if (fila + 1 < filas) {
	            	
	                matriz[fila + 1][col] = '1';
	            }
	            fila++;
	            col++;
	        }

	       
	        for (int i = 0; i < filas; i++) {
	            for (int j = 0; j < columnas; j++) {
	                System.out.print(matriz[i][j] + " ");
	            }
	            System.out.println();
	        }
	    }
	    
}


