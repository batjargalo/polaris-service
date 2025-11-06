package mn.io.polaris.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatementDto {

    private BigDecimal beginBal;
    private BigDecimal endBal;
    private List<TxnDto> txns;

}
