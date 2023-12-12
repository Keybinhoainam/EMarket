package DoAn.B19DCCN445.EMarket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;

import DoAn.B19DCCN445.EMarket.model.Role;
import DoAn.B19DCCN445.EMarket.model.User;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
}
