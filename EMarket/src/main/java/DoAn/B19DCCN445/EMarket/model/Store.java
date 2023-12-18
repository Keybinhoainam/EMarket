package DoAn.B19DCCN445.EMarket.model;



import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Store {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//	@Column(unique=true)
//	private String store_code;
	@Column(unique=true)
	private String store_name;
	private String description;
	private String image;
	private String location;
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp create_at;
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp update_at;
	
	@OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
//	@JsonManagedReference("store-shop_imports")
	private Set<Shop_import> shop_imports;
	@OneToOne(mappedBy = "store", cascade = CascadeType.REMOVE)
	@JsonBackReference("store-users")
	private User user;
	@OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
//	@JsonManagedReference("store-shop_exports")
	private Set<Shop_export>shop_exports;
	@OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY )

	@JsonBackReference("store-categories")
	private Collection<Category>categories;
	
}
