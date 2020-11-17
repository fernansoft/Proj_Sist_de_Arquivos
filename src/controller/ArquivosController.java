package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ArquivosController implements IArquivosController {
    @Override
    public void verificaDirTemp() throws IOException {
        System.out.println("\r\nVerificando a existencia do diretorio 'C:/TEMP'...");
        File temp = new File("C:/TEMP");
        if (temp.exists() && temp.isDirectory()) {
            System.out.println("\r\nDiretorio existente!");
        } else {
            System.out.println("\r\nDiretorio inexistente!");
            temp.mkdir();
            System.out.println("\r\nDiretorio criado com sucesso!");
        }
    }

    @Override
    public boolean verificaCadastro(String arquivo, int codigo) throws IOException {
        File arquiv = new File(arquivo);
        if (arquiv.exists() && arquiv.isFile()) {
            FileInputStream fluxo = new FileInputStream(arquiv); // abrir/ler arquivo em bits
            InputStreamReader leitor = new InputStreamReader(fluxo); // converte o InputStream em String
            BufferedReader buffer = new BufferedReader(leitor);// buffer que recebe o conteudo do InputStream
            String linha = null;
            String tipoSeparacao = ";"; // tipo de split para o CSV
            int colunaBusca = 0; // coluna na qual quer buscar o valor

            while ((linha = buffer.readLine()) != null) { // buscando EOF (End of file)
                String[] leitura = linha.split(tipoSeparacao); // leitura[0]=coluna 1, leitura[2]=coluna2...
                if (leitura[colunaBusca].equals(Integer.toString(codigo))) {
                    return true;
                }
            }
            buffer.close();
            leitor.close();
            fluxo.close();
        } else {
            throw new IOException("Arquivo invalido!");
        }
        return false;
    }

    @Override
    public void imprimeCadastro(String arquivo, int codigo) throws IOException {
        if (verificaCadastro(arquivo, codigo)) {
            FileInputStream fluxo = new FileInputStream(arquivo); // abrir/ler arquivo em bits
            InputStreamReader leitor = new InputStreamReader(fluxo); // converte o InputStream em String
            BufferedReader buffer = new BufferedReader(leitor);// buffer que recebe o conteudo do InputStream
            String linha = null;
            String tipoSeparacao = ";"; // tipo de split para o CSV
            int colunaBusca = 0; // coluna na qual quer buscar o valor

            while ((linha = buffer.readLine()) != null) { // buscando EOF (End of file)
                String[] leitura = linha.split(tipoSeparacao); // leitura[0]=coluna 1, leitura[2]=coluna2...
                if (leitura[colunaBusca].equals(Integer.toString(codigo))) {
                    System.out.println("\r\nCódigo encontrado!\r\nDados:\r\n\r\nCódigo => " + leitura[0]
                            + "\r\nNome => " + leitura[1] + "\r\nE-mail => " + leitura[2] + "\r\n");
                }
            }
            buffer.close();
            leitor.close();
            fluxo.close();
        } else {
            throw new IOException("Arquivo ou codigo invalido!");
        }
    }

    @Override
    public void insereCadastro(String arquivo, int codigo, String nome, String email) throws IOException {
        if (!verificaCadastro(arquivo, codigo)) {
            String conteudo = geraTxt(codigo, nome, email);
            FileWriter fileWriter = new FileWriter(arquivo, true);
            PrintWriter print = new PrintWriter(fileWriter);
            print.write(conteudo);
            print.flush();
            print.close();
            fileWriter.close();
            System.out.println("\r\nCadastro inserido com sucesso!\r\n");
        } else {
            throw new IOException("Arquivo invalido ou codigo ja existente!");
        }
    }

    private String geraTxt(int codigo, String nome, String email) {
        StringBuffer buffer = new StringBuffer();
        String linha = "";
        linha = (Integer.toString(codigo));
        buffer.append(linha + ";");
        linha = nome;
        buffer.append(linha + ";");
        linha = email;
        buffer.append(linha + ";\r\n");
        return buffer.toString();
    }

}