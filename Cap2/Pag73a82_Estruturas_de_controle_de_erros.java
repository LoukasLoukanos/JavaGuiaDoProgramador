import java.io.*; // linha 
import java.util.Scanner; // linha 91

public class Pag73a82_Estruturas_de_controle_de_erros {
	public static void main (String args[]) {

		/* •Diretiva/declaração (Statement): → Instrução do programa (em Java são separadas por ;)
	 	 * •Bloco (blocks): → Grupo de diretivas/declarações (statements)
		 * •Cláusula (Clause): → Procedimento nativo da liguagem
		 * 
		 * ♦Controle de erros com as cláusulas try catch finally e assert
		 * Sintaxe:
		 * 	try [(recurso = inicialização)] {
		 *		diretivas;
		 * } catch ( <exceptin1> ) {
		 *		diretiva_de_tratamento_de_erro1;
		 * } [ catch ( <exceptin2> ) {
		 *		diretiva_de_tratamento_de_erro2;
		 * }] [ finaly {
		 *		diretiva_de_execução_garantida;
		 * }]
		 *
		 * obs: a cláusula finaly garante a execução de um trecho final de código. */

		 
		//Aplicação com teste convencional(sem controle de erros com a diretiva try catch)
		if (args.length > 0) { // testa presença de argumentos
			int j = Integer.parseInt(args[0]); // converte 1o argumento em inteiro
			while (j >= 0) {
				System.out.println(j);
				j--;
			}
		} else { // sinaliza problema
			System.out.println("Falta um argumento inteiro.");
		}


		//Aplicação com uso de controle de erros com a diretiva try catch)
		try { // Tentativa
			int i = Integer.parseInt(args[0]); // converte 1o argumento em inteiro
			while (i >= 0) {
				System.out.println(i); 
				i--;
			}
		} catch (Exception e) { // sinaliza problemas encontrados. Com o uso de Exception será tratado qualquer tipo de erro.
			System.out.println("Argumento nao foi dado ou nao e inteiro.");
		}


		//Usando várias cláusulas catch
		try { // Tentativa
			int j = Integer.parseInt(args[0]); // converte 1o argumento em inteiro
			while (j >= 0) {
				System.out.println(j);
				j--;
			}
		} catch (ArrayIndexOutOfBoundsException e1) {// Captura o erro de falta de argumentos
			System.out.println("Nao foi fornecido um argumento.");

		} catch (NumberFormatException e2) { // Captura o erro de argumento inadequado
			System.out.println("Argumento nao e um inteiro.");
		}


        // Capturar várias exceções diferrentes em uma única clausula multi-catch com o uso de → |
		try { // Tentativa
			int j = Integer.parseInt(args[0]); // converte 1o argumento em inteiro
			while (j >= 0) {
				System.out.println(j);
				j--;
			}
		} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) { // Captura dois erros através do uso do multi-catch → |: o erro de falta de argumentos e de argumento inadequado
			System.out.println("Argumento nao fornecido ou invalido.");
		} 


		// Gerando um trexo final sempre executável diante da diretiva catch, através da cláusula finaly.
		int p = 5; // valor default
		try { // Tentativa
			p = Integer.parseInt(args[0]);
		} catch (Exception e) { // sinaliza problemas encontrados. Com o uso de Exception será tratado qualquer tipo de erro.
			System.out.println("Argumento invalido ou ausente. Usando default.");
		} finally { // execução de um trecho final de código com a cláusula finaly, independente execução da diretiva catch
			while (p >= 0) {	
				System.out.println(p);
				p--;
			}
		}


