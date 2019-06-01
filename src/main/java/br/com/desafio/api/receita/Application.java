package br.com.desafio.api.receita;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.desafio.api.receita.domain.dao.CategoriaRepository;
import br.com.desafio.api.receita.domain.dao.IngredienteRepository;
import br.com.desafio.api.receita.domain.dao.ReceitaRepository;
import br.com.desafio.api.receita.domain.entity.Categoria;
import br.com.desafio.api.receita.domain.entity.Ingrediente;
import br.com.desafio.api.receita.domain.entity.Receita;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	protected CommandLineRunner init(
			final ReceitaRepository receitaRepository
			, final CategoriaRepository categoriaRepository
			, final IngredienteRepository ingredienteRepository) {

		return args -> {
			// Categoria inicio
			Categoria c1 = new Categoria();
			Categoria c2 = new Categoria();
			Categoria c3 = new Categoria();
			Categoria c4 = new Categoria();
			Categoria c5 = new Categoria();
			Categoria c6 = new Categoria();
			Categoria c7 = new Categoria();
			Categoria c8 = new Categoria();
			
			c1.setNome("ACOMPANHAMENTOS");
			c2.setNome("APERITIVOS");
			c3.setNome("ENTRADAS");
			c4.setNome("LANCHES");
			c5.setNome("MOLHOS");
			c6.setNome("PRINCIPAL");
			c7.setNome("SALADAS");
			c8.setNome("SOBREMESAS");
			
			/**Persistencia*/
			categoriaRepository.save(c1);
			categoriaRepository.save(c2);
			categoriaRepository.save(c3);
			categoriaRepository.save(c4);
			categoriaRepository.save(c5);
			categoriaRepository.save(c6);
			categoriaRepository.save(c7);
			categoriaRepository.save(c8);
			// Categoria fim
			
			Ingrediente i1 = new Ingrediente();
			Ingrediente i2 = new Ingrediente();
			Ingrediente i3 = new Ingrediente();
			
			i1.setNome("OVO");
			i2.setNome("FARINHA");
			i3.setNome("LEITE");
			
			ingredienteRepository.save(i1);
			ingredienteRepository.save(i2);
			ingredienteRepository.save(i3);
			
			// Produto inicio
			Receita r1 = new Receita();
			Receita r2 = new Receita();
							
			r1.setNome("Bolo de Cenoura");
			r1.setTempoPreparo(40);
			r1.setRendimento(8);
			r1.setModoPreparo("1 - Em um liquidificador, adicione a cenoura, os ovos e o óleo, depois misture. "
					+ "2 - Acrescente o açúcar e bata novamente por 5 minutos. ");
			r1.setCategoria(c8);
			/**Persistencia*/
			receitaRepository.save(r1);
						
			r2.setNome("Bife à Parmegiana");
			r2.setTempoPreparo(90);
			r2.setRendimento(6);
			r2.setModoPreparo("1 - Tempere os bifes com alho e sal e reserve. "
					+ "2 - Bata o ovo inteiro e passe os bifes pelo ovo e pela farinha de rosca. "
					+ "3 - Aqueça em uma frigideira o óleo e frite os bifes, escorra-os em papel toalha e vá dispondo em um refratário.");
			r2.setCategoria(c6);			
			
			/**Persistencia*/
			receitaRepository.save(r2);
			
			r1.addIngrediente(i1, "2", "Unidades");
			r1.addIngrediente(i2, "3", "Xícaras");
			r1.addIngrediente(i3, "2", "Colheres");
			receitaRepository.save(r1);
		};
	}
}
