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

import DoAn.B19DCCN445.EMarket.config.StorageProperties;
import DoAn.B19DCCN445.EMarket.model.Category;
import DoAn.B19DCCN445.EMarket.model.Payment_type;
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
		return (args->{
			storageService.init();
		});
	}
	@Override
	@Transactional
	public void run(String... args) throws Exception {
//		Role role1= Role.builder().name("ADMIN").build();
//		Role role2= Role.builder().name("SELLER").build();
//		Role role3= Role.builder().name("CUSTOMER").build();
//		em.persist(role1);em.persist(role2);em.persist(role3);
//		List<Role> roles= new ArrayList<>();
//		roles.add(role1);
//		em.persist(User.builder().username("admin").password(encoder.encode("123")).fullname("Bùi Hoài Nam").roles(roles).build());
//		
//		Category category= Category.builder().category_name("laptop").id(null).description("description").build();
//		em.persist(category);
//		Store store= Store.builder().store_code("BHN").store_name("BHN Store").description("Description").image(null).build();
//		em.persist(store);
//		
//		Payment_type payment_type1=Payment_type.builder().name("Pay Now").build();
//		Payment_type payment_type2=Payment_type.builder().name("Buy Now, Pay Later").build();
//		em.persist(payment_type1);
//		em.persist(payment_type2);
	}

}
