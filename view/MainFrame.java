package cooperativa.view;

import cooperativa.controller.AgricultorController;
import cooperativa.controller.ClienteController;
import cooperativa.controller.FornecedorController;
import cooperativa.controller.ProdutoAgricolaController;
import cooperativa.controller.PropriedadeController;
import cooperativa.controller.VendaController;
import cooperativa.controller.VendaController;
import cooperativa.controller.VendaProdutoController;
import cooperativa.model.VendaProduto;
import cooperativa.model.Propriedade;
import cooperativa.model.Venda;
import cooperativa.util.RelatoriosUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import cooperativa.model.Agricultor;
import cooperativa.model.Cliente;
import cooperativa.model.Fornecedor;
import cooperativa.model.ProdutoAgricola;


public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        setTitle("Sistema de Gerenciamento");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 800);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
            
        // Instanciação de views
        ProdutoAgricolaView produtoView = new ProdutoAgricolaView();
        FornecedorView fornecedorView = new FornecedorView();
        AgricultorView agricultorView = new AgricultorView();
        ClienteView clienteView = new ClienteView();

        // Adiciona as telas ao painel principal
        mainPanel.add(new PropriedadePanel(this), "Propriedades");
        mainPanel.add(new VendaPanel(this), "Vendas");
        mainPanel.add(produtoView.criarTela(), "ProdutoAgricola");
        mainPanel.add(fornecedorView.criarTela(), "Fornecedores");
        mainPanel.add(agricultorView.criarTela(), "Agricultores");
        mainPanel.add(clienteView.criarTela(), "Clientes");
        mainPanel.add(new RelatorioPanel(), "Relatorios");

        // Menu de navegação
        JPanel menuPanel = new JPanel(new FlowLayout());
        menuPanel.setBackground(new Color(173, 216, 230));
        
        JButton produtoButton = new JButton("Produtos Agrícolas");
        JButton propriedadesButton = new JButton("Gerenciar Propriedades");
        JButton vendasButton = new JButton("Gerenciar Vendas");
        JButton fornecedoresButton = new JButton("Gerenciar Fornecedores");
        JButton agricultoresButton = new JButton("Gerenciar Agricultores");
        JButton clientesButton = new JButton("Gerenciar Clientes");
        JButton relatoriosButton = new JButton("Relatórios");

        propriedadesButton.addActionListener(e -> cardLayout.show(mainPanel, "Propriedades"));
        vendasButton.addActionListener(e -> cardLayout.show(mainPanel, "Vendas"));
        produtoButton.addActionListener(e -> cardLayout.show(mainPanel, "ProdutoAgricola"));
        fornecedoresButton.addActionListener(e -> cardLayout.show(mainPanel, "Fornecedores"));
        agricultoresButton.addActionListener(e -> cardLayout.show(mainPanel, "Agricultores"));
        clientesButton.addActionListener(e -> cardLayout.show(mainPanel, "Clientes"));
        relatoriosButton.addActionListener(e -> cardLayout.show(mainPanel, "Relatorios"));

        menuPanel.add(propriedadesButton);
        menuPanel.add(vendasButton);
        menuPanel.add(produtoButton);
        menuPanel.add(fornecedoresButton);
        menuPanel.add(agricultoresButton);
        menuPanel.add(clientesButton);
        menuPanel.add(relatoriosButton);

        add(menuPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }

   // Painel para Relatórios
