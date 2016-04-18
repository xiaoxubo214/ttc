package wind.mj.com.board.net;

/**
 * Created by wind on 16/3/4.
 */
public class ListInfo {
    public ListInfo(){

    }
    public ListInfo(String number,String model,String workingNumber,String packagingNumber,String status){
        this.number = number;
        this.model = model;
        this.workingNumber = workingNumber;
        this.packagingNumber = packagingNumber;
        this.status = status;
    }
    public String number;
    public String model;
    public String workingNumber;
    public String packagingNumber;
    public String status;
}
