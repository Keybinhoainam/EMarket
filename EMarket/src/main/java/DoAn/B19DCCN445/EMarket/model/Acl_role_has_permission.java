//package DoAn.B19DCCN445.EMarket.model;
//
//import java.sql.Timestamp;
//import java.util.Set;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import lombok.Data;
//@Data
//@Entity
//public class Acl_role_has_permission {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "permission_id")
//	private Acl_permissions acl_permissions;
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "role_id")
//	private Role acl_roles;
//}
