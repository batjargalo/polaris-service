package mn.io.polaris.repository;

import mn.io.polaris.model.dao.CodeDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface CodeDaoRepository extends JpaRepository<CodeDao, BigInteger> {

    List<CodeDao> findAllByTableNameIsAndCodeStatusIsOrderByCodeOrder(String tableName, Integer codeStatus);
}