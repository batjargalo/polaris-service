package mn.io.polaris.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import mn.io.polaris.model.polaris.DueLoan;
import mn.io.polaris.model.polaris.LoanRepayment;
import mn.io.polaris.model.polaris.LoanRepaymentInfo;
import mn.io.polaris.model.polaris.request.NRSListResponseDto;
import mn.io.polaris.model.polaris.response.DepositTdAccountResponseDto;
import mn.io.polaris.model.polaris.response.LoanAccountResponse;
import mn.io.polaris.model.request.*;
import mn.io.polaris.model.response.*;
import mn.io.polaris.service.LoanService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Loan", description = "Зээл")
@RequestMapping(path = "/loan", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoanController {

    @Resource
    private LoanService loanService;

    @PostMapping(path = "/info")
    @Operation(summary = "Дансны дэлгэрэнгүй")
    public LoanAccountDetailDto getLoanInfo(@RequestBody @Valid InfoRequest infoRequest) {
        return loanService.getLoanInfo(infoRequest);
    }

    @PostMapping(path = "/statement")
    @Operation(summary = "Дансны хуулга")
    public StatementDto getLoanStatement(@RequestBody @Valid StatementRequest statementRequest) {
        return loanService.getLoanStatement(statementRequest);
    }

    @PostMapping(path = "/repayment")
    @Operation(summary = "Эргэн төлөлтийн хуваарь")
    public List<LoanRepayment> getLoanRepayment(@RequestBody @Valid LoanRepaymentRequest loanRepaymentRequest) {
        return loanService.getLoanRepayment(loanRepaymentRequest);
    }

    @PostMapping(path = "/close-amount")
    @Operation(summary = "Зээл хаах дансны дэлгэрэнгүй")
    public LoanCloseDto getCloseAmount(@RequestBody @Valid LoanCloseRequest loanCloseRequest) {
        return loanService.getCloseAmount(loanCloseRequest);
    }

    @GetMapping(path = "/due")
    @Operation(summary = "Төлбөр дөхсөн зээлийн жагсаалт")
    public List<DueLoan> getDueLoans() {
        return loanService.getDueLoans();
    }

    @PostMapping(path = "/bill-balance")
    @Operation(summary = "Хуваарийн дагуу төлөх дүн")
    public BillBalanceDto getBillBalance(@RequestBody @Valid InfoRequest infoRequest) {
        return loanService.getBillBalance(infoRequest);
    }

    @PostMapping(path = "/pay")
    @Operation(summary = "Зээл төлөлт")
    public DepositTdAccountResponseDto payLoan(@RequestBody @Valid PayLoanRequestDto payLoanRequestDto) {
        return loanService.payLoan(payLoanRequestDto);
    }

    @PostMapping(path = "/close")
    @Operation(summary = "Зээлийн данс хаах (Бэлэн бус)")
    public DepositTdAccountResponseDto closeLoan(@RequestBody @Valid PayLoanRequestDto payLoanRequestDto) {
        return loanService.closeLoan(payLoanRequestDto);
    }

    @PostMapping(path = "/create/td")
    @Operation(summary = "Хадгаламжийн данс барьцаалж зээл олгох - Бүх үйлдлийг нэгтгэсэн")
    public DepositTdAccountResponseDto createTdLoan(@RequestBody @Valid CreateTdLoanRequestDto createTdLoanRequestDto) {
        return loanService.createTdLoan(createTdLoanRequestDto);
    }

    // region ПОЛАРИСРУУ ЗЭЭЛ ҮҮСГЭХ ХҮСЭЛТ
    @PostMapping(path = "/create/loan")
    @Operation(summary = "Цахим зээл олгох")
    public LoanAccountResponse createLoan(@RequestBody @Valid CreateLoanRequest createLoanRequest) {
        return loanService.createLoan(createLoanRequest);
    }
    // endregion

    @PostMapping(path = "/edit/repayment")
    @Operation(summary = "Хур.хүүг дараагийн төлөлтөнд оруулах эсэх чектэйгээр төлбөрийн хуваарь засварлах")
    public CustomResponseDto editLoanAccountRepayment(@RequestBody @Valid EditRepaymentRequestDto dto) {
        return loanService.editLoanAccountRepayment(dto);
    }

    @PostMapping(path = "/calculate/repayment")
    @Operation(summary = "Зээлийн эргэн төлөлтийн хуваарь тооцоолох")
    public NRSListResponseDto calculateLoanAccountRepayment(@RequestBody @Valid CalculateRepaymentRequestDto dto) {
        return loanService.calculateLoanAccountRepayment(dto);
    }

    @PostMapping(path = "/calculate/repayment/amount")
    @Operation(summary = "Зээлийн эргэн төлөлтийн хуваарь тооцооолсны дараах дүн")
    public AmountDto calculateLoanAccountRepaymentAmount(@RequestBody @Valid CalculateRepaymentRequestDto dto) {
        return loanService.calculateLoanAccountRepaymentAmount(dto);
    }

    @PostMapping(path = "/repayment/info")
    @Operation(summary = "Зээлийн төлбөрийн хуваарийн тохиргооны дэлгэрэнгүй")
    public LoanRepaymentInfo getRepaymentInfo(@RequestBody @Valid LoanRepaymentRequest loanRepaymentRequest) {
        return loanService.getRepaymentInfo(loanRepaymentRequest);
    }

}