package wind.mj.com.board.event;

/**
 * Created by huaqiang
 * on 2015/8/27.
 */
public class Event {
    public final static int ACTION_ERROR = 0;
    private String message;
    private int actionType;

    public Event(int actionType) {
        this.actionType = actionType;
    }

    public Event(int actionType, String message) {
        this.message = message;
        this.actionType = actionType;
    }

    public Event(String message, int actionType) {
        this.message = message;
        this.actionType = actionType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }


}
