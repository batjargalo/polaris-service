package mn.io.polaris.constant;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Constants {

        private Constants() {
                throw new IllegalStateException("Constants class");
        }

        public static final String STATUS_OPEN = "O";
        public static final String ACCOUNT_TYPE_TD = "TD";
        public static final String ACCOUNT_TYPE_LOAN = "LOAN";
        public static final String ACCOUNT_TYPE_LINE = "LINE";
        public static final Integer RETAIL_ADDRESS_MAIN = 1;
        public static final Integer RETAIL_ADDRESS_STATUS_ACTIVE = 1;
        public static final String CUST_SEG_CODE_RETAIL = "81";
        public static final String CUST_TAX_EXEMPTION = "0";
        public static final String CUST_STATUS = "1";
        public static final Integer CUST_IS_COMPANY_CUSTOMER = 1;
        public static final String CUST_REGISTER_MASK_CODE = "3";
        public static final String CUST_REGISTER_MASK_CODE_CORPORATE = "4";
        public static final String CUST_COUNTRY_CODE = "496";
        public static final String CUST_LANG_CODE = "1";
        // public static final String DEFAULT_BRANCH_CODE = "10500";
        public static final String DEFAULT_BRANCH_CODE = "999901";
        public static final String DEFAULT_COLL_PROD_CODE = "691100072100";
        public static final String DEFAULT_TD_LOAN_PROD_CODE = "130107012100";
        public static final String DEFAULT_TD_LOAN_PURPOSE_CODE = "2";
        public static final String DEFAULT_TD_LOAN_SUB_PURPOSE_CODE = "SUBPURP00071";
        public static final Integer DEFAULT_ACNT_MANAGER = 1265;
        public static final String DEFAULT_CUR_CODE = "MNT";
        public static final String DEFAULT_S_LEVEL = "1";
        public static final String DEFAULT_SINGLE = "S";
        public static final BigDecimal DEFAULT_RATE = BigDecimal.ONE;
        public static final BigDecimal DEFAULT_TD_LOAN_PERCENTAGE = BigDecimal.valueOf(0.80);
        public static final String DEFAULT_SOURCE_TYPE = "OI";
        public static final String DEFAULT_RATE_TYPE_ID = "1";
        public static final Integer DEFAULT_IS_PREVIEW = 0;
        public static final Integer DEFAULT_IS_PREVIEW_FEE = 0;
        public static final Integer DEFAULT_IS_TMW = 1;
        public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
        public static final List<String> TXN_CODE_LIST = Arrays.asList(
                        "ACTIVE",
                        "ADVANCE",
                        "PAY_PRINC",
                        "PAY_BILL_INT",
                        "PAY_FINE",
                        "PAY_ADV_INT",
                        "CRT_BILL_INT",
                        "RENEGOTIATE",
                        "NEG_BILL_PRINC_DEC",
                        "NEG_BILL_INT_DEC",
                        "NEG_FINE_P_DEC",
                        "NEG_FINE_I_DEC",
                        "NEG_ACCR_INT_DEC",
                        "NEG_PRINC_INC",
                        "USE_ADV_INT",
                        "OPEN",
                        "STOP",
                        "CLOSE",
                        "CHANGE_INT_RATE",
                        "EXTEND_ACNT",
                        "CRT_BILL_PRINC",
                        "CHG_APPROV_AMT",
                        "REVERSE",
                        "PAY_INT_BY_PRINC",
                        "CHANGE_INT_SPREAD",
                        "REOPEN_ACNT");
        public static final List<String> TXN_CODE_LIST_ONLY_INT = Arrays.asList(
                        "PAY_BILL_INT",
                        "PAY_ADV_INT",
                        "CRT_BILL_INT",
                        "NEG_BILL_INT_DEC",
                        "NEG_ACCR_INT_DEC",
                        "USE_ADV_INT",
                        "CHANGE_INT_RATE",
                        "PAY_INT_BY_PRINC",
                        "CHANGE_INT_SPREAD",
                        "PAY_FINE",
                        "NEG_FINE_P_DEC",
                        "NEG_FINE_I_DEC");
        public static final List<String> TXN_CODE_LIST_ONLY_PRINCIPAL = Arrays.asList(
                        "ACTIVE",
                        "ADVANCE",
                        "PAY_PRINC",
                        "RENEGOTIATE",
                        "NEG_BILL_PRINC_DEC",
                        "NEG_PRINC_INC",
                        "OPEN",
                        "STOP",
                        "CLOSE",
                        "EXTEND_ACNT",
                        "CRT_BILL_PRINC",
                        "CHG_APPROV_AMT",
                        "REVERSE",
                        "REOPEN_ACNT");
}
