
/*Cria uma linha/tentativa dos botões preto-branco do tamanho da senha */
import java.util.ArrayList;
import java.util.List;

public class Analise {
  List<PinoPB> pinos;

  public Analise(int tam_senha) {
    pinos = new ArrayList<PinoPB>();
    for (int i = 0; i < tam_senha; i++) {
      pinos.add(new PinoPB());
    }
  }

  public void setPinos(List<PinoPB> pinos) {
    this.pinos = pinos;
  }

  public List<PinoPB> getPinos() {
    return pinos;
  }
}
