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
			Categoria c1 = new Categoria("ACOMPANHAMENTOS");
			Categoria c2 = new Categoria("APERITIVOS");
			Categoria c3 = new Categoria("ENTRADAS");
			Categoria c4 = new Categoria("LANCHES");
			Categoria c5 = new Categoria("MOLHOS");
			Categoria c6 = new Categoria("PRINCIPAL");
			Categoria c7 = new Categoria("SALADAS");
			Categoria c8 = new Categoria("SOBREMESAS");
			
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
			
			Ingrediente i1 = new Ingrediente("ÓLEO");
			Ingrediente i2 = new Ingrediente("CENOURA");
			Ingrediente i3 = new Ingrediente("OVO");
			Ingrediente i4 = new Ingrediente("AÇÚCAR");
			Ingrediente i5 = new Ingrediente("FARINHA");
			Ingrediente i6 = new Ingrediente("FERMENTO");
			Ingrediente i7 = new Ingrediente("MANTEIGA");
			Ingrediente i8 = new Ingrediente("CHOCOLATE");
			Ingrediente i9 = new Ingrediente("LEITE");
						
			ingredienteRepository.save(i1);
			ingredienteRepository.save(i2);
			ingredienteRepository.save(i3);
			ingredienteRepository.save(i4);
			ingredienteRepository.save(i5);
			ingredienteRepository.save(i6);
			ingredienteRepository.save(i7);
			ingredienteRepository.save(i8);
			ingredienteRepository.save(i9);
			
			// Produto inicio
			Receita r1 = new Receita();
			Receita r2 = new Receita();
							
			r1.setNome("Bolo de Cenoura");
			r1.setTempoPreparo(40);
			r1.setRendimento(8);
			r1.setModoPreparo("1 - Em um liquidificador, adicione a cenoura, os ovos e o óleo, depois misture. "
					+ "2 - Acrescente o açúcar e bata novamente por 5 minutos. "
					+ "3 - Em uma tigela ou na batedeira, adicione a farinha de trigo e depois misture novamente. "
					+ "4 - Acrescente o fermento e misture lentamente com uma colher. "
					+ "5 - Asse em um forno preaquecido a 180° C por aproximadamente 40 minutos.");
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
			
			r1.addIngrediente(i1, "1/2", "Xícara (chá)");
			r1.addIngrediente(i2, "3", "Unidades raladas");
			r1.addIngrediente(i3, "4", "Unidades");
			r1.addIngrediente(i4, "3", "Xícara (chá)");
			r1.addIngrediente(i5, "2 e 1/2", "Xícara (chá)");
			r1.addIngrediente(i6, "1", "Colher (sopa)");
			r1.addIngrediente(i7, "1", "Colher (sopa)");
			r1.addIngrediente(i8, "3", "Colheres (sopa)");
			r1.addIngrediente(i9, "1", "Xícara (chá)");
			receitaRepository.save(r1);
		};
	}
}
