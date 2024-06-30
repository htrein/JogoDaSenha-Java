
/*contém métodos e atributos comuns a ambos tipos de pinos */
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;

public abstract class Pino extends JButton {
  private Character cor;
  private boolean ativo;

  public Pino() {
    this.cor = 'D';
    this.ativo = false;
    this.setIcon(new ImageIcon(Cores.D.getArquivo()));
    this.addActionListener(e -> acaoDoBotao(e));
  }

  public Character getCor() {
    return cor;
  }

  public boolean isAtivo() {
    return ativo;
  }

  public void setAtivo(boolean ativo) {
    this.ativo = ativo;
  }

  public void setCor(Character cor) {
    this.cor = cor;
    this.setIcon(new ImageIcon(Cores.getEnumCor(cor)));
  }

  public abstract void acaoDoBotao(ActionEvent e);
}
