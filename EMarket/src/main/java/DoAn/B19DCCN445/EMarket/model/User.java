package DoAn.B19DCCN445.EMarket.model;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
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
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Valid
	@Column(unique = true)
	private String username;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String email;
	private String fullname;
	private String gender;
	private Date birthday;
	private String avatar;
	private String phone;
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_at;
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date update_at;

//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "users",fetch = FetchType.LAZY)
//	private Collection<Acl_user_has_role> acl_user_has_roles;
	@Fetch(FetchMode.SUBSELECT)
	@JsonBackReference("user-orders")
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<Order> orders;
	@Fetch(FetchMode.SUBSELECT)
	@JsonBackReference("user-product_review")
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<Product_review> product_reviews;
//	@Fetch(FetchMode.SUBSELECT)
////	@JsonManagedReference("user-cart_details")
//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private Collection<Cart_detail> cart_details;

	@OneToOne
    @JoinColumn(name = "store_id")
	@JsonBackReference("store-users")
	private Store store;

	@ManyToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@Fetch(FetchMode.SUBSELECT)
	private Collection<Role> roles;

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return roles.stream().map((role) -> {
			return new SimpleGrantedAuthority(role.getName());
		}).collect(Collectors.toList());
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
