package application;

import entities.Tabuleiro;
import java.util.Scanner;


public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Tabuleiro tabuleiro = new Tabuleiro();

        imprimirTabuleiro(tabuleiro.getTabuleiro());
    }


    public static void imprimirTabuleiro(String tabuleiro) {
        System.out.println(" " + tabuleiro.charAt(0) + " | " + tabuleiro.charAt(1) + " | " + tabuleiro.charAt(2));
        System.out.println("---|---|---");
        System.out.println(" " + tabuleiro.charAt(3) + " | " + tabuleiro.charAt(4) + " | " + tabuleiro.charAt(5));
        System.out.println("---|---|---");
        System.out.println(" " + tabuleiro.charAt(6) + " | " + tabuleiro.charAt(7) + " | " + tabuleiro.charAt(8));
    }

}