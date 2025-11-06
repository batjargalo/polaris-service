package mn.io.polaris.model.polaris;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Td {

    @Schema(description = "Хадгаламжийн хугацаа хоногоор")
    private Integer tenor;

    @Schema(description = "Хадгаламж эхэлсэн өдөр")
    private Date startDate;

    @Schema(description = "Хугацаа дуусах огноо")
    private Date maturityDate;

    @Schema(description = "Хүү")
    private BigDecimal intRate;

    @Schema(description = "Дансны дугаар")
    private String acntCode;

    private String createdByName;
    private String statusSysName2;
    private Integer readName;
    private String isCorpName2;
    private Integer lastSeqTxn;
    private Integer isCorpAcnt;
    private String maturityOption;
    private String createdByName2;
    private String segName;
    private String prodName;
    private String termBasis;
    private String companyCode;
    private BigDecimal blockBal;
    private String rcvAcntName;
    private String capMethodName2;
    private String rcvAcntName2;
    private Integer readTran;
    private Integer termLen;
    private String prodType;
    private Integer slevel;
    private String maturityOptionName;
    private Date modifiedDate;
    private String custType;
    private String name;
    private Date createdDatetime;
    private String name2;
    private Integer doTran;
    private String capMethodName;
    private String brchCode;
    private String modifiedByName2;
    private BigDecimal currentBal;
    private List<AcntIntList> acntIntList;
    private BigDecimal capInt;
    private BigDecimal capInt2;
    private Date modifiedDatetime;
    private String rcvAcntCode;
    private String maturityOptionName2;
    private String modifiedByName;
    private BigDecimal acrintBal;
    private String prodName2;
    private Integer modifiedBy;
    private String curCode;
    private String statusSys;
    private String brchName;
    private String isCorpName;
    private String dailyBasisCode;
    private Integer isSecure;
    private String closedByName2;
    private String custName;
    private String custCode;
    private BigDecimal availBal;
    private String prodCode;
    private BigDecimal readBal;
    private String jointOrSingle;
    private String segCode;
    private Date createdDate;
    private Integer createdBy;
    private String statusSysName;
    private String closedByName;
    private Integer passbookFacility;
    private Integer capMethod;

}
