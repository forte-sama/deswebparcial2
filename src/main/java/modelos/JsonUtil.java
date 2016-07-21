package modelos;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by saleta on 7/18/2016.
 */
public class JsonUtil {

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static ResponseTransformer json() {
        return JsonUtil::toJson;
    }
}
