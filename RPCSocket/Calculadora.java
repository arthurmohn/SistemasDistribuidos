import java.math.BigDecimal;

public interface Calculadora {

    public BigDecimal soma(BigDecimal op1, BigDecimal op2);

    public BigDecimal subtracao(BigDecimal op1, BigDecimal op2);

    public BigDecimal multiplicacao(BigDecimal op1, BigDecimal op2);

    public BigDecimal divisao(BigDecimal op1, BigDecimal op2);
}
