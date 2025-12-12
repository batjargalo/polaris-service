package mn.io.polaris.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class LoanAccountDetailDto {

    @Schema(description = "Дансны дугаар")
    private String acntCode;

    @Schema(description = "Бүтээгдэхүүний нэр")
    private String prodName;

    @Schema(description = "Үндсэн зээлийн үлдэгдэл")
    private BigDecimal princBal;

    @Schema(description = "Олгосон дүн")
    private BigDecimal advAmount;

    @Schema(description = "Үндсэн хүүгийн хувь")
    private BigDecimal baseFixedIntRate;

    @Schema(description = "Хугацааны урт")
    private Integer termLen;

    @Schema(description = "Эхэлсэн өдөр")
    private Date startDate;

    @Schema(description = "Зээл дуусах огноо")
    private Date endDate;

    @Schema(description = "Нийт зээл төлөх тоо")
    private Integer paymentCount;

    @Schema(description = "Нийт зээл төлсөн тоо")
    private Integer paidCount;

    @Schema(description = "Дараагийн төлөлт хийх огноо")
    private Date nextSchdDate;

    @Schema(description = "Дараагийн нийт төлөх дүн")
    private BigDecimal nextSchdTotal;

    @Schema(description = "Хугацаа хэтэрсэн хоног")
    private Long overdueDayCount;

    @Schema(description = "Зориулалт")
    private String purpose;

    @Schema(description = "Төлөв")
    private String status;

    @Schema(description = "Төлөвийн нэр")
    private String statusName;

    @Schema(description = "Хугацааны төрөл")
    private String termBasis;

    @Schema(description = "Зээлийн хугацаа дууссан эсэх")
    private Boolean isExpired;

    @Schema(description = "Данс хариуцагчийн нэр")
    private String acntManagerName;

    @Schema(description = "Эргэн төлөлтийн дансны дугаар")
    private String repayAcntCode;

    @Schema(description = "Баталсан дүн")
    private BigDecimal approvAmount;

    @Schema(description = "Боломжит үлдэгдэл")
    private BigDecimal availComBal;

    @Schema(description = "Ашигласан дүн")
    private BigDecimal usedComBal;

    @Schema(description = "Бүтээгдэхүүний төрөл")
    private String prodType;

    @Schema(description = "Бүтээгдэхүүний код")
    private String prodCode;

    @Schema(description = "Нийт үлдэгдэл")
    private BigDecimal totalBal;

}
