
/*responsável pelo registro e leitura do arquivo que persiste os jogos */
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Historico {
  public void registrarHistorico(List<Character> senha, int tentativas, String resultado) {
    try {
      FileWriter writer = new FileWriter("historico-jogos.txt", true);
      writer.write(senha.toString() + "," + tentativas + "," + resultado + "\n");
      writer.close();
    } catch (IOException e) {
      System.out.println("Erro ao escrever no arquivo.");
    }
  }

  public void preencherTabela(JTable tabela) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader("historico-jogos.txt"));
      DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
      String linha;
      while ((linha = reader.readLine()) != null) {
        int token_1 = linha.lastIndexOf("],");
        String parteSenha = linha.substring(0, token_1 + 1);
        String restoAux = linha.substring(token_1 + 2);
        String[] resto = restoAux.split(",", 2);
        String parteTentativa = resto[0];
        String parteResultado = resto[1];
        String[] partes = { parteSenha, parteTentativa, parteResultado };
        modelo.addRow(partes);
      }
      reader.close();
    } catch (IOException e) {
      System.out.println("Erro ao ler o arquivo.");
    }
  }
}
