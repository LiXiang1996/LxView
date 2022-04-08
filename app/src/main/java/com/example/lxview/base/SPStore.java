package com.example.lxview.base;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.lang.reflect.Method;
import java.util.Map;

public class SPStore {
    //保存在手机里面的文件名
    public static final String FILE_NAME = "UTownStore";
    public static SharedPreferences sp ;

    public static void init(Context context) {
        if (sp == null)
            sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    /**  存入数据
     * @param key
     * @param value
     * @return
     */
    public static <E>void put(@NonNull String key, @NonNull E value) {
        SharedPreferences.Editor editor = sp.edit();
        if (value instanceof String){
            editor.putString(key, (String) value);
        }else if (value instanceof Integer){
            editor.putInt(key, (Integer) value);
        }else if (value instanceof Boolean){
            editor.putBoolean(key, (Boolean) value);
        }else if (value instanceof Float){
            editor.putFloat(key, (Float) value);
        }else if (value instanceof Long){
            editor.putLong(key, (Long) value);
        }else if (value instanceof Double){
            editor.putString(key, value.toString());
        }else {
            editor.putString(key, new Gson().toJson(value));
        }
        SPCompat.apply(editor);
    }
    /**
     * 得到保存数据的方法
     * @return
     */
    public static <E>E getObject(@NonNull String key,Class cls) {
        //json为null的时候返回对象为null,gson已处理
        return (E) new Gson().fromJson(sp.getString(key, null),cls);
    }

    public static String getString(@NonNull String key){
        return sp.getString(key,null);
    }

    public static int getInt(@NonNull String key){
        return sp.getInt(key,0);
    }

    public static long getLong(@NonNull String key){
        return sp.getLong(key,0);
    }

    public static double getDouble(@NonNull String key){
        return Double.parseDouble(sp.getString(key,"0.0"));
    }

    /**
     * 移除某个key值已经对应的值
     * @param key
     */
    public static void remove(String key) {
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SPCompat.apply(editor);
    }

    /**
     * 清除所有数据
     */
    public static void clear() {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SPCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     * @param key
     * @return
     */
    public static boolean contains(String key) {
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     * @return
     */
    public static Map<String, ?> getAll() {
        return sp.getAll();
    }

    /**
     * 保存对象到sp文件中 被保存的对象须要实现 Serializable 接口
     * @param key
     * @param value
     */
    public static void saveObject(String key, Object value) {
        put(key,value);
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     * @author
     */
    private static class SPCompat {
        private static final Method S_APPLY_METHOD = findApplyMethod();

        /**
         * 反射查找apply的方法
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }
            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (S_APPLY_METHOD != null) {
                    S_APPLY_METHOD.invoke(editor);
                    return;
                }
            } catch (Exception e) {
            }
            editor.commit();
        }
    }
}