		/* Uma asserção torna possível saber de uma situação de que não deveria ocontecer. Funciona como um teste para o código:
		 * caso a condição avaliada da asserção seja verdadeira, o programa ignora a asserção, mas caso seja falso será lançada a
		 * exceção do tipo AssertioinError.
		 * 
		 * •As asserções precisam ser habilitadas:
		 * > java -ea NomeArquivo // sem a extenção .java
		 * --------ou--------
		 * > java -enableassertions NomeArquivo // sem a extenção .java
		 * •Para desabilitá-las:
		 * > java -da NomeArquivo // sem a extenção .java
		 * --------ou--------
		 * > java -disableassertions NomeArquivo // sem a extenção .java */
		Scanner sc = new Scanner(System.in); // cria leitor de dados
		System.out.print("Forneca inteiro>9: ");
		int valor = sc.nextInt(); // lê valor inteiro
		assert valor>9;	// caso o valor da asserção seja menor que 9 será lançada a exceção do tipo AssertioinError
		int soma=0;		// declara variável para soma
		for (int i=1; i<valor; i++) { // realiza laço para efetuar soma
			soma+=i;
			assert soma<99: "soma(" + soma + ")>=99"; // esperado soma<99
		}
		System.out.println("soma[1.."+ valor + "] = " + soma);
		sc.close(); 


		/* try catch para realização monitorada da operação close(), para 'recurso_try_sem_recurso', utilizando a cláusula finally 
		 * com a estrutura de desvio de fluxo condicional simples, cláusula (Clause) if - (Pag65_.java...).  Se mostra uma tarefa 
		 * repetitiva cujo código resultante é pouco legível. A solução, para um código mais legível, é fazer uso do Automatic
		 * Resource Management (ARM) ou try-com-recursos (try-with-resources), abaixo ↓↓↓. */
		// Exemplo 1 - de código pouco legível SEM uso do recurso try-com-recurso—————————————————————————————————————————————————
		InputStream recurso_try_sem_recurso = null;
		try { // ♦tentativa SEM uso do recurso try-com-recurso
			recurso_try_sem_recurso = new FileInputStream(args[0]); 
			System.out.println("Arquivo " + args[0] + " aberto.");
		} catch (IOException e1) { // ♦captura o erro da tentativa
			System.out.println(e1); 
		// Realização monitorada da operação close(), para 'recurso_try_sem_recurso', COM finally e COM if: ↓↓↓
		} finally {
			if (recurso_try_sem_recurso != null) try { // ♦tentativa SEM uso do recurso try-com-recurso
				recurso_try_sem_recurso.close();
				System.out.println("Arquivo " + args[0] + " fechado.");
			} catch (IOException e2) {} // ♦captura o erro da tentativa
		} //Exemplo 1 - FIM_______________________________________________________________________________________________________

		/* Exemplo 2 - de código mais legível COM uso do recurso try-com-recurso——————————————————————————————————————————————————
		 * Realização monitorada da operação close(), para 'recurso_try_com_recurso', SEM finally e SEM if. O uso da sintaxe 
		 * try-com-recurso remove a necessidade de utilizar a cláusula finally, contendo testes e fechamento dos recursos↓↓↓ */
		try (InputStream recurso_try_com_recurso = new FileInputStream(args[0]);) { // ♦tentativa COM uso do recurso try-com-recurso
			System.out.println("Arquivo " + args[0] + " aberto.");
		} catch (IOException e1) { // ♦captura o erro da tentativa
			System.out.println(e1);
		}
		System.out.println("Arquivo " + args[0] + " fechado.");
		//Exemplo 2 - FIM_________________________________________________________________________________________________________


	/*  Simplificação no uso do ARM Automatic Resource Management (ARM) ou try-com-recursos (try-with-resources):
	 * → melhora a legibiilidade do try-com-recursos, declarando-se o recurso antes de usá-lo em try-com-recursos.  */
		String s = "Java - Guia do Programador, 4a Ed.";
		processaRecurso(s);
	} // fim do método construtor — main —
	public static void processaRecurso(String s) {
		StringReader recurso = new StringReader(s); // o objeto 'recurso' do tipo StringReader, é declarado e instanciado antes
		try (recurso) { // ♦tentativa COM uso do recurso try-com-recurso
			int espacos = 0, c;
			while ((c = recurso.read()) != -1) {
				if (c == 32) espacos++;
			}
			System.out.println("Espacos = " + espacos);
		} catch (IOException e1) { // ♦captura o erro da tentativa
			System.out.println(e1);
		}
		System.out.println("Recurso processado.");
	}
}
