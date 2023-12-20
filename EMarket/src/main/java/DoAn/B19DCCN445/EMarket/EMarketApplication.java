package DoAn.B19DCCN445.EMarket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.github.javafaker.Faker;

import DoAn.B19DCCN445.EMarket.config.StorageProperties;
import DoAn.B19DCCN445.EMarket.model.Category;
import DoAn.B19DCCN445.EMarket.model.Product;
import DoAn.B19DCCN445.EMarket.model.Product_review;
import DoAn.B19DCCN445.EMarket.model.Role;
import DoAn.B19DCCN445.EMarket.model.Store;
import DoAn.B19DCCN445.EMarket.model.User;
import DoAn.B19DCCN445.EMarket.service.FilesStorageService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class EMarketApplication implements CommandLineRunner {
	@PersistenceContext
	private EntityManager em;
	@Autowired
	PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(EMarketApplication.class, args);

	}

	@Bean
	CommandLineRunner init(FilesStorageService storageService) {
		return (args -> {
			storageService.init();
		});
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
//		Faker faker = new Faker();
//		Role role1 = Role.builder().name("ADMIN").build();
//		Role role2 = Role.builder().name("SELLER").build();
//		Role role3 = Role.builder().name("CUSTOMER").build();
//		em.persist(role1);
//		em.persist(role2);
//		em.persist(role3);
//		List<Role> roles = new ArrayList<>();
//		roles.add(role1);
//		User user1 = User.builder().username("admin").password(encoder.encode("123")).fullname("Bùi Hoài Nam")
//				.birthday(faker.date().birthday()).phone(faker.phoneNumber().cellPhone()).roles(roles).gender("Male")
//				.email(faker.internet().emailAddress()).build();
//		em.persist(user1);
//
//		roles = new ArrayList<>();
//		roles.add(role2);
//		roles.add(role3);
//		Store store = Store.builder().store_name("BHN Store").description("Description").image(null).build();
//		em.persist(store);
//		User user2 = User.builder().username("bhn").password(encoder.encode("123"))
//				.email(faker.internet().emailAddress()).fullname("Bùi Hoài Ninh").gender("Male")
//				.phone(faker.phoneNumber().cellPhone()).roles(roles).birthday(faker.date().birthday()).store(store)
//				.build();
//		em.persist(user2);
//		roles = new ArrayList<>();
//		roles.add(role3);
//		User user3 = User.builder().username("btk").password(encoder.encode("123"))
//				.email(faker.internet().emailAddress()).fullname("Bùi Tuấn Kiệt").gender("Male")
//				.phone(faker.phoneNumber().cellPhone()).roles(roles).birthday(faker.date().birthday())
//				.build();
//		em.persist(user3);
//		
//		Category category = Category.builder().category_name("Laptop").id(null).description("description")
//				.build();
//		em.persist(category);
//		Category category2 = Category.builder().category_name("Smart Phone").id(null).description("description")
//				.build();
//		em.persist(category2);
//
//		Category category3 = Category.builder().category_name("Test").id(null).description("description")
//				.build();
//		em.persist(category3);
//		for (int i = 0; i < 30; i++) {
//			Double cost = Double.parseDouble(faker.commerce().price(1, 5));
//			String description = faker.lorem().paragraphs(10).stream().reduce("", (s1, s2) -> s1 + "\n" + s2);
//			Product p = Product.builder().product_name(faker.commerce().productName()).description(description)
//					.standard_cost(cost).price(Double.parseDouble(faker.commerce().price(1, cost))).stock(100)
//					.product_status("featured").category(category3).store(store).build();
//			em.persist(p);
//			Product_review pr1 = Product_review.builder().rating(faker.number().numberBetween(1, 6))
//					.title(faker.lorem().sentence()).subtitle(faker.lorem().sentence()).product(p).user(user1).build();
//			Product_review pr2 = Product_review.builder().rating(faker.number().numberBetween(1, 6))
//					.title(faker.lorem().sentence()).subtitle(faker.lorem().sentence()).product(p).user(user2).build();
//			em.persist(pr1);
//			em.persist(pr2);
//		}

	}

}
