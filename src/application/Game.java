package application;

import entities.Tabuleiro;

import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Tabuleiro tabuleiro = new Tabuleiro();

        imprimirTabuleiroxyz(tabuleiro.getTabuleiro());
        System.out.println();
        System.out.println("--- JOGAR ---");
        System.out.println("Posição: ");
        int posicao1 = sc.nextInt();
        System.out.println("X ou O: ");
        char opcao1 = sc.next().charAt(0);
        tabuleiro.jogar(posicao1 - 1, opcao1);
        imprimirTabuleiroxyz(tabuleiro.getTabuleiro());
        System.out.println();
        tabuleiro.jogar(3, 'O');
        imprimirTabuleiroxyz(tabuleiro.getTabuleiro());
        System.out.println();
        tabuleiro.jogar(3, 'X');
        imprimirTabuleiroxyz(tabuleiro.getTabuleiro());
        System.out.println();
        tabuleiro.jogar(7, 'X');
        imprimirTabuleiroxyz(tabuleiro.getTabuleiro());
        System.out.println();

    }


    public static void imprimirTabuleiroxyz(String tabuleiro) {
        System.out.println(" " + tabuleiro.charAt(0) + " | " + tabuleiro.charAt(1) + " | " + tabuleiro.charAt(2));
        System.out.println("---|---|---");
        System.out.println(" " + tabuleiro.charAt(3) + " | " + tabuleiro.charAt(4) + " | " + tabuleiro.charAt(5));
        System.out.println("---|---|---");
        System.out.println(" " + tabuleiro.charAt(6) + " | " + tabuleiro.charAt(7) + " | " + tabuleiro.charAt(8));
    }

}