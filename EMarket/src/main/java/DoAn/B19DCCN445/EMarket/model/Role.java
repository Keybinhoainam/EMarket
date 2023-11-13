package DoAn.B19DCCN445.EMarket.model;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp create_at;
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp update_at;
	private Timestamp delete_at;
	
//	@OneToMany(mappedBy = "acl_roles", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private Set<Acl_role_has_permission> acl_role_has_permissions;
//	@OneToMany(mappedBy = "acl_roles", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private Set<Acl_user_has_role> acl_user_has_roles;
}
