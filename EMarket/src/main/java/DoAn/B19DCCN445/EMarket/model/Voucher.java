package DoAn.B19DCCN445.EMarket.model;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Voucher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String voucher_code;
	private String voucher_name;
	private String description;
	private int uses;
	private int max_uses_user;
	private String type;
	private Double discount_amount;
	private Boolean is_fixed;
	private Timestamp start_date;
	private Timestamp end_date;
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_at;
	@Temporal(TemporalType.TIMESTAMP)
	private Date update_at;
	@Temporal(TemporalType.TIMESTAMP)
	private Date delete_at;
	
	@OneToMany(mappedBy = "voucher", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	private Set<Product_voucher>product_vouchers;
	@OneToMany(mappedBy = "voucher", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	private Set<Customer_voucher>customer_vouchers;
}
