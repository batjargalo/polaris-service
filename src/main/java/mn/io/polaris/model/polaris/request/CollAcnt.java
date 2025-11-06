package mn.io.polaris.model.polaris.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollAcnt {

    private String name;
    private String name2;
    private String custCode;
    private String prodCode;
    private String prodType;
    private String collType;
    private String brchCode;
    private String status;
    private String key2SysNo;
    private String key2;
    private BigDecimal price;
    private String curCode;

}
