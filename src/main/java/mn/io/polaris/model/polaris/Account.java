package mn.io.polaris.model.polaris;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

    @Size(max = 12)
    @Schema(description = "Бүтээгдэхүүний код")
    private String prodCode;

    @Size(max = 20)
    @Schema(description = "Дансны дугаар")
    private String acntCode;

    @Size(max = 200)
    @Schema(description = "Дансны нэр")
    private String acntName;

    @Size(max = 20)
    @Schema(description = "Дансны төрөл")
    private String acntType;

    @Size(max = 200)
    @Schema(description = "Бүтээгдэхүүний нэр")
    private String prodName;

    @Size(max = 3)
    @Schema(description = "Валют")
    private String curCode;

    @Schema(description = "Дансны үлдэгдэл")
    private BigDecimal balance;

    @Schema(description = "Дансны боломжит үлдэгдэл")
    private BigDecimal availBalance;

    @Size(max = 1)
    @Schema(description = "Дансны төлөв")
    private String status;

    @Size(max = 1)
    @Schema(description = "Нууцлагдсан эсэх")
    private Integer isSecure;

    @Schema(description = "Блоклосон дүн")
    private BigDecimal blocked;

    @Schema(description = "Улайлтын дүн")
    private BigDecimal od;

    private Integer flagNoDebit;
    private BigDecimal availLimit;

    @Schema(description = "Данс зогсоосон эсэх")
    private Integer flagStopped;

    private String acntCustCode;

    @Schema(description = "Бүтээгдэхүүний нэр 2-догч хэлээр")
    private String prodName2;

    private String jointTypeCode;
    private Integer flagStoppedPayment;

    @Schema(description = "Cистем дугаар")
    private Integer sysNo;

    @Schema(description = "Дансны нэр 2")
    private String acntName2;

    @Schema(description = "Данс зогсоосон эсэх")
    private Integer flagStoppedInt;

    @Schema(description = "Харилцагчийн дугаар")
    private String custCode;

    private Integer flagFrozen;
    private Integer isAllowPartialLiq;
    private Integer flagDormant;
    private Integer flagNoCredit;

    @Schema(description = "Хүү балансын гадуур гарсан эсэх")
    private Integer flagWroffInt;

    @Schema(description = "Үндсэн зээл балансын гадуур гарсан эсэх")
    private Integer flagWroffPrinc;

    @Schema(description = "Тусгай активт шилжсэн эсэх код")
    private Integer flagMoveSa;

    @Schema(description = "Зээл худалдсан эсэх")
    private Integer flagSec;

    private Integer flagSpecial;

}
