public enum Cores {
  R("imagens/EllipseRed.png"),
  G("imagens/EllipseGreen.png"),
  Y("imagens/EllipseYellow.png"),
  T("imagens/EllipseTurquoise.png"),
  P("imagens/EllipsePurple.png"),
  O("imagens/EllipseOrange.png"),
  B("imagens/EllipseBlack.png"),
  W("imagens/EllipseWhite.png"),
  D("imagens/EllipseDefault.png");

  private final String arquivo;

  Cores(String arquivo) {
    this.arquivo = arquivo;
  }

  static String getEnumCor(Character cor) {
    switch (cor) {
      case 'R':
        return Cores.R.getArquivo();
      case 'G':
        return Cores.G.getArquivo();
      case 'Y':
        return Cores.Y.getArquivo();
      case 'T':
        return Cores.T.getArquivo();
      case 'P':
        return Cores.P.getArquivo();
      case 'O':
        return Cores.O.getArquivo();
      case 'B':
        return Cores.B.getArquivo();
      case 'W':
        return Cores.W.getArquivo();
      default:
        return Cores.D.getArquivo();
    }
  }

  public String getArquivo() {
    return arquivo;
  }
}