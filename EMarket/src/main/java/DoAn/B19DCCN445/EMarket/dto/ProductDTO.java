package DoAn.B19DCCN445.EMarket.dto;

import java.util.Collection;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonBackReference;

import DoAn.B19DCCN445.EMarket.model.Category;
import DoAn.B19DCCN445.EMarket.model.Product_image;
import DoAn.B19DCCN445.EMarket.model.Product_review;
import DoAn.B19DCCN445.EMarket.model.Product_voucher;
import DoAn.B19DCCN445.EMarket.model.Store;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	private Long id;
	private String product_code;
	private String product_name;
	private String description;
	private String short_description;
	private Double standard_cost;
	private Double price;
	private int stock;
	private Boolean is_discontinued;
	private Boolean is_featured;
	private Boolean is_new;
	private Date create_at;
	private Date update_at;
	private Category category;
	private Store store;
	private Collection<Product_image> product_images;
	private Double rating;
	private Integer reviews;
	private Collection<Product_review> product_reviews;
	private Collection<Product_voucher> product_vouchers;
}
