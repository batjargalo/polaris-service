package mn.io.polaris.service;

import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import mn.io.polaris.constant.Constants;
import mn.io.polaris.helper.Utils;
import mn.io.polaris.model.polaris.Statement;
import mn.io.polaris.model.polaris.Td;
import mn.io.polaris.model.polaris.Txn;
import mn.io.polaris.model.polaris.request.*;
import mn.io.polaris.model.polaris.response.DepositTdAccountResponseDto;
import mn.io.polaris.model.polaris.response.TempAccount;
import mn.io.polaris.model.polaris.response.TxnPreview;
import mn.io.polaris.model.request.CloseTdAccountRequestDto;
import mn.io.polaris.model.request.InfoRequest;
import mn.io.polaris.model.request.StatementRequest;
import mn.io.polaris.model.request.WithdrawTdAccountRequestDto;
import mn.io.polaris.model.response.PreviewCloseTdAccountResponseDto;
import mn.io.polaris.model.response.TdAccountDetailDto;
import mn.io.polaris.remote.PolarisClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Log4j2
@Service
public class TdService {

    @Value("${qpay.td.acc}")
    private String qpayTdAccount;

    @Resource
    private PolarisClient polarisClient;

    public TdAccountDetailDto getTdInfo(InfoRequest infoRequest) {
        Td td = polarisClient.getTdInfo(infoRequest);
        return convertTdToTdAccountDetailDto(td);
    }

    public Statement getTdStatement(StatementRequest statementRequest) {
        Statement statement = polarisClient.getTdStatement(statementRequest);
        return processTxns(statement);
    }

    public TdAccountDetailDto convertTdToTdAccountDetailDto(Td td) {
        TdAccountDetailDto tdAccountDetailDto = new TdAccountDetailDto();
        tdAccountDetailDto.setAcntCode(td.getAcntCode());
        tdAccountDetailDto.setCurrentBal(td.getCurrentBal());
        tdAccountDetailDto.setAcrintBal(td.getAcrintBal());
        tdAccountDetailDto.setIntRate(td.getIntRate());
        tdAccountDetailDto.setMaturityDate(td.getMaturityDate());
        tdAccountDetailDto.setTenor(td.getTenor());
        tdAccountDetailDto.setStartDate(td.getStartDate());
        tdAccountDetailDto.setProdName(td.getProdName());
        tdAccountDetailDto.setCurCode(td.getCurCode());
        tdAccountDetailDto.setStatus(td.getStatusSysName());
        tdAccountDetailDto.setExpectedIntBal(getExpectedIntBal(td));
        tdAccountDetailDto.setStatusName(td.getStatusSysName());
        tdAccountDetailDto.setBlockBal(td.getBlockBal());
        tdAccountDetailDto.setAvailBal(td.getAvailBal());
        tdAccountDetailDto.setProdCode(td.getProdCode());
        return tdAccountDetailDto;
    }

    private BigDecimal getExpectedIntBal(Td td) {
        Date maturityDate = td.getMaturityDate();
        String dateNow = polarisClient.getSystemDate();
        String systemDateFormatted = dateNow.replace("\"", "");
        Date now = Utils.changeDateToString(systemDateFormatted, Constants.DATE_FORMAT_YYYY_MM_DD);
        long dayDiff = Utils.getDaysDifference(maturityDate, now);
        BigDecimal intRate = td.getIntRate();
        BigDecimal futureIntBal = td.getCurrentBal()
                .multiply(intRate)
                .multiply(BigDecimal.valueOf(dayDiff))
                .divide(BigDecimal.valueOf(36500), 2, RoundingMode.HALF_EVEN);
        return futureIntBal.add(td.getAcrintBal());
    }

