package mn.io.polaris.model.polaris;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Statement {

    private BigDecimal beginBal;
    private BigDecimal endBal;
    private List<Txn> txns;

}
