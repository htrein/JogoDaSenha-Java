
/*responsável pela interface gráfica*/
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Janela {

  private JSlider numTentativas, tamSenha;
  private JPanel gridLeftPanel, gridRightPanel, painelInterno, leftPanel, rightPanel, painelGrids, bottomPanel,
      espacoBranco, painelTeste;
  private JFrame framePrincipal, frameConfiguracoes;
  private JMenuItem menuItem_novoJogoFacil, menuItem_novoJogoDificil, menuItem_modoTeste, menuItem_config,
      menuItem_historico;
  private JMenuBar menuBarra;
  private JMenu menu, modo;
  private JButton buttonAplicar, buttonVerificar, buttonSim, buttonNao;
  private JLabel sliderTentativasLabel, sliderSenhaLabel, labelTentativas, labelAnalises;
  private Container container;
  private JDialog avisoReiniciaJogo;
  private final int num_cor = 6;

  public Janela() {
    inicializaComponentes();
    configuraJanelaPrincipal();
    configuraMenu();
    configuraJanelaConfiguracoes();
    configuraAvisoReiniciaJogo();
    configuraAcoesMenu();
  }

  public void setModo(boolean modoFacil) {
    modo.setText(modoFacil ? "Modo fácil: tamanho máximo de senha = " + num_cor + ". Sem cores repetidas"
        : "Modo difícil: Possibilidade de cores repetidas e análises fora de ordem");
    modo.setEnabled(false);
  }

  public void mostraSenhaTeste(boolean modoTeste, Senha senha) {
    painelTeste.removeAll();
    if (modoTeste) {
      painelTeste.setLayout(new FlowLayout());
      for (Character c : senha.getSenha()) {
        JButton b = new JButton();
        String imagePath = Cores.getEnumCor(c);
        ImageIcon icon = new ImageIcon(imagePath);
        b.setIcon(icon);
        painelTeste.add(b);
      }
    }
    bottomPanel.remove(espacoBranco);
    bottomPanel.add(painelTeste);
    bottomPanel.revalidate();
    bottomPanel.repaint();
  }

  private void inicializaComponentes() {
    numTentativas = new JSlider(JSlider.HORIZONTAL, 1, 10, 5);
    tamSenha = new JSlider(JSlider.HORIZONTAL, 1, 10, 5);
    gridLeftPanel = new JPanel(new GridLayout(numTentativas.getValue(), 1));
    gridRightPanel = new JPanel(new GridLayout(numTentativas.getValue(), 1));
    bottomPanel = new JPanel(new GridLayout(1, 2));
    espacoBranco = new JPanel();
    framePrincipal = new JFrame("Jogo da Senha");
    container = framePrincipal.getContentPane();
    painelGrids = new JPanel();
    menuBarra = new JMenuBar();
    menu = new JMenu("Opções");
    modo = new JMenu("Modo fácil: tamanho máximo de senha = " + num_cor + ". Sem cores repetidas");
    leftPanel = new JPanel(new BorderLayout());
    rightPanel = new JPanel(new BorderLayout());
    labelTentativas = new JLabel("Tentativas", SwingConstants.CENTER);
    labelAnalises = new JLabel("Análises", SwingConstants.CENTER);
    buttonVerificar = new JButton("Verificar");
    menuItem_novoJogoFacil = new JMenuItem("Novo Jogo Fácil");
    menuItem_novoJogoDificil = new JMenuItem("Novo Jogo Difícil");
    menuItem_modoTeste = new JMenuItem("Modo de Teste");
    menuItem_config = new JMenuItem("Configurações de Jogo");
    menuItem_historico = new JMenuItem("Histórico de Jogos");
    frameConfiguracoes = new JFrame("Configurações de Jogo");
    painelInterno = new JPanel();
    painelTeste = new JPanel();
    buttonAplicar = new JButton("Aplicar");
    sliderTentativasLabel = new JLabel("Tentativas");
    sliderSenhaLabel = new JLabel("Tamanho da Senha");
    avisoReiniciaJogo = new JDialog(frameConfiguracoes, "Configurações de Jogo", true);
    buttonSim = new JButton("Sim");
    buttonNao = new JButton("Não");
  }

  private void configuraJanelaPrincipal() {
    framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    framePrincipal.setSize(800, 600);
    framePrincipal.setResizable(false);
    leftPanel.add(labelTentativas, BorderLayout.NORTH);
    leftPanel.add(gridLeftPanel, BorderLayout.CENTER);
    rightPanel.add(labelAnalises, BorderLayout.NORTH);
    rightPanel.add(gridRightPanel, BorderLayout.CENTER);
    painelGrids.setLayout(new GridLayout(1, 2));
    painelGrids.add(leftPanel);
    painelGrids.add(rightPanel);
    container.setLayout(new GridLayout(2, 1));
    container.add(painelGrids);
    bottomPanel.add(buttonVerificar);
    bottomPanel.add(espacoBranco);
    container.add(bottomPanel);
    framePrincipal.setVisible(true);
  }

  private void configuraMenu() {
    menu.add(menuItem_novoJogoFacil);
    menu.add(menuItem_novoJogoDificil);
    menu.add(menuItem_modoTeste);
    menu.add(menuItem_config);
    menu.add(menuItem_historico);
    menuBarra.add(menu);
    menuBarra.add(modo);
    framePrincipal.setJMenuBar(menuBarra);
  }

  private void configuraJanelaConfiguracoes() {
    painelInterno.setLayout(new BoxLayout(painelInterno, BoxLayout.Y_AXIS));
    numTentativas.setMajorTickSpacing(1);
    numTentativas.setPaintTicks(true);
    numTentativas.setPaintLabels(true);
    tamSenha.setMajorTickSpacing(1);
    tamSenha.setPaintTicks(true);
    tamSenha.setPaintLabels(true);
    painelInterno.add(sliderTentativasLabel);
    painelInterno.add(numTentativas);
    painelInterno.add(sliderSenhaLabel);
    painelInterno.add(tamSenha);
    painelInterno.add(buttonAplicar);
    frameConfiguracoes.add(painelInterno);
    frameConfiguracoes.setSize(300, 200);
    frameConfiguracoes.setResizable(false);
    frameConfiguracoes.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
  }

  private void configuraAvisoReiniciaJogo() {
    avisoReiniciaJogo.setVisible(false);
    avisoReiniciaJogo.setSize(400, 100);
    avisoReiniciaJogo.setLayout(new FlowLayout());
    avisoReiniciaJogo.add(new JLabel("O jogo será reiniciado com as novas configurações. Confirmar?"));
    avisoReiniciaJogo.add(buttonSim);
    avisoReiniciaJogo.add(buttonNao);
  }

  private void configuraAcoesMenu() {
    menuItem_config.addActionListener(click -> frameConfiguracoes.setVisible(true));

    menuItem_historico.addActionListener(click -> {
      Historico historico = new Historico();
      JTable tabela = new JTable();
      DefaultTableModel model = new DefaultTableModel() {
        // impede que o usuário possa sobreescrever os dados da tabela
        @Override
        public boolean isCellEditable(int row, int column) {
          return false;
        }
      };
      String[] columns = { "Senha", "Tentativas", "Resultado" };
      model.setColumnIdentifiers(columns);
      tabela.setModel(model);
      historico.preencherTabela(tabela);
      JFrame frame = new JFrame("Histórico de Jogos");
      frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      frame.setSize(400, 300);
      frame.setResizable(false);
      JScrollPane scrollPane = new JScrollPane(tabela);
      frame.add(scrollPane);
      frame.setVisible(true);
    });
  }

  // setters
  public void setGridRightPanel(JPanel gridRightPanel) {
    this.gridRightPanel = gridRightPanel;
  }

  public void setGridLeftPanel(JPanel gridLeftPanel) {
    this.gridLeftPanel = gridLeftPanel;
  }

  public void setTamSenha(int x) {
    this.tamSenha.setValue(x);
  }

  // getters
  public JMenuBar getMenuBarra() {
    return menuBarra;
  }

  public JButton getButtonVerificar() {
    return buttonVerificar;
  }

  public JButton getButtonAplicar() {
    return buttonAplicar;
  }

  public JFrame getFramePrincipal() {
    return framePrincipal;
  }

  public JFrame getFrameConfiguracoes() {
    return frameConfiguracoes;
  }

  public JPanel getPainelGrids() {
    return painelGrids;
  }

  public JMenuItem getModoTeste() {
    return menuItem_modoTeste;
  }

  public JMenuItem getNovoJogoFacil() {
    return menuItem_novoJogoFacil;
  }

  public JMenuItem getNovoJogoDificil() {
    return menuItem_novoJogoDificil;
  }

  public JPanel getBottomPanel() {
    return bottomPanel;
  }

  public int getTentativas() {
    return numTentativas.getValue();
  }

  public int getTamSenha() {
    return tamSenha.getValue();
  }

  public JPanel getGridLeftPanel() {
    return gridLeftPanel;
  }

  public JPanel getGridRightPanel() {
    return gridRightPanel;
  }

  public JDialog getDialogMessage() {
    return avisoReiniciaJogo;
  }

  public JButton getNaoButton() {
    return buttonNao;
  }

  public JButton getSimButton() {
    return buttonSim;
  }

  public JPanel getEspacoBranco() {
    return espacoBranco;
  }
}
