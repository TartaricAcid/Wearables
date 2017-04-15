package net.gegy1000.wearables.server.wearable.component;

import net.gegy1000.wearables.server.wearable.WearableCategory;
import net.gegy1000.wearables.server.wearable.component.chest.PlainShirtComponent;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ComponentRegistry {
    public static final List<WearableComponentType> COMPONENTS = new ArrayList<>();
    public static final Map<WearableCategory, Set<WearableComponentType>> CATEGORIES = new HashMap<>();
    public static final Map<String, WearableComponentType> IDENTIFIERS = new HashMap<>();

    public static final PlainShirtComponent PLAIN_SHIRT = new PlainShirtComponent();

    public static void register() {
        try {
            for (Field field : ComponentRegistry.class.getDeclaredFields()) {
                Object value = field.get(null);
                if (value instanceof WearableComponentType) {
                    ComponentRegistry.register((WearableComponentType) value);
                } else if (value instanceof WearableComponentType[]) {
                    for (WearableComponentType component : (WearableComponentType[]) value) {
                        ComponentRegistry.register(component);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void register(WearableComponentType component) {
        COMPONENTS.add(component);
        IDENTIFIERS.put(component.getIdentifier(), component);
        Set<WearableComponentType> category = CATEGORIES.computeIfAbsent(component.getCategory(), c -> new HashSet<>());
        category.add(component);
    }

    public static Set<WearableComponentType> get(WearableCategory category) {
        return CATEGORIES.get(category);
    }

    public static WearableComponentType get(String identifier) {
        return IDENTIFIERS.get(identifier);
    }
}
