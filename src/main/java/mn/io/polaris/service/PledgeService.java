package mn.io.polaris.service;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import mn.io.polaris.model.polaris.*;
import mn.io.polaris.model.request.AccountListRequest;
import mn.io.polaris.model.request.PledgeInfoByAccountRequest;
import mn.io.polaris.remote.PolarisClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class PledgeService {

    @Resource
    private PolarisClient polarisClient;

    public PledgeInfo getInfoByAccount(PledgeInfoByAccountRequest pledgeInfoByAccountRequest) {
        return polarisClient.getInfoByAccount(pledgeInfoByAccountRequest);
    }

    public List<PledgeResponse> getPledgeListFromLoanAccount(AccountNo accountNo) {
        return polarisClient.getPledgeListFromLoanAccount(accountNo.getAcntCode());
    }

    public Pledge getPledge(AccountNo accountNo) {
        return polarisClient.getPledge(accountNo.getAcntCode());
    }

    public List<Account> getCollAccountList(@Valid AccountListRequest accountListRequest) {
        return polarisClient.getCollAccountList(accountListRequest);
    }

}
