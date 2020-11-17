package view;

import java.io.IOException;
import java.util.Scanner;

import controller.ArquivosController;
import controller.IArquivosController;

public class Principal {
    public static void main(String[] args) {
        IArquivosController arqController = new ArquivosController();
        // Scanner scan = new Scanner(System.in);
        // System.out.println("Insira o caminho do arquivo csv: ");
        // String arq = scan.next();
        // System.out.println("Insira o código a ser buscado:");
        // int cod = scan.nextInt();
        String arq = "C:/temp/Codigos_Pessoas.csv";
        int cod = 123;
        int code = 576;
        String nome = "Everton";
        String email = "Everton576@gamail.com";

        try {
            System.out.println("\n\rMetodo VerificaDirTemp:");
            arqController.verificaDirTemp();
            System.out.println("=> Funcao Verifica cadastro: "+ arqController.verificaCadastro(arq, cod));
            System.out.println("\n\rMetodo ImprimeCadastro:");
            arqController.imprimeCadastro(arq, cod);
            System.out.println("\n\rMetodo InsereCadastro:");
            arqController.insereCadastro(arq, code, nome, email);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}