    public DepositTdAccountResponseDto closeTdAccount(CloseTdAccountRequestDto closeTdAccountRequestDto) {
        InfoRequest infoRequest = new InfoRequest();
        infoRequest.setAcntCode(closeTdAccountRequestDto.getTxnAcntCode());
        infoRequest.setGetWithSecure(0);
        Td td = polarisClient.getTdInfo(infoRequest);
        if (td.getRcvAcntCode() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Хүлээн авах данс тохируулаагүй байна!");
        }
        if (td.getBlockBal().compareTo(BigDecimal.ZERO) != 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Данс блоктой байгаа тул хаах боломжгүй!");
        }
        CloseTdAccountRequest closeTdAccountRequest = new CloseTdAccountRequest();
        BigDecimal rate = Constants.DEFAULT_RATE;
        String curCode = Constants.DEFAULT_CUR_CODE;
        String contAcntCode = td.getRcvAcntCode();
        closeTdAccountRequest.setOperCode("13610285");
        closeTdAccountRequest.setTxnAcntCode(closeTdAccountRequestDto.getTxnAcntCode());
        closeTdAccountRequest.setRate(rate);
        closeTdAccountRequest.setCurCode(curCode);
        closeTdAccountRequest.setContAcntCode(contAcntCode);
        closeTdAccountRequest.setContCurCode(curCode);
        closeTdAccountRequest.setContRate(rate);
        closeTdAccountRequest.setRateTypeId(Constants.DEFAULT_RATE_TYPE_ID);
        String closeAccTxnDesc = "Хугацаат хадгаламжийн данс бэлэн бусаар хаав.";
        closeTdAccountRequest.setTxnDesc(closeAccTxnDesc);
        closeTdAccountRequest.setSourceType(Constants.DEFAULT_SOURCE_TYPE);
        closeTdAccountRequest.setIsPreview(Constants.DEFAULT_IS_PREVIEW);
        closeTdAccountRequest.setIsPreviewFee(Constants.DEFAULT_IS_PREVIEW_FEE);
        closeTdAccountRequest.setIsTmw(Constants.DEFAULT_IS_TMW);
        List<AspParam> aspParams = Utils.getAspParams(contAcntCode, closeTdAccountRequestDto.getTxnAcntCode(), 13610285);
        closeTdAccountRequest.setAspParam(aspParams);
        List<AddParam> addParams = Utils.getAddParams("1");
        closeTdAccountRequest.setAddParams(addParams);
        DepositTdAccountResponseDto depositTdAccountResponseDto = polarisClient.closeTdAccount(closeTdAccountRequest);

        String systemDate = polarisClient.getSystemDate();
        String systemDateFormatted = systemDate.replace("\"", "");
        Date now = Utils.changeDateToString(systemDateFormatted, Constants.DATE_FORMAT_YYYY_MM_DD);
        StatementRequest statementRequest = new StatementRequest();
        statementRequest.setAcntCode(contAcntCode);
        statementRequest.setStartDate(Utils.changeDateToString(now, Constants.DATE_FORMAT_YYYY_MM_DD));
        statementRequest.setEndDate(Utils.changeDateToString(now, Constants.DATE_FORMAT_YYYY_MM_DD));
        statementRequest.setStartPagingPosition(0);
        statementRequest.setPageRowCount(50);
        Statement statement = polarisClient.getTdStatement(statementRequest);
        BigDecimal txnAmount = null;
        for (Txn t : statement.getTxns()) {
            if (Objects.equals(t.getJrno(), depositTdAccountResponseDto.getTxnJrno()) &&
                    t.getContAcntCode().equalsIgnoreCase(closeTdAccountRequestDto.getTxnAcntCode()) &&
                    t.getAcntCode().equalsIgnoreCase(contAcntCode)) {
                txnAmount = t.getIncome();
                break;
            }
        }

        InfoRequest tempAccountInfoReq = new InfoRequest();
        tempAccountInfoReq.setAcntCode(contAcntCode);
        tempAccountInfoReq.setGetWithSecure(0);
        TempAccount tempAccount = polarisClient.getTempAccountInfo(tempAccountInfoReq);

        String internalAccount = qpayTdAccount;
        log.info("tempAccount.getCurrentBal(): {}", tempAccount.getCurrentBal());
        log.info("tempAccount.getTotalAvailBal(): {}", tempAccount.getTotalAvailBal());
        log.info("txnAmount: {}", txnAmount);

        if (tempAccount.getCurrentBal().compareTo(txnAmount) == 0
                && tempAccount.getTotalAvailBal().compareTo(txnAmount) == 0) {
            //Түр харилцах дансны үлдэгдэл яг таарч байвал дансыг хаах гүйлгээ хийж байна.
            log.info("Түр харилцах дансны үлдэгдэл таарсан тул хаав.");
            CloseTempAccountRequest closeTempAccountReq = new CloseTempAccountRequest();
            closeTempAccountReq.setTxnAcntCode(contAcntCode);
            closeTempAccountReq.setRate(rate);
            closeTempAccountReq.setContAcntCode(internalAccount);
            closeTempAccountReq.setContRate(rate);
            closeTempAccountReq.setRateTypeId(Constants.DEFAULT_RATE_TYPE_ID);
            closeTempAccountReq.setTxnDesc(closeAccTxnDesc);
            closeTempAccountReq.setSourceType(Constants.DEFAULT_SOURCE_TYPE);
            closeTempAccountReq.setIsPreview(Constants.DEFAULT_IS_PREVIEW);
            closeTempAccountReq.setIsPreviewFee(Constants.DEFAULT_IS_PREVIEW_FEE);
            closeTempAccountReq.setIsTmw(Constants.DEFAULT_IS_TMW);
            List<AddParam> tempAddParams = Utils.getAddParams("3");
            closeTempAccountReq.setAddParams(tempAddParams);
            return polarisClient.closeTempAccount(closeTempAccountReq);
        } else {
            //Түр харилцах дансны үлдэгдэл таарахгүй байвал дансны шилжүүлэг хийнэ.
            log.info("Түр харилцах дансны үлдэгдэл таараагүй тул шилжүүлэв.");
            TransferRequest transferRequest = new TransferRequest();
            transferRequest.setTxnAcntCode(contAcntCode);
            transferRequest.setTxnAmount(txnAmount);
            transferRequest.setRateTypeId(Constants.DEFAULT_RATE_TYPE_ID);
            transferRequest.setTxnDesc(closeAccTxnDesc);
            transferRequest.setRate(rate);
            transferRequest.setContAcntCode(internalAccount);
            transferRequest.setContAmount(txnAmount);
            transferRequest.setContRate(rate);
            transferRequest.setSourceType(Constants.DEFAULT_SOURCE_TYPE);
            transferRequest.setIsPreview(Constants.DEFAULT_IS_PREVIEW);
            transferRequest.setIsPreviewFee(Constants.DEFAULT_IS_PREVIEW_FEE);
            transferRequest.setIsTmw(Constants.DEFAULT_IS_TMW);
            return polarisClient.transferFromTempAccToInternalAcc(transferRequest);
        }
    }

