package mn.io.polaris.service;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import mn.io.polaris.constant.Constants;
import mn.io.polaris.model.polaris.*;
import mn.io.polaris.model.polaris.request.GetLoanBillList;
import mn.io.polaris.model.polaris.request.GetLoanBillListParams;
import mn.io.polaris.model.polaris.request.GetLoanBillListParams2;
import mn.io.polaris.model.polaris.request.GetLoanList;
import mn.io.polaris.model.polaris.request.GetLoanListCust;
import mn.io.polaris.model.polaris.request.GetLoanListParams;
import mn.io.polaris.model.polaris.response.LoanAcntBillListResponse;
import mn.io.polaris.model.polaris.response.LoanAcntListResponse;
import mn.io.polaris.model.polaris.request.ArcvNonCashRequest;
import mn.io.polaris.model.request.*;
import mn.io.polaris.model.response.AccountDto;
import mn.io.polaris.model.response.ArcvNonCashResponse;
import mn.io.polaris.model.response.LoanAccountBalance;
import mn.io.polaris.model.response.LoanAccountBill;
import mn.io.polaris.remote.PolarisClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Log4j2
@Service
public class ArcvService {

    @Resource
    private PolarisClient polarisClient;
    @Value("${qpay.loan.acc}")
    private String qpayLoanAccount;

    public ArcvNonCashResponse payArcvTransaction(@Valid ArcvNonCashPayRequest arcvNonCashPayRequest) {

        ArcvNonCashRequest arcvNonCashRequest = new ArcvNonCashRequest();
        arcvNonCashRequest.setTxnAcntCode(arcvNonCashPayRequest.getAcntCode());
        arcvNonCashRequest.setContAcntType("EXPENSE");
        arcvNonCashRequest.setContAcntCode(qpayLoanAccount);
        arcvNonCashRequest.setTxnDesc(arcvNonCashPayRequest.getTxnDesc());
        arcvNonCashRequest.setRate(1);
        arcvNonCashRequest.setRateTypeId("46");
        arcvNonCashRequest.setContAmount(arcvNonCashPayRequest.getTxnAmount());
        arcvNonCashRequest.setTxnAmount(arcvNonCashPayRequest.getTxnAmount());
        arcvNonCashRequest.setCurCode("MNT");
        arcvNonCashRequest.setContSysNo(1301);
        arcvNonCashRequest.setTxnSysNo(1326);
        arcvNonCashRequest.setContRate(0);
        arcvNonCashRequest.setIsTmw(1);
        arcvNonCashRequest.setIdentityType("MANUAL");
        arcvNonCashRequest.setTcustType(0);
        arcvNonCashRequest.setSourceType("OI");
        arcvNonCashRequest.setIsPreview(0);
        arcvNonCashRequest.setIsPreviewFee(0);
        arcvNonCashRequest.setAcntType("INCOME");
        arcvNonCashRequest.setContChartCode("1");
        arcvNonCashRequest.setDtOrCtDominant("D");
        arcvNonCashRequest.setContCurCode("MNT");

        return polarisClient.payArcvTransaction(arcvNonCashRequest);
    }
}
