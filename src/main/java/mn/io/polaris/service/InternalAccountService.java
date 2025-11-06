package mn.io.polaris.service;

import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import mn.io.polaris.constant.Constants;
import mn.io.polaris.helper.Utils;
import mn.io.polaris.model.polaris.request.*;
import mn.io.polaris.model.polaris.response.DepositTdAccountResponseDto;
import mn.io.polaris.model.request.BetweenAccountsRequestDto;
import mn.io.polaris.model.request.DepositTdAccountRequestDto;
import mn.io.polaris.model.request.LoanBetweenAccountsRequestDto;
import mn.io.polaris.model.request.LoanNonCashRequestDto;
import mn.io.polaris.remote.PolarisClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Log4j2
@Service
public class InternalAccountService {

    @Value("${qpay.loan.acc}")
    private String qpayLoanAccount;

    @Value("${qpay.td.acc}")
    private String qpayTdAccount;

    @Value("${qpay.collect.acc}")
    private String qpayCollectAccount;

    @Value("${cgw.loan.allowance.acc}")
    private String cgwLoanAllowanceAccount;

    @Resource
    private PolarisClient polarisClient;

    public DepositTdAccountResponseDto depositTdAccount(DepositTdAccountRequestDto depositTdAccountRequestDto) {
        DepositTdAccountRequest depositTdAccountRequest = new DepositTdAccountRequest();
        BigDecimal rate = Constants.DEFAULT_RATE;
        Integer operationCode = 13610022;
        String txnAccountCode = qpayTdAccount;
        depositTdAccountRequest.setOperCode(String.valueOf(operationCode));
        depositTdAccountRequest.setTxnAcntCode(txnAccountCode);
        depositTdAccountRequest.setTxnAmount(depositTdAccountRequestDto.getTxnAmount());
        depositTdAccountRequest.setRate(rate);
        depositTdAccountRequest.setContAcntCode(depositTdAccountRequestDto.getContAcntCode());
        depositTdAccountRequest.setContAmount(depositTdAccountRequestDto.getTxnAmount());
        depositTdAccountRequest.setContRate(rate);
        depositTdAccountRequest.setRateTypeId(Constants.DEFAULT_RATE_TYPE_ID);
        depositTdAccountRequest.setTxnDesc(depositTdAccountRequestDto.getTxnDesc());
        depositTdAccountRequest.setSourceType(Constants.DEFAULT_SOURCE_TYPE);
        depositTdAccountRequest.setIsPreview(Constants.DEFAULT_IS_PREVIEW);
        depositTdAccountRequest.setIsPreviewFee(Constants.DEFAULT_IS_PREVIEW_FEE);
        depositTdAccountRequest.setIsTmw(Constants.DEFAULT_IS_TMW);
        List<AspParam> aspParams = Utils.getAspParams(depositTdAccountRequestDto.getContAcntCode(), txnAccountCode,
                operationCode);
        depositTdAccountRequest.setAspParam(aspParams);
        return polarisClient.depositTdAccount(depositTdAccountRequest);
    }

