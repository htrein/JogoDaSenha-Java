
/*responsável pela lógica do jogo */
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;

public class Game {
  private Historico historico;
  private Janela janela;
  private Senha senha;
  private Tentativa[] tentativas;
  private Analise[] analises;
  private boolean modoTeste, modoFacil;
  private final int num_cor = 6;

  public Game() {
    this.modoTeste = false;
    this.modoFacil = true;
    this.historico = new Historico();
    this.janela = new Janela();
    this.senha = new Senha(janela.getTamSenha(), modoFacil);
    this.tentativas = new Tentativa[janela.getTentativas()];
    this.analises = new Analise[janela.getTentativas()];
    janela.setModo(modoFacil);
    setActionListeners();
  }

  public void start() {
    for (int i = 0; i < janela.getTentativas(); i++) {
      tentativas[i] = new Tentativa(janela.getTamSenha());
      for (PinoColorido a : tentativas[i].getPinos()) {
        janela.getGridLeftPanel().add(a);
      }
      analises[i] = new Analise(janela.getTamSenha());
      for (PinoPB b : analises[i].getPinos()) {
        janela.getGridRightPanel().add(b);
      }
    }
    for (PinoColorido p : tentativas[tentativas.length - 1].getPinos()) {
      p.setAtivo(true);
    }
    for (int i = 0; i < janela.getTentativas(); i++) {
      tentativas[i].getPinos().stream().filter(pino -> !pino.isAtivo()).forEach(pino -> pino.setEnabled(false));
      analises[i].getPinos().stream().filter(pino -> !pino.isAtivo()).forEach(pino -> pino.setEnabled(false));
    }
    janela.getGridLeftPanel().revalidate();
    janela.getGridLeftPanel().repaint();
    janela.getGridRightPanel().revalidate();
    janela.getGridRightPanel().repaint();
  }

  // setActionListeners() define os eventos de clique dos botões
  public void setActionListeners() {
    janela.getButtonVerificar().addActionListener(click -> {
      for (int i = tentativas.length - 1; i >= 0; i--) {
        List<PinoColorido> tentativaList = tentativas[i].getPinos();
        Pino[] tentativa = tentativaList.toArray(new Pino[0]);
        if (tentativaList.stream().allMatch(Pino::isAtivo)) {
          if (tentativaList.stream().allMatch(pino -> pino.getCor() != 'D')) {
            if (i > 0) {
              List<PinoColorido> previousTentativaList = tentativas[i - 1].getPinos();
              Pino[] previousTentativa = previousTentativaList.toArray(new Pino[0]);
              Arrays.stream(previousTentativa).forEach(pino -> pino.setAtivo(true));
              Arrays.stream(previousTentativa).forEach(pino -> pino.setEnabled(true));
            }
            Arrays.stream(tentativa).forEach(pino -> pino.setAtivo(false));
            analise(i);
          }
        }
      }
    });

    janela.getModoTeste().addActionListener(click -> {
      modoTeste = !modoTeste;
      janela.mostraSenhaTeste(modoTeste, senha);
    });
    janela.getNovoJogoFacil().addActionListener(click -> {
      modoFacil = true;
      janela.setModo(modoFacil);
      reiniciaJogo();
    });
    janela.getNovoJogoDificil().addActionListener(click -> {
      modoFacil = false;
      janela.setModo(modoFacil);
      reiniciaJogo();
    });
    janela.getSimButton().addActionListener(click -> {
      reiniciaJogo();
      janela.getDialogMessage().setVisible(false);
    });
    janela.getNaoButton().addActionListener(click -> {
      janela.getDialogMessage().setVisible(false);
    });
    janela.getButtonAplicar().addActionListener(click -> {
      janela.getDialogMessage().setVisible(true);
      janela.getFrameConfiguracoes().setVisible(false);
    });
  }

