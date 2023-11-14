package DoAn.B19DCCN445.EMarket.model;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private String product_code;
	@Column(unique=true)
	private String product_name;
	private String description;
	private String short_description;
	private Double standard_cost;
	private Double price;
	private Boolean is_discontinued;
	private Boolean is_featured;
	private Boolean is_new;
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_at;
	@Temporal(TemporalType.TIMESTAMP)
	private Date update_at;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	@JsonBackReference("category-products")
	private Category category;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	@JsonBackReference("store-products")
	private Store store;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
//	@JsonManagedReference("product-shop_export_details")
	private Collection<Shop_export_detail> shop_export_details;
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JsonManagedReference("product-order_details")
	private Collection<Order_detail> order_details;
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
//	@JsonManagedReference("product-product_reviews")
	private Collection<Product_review> product_reviews;
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
//	@JsonManagedReference("product-product_vouchers")
	private Collection<Product_voucher> product_vouchers;
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@JsonManagedReference("product-product_images")
	private Collection<Product_image> product_images;
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
//	@JsonManagedReference("product-product_discounts")
	private Collection<Product_discount> product_discounts;
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
//	@JsonManagedReference("product-shop_import_details")
	private Collection<Shop_import_detail> shop_import_details;
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
//	@JsonManagedReference("product-cart_details")
	private Collection<Cart_detail> cart_details;
}
