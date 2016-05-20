package wind.mj.com.board.event;

/**
 * Created by wind on 16/4/17.
 */
public class MessageEvent extends Event {

    public final static int ACTION_REFRESH_ARRIVAL_INFO = 1;

    public final static int ACTION_GET_END_BOARD_DATA = 2;

    public final static int ACTION_CHANGE_FRAGMENT = 3;
    public MessageEvent(int actionType) {
        super(actionType);
    }

    public MessageEvent(int actionType, String message) {
        super(actionType,message);
    }

    public MessageEvent(String message, int actionType) {
        super(message,actionType);
    }
}
