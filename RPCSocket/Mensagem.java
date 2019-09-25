import java.io.Serializable;
import java.math.BigDecimal;

public class Mensagem implements Serializable {
    private TipoOperacao op;
    private BigDecimal op1, op2;


    public Mensagem(TipoOperacao op, BigDecimal op1, BigDecimal op2) {
        super();
        this.op = op;
        this.op1 = op1;
        this.op2 = op2;
    }

    public Mensagem(){
        super();
    }

    public String getOp() {
        return op.name();
    }

    public BigDecimal getParemetro1() {
        return op1;
    }

    public BigDecimal getParametro2() {
        return op2;
    }

    public void setOp(TipoOperacao op) {
        this.op = op;
    }

    public void setParametro1(BigDecimal op1) {
        this.op1 = op1;
    }

    public void setParametro2(BigDecimal op2){
        this.op2 = op2;
    }
}