  // resultado() exibe uma mensagem de vitória ou derrota e registra o histórico
  public void resultado(int t, boolean res) {
    if (res) {
      JOptionPane.showMessageDialog(janela.getFramePrincipal(),
          "Parabéns! Você acertou a senha em " + t + " tentativas!");
      historico.registrarHistorico(senha.getSenha(), t, "Vitória");
    } else {
      JOptionPane.showMessageDialog(janela.getFramePrincipal(),
          "Você perdeu! A senha era: " + senha.getSenha());
      historico.registrarHistorico(senha.getSenha(), t, "Derrota");
    }
    reiniciaJogo();
  }

  // reiniciaJogo() redefine as configurações do jogo e remove os componentes
  // anteriores, mas não adiciona novos componentes na tela nem atualiza a tela
  public void reiniciaJogo() {
    this.tentativas = new Tentativa[janela.getTentativas()];
    this.analises = new Analise[janela.getTentativas()];
    if (modoFacil && janela.getTamSenha() > num_cor) {
      janela.setTamSenha(num_cor);
    }
    this.senha = new Senha(janela.getTamSenha(), modoFacil);
    janela.getGridLeftPanel().removeAll();
    janela.getGridRightPanel().removeAll();
    janela.getGridLeftPanel().setLayout(new GridLayout(janela.getTentativas(), janela.getTamSenha()));
    janela.getGridRightPanel().setLayout(new GridLayout(janela.getTentativas(), janela.getTamSenha()));
    if (modoTeste) {
      janela.mostraSenhaTeste(modoTeste, senha);
    }
    start();
  }

  // analise() classifica a tentativa de acordo com a posição e cor dos pinos,
  // além disso, verifica se o jogo acabou
  public void analise(int i) {
    for (PinoPB p : analises[i].getPinos()) {
      p.setEnabled(true);
    }
    List<Character> remainingColors = new ArrayList<>(senha.getSenha());
    for (int j = 0; j < janela.getTamSenha(); j++) {
      final int finalJ = j;
      if (tentativas[i].getPinos().get(j).getCor().equals(senha.getCharacterAtIndex(finalJ))) {
        analises[i].getPinos().get(j).setCor('B');
        remainingColors.remove((Character) tentativas[i].getPinos().get(j).getCor());
        tentativas[i].getPinos().get(j).setAnalisado(true);
      }
    }
    for (int j = 0; j < janela.getTamSenha(); j++) {
      if (remainingColors.contains(tentativas[i].getPinos().get(j).getCor())
          && !tentativas[i].getPinos().get(j).isAnalisado()) {
        analises[i].getPinos().get(j).setCor('W');
        remainingColors.remove((Character) tentativas[i].getPinos().get(j).getCor());
      }
    }
    if (i == 0 && !analises[0].getPinos().stream().allMatch(pino -> pino.getCor() == 'B')) {
      resultado(tentativas.length, false);
    }
    if (analises[i].getPinos().stream().allMatch(pino -> pino.getCor() == 'B')) {
      resultado(tentativas.length - i, true);
    }
    if (!modoFacil) {
      List<PinoPB> tempB = analises[i].getPinos().stream().filter(pino -> pino.getCor() == 'B')
          .collect(Collectors.toList());
      List<PinoPB> tempW = analises[i].getPinos().stream().filter(pino -> pino.getCor() == 'W')
          .collect(Collectors.toList());
      List<PinoPB> tempD = analises[i].getPinos().stream().filter(pino -> pino.getCor() == 'D')
          .collect(Collectors.toList());
      for (int j = 0; j < tempB.size(); j++) {
        analises[i].getPinos().get(j).setCor('B');
      }
      for (int j = 0; j < tempW.size(); j++) {
        analises[i].getPinos().get(j + tempB.size()).setCor('W');
      }
      for (int j = 0; j < tempD.size(); j++) {
        analises[i].getPinos().get(j + tempB.size() + tempW.size()).setCor('D');
      }

    }
  }
}
