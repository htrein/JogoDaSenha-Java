
/*Cria uma linha/tentativa dos botões coloridos do tamanho da senha */
import java.util.ArrayList;
import java.util.List;

public class Tentativa {
  List<PinoColorido> pinos;

  public Tentativa(int tam_senha) {
    pinos = new ArrayList<PinoColorido>();
    for (int i = 0; i < tam_senha; i++) {
      pinos.add(new PinoColorido());
    }
  }

  public List<PinoColorido> getPinos() {
    return pinos;
  }
}
