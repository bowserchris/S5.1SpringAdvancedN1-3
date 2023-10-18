package cat.itacademy.barcelonactiva.sucursal.s05.t01.n01.S05T01N01Sucursal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"cat.itacademy.barcelonactiva.sucursal.s05.t01.n01.S05T01N01Sucursal"})
@EnableJpaRepositories(basePackages = "cat.itacademy.barcelonactiva.sucursal.s05.t01.n01.S05T01N01Sucursal.model.repository")
@EntityScan(basePackages = "cat.itacademy.barcelonactiva.sucursal.s05.t01.n01.S05T01N01Sucursal.model.domain")
public class S05T01N01SucursalApplication {

    public static void main(String[] args) {
		SpringApplication.run(S05T01N01SucursalApplication.class, args);
	}
   
}
