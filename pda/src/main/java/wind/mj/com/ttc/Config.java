package wind.mj.com.ttc;

/**
 * Created by wind on 16/2/16.
 */
public class Config {
    public final static boolean isDebug = false;

    public final static boolean isTest = false;

    public final static String HOST = "http://192.168.1.110:8069/api/";

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
    public final static String URL_LOGIN = HOST + "Login/admin";                                           // 登录

    public final static String URL_PRODUCT_MATERIAL = HOST + "warehouse/scll/" + DATABASE;

    public final static String URL_SCAN_CONFIRM = HOST + "box/dhsm/" + DATABASE;//Scan confirm

    public final static String URL_ARRIVAL = HOST + "box_dhmx/" + DATABASE;

    public final static String URL_NOT_ARRIVAL = HOST + "box_wdmx/" + DATABASE;

    public final static String URL_HEAD_SCAN =  HOST + "warehouse/scsx/" + DATABASE;

    public final static String URL_PACK_SCAN = HOST + "warehouse/scxx/" + DATABASE;

    public final static String URL_RETURN_WAREHOUSE = HOST + "warehouse/sctk/" + DATABASE;

    public final static String URL_PRODUCT_INBOUND = HOST + "warehouse/scrk/" + DATABASE;

    public final static String URL_SALE = HOST + "warehouse/xsck/" + DATABASE;

    public final static String URL_PRODUCT_DRAIN = HOST + "" + DATABASE;

    public final static String URL_PRODUCT_FINISH = HOST + "" + DATABASE;

    public final static String URL_PRODUCT_ONLINE = HOST + "" + DATABASE;

    public final static String URL_HEAD_BOARD_UP = HOST + "kanban/sxd_up/" + DATABASE;

    public final static String URL_HEAD_BOARD_DOWN = HOST + "kanban/sxd_down/" + DATABASE;

    public final static String URL_END_BOARD = HOST + "kanban/dbd/" + DATABASE;

    public final static String URL_WAREHOUSE_BOARD = HOST + "kanban/ckkb/" + DATABASE;

    public final static String URL_OFFICE_BOARD = HOST + "kanban/bgskb/" + DATABASE;

}
