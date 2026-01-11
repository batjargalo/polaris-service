package mn.io.polaris.repository;

import mn.io.polaris.model.dao.SystemDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemDaoRepository extends JpaRepository<SystemDao, String> {
}