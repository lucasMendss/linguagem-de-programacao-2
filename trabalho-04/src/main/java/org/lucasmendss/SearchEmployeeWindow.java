package org.lucasmendss;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchEmployeeWindow extends Frame {

    private static class Funcionario {
        private final String nome;
        private final String salario;
        private final String cargo;

        public Funcionario(String nome, String salario, String cargo) {
            this.nome = nome;
            this.salario = salario;
            this.cargo = cargo;
        }
    }

    private final TextField nomeABuscar = new TextField("", 30);
    private final TextField nome = new TextField("", 30);
    private final TextField salario = new TextField("", 30);
    private final TextField cargo = new TextField("", 30);
    private final Button pesquisar = new Button("Pesquisar");
    private final Button anterior = new Button("Anterior");
    private final Button proximo = new Button("Próximo");
    private final List<Funcionario> funcionarios = new ArrayList<>();

    private int indiceAtual = -1;
    private Connection conn;

    public SearchEmployeeWindow() {
        super("TRABALHO PRATICO 04");

        setLayout(new BorderLayout(10, 10));
        setSize(400, 275);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(Color.GRAY);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
                dispose();
            }
        });

        nome.setEditable(false);
        salario.setEditable(false);
        cargo.setEditable(false);

        pesquisar.addActionListener(e -> pesquisarFuncionario());
        anterior.addActionListener(e -> anterior());
        proximo.addActionListener(e -> proximo());

        Panel topo = new Panel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        Label lblNome = new Label("Nome:");

        topo.add(lblNome);
        topo.add(nomeABuscar);
        add(topo, BorderLayout.NORTH);

        Panel linhaBusca = new Panel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        linhaBusca.add(pesquisar);
        add(linhaBusca, BorderLayout.CENTER);

        Panel dados = new Panel(new GridLayout(4, 2, 10, 10));
        dados.setBackground(Color.LIGHT_GRAY);

        Label nomeLabel = new Label("Nome:");
        Label salarioLabel = new Label("Salário:");
        Label cargoLabel = new Label("Cargo:");

        dados.add(nomeLabel);
        dados.add(nome);
        dados.add(salarioLabel);
        dados.add(salario);
        dados.add(cargoLabel);
        dados.add(cargo);
        dados.add(anterior);
        dados.add(proximo);

        add(dados, BorderLayout.SOUTH);

        carregarTodosFuncionarios();
        setVisible(true);
    }

    private List<Funcionario> buscarFuncionarios(String nomeFiltro) throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = ConnectionFactory.getConnection();
        }

        String sql = "SELECT f.nome_func, f.sal_func, c.ds_cargo " +
                "FROM tbfuncs f INNER JOIN tbcargos c ON c.cd_cargo = f.cod_cargo ";

        if (nomeFiltro != null && !nomeFiltro.trim().isEmpty()) {
            sql += "WHERE UPPER(f.nome_func) = ? ";
        }

        sql += "ORDER BY f.nome_func";

        List<Funcionario> resultado = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            if (nomeFiltro != null && !nomeFiltro.trim().isEmpty()) {
                ps.setString(1, nomeFiltro.trim().toUpperCase());
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultado.add(new Funcionario(
                            rs.getString("nome_func"),
                            String.valueOf(rs.getDouble("sal_func")),
                            rs.getString("ds_cargo")
                    ));
                }
            }
        }

        return resultado;
    }

    private void carregarTodosFuncionarios() {
        try {
            funcionarios.clear();
            funcionarios.addAll(buscarFuncionarios(null));

            if (!funcionarios.isEmpty()) {
                indiceAtual = 0;
            } else {
                indiceAtual = -1;
                limparCampos();
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar funcionários: " + e.getMessage());
        }
    }

    private void exibirFuncionario(Funcionario funcionario) {
        if (funcionario == null) {
            limparCampos();
            return;
        }

        nome.setText(funcionario.nome);
        salario.setText(funcionario.salario);
        cargo.setText(funcionario.cargo);

        nome.repaint();
        salario.repaint();
        cargo.repaint();
        validate();
        repaint();
    }

    private void limparCampos() {
        nome.setText("");
        salario.setText("");
        cargo.setText("");
        repaint();
    }

    private void pesquisarFuncionario() {
        try {
            String termoBusca = nomeABuscar.getText().trim();

            if (termoBusca.isEmpty()) {
                carregarTodosFuncionarios();
                return;
            }

            List<Funcionario> encontrados = buscarFuncionarios(termoBusca);

            if (!encontrados.isEmpty()) {
                Funcionario funcionarioEncontrado = encontrados.get(0);

                for (int i = 0; i < funcionarios.size(); i++) {
                    if (funcionarios.get(i).nome.equalsIgnoreCase(funcionarioEncontrado.nome)) {
                        indiceAtual = i;
                        break;
                    }
                }

                exibirFuncionario(funcionarioEncontrado);
            } else {
                indiceAtual = -1;
                limparCampos();
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar funcionário: " + e.getMessage());
        }
    }

    private void anterior() {
        if (funcionarios.isEmpty()) {
            carregarTodosFuncionarios();
            return;
        }

        if (funcionarios.size() > 1) {
            if (indiceAtual > 0) {
                indiceAtual--;
            } else {
                indiceAtual = funcionarios.size() - 1;
            }
        } else {
            indiceAtual = 0;
        }

        exibirFuncionario(funcionarios.get(indiceAtual));
    }

    private void proximo() {
        if (funcionarios.isEmpty()) {
            carregarTodosFuncionarios();
            return;
        }

        if (funcionarios.size() > 1) {
            if (indiceAtual < funcionarios.size() - 1) {
                indiceAtual++;
            } else {
                indiceAtual = 0;
            }
        } else {
            indiceAtual = 0;
        }

        exibirFuncionario(funcionarios.get(indiceAtual));
    }
}
