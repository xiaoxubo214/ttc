package wind.mj.com.ttc;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by wind on 16/3/28.
 */
public class DatabaseMaster {

    public static void main(String[] args) throws Exception {

        Schema schema = new Schema(2, "wind.mj.com.ttc.dao");
        addBox(schema);
        addBoxDetail(schema);
        addContainer(schema);
        addGoodDetail(schema);
        new DaoGenerator().generateAll(schema, "../Ttc/pda/src/main/java");

    }

    private static void addBox(Schema schema) {
        Entity box = schema.addEntity("Box");
        box.addIdProperty();
        box.addStringProperty("code").notNull();
        box.addStringProperty("batch");
        box.addStringProperty("name");
        box.addStringProperty("number");
        box.addStringProperty("checked");
    }

    private static void addBoxDetail(Schema schema) {
        Entity boxDetail = schema.addEntity("BoxDetail");
        boxDetail.addIdProperty();
        boxDetail.addStringProperty("batch").notNull();
        boxDetail.addStringProperty("actual_sum");
        boxDetail.addStringProperty("specification");
        boxDetail.addStringProperty("code");
        boxDetail.addStringProperty("name");
    }

    private static void addContainer(Schema schema) {
        Entity container = schema.addEntity("Container");
        container.addIdProperty();
        container.addStringProperty("code").notNull();
        container.addStringProperty("name");
        container.addStringProperty("batch");
        container.addStringProperty("checked");
    }

    private static void addGoodDetail(Schema schema) {
        Entity goodDetail = schema.addEntity("GoodDetail");
        goodDetail.addIdProperty();
        goodDetail.addStringProperty("batch").notNull();
        goodDetail.addStringProperty("container_code");
        goodDetail.addStringProperty("barcode");
        goodDetail.addStringProperty("code");
        goodDetail.addStringProperty("type");
        goodDetail.addStringProperty("name");
        goodDetail.addStringProperty("checked");
    }
}
