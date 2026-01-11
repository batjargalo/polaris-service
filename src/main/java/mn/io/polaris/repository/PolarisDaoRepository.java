package mn.io.polaris.repository;

import mn.io.polaris.model.dao.PolarisDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PolarisDaoRepository extends JpaRepository<PolarisDao, BigInteger> {
}