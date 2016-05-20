package wind.mj.com.ttc;

/**
 * Created by wind on 16/2/16.
 */
public class Config {
    public final static boolean isDebug = true;

    public final static boolean isTest = true;

    public final static String KEY_IS_FIRST_RUN     = "is_first_run";
    public final static String KEY_SERVER_IP        = "server_ip";

    public final static String HOST = "http://192.168.5.160:8069/api/";

    public final static String HOST_PREFIX = "http://";

    public final static String HOST_SUFFIX = ":8069/api/";

    public final static String DATABASE = "TTC";

    public final static String LINE_NUMBER = "00";

    public final static String STR_USERNAME = "username";

    public final static String DEFAULT_USERNAME = "admin";
    public final static String DEFAULT_PASSWORD = "1";

    public final static String STR_PASSWORD = "password";

    public final static String STR_CONTAINER_CODE = "container_code";

    public final static String STR_BATCH = "batch";

    public final static String STR_ZERO = "0";

    public final static String STR_ONE = "1";

    public final static String STR_YES = "YES";
    // url start
    public final static String URL_LOGIN = "Login/admin";                                           // 登录

    public final static String URL_PRODUCT_MATERIAL = "warehouse/scll/" + DATABASE;

    public final static String URL_SCAN_CONFIRM = "box/dhsm/" + DATABASE;//Scan confirm

    public final static String URL_ARRIVAL = "box_dhmx/" + DATABASE;

    public final static String URL_NOT_ARRIVAL = "box_wdmx/" + DATABASE;

    public final static String URL_HEAD_SCAN =  "warehouse/scsx/" + DATABASE;

    public final static String URL_PACK_SCAN = "warehouse/scxx/" + DATABASE;

    public final static String URL_RETURN_WAREHOUSE = "warehouse/sctk/" + DATABASE;

    public final static String URL_PRODUCT_INBOUND = "warehouse/scrk/" + DATABASE;

    public final static String URL_SALE = "warehouse/xsck/" + DATABASE;

    public final static String URL_PRODUCT_DRAIN = "scdd/scxh/" + DATABASE;

    public final static String URL_PRODUCT_FINISH = "scdd/scwg/" + DATABASE;

    public final static String URL_PRODUCT_ONLINE = "scdd/sxsm/" + DATABASE;

    public final static String URL_HEAD_BOARD_UP = "kanban/sxd_up/" + DATABASE;

    public final static String URL_HEAD_BOARD_DOWN = "kanban/sxd_down/" + DATABASE;

    public final static String URL_END_BOARD = "kanban/dbd/" + DATABASE;

    public final static String URL_WAREHOUSE_BOARD = "kanban/ckkb/" + DATABASE;

    public final static String URL_OFFICE_BOARD = "kanban/bgskb/" + DATABASE;

}