class RelatorioPanel extends JPanel {
    public RelatorioPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(173, 216, 230));
        
        JLabel titulo = new JLabel("Relatórios", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        // Botões para os relatórios
        JButton vendasRelatorioButton = new JButton("Relatório de Vendas por Cliente");
        JButton propriedadesRelatorioButton = new JButton("Relatório de Propriedades");
        JButton produtosRelatorioButton = new JButton("Relatório de Produtos Agrícolas");
        JButton vendasDetalhadasRelatorioButton = new JButton("Relatório Detalhado de Vendas");
        JButton estoqueProdutosRelatorioButton = new JButton("Relatório de Estoque");

        // Adicionando os botões ao painel
        add(titulo);
        add(Box.createRigidArea(new Dimension(0, 20))); // Espaço entre componentes
        add(vendasRelatorioButton);
        add(propriedadesRelatorioButton);
        add(produtosRelatorioButton);
        add(vendasDetalhadasRelatorioButton);
        add(estoqueProdutosRelatorioButton);

        // Ações para os relatórios
        vendasRelatorioButton.addActionListener(e -> {
            try {
                String relatorio = RelatoriosUtil.gerarRelatorioVendasPorCliente();
                JOptionPane.showMessageDialog(this, relatorio);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao gerar relatório de vendas por cliente: " + ex.getMessage());
            }
        });

        propriedadesRelatorioButton.addActionListener(e -> {
            try {
                String relatorio = RelatoriosUtil.gerarRelatorioPropriedades();
                JOptionPane.showMessageDialog(this, relatorio);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao gerar relatório de propriedades: " + ex.getMessage());
            }
        });

        produtosRelatorioButton.addActionListener(e -> {
            try {
                String relatorio = RelatoriosUtil.gerarRelatorioProdutosAgricolas();
                JOptionPane.showMessageDialog(this, relatorio);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao gerar relatório de produtos agrícolas: " + ex.getMessage());
            }
        });

        vendasDetalhadasRelatorioButton.addActionListener(e -> {
            try {
                String relatorio = RelatoriosUtil.gerarRelatorioVendasDetalhado();
                JOptionPane.showMessageDialog(this, relatorio);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao gerar relatório detalhado de vendas: " + ex.getMessage());
            }
        });

        estoqueProdutosRelatorioButton.addActionListener(e -> {
            try {
                String relatorio = RelatoriosUtil.gerarRelatorioEstoqueProdutos();
                JOptionPane.showMessageDialog(this, relatorio);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao gerar relatório de estoque: " + ex.getMessage());
            }
        });
    }
}
 
}


    // Painel para gerenciar propriedades
    class PropriedadePanel extends JPanel {
        private PropriedadeController controller;

        public PropriedadePanel(JFrame parentFrame) {
            this.controller = new PropriedadeController();
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBackground(new Color(173, 216, 230));
           
            
            JTextField nomeField = new JTextField(20);
            JTextField localizacaoField = new JTextField(20);
            JTextField tamanhoField = new JTextField(20);
            JTextField agricultorIdField = new JTextField(20);

            JButton adicionarButton = new JButton("Adicionar");
            JButton listarButton = new JButton("Listar");
            JButton atualizarButton = new JButton("Atualizar");
            JButton removerButton = new JButton("Remover");

            add(new JLabel("Nome:"));
            add(nomeField);
            add(new JLabel("Localização:"));
            add(localizacaoField);
            add(new JLabel("Tamanho (ha):"));
            add(tamanhoField);
            add(new JLabel("ID do Agricultor:"));
            add(agricultorIdField);
            add(adicionarButton);
            add(listarButton);
            add(atualizarButton);
            add(removerButton);

            adicionarButton.addActionListener(e -> {
                try {
                    Propriedade propriedade = new Propriedade(
                            0,
                            nomeField.getText(),
                            localizacaoField.getText(),
                            Double.parseDouble(tamanhoField.getText()),
                            Integer.parseInt(agricultorIdField.getText())
                    );
                    controller.adicionarPropriedade(propriedade);
                    JOptionPane.showMessageDialog(parentFrame, "Propriedade adicionada com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(parentFrame, "Erro ao adicionar propriedade: " + ex.getMessage());
                }
            });

            listarButton.addActionListener(e -> {
                try {
                    List<Propriedade> propriedades = controller.listarPropriedades();
                    StringBuilder lista = new StringBuilder("Propriedades:\n");
                    for (Propriedade propriedade : propriedades) {
                        lista.append("ID: ").append(propriedade.getId())
                                .append(", Nome: ").append(propriedade.getNome())
                                .append(", Localização: ").append(propriedade.getLocalizacao())
                                .append(", Tamanho: ").append(propriedade.getTamanhoHa())
                                .append(" ha\n");
                    }
                    JOptionPane.showMessageDialog(parentFrame, lista.toString());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(parentFrame, "Erro ao listar propriedades: " + ex.getMessage());
                }
            });

            atualizarButton.addActionListener(e -> {
                try {
                    String idStr = JOptionPane.showInputDialog(parentFrame, "Digite o ID da propriedade para atualizar:");
                    int id = Integer.parseInt(idStr);
                    Propriedade propriedade = new Propriedade(
                            id,
                            nomeField.getText(),
                            localizacaoField.getText(),
                            Double.parseDouble(tamanhoField.getText()),
                            Integer.parseInt(agricultorIdField.getText())
                    );
                    controller.atualizarPropriedade(propriedade);
                    JOptionPane.showMessageDialog(parentFrame, "Propriedade atualizada com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(parentFrame, "Erro ao atualizar propriedade: " + ex.getMessage());
                }
            });

            removerButton.addActionListener(e -> {
                try {
                    String idStr = JOptionPane.showInputDialog(parentFrame, "Digite o ID da propriedade para remover:");
                    int id = Integer.parseInt(idStr);
                    controller.removerPropriedade(id);
                    JOptionPane.showMessageDialog(parentFrame, "Propriedade removida com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(parentFrame, "Erro ao remover propriedade: " + ex.getMessage());
                }
            });
        }
    }

    // Painel para gerenciar vendas
  // Painel para gerenciar vendas
class VendaPanel extends JPanel {
    private VendaController controller;
    private VendaProdutoController vendaProdutoController;
    private JTable produtosTable;
    private DefaultTableModel tableModel;

    public VendaPanel(JFrame parentFrame) {
        this.controller = new VendaController();
        this.vendaProdutoController = new VendaProdutoController();
        setLayout(new BorderLayout());
        setBackground(new Color(173, 216, 230));
        
        // Painel superior com campos e botões de venda
        JPanel vendaPanel = new JPanel();
        vendaPanel.setLayout(new BoxLayout(vendaPanel, BoxLayout.Y_AXIS));
        vendaPanel.setBackground(new Color(173, 216, 230));
        
        JTextField dataField = new JTextField(20);
        JTextField valorTotalField = new JTextField(20);
        JTextField clienteIdField = new JTextField(20);

        JButton adicionarButton = new JButton("Adicionar Venda");
        JButton listarButton = new JButton("Listar Vendas");
        JButton atualizarButton = new JButton("Atualizar Venda");
        JButton removerButton = new JButton("Remover Venda");

        vendaPanel.add(new JLabel("Data (yyyy-MM-dd):"));
        vendaPanel.add(dataField);
        vendaPanel.add(new JLabel("Valor Total:"));
        vendaPanel.add(valorTotalField);
        vendaPanel.add(new JLabel("ID do Cliente:"));
        vendaPanel.add(clienteIdField);
        vendaPanel.add(adicionarButton);
        vendaPanel.add(listarButton);
        vendaPanel.add(atualizarButton);
        vendaPanel.add(removerButton);

        // Ações para os botões de vendas
        adicionarButton.addActionListener(e -> {
    try {
        // Obtenha e valide a data
        String dataTexto = dataField.getText().trim();
        if (dataTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo de data não pode estar vazio.");
            return;
        }
        LocalDate localDate = LocalDate.parse(dataTexto);
        Date date = Date.valueOf(localDate);

        // Obtenha e valide o valor total
        String valorTotalTexto = valorTotalField.getText().trim();
        if (valorTotalTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo de valor total não pode estar vazio.");
            return;
        }
        double valorTotal = Double.parseDouble(valorTotalTexto);

        // Obtenha e valide o ID do cliente
        String clienteIdTexto = clienteIdField.getText().trim();
        if (clienteIdTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo de ID do cliente não pode estar vazio.");
            return;
        }
        int idCliente = Integer.parseInt(clienteIdTexto);
        if (idCliente <= 0) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um ID de cliente válido.");
            return;
        }

        // Crie a venda com o ID do cliente correto
        Venda venda = new Venda(date, valorTotal, idCliente);
        controller.adicionarVenda(venda);

        JOptionPane.showMessageDialog(this, "Venda adicionada com sucesso!");

        // Limpe os campos após adicionar a venda
        dataField.setText("");
        valorTotalField.setText("");
        clienteIdField.setText("");

    } catch (DateTimeParseException ex) {
        JOptionPane.showMessageDialog(this, "Formato de data inválido. Use o formato: yyyy-MM-dd.");
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Os campos Valor Total e ID do Cliente devem conter números válidos.");
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Erro ao adicionar venda: " + ex.getMessage());
    }
});

        listarButton.addActionListener(e -> {
            try {
                List<Venda> vendas = controller.listarVendas();
                StringBuilder lista = new StringBuilder("Vendas cadastradas:\n");
                for (Venda venda : vendas) {
                    lista.append("ID: ").append(venda.getId())
                            .append(", Data: ").append(venda.getData())
                            .append(", Valor Total: ").append(venda.getValorTotal())
                            .append(", ID do Cliente: ").append(venda.getIdCliente())
                            .append("\n");
                }
                JOptionPane.showMessageDialog(parentFrame, lista.toString());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(parentFrame, "Erro ao listar vendas: " + ex.getMessage());
            }
        });

        atualizarButton.addActionListener(e -> {
            try {
                String idStr = JOptionPane.showInputDialog(parentFrame, "Digite o ID da venda para atualizar:");
                int id = Integer.parseInt(idStr);

                LocalDate localDate = LocalDate.parse(dataField.getText());
                Date date = Date.valueOf(localDate);

                Venda venda = new Venda(
                        id,
                        date,
                        Double.parseDouble(valorTotalField.getText()),
                        Integer.parseInt(clienteIdField.getText())
                );

                controller.atualizarVenda(venda);
                JOptionPane.showMessageDialog(parentFrame, "Venda atualizada com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(parentFrame, "Erro ao atualizar venda: " + ex.getMessage());
            }
        });

        removerButton.addActionListener(e -> {
            try {
                String idStr = JOptionPane.showInputDialog(parentFrame, "Digite o ID da venda para remover:");
                int id = Integer.parseInt(idStr);
                controller.removerVenda(id);
                JOptionPane.showMessageDialog(parentFrame, "Venda removida com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(parentFrame, "Erro ao remover venda: " + ex.getMessage());
            }
        });
//================ v01
        // Painel para exibir e gerenciar produtos associados à venda
        JPanel produtosPanel = new JPanel(new BorderLayout());

        // Configurando a tabela de produtos
        String[] colunas = {"ID Produto", "Nome", "Quantidade", "Preço"};
        tableModel = new DefaultTableModel(colunas, 0);
        produtosTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(produtosTable);
        produtosPanel.add(scrollPane, BorderLayout.CENTER);

        // Botões para gerenciar produtos na venda
        JPanel produtosButtonsPanel = new JPanel(new FlowLayout());
        JButton adicionarProdutoButton = new JButton("Adicionar Produto");
        JButton removerProdutoButton = new JButton("Remover Produto");

        produtosButtonsPanel.add(adicionarProdutoButton);
        produtosButtonsPanel.add(removerProdutoButton);
        produtosPanel.add(produtosButtonsPanel, BorderLayout.SOUTH);

        // Ação para adicionar produto à venda
       adicionarButton.addActionListener(e -> {
    try {
        // Obtenção e validação dos campos
        String dataTexto = dataField.getText().trim();
        if (dataTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo de data não pode estar vazio.");
            return;
        }
        LocalDate localDate = LocalDate.parse(dataTexto);
        Date date = Date.valueOf(localDate);

        String valorTotalTexto = valorTotalField.getText().trim();
        if (valorTotalTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo de valor total não pode estar vazio.");
            return;
        }
        double valorTotal = Double.parseDouble(valorTotalTexto);

        String clienteIdTexto = clienteIdField.getText().trim();
        if (clienteIdTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo de ID do cliente não pode estar vazio.");
            return;
        }
        int idCliente = Integer.parseInt(clienteIdTexto);

        // Validação: Verifica se o cliente existe no banco
        if (!controller.verificarClienteExistente(idCliente)) {
            JOptionPane.showMessageDialog(this, "O cliente informado não existe. Verifique o ID.");
            return;
        }

        // Criação e inserção da venda
        Venda venda = new Venda(date, valorTotal, idCliente);
        controller.adicionarVenda(venda);

        JOptionPane.showMessageDialog(this, "Venda adicionada com sucesso!");
        // Limpeza dos campos
        dataField.setText("");
        valorTotalField.setText("");
        clienteIdField.setText("");

    } catch (DateTimeParseException ex) {
        JOptionPane.showMessageDialog(this, "Formato de data inválido. Use o formato: yyyy-MM-dd.");
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Os campos Valor Total e ID do Cliente devem conter números válidos.");
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Erro ao adicionar venda: " + ex.getMessage());
    }
});


        // Ação para remover produto da venda
        removerProdutoButton.addActionListener(e -> {
    try {
        String idVendaProdutoStr = JOptionPane.showInputDialog(parentFrame, "Digite o ID do produto na venda:");
        int idVendaProduto = Integer.parseInt(idVendaProdutoStr);

        vendaProdutoController.removerProdutoDeVenda(idVendaProduto);
        JOptionPane.showMessageDialog(parentFrame, "Produto removido da venda com sucesso!");

        String idVendaStr = JOptionPane.showInputDialog(parentFrame, "Digite o ID da venda:");
        int idVenda = Integer.parseInt(idVendaStr);
        atualizarTabelaProdutos(idVenda);
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(parentFrame, "Erro ao remover produto da venda: " + ex.getMessage());
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(parentFrame, "Por favor, insira valores válidos.");
    }
});

        // Adiciona os painéis ao layout principal
        add(vendaPanel, BorderLayout.NORTH);
        add(produtosPanel, BorderLayout.CENTER);
    }
    private void atualizarTabelaProdutos(int idVenda) {
    try {
        List<VendaProduto> produtos = vendaProdutoController.listarProdutosPorVenda(idVenda);
        tableModel.setRowCount(0); // Limpa os dados existentes na tabela
        for (VendaProduto produto : produtos) {
            tableModel.addRow(new Object[]{
                produto.getIdProduto(),
                 // Assumindo que o nome está disponível
                produto.getQuantidade(),
                produto.getPreco()
            });
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Erro ao atualizar tabela de produtos: " + ex.getMessage());
    }
}

}


class ProdutoAgricolaView {
    private ProdutoAgricolaController controller;

    public ProdutoAgricolaView() {
        this.controller = new ProdutoAgricolaController();
    }

    public JPanel criarTela() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground ( new Color(173, 216, 230));

        JTextField nomeField = new JTextField(10);
        
        JTextField tipoField = new JTextField(20);
        JTextField precoField = new JTextField(20);
        JTextField propriedadeIdField = new JTextField(20);

        JButton adicionarButton = new JButton("Adicionar");
        JButton listarButton = new JButton("Listar");
        JButton atualizarButton = new JButton("Atualizar");
        JButton removerButton = new JButton("Remover");
      //  adicionarButton.setBackground(new Color(173, 216, 230)); // Amarelo dourado
        

        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(new JLabel("Tipo:"));
        panel.add(tipoField);
        panel.add(new JLabel("Preço:"));
        panel.add(precoField);
        panel.add(new JLabel("ID da Propriedade:"));
        panel.add(propriedadeIdField);
        panel.add(adicionarButton);
        panel.add(listarButton);
        panel.add(atualizarButton);
        panel.add(removerButton);

        // Adicionar Produto
        adicionarButton.addActionListener(e -> {
            try {
                ProdutoAgricola produto = new ProdutoAgricola(
                        0,
                        nomeField.getText(),
                        tipoField.getText(),
                        Double.parseDouble(precoField.getText()),
                        Integer.parseInt(propriedadeIdField.getText())
                );
                controller.adicionarProduto(produto);
                JOptionPane.showMessageDialog(panel, "Produto adicionado com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Erro ao adicionar produto: " + ex.getMessage());
            }
        });

        // Listar Produtos
        listarButton.addActionListener(e -> {
            try {
                List<ProdutoAgricola> produtos = controller.listarProdutos();
                StringBuilder lista = new StringBuilder("Produtos Agrícolas:\n");
                for (ProdutoAgricola produto : produtos) {
                    lista.append("ID: ").append(produto.getId())
                            .append(", Nome: ").append(produto.getNome())
                            .append(", Tipo: ").append(produto.getTipo())
                            .append(", Preço: ").append(produto.getPreco())
                            .append("\n");
                }
                JOptionPane.showMessageDialog(panel, lista.toString());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Erro ao listar produtos: " + ex.getMessage());
            }
        });

        // Atualizar Produto
        atualizarButton.addActionListener(e -> {
            try {
                String idStr = JOptionPane.showInputDialog(panel, "Digite o ID do produto para atualizar:");
                int id = Integer.parseInt(idStr);

                ProdutoAgricola produto = new ProdutoAgricola(
                        id,
                        nomeField.getText(),
                        tipoField.getText(),
                        Double.parseDouble(precoField.getText()),
                        Integer.parseInt(propriedadeIdField.getText())
                );

                controller.atualizarProduto(produto);
                JOptionPane.showMessageDialog(panel, "Produto atualizado com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Erro ao atualizar produto: " + ex.getMessage());
            }
        });

        // Remover Produto
        removerButton.addActionListener(e -> {
            try {
                String idStr = JOptionPane.showInputDialog(panel, "Digite o ID do produto para remover:");
                int id = Integer.parseInt(idStr);
                controller.removerProduto(id);
                JOptionPane.showMessageDialog(panel, "Produto removido com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Erro ao remover produto: " + ex.getMessage());
            }
        });

        return panel;
    }
}

 class FornecedorView {
    private FornecedorController controller;

    public FornecedorView() {
        this.controller = new FornecedorController();
    }

    public JPanel criarTela() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground ( new Color(173, 216, 230));
        
        JTextField nomeField = new JTextField(20);
        JTextField produtoField = new JTextField(20);
        JTextField cnpjField = new JTextField(20);
        JTextField telefoneField = new JTextField(20);

        JButton adicionarButton = new JButton("Adicionar");
        JButton listarButton = new JButton("Listar");
        JButton atualizarButton = new JButton("Atualizar");
        JButton removerButton = new JButton("Remover");

        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(new JLabel("Produto Fornecido:"));
        panel.add(produtoField);
        panel.add(new JLabel("CNPJ:"));
        panel.add(cnpjField);
        panel.add(new JLabel("Telefone:"));
        panel.add(telefoneField);
        panel.add(adicionarButton);
        panel.add(listarButton);
        panel.add(atualizarButton);
        panel.add(removerButton);

        // Adicionar Fornecedor
        adicionarButton.addActionListener(e -> {
            try {
                Fornecedor fornecedor = new Fornecedor(
                        0,
                        nomeField.getText(),
                        produtoField.getText(),
                        cnpjField.getText(),
                        telefoneField.getText()
                );
                controller.adicionarFornecedor(fornecedor);
                JOptionPane.showMessageDialog(panel, "Fornecedor adicionado com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Erro ao adicionar fornecedor: " + ex.getMessage());
            }
        });

        // Listar Fornecedores
        listarButton.addActionListener(e -> {
            try {
                List<Fornecedor> fornecedores = controller.listarFornecedores();
                StringBuilder lista = new StringBuilder("Fornecedores:\n");
                for (Fornecedor fornecedor : fornecedores) {
                    lista.append("ID: ").append(fornecedor.getId())
                            .append(", Nome: ").append(fornecedor.getNome())
                            .append(", Produto: ").append(fornecedor.getProdutoFornecido())
                            .append("\n");
                }
                JOptionPane.showMessageDialog(panel, lista.toString());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Erro ao listar fornecedores: " + ex.getMessage());
            }
        });

        // Atualizar Fornecedor
        atualizarButton.addActionListener(e -> {
            try {
                String idStr = JOptionPane.showInputDialog(panel, "Digite o ID do fornecedor para atualizar:");
                int id = Integer.parseInt(idStr);

                Fornecedor fornecedor = new Fornecedor(
                        id,
                        nomeField.getText(),
                        produtoField.getText(),
                        cnpjField.getText(),
                        telefoneField.getText()
                );

                controller.atualizarFornecedor(fornecedor);
                JOptionPane.showMessageDialog(panel, "Fornecedor atualizado com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Erro ao atualizar fornecedor: " + ex.getMessage());
            }
        });

        // Remover Fornecedor
        removerButton.addActionListener(e -> {
            try {
                String idStr = JOptionPane.showInputDialog(panel, "Digite o ID do fornecedor para remover:");
                int id = Integer.parseInt(idStr);
                controller.removerFornecedor(id);
                JOptionPane.showMessageDialog(panel, "Fornecedor removido com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Erro ao remover fornecedor: " + ex.getMessage());
            }
        });

        return panel;
    }
}





 class AgricultorView {
    private AgricultorController controller;

    public AgricultorView() {
        this.controller = new AgricultorController();
    }

    public JPanel criarTela() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground ( new Color(173, 216, 230));
        
        JTextField nomeField = new JTextField(20);
        JTextField cpfCnpjField = new JTextField(20);
        JTextField enderecoField = new JTextField(20);
        JTextField telefoneField = new JTextField(20);

        JButton adicionarButton = new JButton("Adicionar");
        JButton listarButton = new JButton("Listar");
        JButton atualizarButton = new JButton("Atualizar");
        JButton removerButton = new JButton("Remover");

        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(new JLabel("CPF/CNPJ:"));
        panel.add(cpfCnpjField);
        panel.add(new JLabel("Endereço:"));
        panel.add(enderecoField);
        panel.add(new JLabel("Telefone:"));
        panel.add(telefoneField);
        panel.add(adicionarButton);
        panel.add(listarButton);
        panel.add(atualizarButton);
        panel.add(removerButton);

        // Adicionar Agricultor
        adicionarButton.addActionListener((ActionEvent e) -> {
            try {
                Agricultor agricultor = new Agricultor(0, nomeField.getText(), cpfCnpjField.getText(),
                        enderecoField.getText(), telefoneField.getText());
                controller.adicionarAgricultor(agricultor);
                JOptionPane.showMessageDialog(panel, "Agricultor adicionado com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Erro ao adicionar agricultor: " + ex.getMessage());
            }
        });

        // Listar Agricultores
        listarButton.addActionListener((ActionEvent e) -> {
            try {
                List<Agricultor> agricultores = controller.listarAgricultores();
                StringBuilder lista = new StringBuilder("Agricultores:\n");
                for (Agricultor agricultor : agricultores) {
                    lista.append("ID: ").append(agricultor.getId())
                            .append(", Nome: ").append(agricultor.getNome())
                            .append(", CPF/CNPJ: ").append(agricultor.getCpfCnpj())
                            .append("\n");
                }
                JOptionPane.showMessageDialog(panel, lista.toString());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Erro ao listar agricultores: " + ex.getMessage());
            }
        });

        // Atualizar Agricultor
        atualizarButton.addActionListener((ActionEvent e) -> {
            try {
                String idStr = JOptionPane.showInputDialog(panel, "Digite o ID do agricultor para atualizar:");
                int id = Integer.parseInt(idStr);
                Agricultor agricultor = new Agricultor(id, nomeField.getText(), cpfCnpjField.getText(),
                        enderecoField.getText(), telefoneField.getText());
                controller.atualizarAgricultor(agricultor);
                JOptionPane.showMessageDialog(panel, "Agricultor atualizado com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Erro ao atualizar agricultor: " + ex.getMessage());
            }
        });

        // Remover Agricultor
        removerButton.addActionListener((ActionEvent e) -> {
            try {
                String idStr = JOptionPane.showInputDialog(panel, "Digite o ID do agricultor para remover:");
                int id = Integer.parseInt(idStr);
                controller.removerAgricultor(id);
                JOptionPane.showMessageDialog(panel, "Agricultor removido com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Erro ao remover agricultor: " + ex.getMessage());
            }
        });

        return panel;
    }
}

 class ClienteView {
    private ClienteController controller;

    public ClienteView() {
        this.controller = new ClienteController();
    }

    public JPanel criarTela() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground ( new Color(173, 216, 230));
        
        JTextField nomeField = new JTextField(20);
        JTextField cpfCnpjField = new JTextField(20);
        JTextField enderecoField = new JTextField(20);
        JTextField telefoneField = new JTextField(20);

        JButton adicionarButton = new JButton("Adicionar");
        JButton listarButton = new JButton("Listar");
        JButton atualizarButton = new JButton("Atualizar");
        JButton removerButton = new JButton("Remover");

        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(new JLabel("CPF/CNPJ:"));
        panel.add(cpfCnpjField);
        panel.add(new JLabel("Endereço:"));
        panel.add(enderecoField);
        panel.add(new JLabel("Telefone:"));
        panel.add(telefoneField);
        panel.add(adicionarButton);
        panel.add(listarButton);
        panel.add(atualizarButton);
        panel.add(removerButton);

        // Adicionar Cliente
        adicionarButton.addActionListener((ActionEvent e) -> {
            try {
                Cliente cliente = new Cliente(
                        0,
                        nomeField.getText(),
                        cpfCnpjField.getText(),
                        enderecoField.getText(),
                        telefoneField.getText()
                );
                controller.adicionarCliente(cliente);
                JOptionPane.showMessageDialog(panel, "Cliente adicionado com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Erro ao adicionar cliente: " + ex.getMessage());
            }
        });

        // Listar Clientes
        listarButton.addActionListener((ActionEvent e) -> {
            try {
                List<Cliente> clientes = controller.listarClientes();
                StringBuilder lista = new StringBuilder("Clientes:\n");
                for (Cliente cliente : clientes) {
                    lista.append("ID: ").append(cliente.getId())
                            .append(", Nome: ").append(cliente.getNome())
                            .append(", CPF/CNPJ: ").append(cliente.getCpfCnpj())
                            .append("\n");
                }
                JOptionPane.showMessageDialog(panel, lista.toString());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Erro ao listar clientes: " + ex.getMessage());
            }
        });

        // Atualizar Cliente
        atualizarButton.addActionListener((ActionEvent e) -> {
            try {
                String idStr = JOptionPane.showInputDialog(panel, "Digite o ID do cliente para atualizar:");
                int id = Integer.parseInt(idStr);
                Cliente cliente = new Cliente(
                        id,
                        nomeField.getText(),
                        cpfCnpjField.getText(),
                        enderecoField.getText(),
                        telefoneField.getText()
                );
                controller.atualizarCliente(cliente);
                JOptionPane.showMessageDialog(panel, "Cliente atualizado com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Erro ao atualizar cliente: " + ex.getMessage());
            }
        });

        // Remover Cliente
        removerButton.addActionListener((ActionEvent e) -> {
            try {
                String idStr = JOptionPane.showInputDialog(panel, "Digite o ID do cliente para remover:");
                int id = Integer.parseInt(idStr);
                controller.removerCliente(id);
                JOptionPane.showMessageDialog(panel, "Cliente removido com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Erro ao remover cliente: " + ex.getMessage());
            }
        });

        return panel;
    }
}