    public DepositTdAccountResponseDto betweenAccounts(BetweenAccountsRequestDto betweenAccountsRequestDto) {
        BetweenAccountsRequest betweenAccountsRequest = new BetweenAccountsRequest();
        BigDecimal rate = Constants.DEFAULT_RATE;
        if (betweenAccountsRequestDto.getTxnType().equals(BetweenAccountsRequestDto.TxnType.LOAN)) {
            betweenAccountsRequest.setTxnAcntCode(qpayLoanAccount);
        } else if (betweenAccountsRequestDto.getTxnType().equals(BetweenAccountsRequestDto.TxnType.TD)) {
            betweenAccountsRequest.setTxnAcntCode(qpayTdAccount);
        }
        betweenAccountsRequest.setTxnAmount(betweenAccountsRequestDto.getTxnAmount());
        betweenAccountsRequest.setRate(rate);
        betweenAccountsRequest.setContAcntCode(qpayCollectAccount);
        betweenAccountsRequest.setContAmount(betweenAccountsRequestDto.getTxnAmount());
        betweenAccountsRequest.setContRate(rate);
        betweenAccountsRequest.setTxnDesc(betweenAccountsRequestDto.getTxnDesc());
        betweenAccountsRequest.setRateTypeId(Constants.DEFAULT_RATE_TYPE_ID);
        betweenAccountsRequest.setSourceType(Constants.DEFAULT_SOURCE_TYPE);
        betweenAccountsRequest.setIsPreview(Constants.DEFAULT_IS_PREVIEW);
        betweenAccountsRequest.setIsPreviewFee(Constants.DEFAULT_IS_PREVIEW_FEE);
        betweenAccountsRequest.setIsTmw(Constants.DEFAULT_IS_TMW);
        return polarisClient.betweenAccounts(betweenAccountsRequest);
    }

    public DepositTdAccountResponseDto loanBetweenAccounts(LoanBetweenAccountsRequestDto loanBetweenAccountsRequestDto) {
        BigDecimal rate = Constants.DEFAULT_RATE;
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setTxnAcntCode(loanBetweenAccountsRequestDto.getTxnAcntCode());
        transferRequest.setTxnAmount(loanBetweenAccountsRequestDto.getTxnAmount());
        transferRequest.setRate(rate);
        transferRequest.setContAcntCode(cgwLoanAllowanceAccount);
        transferRequest.setContRate(rate);
        transferRequest.setContAmount(loanBetweenAccountsRequestDto.getTxnAmount());
        transferRequest.setRateTypeId(Constants.DEFAULT_RATE_TYPE_ID);
        transferRequest.setTxnDesc(loanBetweenAccountsRequestDto.getTxnDesc());
        transferRequest.setSourceType(Constants.DEFAULT_SOURCE_TYPE);
        transferRequest.setIsPreview(Constants.DEFAULT_IS_PREVIEW);
        transferRequest.setIsPreviewFee(Constants.DEFAULT_IS_PREVIEW_FEE);
        transferRequest.setIsTmw(Constants.DEFAULT_IS_TMW);
        return polarisClient.transferFromTempAccToInternalAcc(transferRequest);
    }

    public DepositTdAccountResponseDto loanNonCash(LoanNonCashRequestDto loanNonCashRequestDto) {
        LoanNonCashRequest loanNonCashRequest = new LoanNonCashRequest();
        BigDecimal rate = Constants.DEFAULT_RATE;
        loanNonCashRequest.setTxnAcntCode(loanNonCashRequestDto.getTxnAcntCode());
        loanNonCashRequest.setTxnAmount(loanNonCashRequestDto.getTxnAmount());
        loanNonCashRequest.setCurCode(Constants.DEFAULT_CUR_CODE);
        loanNonCashRequest.setRate(rate);
        loanNonCashRequest.setContAcntCode(loanNonCashRequestDto.getContAcntCode());
        loanNonCashRequest.setContAmount(loanNonCashRequestDto.getTxnAmount());
        loanNonCashRequest.setContCurCode(Constants.DEFAULT_CUR_CODE);
        loanNonCashRequest.setContRate(rate);
        loanNonCashRequest.setRateTypeId(Constants.DEFAULT_RATE_TYPE_ID);
        loanNonCashRequest.setTxnDesc(loanNonCashRequestDto.getTxnDesc());
        loanNonCashRequest.setSourceType(Constants.DEFAULT_SOURCE_TYPE);
        loanNonCashRequest.setIsTmw(Constants.DEFAULT_IS_TMW);
        loanNonCashRequest.setIsPreview(Constants.DEFAULT_IS_PREVIEW);
        loanNonCashRequest.setIsPreviewFee(Constants.DEFAULT_IS_PREVIEW_FEE);
        return polarisClient.loanNonCash(loanNonCashRequest);
    }

}
