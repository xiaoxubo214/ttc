package wind.mj.com.board;

/**
 * Created by wind on 16/2/16.
 */
public class Config {
    public final static boolean isDebug = false;

    public final static boolean isTest = false;

    public final static String HOST = "http://192.168.1.105:8069/api/";

    public final static String DATABASE = "TTC";

    public final static String LINE_NUMBER = "00";

    public final static String STR_USERNAME = "username";

    public final static String DEFAULT_USERNAME = "admin";

    public final static String DEFAULT_PASSWORD = "1";

    public final static Boolean isStartRun = true;


    public final static String KEY_IS_FIRST_RUN     = "is_first_run";
    public final static String KEY_WORKSTATION_NO   = "workstation_no";
    public final static String KEY_SERVER_IP        = "server_ip";

    public final static int LINE_ONE_START_BOARD = 1;
    public final static int LINE_ONE_END_BOARD = 2;

    public final static int LINE_TWO_START_BOARD = 3;
    public final static int LINE_TWO_END_BOARD = 4;

    public final static int LINE_THREE_START_BOARD = 5;
    public final static int LINE_THREE_END_BOARD = 6;

    public final static int LINE_FOUR_START_BOARD = 7;
    public final static int LINE_FOUR_END_BOARD = 8;

    public final static int OFFICE_BOARD = 9;

    public final static int WAREHOUSE_BOARD = 10;
    // url start

    public final static String URL_HEAD_BOARD_UP = HOST + "kanban/sxd_up/" + DATABASE;

    public final static String URL_HEAD_BOARD_DOWN = HOST + "kanban/sxd_down/" + DATABASE;

    public final static String URL_END_BOARD = HOST + "kanban/dbd/" + DATABASE;

    public final static String URL_WAREHOUSE_BOARD = HOST + "kanban/ckkb/" + DATABASE;

    public final static String URL_OFFICE_BOARD = HOST + "kanban/bgskb/" + DATABASE;

}
