package wind.mj.com.ttc.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import wind.mj.com.ttc.model.ArrivalDetail;
import wind.mj.com.ttc.model.ArrivalInbound;
import wind.mj.com.ttc.model.ArrivalInfo;
import wind.mj.com.ttc.model.EndBoard;
import wind.mj.com.ttc.model.Error;
import wind.mj.com.ttc.model.HeadBoardDown;
import wind.mj.com.ttc.model.HeadBoardUp;
import wind.mj.com.ttc.model.HeadScan;
import wind.mj.com.ttc.model.OfficeBoard;
import wind.mj.com.ttc.model.ProductOnline;
import wind.mj.com.ttc.model.ScanConfirm;
import wind.mj.com.ttc.model.WareHouseBoard;

/**
 * Created by wind on 16/2/16.
 */
public class DataUtil {
    public static String getJSONFromAsset(Context context, String jsonFileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static ArrivalInbound getArrivalInbound(Context context, String assetsFile, String jsonString) {
        ArrivalInbound data = new Gson()
                .fromJson(assetsFile != "" ? getJSONFromAsset(context, assetsFile):jsonString,
                        new TypeToken<ArrivalInbound>() {
                }.getType());
        return data;
    }

    public static ArrivalInfo getArrivalInfo(Context context, String assetsFile, String jsonString) {
        ArrivalInfo data = new Gson()
                .fromJson(assetsFile != "" ? getJSONFromAsset(context, assetsFile):jsonString,
                        new TypeToken<ArrivalInfo>() {
                        }.getType());
        return data;
    }

    public static List<ArrivalDetail> getArrivalDetail(Context context, String assetsFile, String jsonString) {
        List<ArrivalDetail> data = new Gson()
                .fromJson(assetsFile != "" ? getJSONFromAsset(context, assetsFile):jsonString,
                        new TypeToken<List<ArrivalDetail>>() {
                        }.getType());
        return data;
    }

    public static HeadScan getHeadScan(Context context, String assetsFile, String jsonString) {
        HeadScan data = new Gson()
                .fromJson(assetsFile != "" ? getJSONFromAsset(context, assetsFile):jsonString,
                        new TypeToken<HeadScan>() {
                        }.getType());
        return data;
    }

    public static ScanConfirm getScanConfirm(Context context, String assetsFile, String jsonString) {
        ScanConfirm data = new Gson()
                .fromJson(assetsFile != "" ? getJSONFromAsset(context, assetsFile):jsonString,
                        new TypeToken<ScanConfirm>() {
                        }.getType());
        return data;
    }

    public static ProductOnline getProductOnline(Context context, String assetsFile, String jsonString) {
        ProductOnline data = new Gson()
                .fromJson(assetsFile != "" ? getJSONFromAsset(context, assetsFile):jsonString,
                        new TypeToken<ProductOnline>() {
                        }.getType());
        return data;
    }

    public static EndBoard getEndBoard(Context context, String assetsFile, String jsonString) {
        EndBoard data = new Gson()
                .fromJson(assetsFile != "" ? getJSONFromAsset(context, assetsFile):jsonString,
                        new TypeToken<EndBoard>() {
                        }.getType());
        return data;
    }

    public static List<OfficeBoard> getOfficeBoard(Context context, String assetsFile, String jsonString) {
        List<OfficeBoard> data = new Gson()
                .fromJson(assetsFile != "" ? getJSONFromAsset(context, assetsFile):jsonString,
                        new TypeToken<List<OfficeBoard>>() {
                        }.getType());
        return data;
    }

    public static List<WareHouseBoard> getWarehouseBoard(Context context, String assetsFile, String jsonString) {
        List<WareHouseBoard> data = new Gson()
                .fromJson(assetsFile != "" ? getJSONFromAsset(context, assetsFile):jsonString,
                        new TypeToken<List<WareHouseBoard>>() {
                        }.getType());
        return data;
    }

    public static List<HeadBoardUp> getHeadBoardUp(Context context, String assetsFile, String jsonString) {
        List<HeadBoardUp> data = new Gson()
                .fromJson(assetsFile != "" ? getJSONFromAsset(context, assetsFile):jsonString,
                        new TypeToken<List<HeadBoardUp>>() {
                        }.getType());
        return data;
    }

    public static List<HeadBoardDown> getHeadBoardDown(Context context, String assetsFile, String jsonString) {
        List<HeadBoardDown> data = new Gson()
                .fromJson(assetsFile != "" ? getJSONFromAsset(context, assetsFile):jsonString,
                        new TypeToken<List<HeadBoardDown>>() {
                        }.getType());
        return data;
    }

    public static Error getError(Context context, String assetsFile, String jsonString) {
        Error data = new Gson()
                .fromJson(assetsFile != "" ? getJSONFromAsset(context, assetsFile):jsonString,
                        new TypeToken<Error>() {
                        }.getType());
        return data;
    }


}
