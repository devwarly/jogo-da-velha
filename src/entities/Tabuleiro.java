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

    public boolean jogar(int pos, char caractere) {

        // pos - 1 para conver ter 1-9 em indice 0-8
        if (pos < 1 || pos > 9 || tabuleiro.charAt(pos - 1) == 'X' || tabuleiro.charAt(pos - 1) == 'O'){
            return false;
        }

        StringBuilder builder = new StringBuilder(tabuleiro);
        builder.setCharAt(pos - 1, caractere);
        tabuleiro = builder.toString();
        return true;
    }

    public boolean vencedor(){
        int[][] vitorias = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Linhas
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Colunas
                {0, 4, 8}, {2, 4, 6}             // Diagonais
        };

        for (int[] combo : vitorias){
            char p1 = tabuleiro.charAt(combo[0]);
            char p2 = tabuleiro.charAt(combo[1]);
            char p3 = tabuleiro.charAt(combo[2]);

            // Se os três forem iguais e forem 'X' ou 'O'
            if (p1 == p2 && p2 == p3){
                return true;
            }
        }

        return false;
    }

    public boolean empate(){
        // Não tem vencendor e não existe mais números de de 1 a 9 na string
        return !vencedor() && !tabuleiro.matches(".*[1-9].*");
    }


}