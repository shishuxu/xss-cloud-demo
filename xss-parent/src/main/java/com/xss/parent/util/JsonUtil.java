package com.xss.parent.util;

import com.google.gson.*;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: lyc
 * @Date: 2019/10/17 11:53
 * @Description:
 */
@Slf4j
public class JsonUtil {

    private static final Gson GSON = new Gson();
    private static final JsonParser PARSER = new JsonParser();

    private JsonUtil() {
    }

    public static String toJson(Object obj) {
        return GSON.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return GSON.fromJson(json, classOfT);
    }

    public static Map<String, Object> fromJson(String json) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(new TypeToken<Map<String, Object>>() {
                }.getType(), new MapTypeAdapter()).create();
        return gson.fromJson(json, new TypeToken<Map<String, Object>>() {
        }.getType());
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return GSON.fromJson(json, typeOfT);
    }

    public static <T> List<T> fromJsonArray(String json, Class<T> classOfT) {
        if (json == null) {
            return null;
        }
        JsonElement jsonEle = PARSER.parse(json);
        if (jsonEle.isJsonArray()) {
            JsonArray arr = (JsonArray) jsonEle;
            int size = arr.size();
            if (size > 0) {
                List<T> data = null;
                for (int i = 0; i < size; i++) {
                    T obj = GSON.fromJson(arr.get(i), classOfT);
                    if (obj != null) {
                        if (data == null) {
                            data = new ArrayList<T>(size);
                        }
                        data.add(obj);
                    }
                }
                return data;
            } else {
                return null;
            }
        } else {
            T obj = GSON.fromJson(jsonEle, classOfT);
            if (obj == null) {
                return null;
            }
            List<T> data = new ArrayList<T>();
            data.add(obj);
            return data;
        }
    }

    public static <T> List<T> fromDataJsonArray(String json, Class<T> classOfT) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        JsonElement jsonEle = PARSER.parse(json);
        JsonElement jsonElement = jsonEle.getAsJsonObject().get("data");
        if (Objects.isNull(jsonElement)) {
            return null;
        } else if (jsonElement.isJsonArray()) {
            JsonArray arr = (JsonArray) jsonElement;
            int size = arr.size();
            if (size > 0) {
                List<T> data = null;
                for (int i = 0; i < size; i++) {
                    T obj = GSON.fromJson(arr.get(i), classOfT);
                    if (obj != null) {
                        if (data == null) {
                            data = new ArrayList<T>(size);
                        }
                        data.add(obj);
                    }
                }
                return data;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static <T> T fromJsonData(String json, Class<T> classOfT) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        JsonElement jsonEle = PARSER.parse(json);
        JsonElement jsonElement = jsonEle.getAsJsonObject().get("data");
        if (Objects.isNull(jsonElement)) {
            return null;
        }
        if (jsonElement.isJsonNull()) {
            return null;
        }
        T obj = GSON.fromJson(jsonElement, classOfT);
        return obj;

    }

    private static interface Convert<F, T> {
        public T cvt(F from);
    }

    final private static Convert<String, JsonElement> STR2JSON = new Convert<String, JsonElement>() {
        @Override
        public JsonElement cvt(String from) {
            return PARSER.parse(from);
        }
    };
    final private static Convert<JsonElement, JsonElement> JSON2JSON = new Convert<JsonElement, JsonElement>() {
        @Override
        public JsonElement cvt(JsonElement from) {
            return from;
        }
    };
    final private static Convert<JsonElement, Object> JSON2OBJ = new Convert<JsonElement, Object>() {
        @Override
        public Object cvt(JsonElement from) {
            if (from.isJsonPrimitive()) {
                JsonPrimitive value = (JsonPrimitive) from;
                if (value.isBoolean()) {
                    return value.getAsBoolean();
                }
                if (value.isNumber()) {
                    return value.getAsDouble();
                }
                return value.getAsString();
            } else if (from.isJsonObject()) {
                return GSON.fromJson(from, Map.class);
            } else if (from.isJsonArray()) {
                return GSON.fromJson(from, List.class);
            } else {
                return null;
            }
        }
    };

    private static class ChainConvert<I, O> implements Convert<I, O> {
        private Convert<I, O> converter;

        public ChainConvert(Convert<I, O> converter) {
            this.converter = converter;
        }

        @Override
        public O cvt(I from) {
            return this.converter.cvt(from);
        }

        public <N> ChainConvert<I, N> then(final Convert<O, N> next) {
            final Convert<I, O> first = this.converter;
            return new ChainConvert<I, N>(new Convert<I, N>() {
                @Override
                public N cvt(I from) {
                    return next.cvt(first.cvt(from));
                }
            });
        }
    }

    private static <I, O> ChainConvert<I, O> wrap(Convert<I, O> converter) {
        return new ChainConvert<I, O>(converter);
    }

    public static Object fetchObject(String jsonStr, String path) {
        if (jsonStr == null) {
            return null;
        }
        if (path == null) {
            return JSON2OBJ.cvt(STR2JSON.cvt(jsonStr));//Wrap(STR2JSON).then(JSON2OBJ).cvt(jsonStr);
        }
        return fetchByArray(jsonStr, STR2JSON, JSON2OBJ, path.split("/"));
    }

    public static JsonElement fetchJsonElement(String jsonStr, String path) {
        if (jsonStr == null) {
            return null;
        }
        if (path == null) {
            return STR2JSON.cvt(jsonStr);
        }
        return fetchByArray(jsonStr, STR2JSON, JSON2JSON, path.split("/"));
    }

    public static Object fetchObject(JsonElement json, String path) {
        if (json == null) {
            return null;
        }
        if (path == null) {
            return JSON2OBJ.cvt(json);
        }
        return fetchByArray(json, JSON2JSON, JSON2OBJ, path.split("/"));
    }

    public static JsonElement fetchJsonElement(JsonElement json, String path) {
        if (json == null) {
            return null;
        }
        if (path == null) {
            return JSON2JSON.cvt(json);
        }
        return fetchByArray(json, JSON2JSON, JSON2JSON, path.split("/"));
    }

    private static <IF, OT> OT fetchByArray(IF json, Convert<IF, JsonElement> inputConv, Convert<JsonElement, OT> outputConv, String... paths) {
        JsonElement jsonEle = inputConv.cvt(json);//PARSER.parse(json);
        if (paths == null) {
            return outputConv.cvt(jsonEle);
        }
        int idx = 0;
        JsonElement tmp = jsonEle;
        while (tmp != null) {
            if (idx > paths.length - 1) {
                break;
            }
            String current = paths[idx];
            if (current != null && !current.isEmpty()) {
                if (tmp.isJsonObject()) {
                    tmp = ((JsonObject) tmp).get(current);
                } else if (tmp.isJsonArray()) {
                    int subIdx = -1;
                    try {
                        subIdx = Integer.parseInt(current);
                        tmp = subIdx == -1 ? null : ((JsonArray) tmp).get(subIdx);
                    } catch (Exception e) {
                        tmp = null;
                    }
                } else {
                    tmp = null;
                }
            }
            idx++;
        }
        if (tmp != null) {
            return outputConv.cvt(tmp);
        } else {
            return null;
        }
    }

    public static JsonObject convertMapToJsonObject(Map<?, ?> map) {
        return GSON.toJsonTree(map).getAsJsonObject();
    }

    public static <T> T fromJsonObject(JsonObject jsonObject, Class<T> clazz) {
        return GSON.fromJson(jsonObject, clazz);
    }

    public static <T> T fromJsonStr(String jsonStr, Class<T> clazz) {
        return GSON.fromJson(jsonStr, clazz);
    }

    public static <T> List<T> fromJsonArray(JsonArray jsonArr, Type type) {
        return GSON.fromJson(jsonArr, type);
    }

    public static JsonElement parse(String jsonStr) {
        return PARSER.parse(jsonStr);
    }

    public static JsonArray convertListToJsonArray(List<?> list) {
        return GSON.toJsonTree(list).getAsJsonArray();
    }

    public static JsonObject convertJsonStrToJsonObject(String jsonStr) {
        return PARSER.parse(jsonStr).getAsJsonObject();
    }

    public static JsonArray convertJsonStrToJsonArray(String jsonStr) {
        return PARSER.parse(jsonStr).getAsJsonArray();
    }

    public static <T> List<T> convertJsonArrayToList(JsonArray jsonArr, Type type) {
        return GSON.fromJson(GSON.toJson(jsonArr), type);
    }

    public static <T> T convertJsonObjectToObject(JsonObject jsonObject, Class<T> T) {
        return GSON.fromJson(jsonObject, T);
    }

    public static <T> T convertJsonObjectToObject(JsonObject jsonObject, Type type) {
        return GSON.fromJson(jsonObject, type);
    }

    public static <T> T deepCopy(T t) {
        return fromJson(toJson(t), (Class<T>) t.getClass());
    }

    public static String getDataAsJson(String json) {
        JsonObject rootObject = PARSER.parse(json).getAsJsonObject();
        return rootObject.get("data").toString();
    }

    public static String toPrettyFormat(String jsonString) {
        JsonObject json = PARSER.parse(jsonString).getAsJsonObject();
        return GSON.toJson(json);
    }

    public static <T> List<T> fromJsonList(String json, Class<T> classOfT) {
        return GSON.fromJson(
                json,
                new TypeToken<List<T>>() {
                }.getType()
        );
    }
}