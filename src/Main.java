import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //3.1 - Adicionar os funcionários em ordem
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // 3.2 - Remover o funcionário João
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 – Imprimir todos os funcionários com todas as informações
        System.out.println("\n3.3 – Imprimir todos os funcionários com todas as informações:");
        imprimirFuncionarios(funcionarios);

        // 3.4 – Os funcionários receberam 10% de aumento de salário
        funcionarios.forEach(funcionario -> funcionario.aumentarSalario(new BigDecimal("10.00")));

        // 3.5 – Agrupar os funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 – Imprimir os funcionários, agrupados por função
        System.out.println("\n3.6 - Imprimir os funcionários, agrupados por função:");
        imprimirFuncionariosPorFuncao(funcionariosPorFuncao);

        // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12
        System.out.println("\n3.8 - Imprimir os funcionários que fazem aniversário nos meses 10 e 12:");
        imprimirAniversariantes(funcionarios, 10, 12);

        // 3.9 – Imprimir o funcionário com a maior idade
        System.out.println("\n3.9 - Imprimir o funcionário com a maior idade:");
        imprimirFuncionarioMaiorIdade(funcionarios);

        // 3.10 – Imprimir a lista de funcionários por ordem alfabética
        System.out.println("\n3.10 - Imprimir a lista de funcionários por ordem alfabética:");
        imprimirFuncionariosOrdenados(funcionarios);

        // 3.11 – Imprimir o total dos salários dos funcionários
        System.out.println("\n3.11 - Imprimir o total dos salários dos funcionários:");
        imprimirTotalSalarios(funcionarios);

        // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário
        System.out.println("\n3.12 - Imprimir quantos salários mínimos ganha cada funcionário:");
        imprimirSalariosMinimos(funcionarios);
    }

    private static void imprimirFuncionarios(List<Funcionario> funcionarios) {
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de Nascimento: " + funcionario.getDataNascimento());
            System.out.println("Salário: " + formatarNumero(funcionario.getSalario()));
            System.out.println("Função: " + funcionario.getFuncao());
            System.out.println("--------------");
        }
    }

    private static void imprimirFuncionariosPorFuncao(Map<String, List<Funcionario>> funcionariosPorFuncao) {
        funcionariosPorFuncao.forEach((funcao, listaFuncionarios) -> {
            System.out.println("Função: " + funcao);
            imprimirFuncionarios(listaFuncionarios);
            System.out.println("==============");
        });
    }

    private static String formatarNumero(BigDecimal numero) {
        return String.format("%,.2f", numero);
    }

    private static void imprimirAniversariantes(List<Funcionario> funcionarios, int... meses) {
        for (int mes : meses) {
            List<Funcionario> aniversariantesMes = funcionarios.stream()
                    .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == mes)
                    .collect(Collectors.toList());

            System.out.println("Aniversariantes do mês " + mes + ":");
            imprimirFuncionarios(aniversariantesMes);
            System.out.println("================");
        }
    }

    private static void imprimirFuncionarioMaiorIdade(List<Funcionario> funcionarios) {
        Funcionario maisVelho = Collections.max(funcionarios, Comparator.comparing(funcionario ->
                funcionario.getDataNascimento()));

        int idade = LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear();
        System.out.println("Funcionário mais velho:");
        System.out.println("Nome: " + maisVelho.getNome());
        System.out.println("Idade: " + idade + " anos");
        System.out.println("================");
    }

    private static void imprimirFuncionariosOrdenados(List<Funcionario> funcionarios) {
        List<Funcionario> funcionariosOrdenados = funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .collect(Collectors.toList());

        System.out.println("Funcionários por ordem alfabética:");
        imprimirFuncionarios(funcionariosOrdenados);
        System.out.println("================");
    }

    private static void imprimirTotalSalarios(List<Funcionario> funcionarios) {
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Total dos salários dos funcionários: " + formatarNumero(totalSalarios));
        System.out.println("================");
    }

    private static void imprimirSalariosMinimos(List<Funcionario> funcionarios) {
        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        System.out.println("Salários em múltiplos de salário mínimo:");
        funcionarios.forEach(funcionario -> {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_DOWN);
            System.out.println(funcionario.getNome() + ": " + formatarNumero(salariosMinimos) + " salários mínimos");
        });
        System.out.println("================");
    }
}
