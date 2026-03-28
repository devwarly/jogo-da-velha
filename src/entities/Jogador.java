package entities;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Jogador {
    public char sim;
    public DataOutputStream saida;
    public DataInputStream entrada;

    public Jogador(char sim, DataOutputStream saida, DataInputStream entrada) {
        this.sim = sim;
        this.saida = saida;
        this.entrada = entrada;
    }
}
