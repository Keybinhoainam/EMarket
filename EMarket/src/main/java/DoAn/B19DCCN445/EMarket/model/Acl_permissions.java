//package DoAn.B19DCCN445.EMarket.model;
//
//import java.sql.Timestamp;
//import java.util.Date;
//import java.util.Set;
//
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//import lombok.Data;
//@Data
//@Entity
//public class Acl_permissions {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	private String name;
//	private Timestamp create_at;
//	private Timestamp update_at;
//	private Timestamp delete_at;
//	
//	@OneToMany(mappedBy = "acl_permissions")
//	private Set<Acl_role_has_permission> acl_role_has_permissions;
//}
