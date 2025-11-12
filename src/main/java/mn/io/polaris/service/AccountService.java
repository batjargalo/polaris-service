package mn.io.polaris.service;

import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import mn.io.polaris.constant.Constants;
import mn.io.polaris.helper.Utils;
import mn.io.polaris.model.polaris.AccountNo;
import mn.io.polaris.model.request.*;
import mn.io.polaris.remote.PolarisClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Log4j2
@Service
public class AccountService {

    @Value("${ca.prodcode}")
    private String caProdCode;

    @Resource
    private PolarisClient polarisClient;

    public AccountNo createAccount(AccountCreateRequest accountCreateRequest) {
        accountCreateRequest.setProdCode(caProdCode);
        accountCreateRequest.setAcntType("CA");
        accountCreateRequest.setBrchCode(Constants.DEFAULT_BRANCH_CODE);
        accountCreateRequest.setCurCode(Constants.DEFAULT_CUR_CODE);
        accountCreateRequest.setSlevel(Constants.DEFAULT_S_LEVEL);
        accountCreateRequest.setJointOrSingle(Constants.DEFAULT_SINGLE);
        accountCreateRequest.setFlagNoCredit("0");
        accountCreateRequest.setFlagNoDebit("0");
        accountCreateRequest.setSalaryAcnt("N");
        accountCreateRequest.setCorporateAcnt("N");
        accountCreateRequest.setCapMethod("0");
        accountCreateRequest.setSegCode(Constants.CUST_SEG_CODE_RETAIL);
        accountCreateRequest.setOdType("NON");
        return polarisClient.createAccount(accountCreateRequest);
    }

    public void changeAccountStatus(AccountNoRequest accountNoRequest) {
        polarisClient.changeAccountStatus(accountNoRequest.getAcntCode());
    }

    public AccountNo createTdAccount(AccountTdCreateRequest accountTdCreateRequest) {
        accountTdCreateRequest.setProdCode(accountTdCreateRequest.getProdCode());
        accountTdCreateRequest.setCapAcntSysNo("1306");
        accountTdCreateRequest.setMaturityOption("R");
        accountTdCreateRequest.setBrchCode(Constants.DEFAULT_BRANCH_CODE);
        accountTdCreateRequest.setSlevel(Constants.DEFAULT_S_LEVEL);
        accountTdCreateRequest.setCapMethod("1");
        String systemDate = polarisClient.getSystemDate();
        String systemDateFormatted = systemDate.replace("\"", "");
        accountTdCreateRequest.setStartDate(systemDateFormatted);
        accountTdCreateRequest.setCurCode(Constants.DEFAULT_CUR_CODE);
        Date maturityDate = Utils.addMonthsToDate(systemDateFormatted, accountTdCreateRequest.getTermLen());
        accountTdCreateRequest
                .setMaturityDate(Utils.changeDateToString(maturityDate, Constants.DATE_FORMAT_YYYY_MM_DD));
        accountTdCreateRequest.setSegCode(Constants.CUST_SEG_CODE_RETAIL);
        accountTdCreateRequest.setJointOrSingle(Constants.DEFAULT_SINGLE);
        return polarisClient.createTdAccount(accountTdCreateRequest);
    }

    public void changeTdAccountStatus(String acntCode) {
        ChangeTdAccountStatus changeTdAccountStatus = new ChangeTdAccountStatus();
        changeTdAccountStatus.setAcntCode(acntCode);
        changeTdAccountStatus.setDescription("Хадгаламжийн дансны төлөв нээв");
        polarisClient.changeTdAccountStatus(changeTdAccountStatus);
    }

    public AccountNo createAccountAllInOne(AccountCreateAllInOneRequest accountCreateAllInOneRequest) {
        AccountCreateRequest accountCreateRequest = new AccountCreateRequest();
        accountCreateRequest.setCustCode(accountCreateAllInOneRequest.getCustCode());
        accountCreateRequest.setName(accountCreateAllInOneRequest.getName());
        accountCreateRequest.setName2(accountCreateAllInOneRequest.getName2());

        AccountNo tempAccountNo = this.createAccount(accountCreateRequest);
        log.info("Түр данс амжилттай үүссэн: " + tempAccountNo.getAcntCode());

        AccountNoRequest accountNoRequest = new AccountNoRequest();
        accountNoRequest.setAcntCode(tempAccountNo.getAcntCode());

        this.changeAccountStatus(accountNoRequest);
        log.info("Түр дансны төлөв амжилттай солигдсон.");

        AccountTdCreateRequest accountTdCreateRequest = getAccountTdCreateRequest(accountCreateAllInOneRequest,
                tempAccountNo);
        AccountNo tdAccountNo = this.createTdAccount(accountTdCreateRequest);
        log.info("Хадгаламжийн данс амжилттай үүссэн: " + tdAccountNo.getAcntCode());

        // this.changeTdAccountStatus(tdAccountNo.getAcntCode());
        // log.info("Хадгаламжийн дансны төлөв амжилттай нээгдсэн.");

        return tdAccountNo;

    }

    private static AccountTdCreateRequest getAccountTdCreateRequest(
            AccountCreateAllInOneRequest accountCreateAllInOneRequest, AccountNo tempAccountNo) {
        AccountTdCreateRequest accountTdCreateRequest = new AccountTdCreateRequest();
        accountTdCreateRequest.setCapAcntCode(tempAccountNo.getAcntCode());
        accountTdCreateRequest.setRcvAcntCode(tempAccountNo.getAcntCode());
        accountTdCreateRequest.setName(accountCreateAllInOneRequest.getName());
        accountTdCreateRequest.setName2(accountCreateAllInOneRequest.getName2());
        accountTdCreateRequest.setTermLen(accountCreateAllInOneRequest.getTermLen());
        accountTdCreateRequest.setCustCode(accountCreateAllInOneRequest.getCustCode());
        accountTdCreateRequest.setProdCode(accountCreateAllInOneRequest.getProdCode());
        return accountTdCreateRequest;
    }
    // region Casa данс үүсгэх Munkh

    public AccountNo createCasaAcc(AccountCasaCreateRequest accountCasaCreateRequest) {
        AccountCreateRequest accountCreateRequest = new AccountCreateRequest();
        accountCreateRequest.setCustCode(accountCasaCreateRequest.getCustCode());
        accountCreateRequest.setName(accountCasaCreateRequest.getName());
        accountCreateRequest.setName2(accountCasaCreateRequest.getName2());

        AccountNo tempAccountNo = this.createAccount(accountCreateRequest);
        log.info("Түр данс амжилттай үүссэн: " + tempAccountNo.getAcntCode());

        AccountNoRequest accountNoRequest = new AccountNoRequest();
        accountNoRequest.setAcntCode(tempAccountNo.getAcntCode());

        this.changeAccountStatus(accountNoRequest);
        log.info("Түр дансны төлөв амжилттай солигдсон.");

        return tempAccountNo;

    }
    // endregion

}
