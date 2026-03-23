package entities;

public class Tabuleiro {

    private String tabuleiro = "123456789";

    public Tabuleiro() {}

    public Tabuleiro(String tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public String getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(String tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public void jogar(int pos, char caractere) {
        if(tabuleiro.charAt(pos) == 'X' || tabuleiro.charAt(pos) == 'O') {
            System.out.println("Jogada inválida");
            return;
        }

        StringBuilder builder = new StringBuilder(tabuleiro);
        builder.setCharAt(pos, caractere);
        tabuleiro = builder.toString();
    }

}