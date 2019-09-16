package Principal;

import java.io.Serializable;
import java.math.BigDecimal;

public class Mensagem implements Serializable {
    private TipoOperacao op;
    private BigDecimal[] parametros;


    public Mensagem(TipoOperacao op, BigDecimal[] parametros) {
        super();
        this.op = op;
        this.parametros = parametros;
    }

    public Mensagem(){
        super();
    }

    public String getOp() {
        return op.name();
    }

    public BigDecimal getParemetro1() {
        return parametros[0];
    }

    public BigDecimal getParametro2() {
        return parametros[1];
    }
}
