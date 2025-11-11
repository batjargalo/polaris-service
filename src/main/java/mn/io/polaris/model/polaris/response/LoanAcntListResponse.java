package mn.io.polaris.model.polaris.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// @AllArgsConstructor
// @NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanAcntListResponse {

    private Integer sysNo;
    private Integer flagWroffPrinc;
    private String acntName;
    private Integer flagWroffInt;
    private Integer flagMoveSa;
    private String acntName2;
    private String acntCode;
    private Integer flagSec;
    private Integer isSecure;
    private Integer flagStopped;
    private Integer flagStoppedInt;
    private String custCode;
    private Integer flagSpecial;
    private String prodCode;
    private String acntCustCode;
    private String availBalance;
    private Integer isAllowPartialLiq;
    private String balance;
    private String prodName2;
    private String acntType;
    private String prodName;
    private String jointTypeCode;
    private String curCode;
    private String status;

}
