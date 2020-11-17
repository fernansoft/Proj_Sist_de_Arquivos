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
        // String arq = "C:/temp/Codigos_Pessoas.csv";
        String arq = "Codigos_Pessoas.csv"; // usei esse diretorio para acessar o CSV da pasta do projeto
        int codigoVerificacao = 123;
        
        int codigoInsercao = 645;
        String nomeIsercao = "Juliano";
        String emailInsercao = "Juliano645@gmail.com";

        try {
            System.out.println("\n\rMetodo VerificaDirTemp:");
            arqController.verificaDirTemp();
            System.out.println("=> Funcao Verifica cadastro: "+ arqController.verificaCadastro(arq, codigoVerificacao));
            System.out.println("\n\rMetodo ImprimeCadastro:");
            arqController.imprimeCadastro(arq, codigoVerificacao);
            System.out.println("\n\rMetodo InsereCadastro:\r\n");
            arqController.insereCadastro(arq, codigoInsercao, nomeIsercao, emailInsercao);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}