    public PreviewCloseTdAccountResponseDto previewCloseTdAccount(CloseTdAccountRequestDto closeTdAccountRequestDto) {
        InfoRequest infoRequest = new InfoRequest();
        infoRequest.setAcntCode(closeTdAccountRequestDto.getTxnAcntCode());
        infoRequest.setGetWithSecure(0);
        Td td = polarisClient.getTdInfo(infoRequest);
        if (td.getRcvAcntCode() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Хүлээн авах данс тохируулаагүй байна!");
        }
        if (td.getBlockBal().compareTo(BigDecimal.ZERO) != 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Данс блоктой байгаа тул хаах боломжгүй!");
        }
        CloseTdAccountRequest closeTdAccountRequest = new CloseTdAccountRequest();
        BigDecimal rate = Constants.DEFAULT_RATE;
        String curCode = Constants.DEFAULT_CUR_CODE;
        String contAcntCode = td.getRcvAcntCode();
        closeTdAccountRequest.setOperCode("13610285");
        closeTdAccountRequest.setTxnAcntCode(closeTdAccountRequestDto.getTxnAcntCode());
        closeTdAccountRequest.setRate(rate);
        closeTdAccountRequest.setCurCode(curCode);
        closeTdAccountRequest.setContAcntCode(contAcntCode);
        closeTdAccountRequest.setContCurCode(curCode);
        closeTdAccountRequest.setContRate(rate);
        closeTdAccountRequest.setRateTypeId(Constants.DEFAULT_RATE_TYPE_ID);
        String closeAccTxnDesc = "Хугацаат хадгаламжийн данс бэлэн бусаар хаав.";
        closeTdAccountRequest.setTxnDesc(closeAccTxnDesc);
        closeTdAccountRequest.setSourceType(Constants.DEFAULT_SOURCE_TYPE);
        closeTdAccountRequest.setIsPreview(1);
        closeTdAccountRequest.setIsPreviewFee(Constants.DEFAULT_IS_PREVIEW_FEE);
        closeTdAccountRequest.setIsTmw(Constants.DEFAULT_IS_TMW);
        List<AspParam> aspParams = Utils.getAspParams(contAcntCode, closeTdAccountRequestDto.getTxnAcntCode(), 13610285);
        closeTdAccountRequest.setAspParam(aspParams);
        List<AddParam> addParams = Utils.getAddParams("1");
        closeTdAccountRequest.setAddParams(addParams);
        DepositTdAccountResponseDto depositTdAccountResponseDto = polarisClient.closeTdAccount(closeTdAccountRequest);
        return getPreviewCloseTdAccountResponseDto(depositTdAccountResponseDto);
    }

