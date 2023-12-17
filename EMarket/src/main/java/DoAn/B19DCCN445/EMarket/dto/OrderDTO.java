package DoAn.B19DCCN445.EMarket.dto;


import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import DoAn.B19DCCN445.EMarket.model.Order_detail;
import DoAn.B19DCCN445.EMarket.model.User;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date order_date;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date shipped_date;
	private String note;
	private String ship_address;
	private Double ship_fee;
	private Date paid_date;
	private String order_status;
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_at;
	@Temporal(TemporalType.TIMESTAMP)
	private Date update_at;
//	private User user;
	private String payment_type;
	private Collection<Order_detail>order_details;
	private Double amount;
}
