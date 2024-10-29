//pacote a que tudo pertence
package br.com.springboot.project_otavio;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "br.com.springboot.project_otavio")
public class Application {
	
	//Main é um método auto executável em Java
    public static void main(String[] args) {
    	
    	//método Main com springboot e o RUN starta a classe que vai rodar a aplicação
    	//É A LINHA PRINCIPAL QUE RODA O PROJETO JAVA SPRING BOOT
        SpringApplication.run(Application.class, args);
    }
}