    public PreviewCloseTdAccountResponseDto getPreviewCloseTdAccountResponseDto(DepositTdAccountResponseDto depositTdAccountResponseDto) {
        PreviewCloseTdAccountResponseDto previewCloseTdAccountResponseDto = new PreviewCloseTdAccountResponseDto();
        previewCloseTdAccountResponseDto.setTotalBal(BigDecimal.ZERO);
        previewCloseTdAccountResponseDto.setTaxBal(BigDecimal.ZERO);
        previewCloseTdAccountResponseDto.setIntBal(BigDecimal.ZERO);
        if (depositTdAccountResponseDto != null) {
            if (depositTdAccountResponseDto.getTxnPreview() != null && !depositTdAccountResponseDto.getTxnPreview().isEmpty()) {
                for (TxnPreview txnPreview : depositTdAccountResponseDto.getTxnPreview()) {
                    if (txnPreview != null) {
                        if (txnPreview.getSysNo() == 1305) {
                            previewCloseTdAccountResponseDto.setTotalBal(txnPreview.getTxnAmount());
                        }
                        if (txnPreview.getTxnCode().equalsIgnoreCase("GET_TAX")) {
                            if (txnPreview.getBalTypeCode().equalsIgnoreCase("PAID_TAX")) {
                                previewCloseTdAccountResponseDto.setTaxBal(txnPreview.getCreditAmount());
                            }
                        } else if (txnPreview.getTxnCode().equalsIgnoreCase("CAP")) {
                            if (txnPreview.getBalTypeCode().equalsIgnoreCase("INT_PAY")) {
                                previewCloseTdAccountResponseDto.setIntBal(txnPreview.getTxnAmount());
                            }
                        }
                    }
                }
            }
        }
        return previewCloseTdAccountResponseDto;
    }

    public DepositTdAccountResponseDto withdrawTdAccount(WithdrawTdAccountRequestDto withdrawTdAccountRequestDto) {
        WithdrawTdAccountRequest withdrawTdAccountRequest = new WithdrawTdAccountRequest();
        int operationCode = 13610289;
        withdrawTdAccountRequest.setOperCode(Integer.toString(operationCode));
        withdrawTdAccountRequest.setTxnAcntCode(withdrawTdAccountRequestDto.getTxnAcntCode());
        withdrawTdAccountRequest.setTxnAmount(withdrawTdAccountRequestDto.getTxnAmount());
        withdrawTdAccountRequest.setRate(Constants.DEFAULT_RATE);
        withdrawTdAccountRequest.setRateTypeId(Constants.DEFAULT_RATE_TYPE_ID);
        withdrawTdAccountRequest.setContAmount(withdrawTdAccountRequestDto.getTxnAmount());
        withdrawTdAccountRequest.setContCurCode(Constants.DEFAULT_CUR_CODE);
        withdrawTdAccountRequest.setCurCode(Constants.DEFAULT_CUR_CODE);
        withdrawTdAccountRequest.setContRate(Constants.DEFAULT_RATE);
        withdrawTdAccountRequest.setContAcntCode(qpayTdAccount);
        withdrawTdAccountRequest.setTxnDesc(withdrawTdAccountRequestDto.getTxnAcntCode() + " хадгаламжнаас зарлага гаргав");
        withdrawTdAccountRequest.setSourceType(Constants.DEFAULT_SOURCE_TYPE);
        withdrawTdAccountRequest.setIsPreview(Constants.DEFAULT_IS_PREVIEW);
        withdrawTdAccountRequest.setIsPreviewFee(Constants.DEFAULT_IS_PREVIEW_FEE);
        withdrawTdAccountRequest.setIsTmw(Constants.DEFAULT_IS_TMW);
        List<AddParam> addParams = new ArrayList<>();
        AddParam addParam = new AddParam();
        addParam.setAcntType("3");
        addParam.setWithoutTbreak(0);
        addParams.add(addParam);
        withdrawTdAccountRequest.setAddParams(addParams);
        withdrawTdAccountRequest.setTranAmt(withdrawTdAccountRequestDto.getTxnAmount());
        withdrawTdAccountRequest.setTranCurCode(Constants.DEFAULT_CUR_CODE);
        return polarisClient.withdrawFromTdAccount(withdrawTdAccountRequest);
    }

    public Statement processTxns(Statement statement) {
        Statement newStatement = new Statement();
        newStatement.setBeginBal(statement.getBeginBal());
        newStatement.setEndBal(statement.getEndBal());
        List<Txn> txns = new ArrayList<>();
        for (Txn t : statement.getTxns()) {
            if (t != null) {
                if (t.getBegbalance() != null &&
                        t.getEndbalance() != null && t.getBegbalance().equals(t.getEndbalance())) {
                    continue; //Хоосон гүйлгээг алгасаж байна.
                }
                txns.add(t);
            }
        }
        newStatement.setTxns(txns);
        return newStatement;
    }

}
