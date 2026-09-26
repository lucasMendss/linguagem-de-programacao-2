package trabalho.pkg02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// IFSP CBT ADS 2026 - Linguagem de Programação 2
// Aluno: João Pedro Badaró
// Aluno: Lucas Rafael Silva Mendes
public class FormCadastroAluno extends Frame implements WindowListener {

    FormCadastroAluno(String titulo, int largura, int altura) {
        super(titulo);
        setSize(largura, altura);

        Dimension limiteTela = Toolkit.getDefaultToolkit().getScreenSize();
        int xCentralizado = (limiteTela.width - largura) / 2;
        int yCentralizado = (limiteTela.height - altura) / 2;

        setLocation(xCentralizado, yCentralizado);
    }

    public static void main(String[] args) {
        List<Aluno> alunosCadastrados = new ArrayList<Aluno>();

        FormCadastroAluno form = new FormCadastroAluno("Cadastro de Aluno - TP02 - LPR2", 400, 180);
        form.addWindowListener(form);
        form.setLayout(new BorderLayout(10, 10));
        form.setBackground(Color.LIGHT_GRAY);
        form.setResizable(false);

        // grid 3x2 (centralizado)
        Panel campos = new Panel();
        campos.setLayout(new GridLayout(3, 2, 10, 10));

        campos.add(new Label("Nome: "));
        TextField textFieldNome = new TextField();
        campos.add(textFieldNome);

        campos.add(new Label("Idade: "));
        TextField textFieldIdade = new TextField();
        campos.add(textFieldIdade);

        campos.add(new Label("Endereço: "));
        TextField textFieldEndereco = new TextField();
        campos.add(textFieldEndereco);

        form.add(campos, BorderLayout.CENTER);

        // grid 1x4 (base)
        Panel botoes = new Panel();
        botoes.setLayout(new GridLayout(1, 4, 5, 5));

        Button buttonOk = new Button("Ok");
        buttonOk.addActionListener(e -> {
            String nome = textFieldNome.getText();
            if(nome.equals("") || nome.length() < 3 || !nome.matches("^[A-Za-z]*$")) {
                JOptionPane.showMessageDialog(form, "Insira um nome válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            String endereco = textFieldEndereco.getText();
            if(endereco.equals("") ||  endereco.length() < 3 || !endereco.matches("^[A-Za-z0-9\\s]*$")) {
                JOptionPane.showMessageDialog(form, "Insira um endereço válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            try{
                int idade = Integer.parseInt(textFieldIdade.getText());
                Aluno a = new Aluno(textFieldEndereco.getText(), idade, textFieldNome.getText(), UUID.randomUUID());
                alunosCadastrados.add(a);
            }
            catch (NumberFormatException erro){
                JOptionPane.showMessageDialog(form, "Insira uma idade válida.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            limparCamposDoPainel(campos);
            JOptionPane.showMessageDialog(form, "Aluno cadastrado com sucesso.");
        });
        
        Button buttonLimpar = new Button("Limpar");
        buttonLimpar.addActionListener(e -> {
            limparCamposDoPainel(campos);
        });

        Button buttonMostrar = new Button("Mostrar");
        buttonMostrar.addActionListener(e -> {
            StringBuilder mensagemAlunosCadastrados = new StringBuilder("Alunos cadastrados:\n");

            alunosCadastrados.forEach(a -> {
                mensagemAlunosCadastrados.append("Id: " + a.getUuid()).append(" | Nome: ").append(a.getNome()).append("\n");
            });
            JOptionPane.showMessageDialog(form, mensagemAlunosCadastrados.toString());
        });

        Button buttonFechar = new Button("Fechar");
        buttonFechar.addActionListener(e -> {
            System.exit(0);
        });

        botoes.add(buttonOk);
        botoes.add(buttonLimpar);
        botoes.add(buttonMostrar);
        botoes.add(buttonFechar);
        form.add(botoes, BorderLayout.SOUTH);

        form.setVisible(true);
    }

    private static void limparCamposDoPainel(Panel painel) {
        for (Component comp : painel.getComponents()) {
            if (comp instanceof TextField) {
                ((TextField) comp).setText("");
            }
        }
    }

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    public void windowOpened(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
}