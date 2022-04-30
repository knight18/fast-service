package com.x.fs.base.utils;

import com.x.fs.common.exception.FsServiceException;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 反射常用工具类
 * @author x
 */
public class ReflectUtils {

    private static final String LETTER_VALUE[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z"};

    public static <T> T newInstance(Class<T> clazz, Object... args) {
        HashMap<String, Object> map = asToMap(Arrays.asList(args));

        T object;
        try {
            object = clazz.newInstance();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Field declaredField;
                try {
                    declaredField = clazz.getDeclaredField(entry.getKey());
                } catch (NoSuchFieldException e) {
                    // 尝试从父类中获取
                    Class<? super T> superclass = clazz.getSuperclass();
                    declaredField = superclass.getDeclaredField(entry.getKey());
                }
                declaredField.setAccessible(true);

                declaredField.set(object, entry.getValue());
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new FsServiceException("入参有误");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new FsServiceException("入参有误");
        }

        return object;
    }


    public static <T> T newInstanceWithDefault(Class<T> clazz, Object... args) {
        return setDefault(newInstance(clazz, args));
    }

    public static <T> T setDefault(T object) {
        return setDefault(object, new HashMap<>());
    }

    /**
     * 给对象设置默认值
     *
     * @param object
     * @param map
     */
    public static <T> T setDefault(T object, HashMap<String, Object> map) {
        Class<?> superclass = object.getClass().getSuperclass();
        int count = 1;
        if (superclass != Object.class) {
            Field[] superclassDeclaredFields = superclass.getDeclaredFields();
            setValue(object, superclassDeclaredFields, map, count);
        }

        Field[] declaredFields = object.getClass().getDeclaredFields();
        setValue(object, declaredFields, map, count);

        return object;
    }

    private static HashMap<String, Object> asToMap(List<Object> objectList) {
        if (null == objectList || objectList.size() % 2 != 0) {
            throw new FsServiceException("入参值不正确");
        }
        HashMap<String, Object> map = new HashMap<>();
        for (int i = 0; i < objectList.size(); i = i + 2) {
            Object key = objectList.get(i);
            if (key instanceof String) {
                map.put((String) key, objectList.get(i + 1));
            } else {
                throw new FsServiceException("入参值不正确");
            }
        }
        return map;

    }


    private static <T> void setValue(T object, Field[] declaredFields, HashMap<String, Object> map, int count) {
        for (Field declaredField : declaredFields) {
            try {
                declaredField.setAccessible(true);
                if (declaredField.get(object) != null) {
                    continue;
                }

                Class<?> type = declaredField.getType();
                String name = declaredField.getName();
                Object value = map.get(name);


                if (value != null && value.getClass() == type) {
                    declaredField.set(object, value);
                    continue;
                }

                if (type == short.class || type == Short.class) {
                    declaredField.set(object, (short) count++);
                } else if (type == int.class || type == Integer.class) {
                    declaredField.set(object, count++);
                } else if (type == long.class || type == Long.class) {
                    declaredField.set(object, (long) count++);
                } else if (type == String.class) {
                    declaredField.set(object, LETTER_VALUE[count++ % 26]);
                } else if (type == Timestamp.class) {
                    declaredField.set(object, new Timestamp(System.currentTimeMillis()));
                } else {
                    throw new FsServiceException("入参不正确");
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new FsServiceException("入参不正确");
            }
        }
    }


}
