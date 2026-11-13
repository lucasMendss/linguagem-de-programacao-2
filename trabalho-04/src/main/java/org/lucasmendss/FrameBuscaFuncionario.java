package org.lucasmendss;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameBuscaFuncionario extends Frame {

    public FrameBuscaFuncionario() {
        super("TRABALHO PRATICO 04");

        setLayout(new BorderLayout(10, 10));
        setSize(400, 275);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(Color.GRAY);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        Panel topo = new Panel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        Label lblNome = new Label("Nome:");
        TextField campoNome = new TextField("marcelo", 30);
        topo.add(lblNome);
        topo.add(campoNome);
        add(topo, BorderLayout.NORTH);

        Panel linhaBusca = new Panel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        Button pesquisar = new Button("Pesquisar");
        linhaBusca.add(pesquisar);
        add(linhaBusca, BorderLayout.CENTER);

        Panel dados = new Panel(new GridLayout(4, 2, 10, 10));
        dados.setBackground(Color.LIGHT_GRAY);

        Label nomeLabel = new Label("Nome:");
        TextField nomeValor = new TextField("Marcelo");


        Label salarioLabel = new Label("Salário:");
        TextField salarioValor = new TextField("2000.00");


        Label cargoLabel = new Label("Cargo:");
        TextField cargoValor = new TextField("Administrativo");


        Button anterior = new Button("Anterior");
        Button proximo = new Button("Próximo");

        dados.add(nomeLabel);
        dados.add(nomeValor);
        dados.add(salarioLabel);
        dados.add(salarioValor);
        dados.add(cargoLabel);
        dados.add(cargoValor);
        dados.add(anterior);
        dados.add(proximo);

        add(dados, BorderLayout.SOUTH);
        setVisible(true);
    }
}
