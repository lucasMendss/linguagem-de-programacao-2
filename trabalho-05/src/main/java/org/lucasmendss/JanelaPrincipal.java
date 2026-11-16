
package main.java.org.lucasmendss;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class JanelaPrincipal {

    private static JPanel painelSuperior = new JPanel();
    private static JPanel painelIniciar = new JPanel();
    private static JPanel painelListas = new JPanel();
    private static JPanel painelLimpar = new JPanel();

    private static JTextField txtMaximo = new JTextField();
    private static JTextField txtPrioridade1 = new JTextField();
    private static JTextField txtPrioridade2 = new JTextField();

    private static JButton btnIniciar = new JButton("Iniciar");
    private static JButton btnLimpar = new JButton("Limpar");

    private static DefaultListModel<String> modelo1 =
            new DefaultListModel<>();

    private static DefaultListModel<String> modelo2 =
            new DefaultListModel<>();

    private static JList<String> lista1 = new JList<>(modelo1);
    private static JList<String> lista2 = new JList<>(modelo2);

    private static JLabel lblFim1 = new JLabel("Fim1");
    private static JLabel lblFim2 = new JLabel("Fim2");

    private static Thread thread1;
    private static Thread thread2;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame janela = new JFrame("Trabalho Prático 05 - Threads");

            janela.setSize(700, 500);
            janela.setLocationRelativeTo(null);

            JPanel painelPrincipal =
                    new JPanel(new GridLayout(4, 1, 10, 10));

            painelPrincipal.setBorder(
                    new EmptyBorder(15, 15, 15, 15));

            // Painel Superior
            painelSuperior.setLayout(
                    new GridLayout(3, 2, 10, 10));

            painelSuperior.add(new JLabel("Máximo:"));
            painelSuperior.add(txtMaximo);

            painelSuperior.add(
                    new JLabel("Prioridade 1 (1 a 10):"));
            painelSuperior.add(txtPrioridade1);

            painelSuperior.add(
                    new JLabel("Prioridade 2 (1 a 10):"));
            painelSuperior.add(txtPrioridade2);

            // Painel Iniciar
            painelIniciar.setLayout(new BorderLayout());
            painelIniciar.add(btnIniciar, BorderLayout.CENTER);

            painelIniciar.setBorder(
                    new EmptyBorder(30, 70, 30, 70));

            // Painel Listas
            painelListas.setLayout(
                    new GridLayout(1, 2, 5, 5));

            painelListas.setBorder(
                    new EmptyBorder(0, 100, 0, 100));

            JPanel coluna1 = new JPanel(new BorderLayout());
            JPanel coluna2 = new JPanel(new BorderLayout());

            lblFim1.setVisible(false);
            lblFim2.setVisible(false);

            coluna1.add(new JScrollPane(lista1),
                    BorderLayout.CENTER);
            coluna1.add(lblFim1, BorderLayout.SOUTH);

            coluna2.add(new JScrollPane(lista2),
                    BorderLayout.CENTER);
            coluna2.add(lblFim2, BorderLayout.SOUTH);

            painelListas.add(coluna1);
            painelListas.add(coluna2);

            // Painel Limpar
            painelLimpar.setLayout(new BorderLayout());
            painelLimpar.add(btnLimpar, BorderLayout.CENTER);

            painelLimpar.setBorder(
                    new EmptyBorder(30, 70, 30, 70));

            painelPrincipal.add(painelSuperior);
            painelPrincipal.add(painelIniciar);
            painelPrincipal.add(painelListas);
            painelPrincipal.add(painelLimpar);

            janela.add(painelPrincipal);

            janela.setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE);

            // Eventos
            btnIniciar.addActionListener(e -> iniciarThreads());

            btnLimpar.addActionListener(e -> limpar());

            janela.setVisible(true);
        });
    }

    private static void iniciarThreads() {

        try {

            int maximo = Integer.parseInt(
                    txtMaximo.getText());

            int prioridade1 = Integer.parseInt(
                    txtPrioridade1.getText());

            int prioridade2 = Integer.parseInt(
                    txtPrioridade2.getText());

            if (maximo < 1 ||
                prioridade1 < 1 || prioridade1 > 10 ||
                prioridade2 < 1 || prioridade2 > 10) {

                JOptionPane.showMessageDialog(
                        null,
                        "Informe um máximo positivo e prioridades entre 1 e 10.");

                return;
            }

            if ((thread1 != null && thread1.isAlive()) ||
                (thread2 != null && thread2.isAlive())) {

                JOptionPane.showMessageDialog(
                        null,
                        "As threads já estão em execução.");

                return;
            }

            modelo1.clear();
            modelo2.clear();

            lblFim1.setVisible(false);
            lblFim2.setVisible(false);

            btnIniciar.setEnabled(false);

            // Criação da primeira thread
            thread1 = new Thread(() -> {

                for (int i = 1; i <= maximo; i++) {

                    final int valor = i;

                    SwingUtilities.invokeLater(() ->
                            modelo1.addElement(
                                    String.valueOf(valor)));

                    try {
                        Thread.sleep(110 - (prioridade1 * 10));
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                SwingUtilities.invokeLater(() -> {
                    lblFim1.setVisible(true);
                    verificarFim();
                });

            });

            // Criação da segunda thread
            thread2 = new Thread(() -> {

                for (int i = 1; i <= maximo; i++) {

                    final int valor = i;

                    SwingUtilities.invokeLater(() ->
                            modelo2.addElement(
                                    String.valueOf(valor)));

                    try {
                        Thread.sleep(110 - (prioridade2 * 10));
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                SwingUtilities.invokeLater(() -> {
                    lblFim2.setVisible(true);
                    verificarFim();
                });

            });

            // Definindo prioridades
            thread1.setPriority(prioridade1);
            thread2.setPriority(prioridade2);

            // Iniciando as duas threads
            thread1.start();
            thread2.start();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    null,
                    "Preencha todos os campos com números válidos.");
        }
    }

    private static void verificarFim() {

        if (!thread1.isAlive() && !thread2.isAlive()) {
            btnIniciar.setEnabled(true);
        }
    }

    private static void limpar() {

        if ((thread1 != null && thread1.isAlive()) ||
            (thread2 != null && thread2.isAlive())) {

            JOptionPane.showMessageDialog(
                    null,
                    "Aguarde as threads terminarem para limpar.");

            return;
        }

        modelo1.clear();
        modelo2.clear();

        txtMaximo.setText("");
        txtPrioridade1.setText("");
        txtPrioridade2.setText("");

        lblFim1.setVisible(false);
        lblFim2.setVisible(false);

        btnIniciar.setEnabled(true);
    }
}