package mn.io.polaris.model.polaris;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pledge {

    private BigDecimal unitPrice;
    private String key2;
    private String brchCode;
    private Integer key2SysNo;
    private BigDecimal prvnReleasePrcnt;
    private String acntCode;
    private BigDecimal limitAmt;
    private BigDecimal available;
    private Integer collType;
    private String prodType;
    private String custCode;
    private String prodCode;
    private BigDecimal price;
    private Integer qty;
    private String name;
    private String curCode;
    private String name2;
    private BigDecimal utilized;
    private String status;

}
