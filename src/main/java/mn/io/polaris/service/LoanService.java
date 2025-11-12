package mn.io.polaris.service;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import mn.io.polaris.constant.Constants;
import mn.io.polaris.helper.Utils;
import mn.io.polaris.model.polaris.*;
import mn.io.polaris.model.polaris.request.*;
import mn.io.polaris.model.polaris.response.DepositTdAccountResponseDto;
import mn.io.polaris.model.polaris.response.LoanAccountResponse;
import mn.io.polaris.model.polaris.response.LoanExtendPResponse;
import mn.io.polaris.model.request.*;
import mn.io.polaris.model.response.*;
import mn.io.polaris.remote.PolarisClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class LoanService {

    @Resource
    private PolarisClient polarisClient;

    @Resource
    private AccountService accountService;

    @Value("${qpay.loan.acc}")
    private String qpayLoanAccount;

    public LoanAccountDetailDto getLoanInfo(InfoRequest infoRequest) {
        LoanAccount loanAccount = polarisClient.getLoanInfo(infoRequest);
        LoanRepaymentRequest loanRepaymentRequest = new LoanRepaymentRequest();
        loanRepaymentRequest.setAcntCode(infoRequest.getAcntCode());
        List<LoanRepayment> loanRepayments = polarisClient.getLoanRepayment(loanRepaymentRequest);
        int paymentCount = 0;
        int paidCount = 0;
        if (loanRepayments != null && !loanRepayments.isEmpty()) {
            paymentCount = loanRepayments.size();
            for (LoanRepayment lr : loanRepayments) {
                if (loanAccount.getPrincBal().compareTo(lr.getTheorBal()) > 0) {
                    break;
                } else {
                    paidCount++;
                }
            }
        }
        LoanAccountDetailDto accountDetailDto = convertLoanAccountToLoanAccountDetailDto(loanAccount);
        accountDetailDto.setPaymentCount(paymentCount);
        accountDetailDto.setPaidCount(paidCount);
        return accountDetailDto;
    }

    public List<LoanRepayment> getLoanRepayment(LoanRepaymentRequest loanRepaymentRequest) {
        InfoRequest infoRequest = new InfoRequest();
        infoRequest.setAcntCode(loanRepaymentRequest.getAcntCode());
        infoRequest.setGetWithSecure(0);
        LoanAccount loanAccount = polarisClient.getLoanInfo(infoRequest);
        List<LoanRepayment> loanRepayments = polarisClient.getLoanRepayment(loanRepaymentRequest);
        if (loanRepayments != null && !loanRepayments.isEmpty()) {
            for (LoanRepayment lr : loanRepayments) {
                lr.setIsPaid(loanAccount.getPrincBal().compareTo(lr.getTheorBal()) <= 0);
            }
        }
        return loanRepayments;
    }

    public StatementDto getLoanStatement(StatementRequest statementRequest) {
        Statement statement = polarisClient.getLoanStatement(statementRequest);
        return processTxns(statement);
    }

    public StatementDto processTxns(Statement statement) {
        StatementDto statementDto = new StatementDto();
        statementDto.setBeginBal(statement.getBeginBal());
        statementDto.setEndBal(statement.getEndBal());
        List<TxnDto> sortedTxns = new ArrayList<>();
        for (Txn t : statement.getTxns()) {
            if (t != null) {
                // txnCode
                boolean doesTxnCodeMatch = false;
                if (t.getTxnCode() != null) {
                    for (String code : Constants.TXN_CODE_LIST) {
                        if (t.getTxnCode().equalsIgnoreCase(code)) {
                            doesTxnCodeMatch = true;
                            break;
                        }
                    }
                }
                if (!doesTxnCodeMatch) {
                    continue; // Хэрэггүй гүйлгээг алгасаж байна.
                }

                BigDecimal income = t.getIncome();
                BigDecimal outcome = t.getOutcome();

                TxnDto txnDto = new TxnDto();
                txnDto.setPostDate(t.getPostDate());
                txnDto.setTxnDate(t.getTxnDate());
                txnDto.setBalance(t.getBalance());

                boolean isIntTxn = false;
                for (String codeInt : Constants.TXN_CODE_LIST_ONLY_INT) {
                    if (t.getTxnCode().equalsIgnoreCase(codeInt)) {
                        txnDto.setTotalIntAmountGrouped(Utils.getSafeBigDecimal(txnDto.getTotalIntAmountGrouped()).add(
                                Utils.getSafeBigDecimal(outcome)).subtract(Utils.getSafeBigDecimal(income)));
                        isIntTxn = true;
                        break;
                    }
                }
                if (!isIntTxn) {
                    for (String codePrincipal : Constants.TXN_CODE_LIST_ONLY_PRINCIPAL) {
                        if (t.getTxnCode().equalsIgnoreCase(codePrincipal)) {
                            txnDto.setTotalPrincAmountGrouped(Utils
                                    .getSafeBigDecimal(txnDto.getTotalPrincAmountGrouped()).add(
                                            Utils.getSafeBigDecimal(outcome))
                                    .subtract(Utils.getSafeBigDecimal(income)));
                            break;
                        }
                    }
                }
                sortedTxns.add(txnDto);
            }
        }
        sortedTxns.sort(Comparator.comparing(TxnDto::getPostDate));
        statementDto.setTxns(sortedTxns);
        return statementDto;
    }

    public LoanCloseDto getCloseAmount(LoanCloseRequest loanCloseRequest) {
        LoanClose loanClose = polarisClient.getCloseAmount(loanCloseRequest);
        return convertLoanCloseToLoanCloseDto(loanClose);
    }

    public LoanRepaymentInfo getRepaymentInfo(LoanRepaymentRequest loanRepaymentRequest) {
        return polarisClient.getRepaymentInfo(loanRepaymentRequest.getAcntCode());
    }

    public List<DueLoan> getDueLoans() {
        return polarisClient.getDueLoans();
    }

    public LoanAccountDetailDto convertLoanAccountToLoanAccountDetailDto(LoanAccount loanAccount) {
        LoanAccountDetailDto loanAccountDetailDto = new LoanAccountDetailDto();
        loanAccountDetailDto.setAcntCode(loanAccount.getAcntCode());
        loanAccountDetailDto.setProdName(loanAccount.getProdName());
        loanAccountDetailDto.setPrincBal(loanAccount.getPrincBal());
        loanAccountDetailDto.setAdvAmount(loanAccount.getAdvAmount());
        loanAccountDetailDto.setBaseFixedIntRate(loanAccount.getBaseFixedIntRate());
        loanAccountDetailDto.setTermLen(loanAccount.getTermLen());
        loanAccountDetailDto.setStartDate(loanAccount.getStartDate());
        loanAccountDetailDto.setEndDate(loanAccount.getEndDate());
        loanAccountDetailDto.setStatus(loanAccount.getStatus());
        loanAccountDetailDto.setStatusName(loanAccount.getStatusName());
        loanAccountDetailDto.setTermBasis(loanAccount.getTermBasis());
        loanAccountDetailDto.setAcntManagerName(loanAccount.getAcntManagerName());
        loanAccountDetailDto.setRepayAcntCode(loanAccount.getRepayAcntCode());
        loanAccountDetailDto.setTotalBal(loanAccount.getTotalBal());
        if (loanAccount.getTotalBill() != null && loanAccount.getTotalBill().compareTo(BigDecimal.ZERO) > 0) {
            loanAccountDetailDto.setNextSchdTotal(loanAccount.getTotalBill());
            Date earliest = Utils.findEarliestDate(loanAccount.getBillPrincDate(), loanAccount.getBillBaseintDate(),
                    loanAccount.getBillFineDate());
            loanAccountDetailDto.setNextSchdDate(earliest);
        } else {
            loanAccountDetailDto.setNextSchdTotal(Utils.getSafeBigDecimal(loanAccount.getNextSchdAmt()).add(
                    Utils.getSafeBigDecimal(loanAccount.getNextSchdInt())));
            loanAccountDetailDto.setNextSchdDate(loanAccount.getNextSchdDate());
        }
        Date now = new Date();
        if (loanAccountDetailDto.getNextSchdDate() != null) {
            if (loanAccountDetailDto.getNextSchdDate().before(now)) {
                loanAccountDetailDto.setOverdueDayCount(
                        Utils.getDaysDifference(loanAccountDetailDto.getNextSchdDate(), now));
            }
        }
        // Зээлийн дуусах огноо хэтэрсэн бол
        if (loanAccountDetailDto.getEndDate() != null && loanAccountDetailDto.getEndDate().before(now)) {
            loanAccountDetailDto.setNextSchdDate(Utils.findEarliestDate(loanAccountDetailDto.getNextSchdDate(),
                    loanAccountDetailDto.getEndDate()));
            LoanCloseRequest loanCloseRequest = new LoanCloseRequest();
            loanCloseRequest.setCloseDate(Utils.changeDateToString(now, Constants.DATE_FORMAT_YYYY_MM_DD));
            loanCloseRequest.setAcntCode(loanAccountDetailDto.getAcntCode());
            LoanClose loanClose = null;
            try {
                loanClose = polarisClient.getCloseAmount(loanCloseRequest);
            } catch (Exception e) {
                log.error("An exception occurred when calling the getCloseAmount API: {}", e.getMessage());
            }
            if (loanClose != null) {
                loanAccountDetailDto.setNextSchdTotal(loanClose.getTotalBal());
            }
            loanAccountDetailDto.setOverdueDayCount(
                    Utils.getDaysDifference(loanAccountDetailDto.getNextSchdDate(), now));
            loanAccountDetailDto.setIsExpired(true);
        }
        loanAccountDetailDto.setPurpose(loanAccount.getPurpose());
        loanAccountDetailDto.setApprovAmount(loanAccount.getApprovAmount());
        loanAccountDetailDto.setAvailComBal(loanAccount.getAvailComBal());
        loanAccountDetailDto.setUsedComBal(loanAccount.getUsedComBal());
        loanAccountDetailDto.setProdType(loanAccount.getProdType());
        return loanAccountDetailDto;
    }

    public LoanCloseDto convertLoanCloseToLoanCloseDto(LoanClose loanClose) {
        LoanCloseDto loanAccountDetailDto = new LoanCloseDto();
        loanAccountDetailDto.setTotalBal(loanClose.getTotalBal());
        return loanAccountDetailDto;
    }

    public BillBalanceDto getBillBalance(InfoRequest infoRequest) {
        LoanAccount loanAccount = polarisClient.getLoanInfo(infoRequest);
        BillBalanceDto billBalanceDto = new BillBalanceDto();
        billBalanceDto.setBillBaseintBal(loanAccount.getBillBaseintBal());
        billBalanceDto.setBillFinebBal(loanAccount.getBillFinebBal());
        billBalanceDto.setBillFinepBal(loanAccount.getBillFinepBal());
        billBalanceDto.setBillComintBal(loanAccount.getBillComintBal());
        billBalanceDto.setBillPrincBal(loanAccount.getBillPrincBal());
        BigDecimal totalBill = loanAccount.getBillBaseintBal()
                .add(loanAccount.getBillFinebBal())
                .add(loanAccount.getBillFinepBal())
                .add(loanAccount.getBillComintBal())
                .add(loanAccount.getBillPrincBal());
        billBalanceDto.setTotalBill(totalBill);
        return billBalanceDto;
    }

    public DepositTdAccountResponseDto payLoan(PayLoanRequestDto payLoanRequestDto) {
        PayLoanRequest payLoanRequest = new PayLoanRequest();
        BigDecimal rate = Constants.DEFAULT_RATE;
        String contAcntCode = qpayLoanAccount;
        payLoanRequest.setTxnAcntCode(payLoanRequestDto.getTxnAcntCode());
        payLoanRequest.setTxnAmount(payLoanRequestDto.getTxnAmount());
        payLoanRequest.setContAmount(payLoanRequestDto.getTxnAmount());
        payLoanRequest.setRate(rate);
        payLoanRequest.setRateTypeId(Constants.DEFAULT_RATE_TYPE_ID);
        payLoanRequest.setContAcntCode(contAcntCode);
        payLoanRequest.setContRate(rate);
        payLoanRequest.setTxnDesc("Зээл төлөв");
        payLoanRequest.setSourceType(Constants.DEFAULT_SOURCE_TYPE);
        payLoanRequest.setIsPreview(Constants.DEFAULT_IS_PREVIEW);
        payLoanRequest.setIsPreviewFee(Constants.DEFAULT_IS_PREVIEW_FEE);
        payLoanRequest.setIsTmw(Constants.DEFAULT_IS_TMW);
        List<PayLoanAddParam> addParams = new ArrayList<>();
        PayLoanAddParam payLoanAddParam = new PayLoanAddParam();
        payLoanAddParam.setContAcntType("BAC");
        addParams.add(payLoanAddParam);
        payLoanRequest.setAddParams(addParams);
        return polarisClient.payLoan(payLoanRequest);
    }

    public DepositTdAccountResponseDto closeLoan(PayLoanRequestDto payLoanRequestDto) {
        CloseLoanRequest payLoanRequest = new CloseLoanRequest();
        BigDecimal rate = Constants.DEFAULT_RATE;
        String contAcntCode = qpayLoanAccount;
        payLoanRequest.setTxnAcntCode(payLoanRequestDto.getTxnAcntCode());
        payLoanRequest.setTxnAmount(payLoanRequestDto.getTxnAmount());
        payLoanRequest.setCurCode(Constants.DEFAULT_CUR_CODE);
        payLoanRequest.setRate(rate);
        payLoanRequest.setRateTypeId(Constants.DEFAULT_RATE_TYPE_ID);
        payLoanRequest.setContAcntCode(contAcntCode);
        payLoanRequest.setContAmount(payLoanRequestDto.getTxnAmount());
        payLoanRequest.setContRate(rate);
        payLoanRequest.setContCurCode(Constants.DEFAULT_CUR_CODE);
        payLoanRequest.setTxnDesc("Зээл хаав");
        payLoanRequest.setSourceType(Constants.DEFAULT_SOURCE_TYPE);
        payLoanRequest.setIsPreview(Constants.DEFAULT_IS_PREVIEW);
        payLoanRequest.setIsPreviewFee(Constants.DEFAULT_IS_PREVIEW_FEE);
        payLoanRequest.setIsTmw(Constants.DEFAULT_IS_TMW);
        List<AspParam> aspParams = Utils.getAspParams(contAcntCode, payLoanRequestDto.getTxnAcntCode(), 13600107);
        payLoanRequest.setAspParam(aspParams);
        List<CloseLoanAddParam> addParams = new ArrayList<>();
        CloseLoanAddParam closeLoanAddParam = new CloseLoanAddParam();
        closeLoanAddParam.setContAcntType("BAC");
        closeLoanAddParam.setChkAcntInt("N");
        addParams.add(closeLoanAddParam);
        payLoanRequest.setAddParams(addParams);
        return polarisClient.closeLoan(payLoanRequest);
    }

    public Date getSystemDateInDateObject() {
        String systemDate = polarisClient.getSystemDate();
        String systemDateFormatted = systemDate.replace("\"", "");
        return Utils.changeDateToString(systemDateFormatted, Constants.DATE_FORMAT_YYYY_MM_DD);
    }

    public String getSystemDateInStringObject() {
        String systemDate = polarisClient.getSystemDate();
        return systemDate.replace("\"", "");
    }

    // region ПОЛАРИСРУУ TD ЗЭЭЛ ҮҮСГЭХ

    public DepositTdAccountResponseDto createTdLoan(CreateTdLoanRequestDto createTdLoanRequestDto) {
        String systemDate = polarisClient.getSystemDate();
        String systemDateFormatted = systemDate.replace("\"", "");
        Date now = Utils.changeDateToString(systemDateFormatted, Constants.DATE_FORMAT_YYYY_MM_DD);

        InfoRequest infoRequest = new InfoRequest();
        infoRequest.setAcntCode(createTdLoanRequestDto.getTdAcntCode());
        infoRequest.setGetWithSecure(0);
        Td td = polarisClient.getTdInfo(infoRequest);
        BigDecimal tdCurrentBal = td.getCurrentBal();
        BigDecimal loanMaxAvailableBal = tdCurrentBal.multiply(Constants.DEFAULT_TD_LOAN_PERCENTAGE);
        BigDecimal tdBlockBal = td.getBlockBal();
        BigDecimal loanCurrentAvailableBal = loanMaxAvailableBal.subtract(tdBlockBal);

        if (createTdLoanRequestDto.getTxnAmount().compareTo(loanCurrentAvailableBal) > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Боломжит зээлийн дүнгээс хэтэрч байна!");
        }

        Date expectedMaturityDate = td.getMaturityDate();
        if (now.after(expectedMaturityDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Хадгаламжийн хугацаа дууссан тул зээл авах боломжгүй байна!");
        }
        Integer termLen = Utils.calculateFullMonthsBetween(now, expectedMaturityDate);

        AccountCreateRequest accountCreateRequest = new AccountCreateRequest();
        accountCreateRequest.setCustCode(td.getCustCode());
        accountCreateRequest.setName(td.getName());
        accountCreateRequest.setName2(td.getName2());
        AccountNo tempAccountNo = accountService.createAccount(accountCreateRequest);
        AccountNoRequest accountNoRequest = new AccountNoRequest();
        accountNoRequest.setAcntCode(tempAccountNo.getAcntCode());
        accountService.changeAccountStatus(accountNoRequest);

        String defaultCurCode = Constants.DEFAULT_CUR_CODE;
        BigDecimal defaultRate = Constants.DEFAULT_RATE;
        String defaultRateTypeId = Constants.DEFAULT_RATE_TYPE_ID;

        TdLoanRequest tdLoanRequest = new TdLoanRequest();
        tdLoanRequest.setTxnAmount(createTdLoanRequestDto.getTxnAmount());
        tdLoanRequest.setCurCode(defaultCurCode);
        tdLoanRequest.setRate(defaultRate);
        tdLoanRequest.setContAcntCode(tempAccountNo.getAcntCode());
        tdLoanRequest.setContAmount(createTdLoanRequestDto.getTxnAmount());
        tdLoanRequest.setContCurCode(defaultCurCode);
        tdLoanRequest.setContRate(defaultRate);
        tdLoanRequest.setRateTypeId(defaultRateTypeId);
        tdLoanRequest.setTxnDesc("ХБЗээл олгов");
        tdLoanRequest.setSourceType(Constants.DEFAULT_SOURCE_TYPE);
        tdLoanRequest.setIsPreview(Constants.DEFAULT_IS_PREVIEW);
        tdLoanRequest.setIsPreviewFee(Constants.DEFAULT_IS_PREVIEW_FEE);
        tdLoanRequest.setIsBlockInt(0);

        CollAcnt collAcnt = getCollAcnt(createTdLoanRequestDto, td, defaultCurCode);
        tdLoanRequest.setCollAcnt(collAcnt);

        String defaultLoanProdCode = Constants.DEFAULT_TD_LOAN_PROD_CODE;

        LoanAcnt loanAcnt = new LoanAcnt();
        loanAcnt.setCustCode(td.getCustCode());
        loanAcnt.setName(td.getName());
        loanAcnt.setName2(td.getName2());
        loanAcnt.setProdCode(defaultLoanProdCode);
        loanAcnt.setCurCode(defaultCurCode);
        loanAcnt.setApprovAmount(createTdLoanRequestDto.getTxnAmount());
        loanAcnt.setApprovDate(now);
        loanAcnt.setStartDate(now);
        loanAcnt.setTermLen(termLen);
        loanAcnt.setTermBasis("M");
        loanAcnt.setEndDate(expectedMaturityDate);
        loanAcnt.setPurpose(Constants.DEFAULT_TD_LOAN_PURPOSE_CODE);
        loanAcnt.setSubPurpose(Constants.DEFAULT_TD_LOAN_SUB_PURPOSE_CODE);
        loanAcnt.setIsNotAutoClass(0);
        loanAcnt.setComRevolving(0);
        loanAcnt.setDailyBasisCode("ACTUAL/365 FIXED");
        loanAcnt.setAcntManager(Constants.DEFAULT_ACNT_MANAGER);
        loanAcnt.setBrchCode(Constants.DEFAULT_BRANCH_CODE);
        loanAcnt.setIsGetBrchFromOutside(1);
        loanAcnt.setSegCode(Constants.CUST_SEG_CODE_RETAIL);
        loanAcnt.setStatus("N");
        loanAcnt.setSlevel(1);
        loanAcnt.setClassNoTrm(1);
        loanAcnt.setClassNoQlt(1);
        loanAcnt.setClassNo(1);
        loanAcnt.setIsBrowseAcntOtherCom(0);
        loanAcnt.setRepayAcntCode(tempAccountNo.getAcntCode());
        loanAcnt.setRepayPriority(0);
        loanAcnt.setLosMultiAcnt(0);
        loanAcnt.setImpairmentPer(0);
        loanAcnt.setValidLosAcnt(1);
        loanAcnt.setProdType(Constants.ACCOUNT_TYPE_LOAN);
        loanAcnt.setSecType(0);
        tdLoanRequest.setLoanAcnt(loanAcnt);

        AcntNrs acntNrs = new AcntNrs();
        acntNrs.setStartDate(now);
        acntNrs.setCalcAmt(createTdLoanRequestDto.getTxnAmount());
        acntNrs.setPayType("2");
        acntNrs.setPayFreq("E");
        acntNrs.setHolidayOption("2");
        acntNrs.setShiftPartialPay(0);
        acntNrs.setTermFreeTimes(0);
        acntNrs.setIntTypeCode("SIMPLE_INT");
        acntNrs.setEndDate(expectedMaturityDate);
        tdLoanRequest.setAcntNrs(acntNrs);

        AcntInt acntInt = new AcntInt();
        acntInt.setIntTypeCode("SIMPLE_INT");

        BigDecimal loanTdRate;
        if (createTdLoanRequestDto.getIntRate() != null) {
            loanTdRate = createTdLoanRequestDto.getIntRate();
        } else {
            loanTdRate = BigDecimal.valueOf(30); // Тогтмол утга
        }
        acntInt.setIntRate(String.valueOf(loanTdRate));
        tdLoanRequest.setAcntInt(acntInt);

        DepositTdAccountResponseDto response = polarisClient.createTdLoan(tdLoanRequest);
        response.setCaAcntCode(tempAccountNo.getAcntCode());
        return response;
    }

    // endregion

    // region ПОЛАРИС ЗЭЭЛ ҮҮСГЭХ Munkh

    public LoanAccountResponse createLoan(CreateLoanRequest createLoanRequest) {

        LoanRequest loanRequest = new LoanRequest();
        loanRequest.setCustCode(createLoanRequest.getCustCode());
        loanRequest.setName(createLoanRequest.getName());
        loanRequest.setProdCode(createLoanRequest.getProdCode());
        loanRequest.setPurpose(createLoanRequest.getPurpose());
        loanRequest.setSubPurpose(createLoanRequest.getSubPurpose());
        loanRequest.setDailyBasisCode(createLoanRequest.getDailyBasisCode());
        loanRequest.setCurCode(createLoanRequest.getCurCode());
        loanRequest.setApprovAmount(createLoanRequest.getApprovAmount());
        loanRequest.setApprovDate(createLoanRequest.getApprovDate());
        loanRequest.setAcntManager(createLoanRequest.getAcntManager());
        loanRequest.setBrchCode(createLoanRequest.getBrchCode());
        loanRequest.setIsGetBrchFromOutside(createLoanRequest.getIsGetBrchFromOutside());
        loanRequest.setStartDate(createLoanRequest.getStartDate());
        loanRequest.setEndDate(createLoanRequest.getEndDate());
        loanRequest.setTermLen(createLoanRequest.getTermLen());
        loanRequest.setRepayAcntCode(createLoanRequest.getRepayAcntCode());
        loanRequest.setStatus("O");

        LoanAccountResponse response = polarisClient.createLoan(loanRequest);
        return response;
    }

    // endregion

    private CollAcnt getCollAcnt(CreateTdLoanRequestDto createTdLoanRequestDto, Td td, String defaultCurCode) {
        String defaultCollProdCode = Constants.DEFAULT_COLL_PROD_CODE;
        CollAcnt collAcnt = new CollAcnt();
        collAcnt.setName(td.getName());
        collAcnt.setName2(td.getName2());
        collAcnt.setCustCode(td.getCustCode());
        collAcnt.setProdCode(defaultCollProdCode);
        collAcnt.setProdType("COLL");
        collAcnt.setCollType("4");
        collAcnt.setBrchCode(Constants.DEFAULT_BRANCH_CODE);
        collAcnt.setStatus("N");
        collAcnt.setKey2SysNo("1306");
        collAcnt.setKey2(td.getAcntCode());
        collAcnt.setPrice(createTdLoanRequestDto.getTxnAmount());
        collAcnt.setCurCode(defaultCurCode);
        return collAcnt;
    }

    public CustomResponseDto editLoanAccountRepayment(EditRepaymentRequestDto editRepaymentRequestDto) {
        EditRepaymentRequest editRepaymentRequest = new EditRepaymentRequest();
        editRepaymentRequest.setAcntCode(editRepaymentRequestDto.getAcntCode());
        editRepaymentRequest.setCalcAmt(editRepaymentRequestDto.getCalcAmt());
        editRepaymentRequest.setPayType(editRepaymentRequestDto.getPayType());
        editRepaymentRequest.setPayFreq(editRepaymentRequestDto.getPayFreq());
        editRepaymentRequest.setPayMonth(null);
        editRepaymentRequest.setPayDay1(editRepaymentRequestDto.getPayDay1());
        editRepaymentRequest.setPayDay2(null);
        editRepaymentRequest.setHolidayOption(null);
        editRepaymentRequest.setShiftPartialPay(0);
        editRepaymentRequest.setShiftType(null);
        editRepaymentRequest.setTermFreeTimes(0);
        editRepaymentRequest.setIntTypeCode("SIMPLE_INT");
        editRepaymentRequest.setStartDate(getSystemDateInStringObject());
        editRepaymentRequest.setEndDate(editRepaymentRequestDto.getEndDate());
        editRepaymentRequest.setAdvDate(null);
        editRepaymentRequest.setDescription(editRepaymentRequestDto.getDescription());
        editRepaymentRequest.setEscapeMonths(null);
        editRepaymentRequest.setIsFixedPayment(0);
        editRepaymentRequest.setPayAmt(null);
        editRepaymentRequest.setAddAccrIntAmt(1);
        polarisClient.editLoanAccountRepayment(editRepaymentRequest);
        return new CustomResponseDto("Амжилттай!");
    }

    public NRSListResponseDto calculateLoanAccountRepayment(CalculateRepaymentRequestDto calculateRepaymentRequestDto) {
        CalculateRepaymentRequest calculateRepaymentRequest = new CalculateRepaymentRequest();
        calculateRepaymentRequest.setAcntCode(calculateRepaymentRequestDto.getAcntCode());
        calculateRepaymentRequest.setStartDate(getSystemDateInStringObject());
        calculateRepaymentRequest.setCalcAmt(calculateRepaymentRequestDto.getCalcAmt());
        calculateRepaymentRequest.setPayType(calculateRepaymentRequestDto.getPayType());
        calculateRepaymentRequest.setPayFreq(calculateRepaymentRequestDto.getPayFreq());
        calculateRepaymentRequest.setPayMonth(null);
        calculateRepaymentRequest.setPayDay1(calculateRepaymentRequestDto.getPayDay1());
        calculateRepaymentRequest.setPayDay2(null);
        calculateRepaymentRequest.setHolidayOption(2);
        calculateRepaymentRequest.setShiftPartialPay(0);
        calculateRepaymentRequest.setShiftType(null);
        calculateRepaymentRequest.setTermFreeTimes(0);
        calculateRepaymentRequest.setIntTypeCode("SIMPLE_INT");
        calculateRepaymentRequest.setEndDate(calculateRepaymentRequestDto.getEndDate());
        calculateRepaymentRequest.setAdvDate(null);
        calculateRepaymentRequest.setEscapeMonths(null);
        calculateRepaymentRequest.setListNrs(null);
        return polarisClient.calculateLoanAccountRepayment(calculateRepaymentRequest);
    }

    public AmountDto calculateLoanAccountRepaymentAmount(@Valid CalculateRepaymentRequestDto dto) {
        NRSListResponseDto nrsListResponseDto = this.calculateLoanAccountRepayment(dto);
        AmountDto amountDto = new AmountDto();
        BigDecimal maxTotalAmount = BigDecimal.ZERO;
        if (nrsListResponseDto != null && nrsListResponseDto.getNrsList() != null
                && !nrsListResponseDto.getNrsList().isEmpty()) {
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            for (NRSListDto nrs : nrsListResponseDto.getNrsList()) {
                if (nrs.getSchdDate() != null) {
                    String dateStr = nrs.getSchdDate().substring(0, 10);
                    LocalDate schdDate = LocalDate.parse(dateStr, formatter);
                    if (!schdDate.isBefore(today)) {
                        BigDecimal totalAmount = nrs.getTotalAmount();
                        if (totalAmount.compareTo(maxTotalAmount) > 0) {
                            maxTotalAmount = totalAmount;
                        }
                    }
                }
            }
        }
        amountDto.setTotalAmount(maxTotalAmount);
        return amountDto;
    }

    // region Нээлттэй харилцах дансны жагсаалт Munkh

    public LoanExtendPResponse extendLoan(@Valid LoanExtensionRequest loanExtensionRequest) {
        LoanExtendPResponse accounts = polarisClient.extendLoan(loanExtensionRequest);
        return accounts;
    }
    // endregion

}
