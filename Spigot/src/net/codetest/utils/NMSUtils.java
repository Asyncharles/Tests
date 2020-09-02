package net.codetest.utils;

import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.EntityTypes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NMSUtils {

    public static void registerEntity(String name, int id, Class<? extends EntityInsentient> nmsClass, Class<? extends EntityInsentient> customClass) {
        try {
            List<Map<?, ?>> dataMap = new ArrayList<>();
            for (Field field : EntityTypes.class.getDeclaredFields()) {
                if (field.getType().getSimpleName().equals(Map.class.getSimpleName())) {
                    field.setAccessible(true);
                    dataMap.add((Map<?, ?>) field.get(null));
                }
            }
            Field c = EntityTypes.class.getDeclaredField("c");
            c.setAccessible(true);
            Field d = EntityTypes.class.getDeclaredField("d");
            d.setAccessible(true);
            Field f = EntityTypes.class.getDeclaredField("f");
            f.setAccessible(true);
            Field g = EntityTypes.class.getDeclaredField("g");
            g.setAccessible(true);
            ((Map<String, Class<? extends EntityInsentient>>) c.get(null)).put(name, customClass);
            ((Map<Class<? extends EntityInsentient>, String>) d.get(null)).put(customClass, name);
            ((Map<Class<? extends EntityInsentient>, Integer>) f.get(null)).put(customClass, id);
            ((Map<String, Integer>) g.get(null)).put(name, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getPrivateField(String fieldName, Class clazz, Object object) {
        Field field;
        Object o = null;

        try {
            field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            o = field.get(object);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return o;
    }

}
