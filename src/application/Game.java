package application;

import Server.Server;
import entities.Tabuleiro;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        Server server = new Server();
        server.serverPlay();

    }

}