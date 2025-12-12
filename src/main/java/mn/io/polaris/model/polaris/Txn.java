package mn.io.polaris.model.polaris;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Txn {

    @Schema(description = "Гүйлгээ хийсэн бодит огноо")
    private Date postDate;

    private BigDecimal income;
    private BigDecimal outcome;
    private String txnDesc;

    @Schema(description = "Үлдэгдэл")
    private BigDecimal balance;

    private BigDecimal contCurRate;
    private String acntCode;
    private String userName;
    private Integer userId;
    private String userBrchCode;
    private String txnNo;
    private String txnCode;
    private String balTypeCode;
    private BigInteger jrno;
    private String curCode;
    private String contAcntCode;
    private BigDecimal curRate;
    private Long jritemNo;
    private String txnDate;
    private String contAcntName;
    private BigDecimal available;
    private BigDecimal begbalance;
    private BigDecimal endbalance;

}
