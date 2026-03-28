package Server;

import entities.Jogador;
import entities.Tabuleiro;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void serverPlay(){
        try {
            ServerSocket server = new ServerSocket(12345);
            System.out.println("Aguardando jogadores...");

            // ACEITA O JOGADOR 1
            Socket socketJ1 = server.accept();
            System.out.println("Jogador 1 conectado!");
            DataOutputStream saidaJ1 = new DataOutputStream(socketJ1.getOutputStream());
            DataInputStream entradaJ1 = new DataInputStream(socketJ1.getInputStream());

            // ACEITA O JOGADOR 2 IMEDIATAMENTE
            Socket socketJ2 = server.accept();
            System.out.println("Jogador 2 conectado!");
            DataOutputStream saidaJ2 = new DataOutputStream(socketJ2.getOutputStream());
            DataInputStream entradaJ2 = new DataInputStream(socketJ2.getInputStream());


            saidaJ1.writeUTF("ESCOLHER_SIMBOLO");
            saidaJ2.writeUTF("AGUARDANDO_OPONENTE");


            byte opcao = entradaJ1.readByte();

            char simbJ1 = (opcao == 1) ? 'X' : 'O';
            char simbJ2 = (simbJ1 == 'X') ? 'O' : 'X';


            saidaJ1.writeChar(simbJ1);
            saidaJ2.writeChar(simbJ2);

            System.out.println("Partida iniciada: J1=" + simbJ1 + " | J2=" + simbJ2);

            // Inicia a lógica do jogo
            gerenciarPartida(saidaJ1, entradaJ1, simbJ1, saidaJ2, entradaJ2, simbJ2);

        } catch (IOException e){
            System.out.println("Erro no servidor: " + e.getMessage());
        }
    }

    public void gerenciarPartida(DataOutputStream saidaJ1, DataInputStream entradaJ1, char simb1,
                                 DataOutputStream saidaJ2, DataInputStream entradaJ2, char simb2) {

        Tabuleiro tabuleiro = new Tabuleiro();
        boolean rodando = true;

        // Jogador 1
        int turno = 1;

        try {
            while (rodando) {
                // Envia o estado atual do tabuleiro para ambos os clientes
                saidaJ1.writeUTF(tabuleiro.getTabuleiro());
                saidaJ2.writeUTF(tabuleiro.getTabuleiro());

                // Definir quem é o jogador da vez
                boolean vezDoJ1 = (turno % 2 != 0);

                // Avisar aos clientes de quem é a vez
                saidaJ1.writeBoolean(vezDoJ1);
                saidaJ2.writeBoolean(!vezDoJ1);

                // 3. Receber a jogada apenas de quem está na vez
                int posicao;
                char simboloAtual;

                if (vezDoJ1) {
                    posicao = entradaJ1.readInt();
                    simboloAtual = simb1;
                } else {
                    posicao = entradaJ2.readInt();
                    simboloAtual = simb2;
                }

                // Realizar a jogada no objeto Tabuleiro
                if (tabuleiro.jogar(posicao, simboloAtual)) {

                    // Verificar se alguém venceu
                    if (tabuleiro.vencedor()) {
                        finalizarPartida(saidaJ1, saidaJ2, tabuleiro.getTabuleiro(), "Vitória do " + simboloAtual);
                        rodando = false;
                    }
                    // Verificar se deu empate
                    else if (tabuleiro.empate()) {
                        finalizarPartida(saidaJ1, saidaJ2, tabuleiro.getTabuleiro(), "Empate! Deu Velha.");
                        rodando = false;
                    }
                    // Se o jogo continua, passa o turno
                    else {
                        turno++;
                    }
                } else {

                    System.out.println("Jogada inválida recebida. Repetindo turno.");
                }
            }
        } catch (IOException e) {
            System.out.println("Conexão perdida durante a partida: " + e.getMessage());
        }
    }

    // Método encerrar
    private void finalizarPartida(DataOutputStream s1, DataOutputStream s2, String tabFinal, String msg) throws IOException {
        s1.writeUTF(tabFinal);
        s2.writeUTF(tabFinal);
        s1.writeUTF("FIM:" + msg);
        s2.writeUTF("FIM:" + msg);
    }


}
