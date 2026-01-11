package mn.io.polaris.service;

import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import mn.io.polaris.model.dao.CodeDao;
import mn.io.polaris.repository.CodeDaoRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Log4j2
@Service
public class CodeService {

    @Resource
    private CodeDaoRepository codeDaoRepository;

    public List<CodeDao> findAllByTableName(String tableName) {
        return codeDaoRepository.findAllByTableNameIsAndCodeStatusIsOrderByCodeOrder(tableName, 1);
    }

    public CodeDao findById(BigInteger id) {
        return codeDaoRepository.findById(id).orElse(null);
    }

}
