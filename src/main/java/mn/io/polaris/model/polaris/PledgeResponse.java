package mn.io.polaris.model.polaris;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PledgeResponse {

    private String mainCurCode;
    private String acntName;
    private String utilId;
    private String useAcntName2;
    private String useAcntName;
    private String useSysName;
    private String linkTypeName;
    private BigDecimal lastRate;
    private String statusName;
    private String sysName;
    private String linkTypeName2;
    private BigDecimal useAmount;
    private Integer useSysNo;
    private String companyCode;
    private Integer sysNo;
    private String acntCode;
    private BigDecimal mainAmount;
    private String useAcntCode;
    private BigDecimal availBal;
    private String useCurCode;
    private Date lastRevalDate;
    private Integer isBlockInt;
    private BigDecimal limitAmount;
    private String linkType;
    private String status;

}
