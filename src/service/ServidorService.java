package service;

import java.net.ServerSocket;
import java.io.*;
import java.net.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ServidorService {

    /*
    * Isso é um exemplo de uma lógica de sistemas multiusuário utilizando o socket, onde dará espaço para os clientes se conectarem
    * */

    //Lista todos os outputs dos clientes conectados
    private static Set<PrintWriter> clientes = new HashSet<>();

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(1234);

        System.out.println("Servidor rodando na porta " + servidor + "...");

        while (true) {
            Socket socket = servidor.accept();
            new Thread(new ManipuladorCliente(socket)).start();
        }
    }

    private static class ManipuladorCliente implements Runnable {
        private Socket socket;
        private PrintWriter out;

        public ManipuladorCliente(Socket socket) {
            this.socket = socket;
        }


        public void run() {
            try {
                Scanner in = new Scanner(System.in);

                out = new PrintWriter(socket.getOutputStream(), true);

                synchronized (clientes) {
                    while (in.hasNextLine()) {
                        String msg =  in.nextLine();
                        System.out.println("Mensagem recebida: " + msg);

                        for (PrintWriter cliente : clientes) {
                            cliente.println(msg);
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
