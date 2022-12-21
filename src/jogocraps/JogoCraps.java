/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
//Jogo de dados Craps
package jogocraps;

import java.security.SecureRandom;

/**
 *
 * @author ph757
 */
public class JogoCraps {

    //Objeto gerador de numeros aleatorios pra usar no metodo jogarDados();
    private static final SecureRandom objRandom = new SecureRandom();

    //Constantes Enum que Representam o Estado do Jogo.
    private enum Status {
        CONTINUA, GANHOU, PERDEU
    };

    //Lançamentos Comuns Dos Dados
    private static final int SNAKE_EYES = 2;
    private static final int TREY = 3;
    private static final int SEVEN = 7;
    private static final int YO_LEVEN = 11;
    private static final int BOX_CARS = 12;

    /**
     * @param args the command line arguments
     */
    //Joga um Partida de Craps
    public static void main(String[] args) {

        int meusPontos = 0; //Pontos que se acumulam Caso Não Perca ou Ganhe na Primeira Rodada
        Status gameStatus; //Pode Conter Continua, Ganhou Ou Perdeu.

        int SomaDosDados = jogarDados(); //Primeira rolagem de Dados

        //Determina o Estado do Jogo e a Pontução no primeiro lançamento
        switch (SomaDosDados) {
            case SNAKE_EYES:
            case TREY:
            case BOX_CARS:
                gameStatus = Status.PERDEU;
                break;
            case SEVEN:
            case YO_LEVEN:
                gameStatus = Status.GANHOU;
                break;
            default: //nem ganhou nem perdeu registra a pontuação segue o jogo
                gameStatus = Status.CONTINUA;
                meusPontos = SomaDosDados; //informa a pontuação
                System.out.println("Meus Pontos São: " + meusPontos);
                break;
        }
        //Enquanto o Jogo nao estiver Completo
        while (gameStatus == Status.CONTINUA) {// ou seja nem perdeu nem ganhou

            SomaDosDados = jogarDados(); // lança os dados denovo

            if (SomaDosDados == meusPontos) {
                gameStatus = Status.GANHOU; //Vitoria Por Pontuação
            } else if (SomaDosDados == SEVEN) {
                gameStatus = Status.PERDEU; //Perde Antes de Atingir pontuação
            }

        }
//mostra mensagem ganhou ou perdeu
        if (gameStatus == Status.GANHOU) {
            System.out.println("Jogador Ganhou");
        } else {
            System.out.println("Jogador Perdeu");
        }

    }

    //Lança os dados, Calcula a Soma e Mostra o Resultado
    private static int jogarDados() {

        //Seleciona os valores aleatorios
        int jogada1 = 1 + objRandom.nextInt(6);//primeiro lançamento de dado
        int jogada2 = 1 + objRandom.nextInt(6);//segundo lançamento de dado

        int soma = jogada1 + jogada2; //Soma os valores dos dados
        System.out.printf("jogador lançou %d + %d = %d%n", jogada1, jogada2, soma);
        return soma;
    }
}
