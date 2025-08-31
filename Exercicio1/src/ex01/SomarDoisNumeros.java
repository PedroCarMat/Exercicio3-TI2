package ex01;
import java.util.*;

class SomarDoisNumeros {
	
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int num1 = 0;
		int num2 = 0;
		int soma = 0;
		
		System.out.println("Primeiro Numero:");
		num1 = sc.nextInt();
		
		System.out.println("Segundo Numero:");
		num2 = sc.nextInt();
		
		soma = num1 + num2;
		
		System.out.println("A soma e igual a:");
		System.out.println(soma);

	}

}
