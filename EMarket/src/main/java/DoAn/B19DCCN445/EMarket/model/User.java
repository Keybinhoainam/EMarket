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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Valid
	@Column(unique = true)
	private String username;
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

	@Fetch(FetchMode.SUBSELECT)
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
	private Set<Shop_import> shop_imports;
	@Fetch(FetchMode.SUBSELECT)
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
	private Set<Shop_export> shop_exports;
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "users",fetch = FetchType.LAZY)
//	private Collection<Acl_user_has_role> acl_user_has_roles;
	@Fetch(FetchMode.SUBSELECT)
	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Order> orders;
	@Fetch(FetchMode.SUBSELECT)
	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Product_review> product_reviews;
	@Fetch(FetchMode.SUBSELECT)
	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Customer_voucher> customer_vouchers;
	@Fetch(FetchMode.SUBSELECT)
	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Cart_detail> cart_details;
	@Fetch(FetchMode.SUBSELECT)
	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<User_address> user_addresses;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	@JsonBackReference
	private Store store;

	@ManyToMany(fetch = FetchType.EAGER)
	@JsonManagedReference
	@Fetch(FetchMode.SUBSELECT)
	private Collection<Role> roles;

	@Override
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
