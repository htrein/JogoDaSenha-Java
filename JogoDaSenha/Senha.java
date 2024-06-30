
/*cria uma senha com ou sem repeticao*/
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Random;

public class Senha {
  private List<Character> senha;
  // para busca O(1)
  private static final Set<Character> set = Set.of('R', 'G', 'Y', 'T', 'P', 'O');

  public Senha(int tam_senha, boolean facil) {
    senha = new ArrayList<Character>();
    if (facil)
      senhaSemRepeticao(tam_senha);
    else
      senhaComRepeticao(tam_senha);
  }

  private void senhaComRepeticao(int tam_senha) {
    for (int i = 0; i < tam_senha; i++) {
      senha.add(getRandomCharacterFromSet());
    }
  }

  private void senhaSemRepeticao(int tam_senha) {
    for (int i = 0; i < tam_senha; i++) {
      Character c = getRandomCharacterFromSet();
      while (senha.contains(c)) {
        c = getRandomCharacterFromSet();
      }
      senha.add(c);
    }
  }

  // gera um numero aleatorio nos limites do set e inicializa um indice, pega um
  // caractere do set e se o índice for igual ao número aleatório, retorna o
  // caractere
  private char getRandomCharacterFromSet() {
    int aux = new Random().nextInt(set.size());
    int i = 0;
    for (Character c : set) {
      if (i == aux)
        return c;
      i++;
    }
    return ' ';
  }

  public List<Character> getSenha() {
    return senha;
  }

  public Character getCharacterAtIndex(int index) {
    if (index >= 0 && index < senha.size()) {
      return senha.get(index);
    } else {
      return null;
    }
  }
}