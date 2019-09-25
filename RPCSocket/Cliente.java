import java.math.BigDecimal;

public class Cliente {

    public static void main(String args[])
    {
        Calculadora calculadora = new StubClient();

        System.out.println(calculadora.soma(new BigDecimal(1.2), (new BigDecimal(2.1))).setScale(3, BigDecimal.ROUND_DOWN));

        System.out.println(calculadora.subtracao(new BigDecimal(3.2), (new BigDecimal(1.1))).setScale(3, BigDecimal.ROUND_DOWN));

        System.out.println(calculadora.divisao(new BigDecimal(4.2), (new BigDecimal(2.1))).setScale(3, BigDecimal.ROUND_DOWN));

        System.out.println(calculadora.multiplicacao(new BigDecimal(1.3), (new BigDecimal(12.1))).setScale(3, BigDecimal.ROUND_DOWN));

    }
}
