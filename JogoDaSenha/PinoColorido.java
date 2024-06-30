
/*cria um pino colorido que tem interação de click */
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

public class PinoColorido extends Pino {
    // define uma ordem de rotação de cores
    private static List<Character> corOrdem = Arrays.asList('R', 'G', 'Y', 'T', 'P', 'O');
    private boolean analisado;

    public PinoColorido() {
        super();
    }

    public boolean isAnalisado() {
        return analisado;
    }

    public void setAnalisado(boolean analisado) {
        this.analisado = analisado;
    }

    @Override
    public void acaoDoBotao(ActionEvent e) {
        if (this.isAtivo()) {
            int index = corOrdem.indexOf(getCor());
            index = index + 1;
            if (index >= corOrdem.size()) {
                index = 0;
            }
            setCor(corOrdem.get(index));
        }
    }
}