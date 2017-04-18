package net.gegy1000.wearables.server.wearable.component;

import net.gegy1000.wearables.server.wearable.WearableCategory;
import net.gegy1000.wearables.server.wearable.component.chest.BowTieComponent;
import net.gegy1000.wearables.server.wearable.component.chest.JetpackComponent;
import net.gegy1000.wearables.server.wearable.component.chest.TieComponent;
import net.gegy1000.wearables.server.wearable.component.feet.FlippersComponent;
import net.gegy1000.wearables.server.wearable.component.head.Glasses1Component;
import net.gegy1000.wearables.server.wearable.component.chest.ModOffCapeComponent;
import net.gegy1000.wearables.server.wearable.component.head.RoundGlassesComponent;
import net.gegy1000.wearables.server.wearable.component.head.Retro3DGlassesComponent;
import net.gegy1000.wearables.server.wearable.component.head.TopHatComponent;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ComponentRegistry {
    public static final List<WearableComponentType> COMPONENTS = new ArrayList<>();
    public static final Map<WearableCategory, Set<WearableComponentType>> CATEGORIES = new HashMap<>();
    public static final Map<String, WearableComponentType> IDENTIFIERS = new HashMap<>();

    public static final Glasses1Component GLASSES_1 = new Glasses1Component();
    public static final TopHatComponent TOP_HAT = new TopHatComponent();
    public static final Retro3DGlassesComponent RETRO_3D_GLASSES = new Retro3DGlassesComponent();
    public static final RoundGlassesComponent HARRY_POTTER_GLASSES = new RoundGlassesComponent();
    public static final ModOffCapeComponent MODOFF_CAPE = new ModOffCapeComponent();
    public static final BowTieComponent BOW_TIE = new BowTieComponent();
    public static final TieComponent TIE = new TieComponent();
    public static final FlippersComponent FLIPPERS = new FlippersComponent();
    public static final JetpackComponent JETPACK = new JetpackComponent();

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
        COMPONENTS.sort(Comparator.comparing(WearableComponentType::getIdentifier));
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

    public static WearableComponentType getDefault() {
        if (!COMPONENTS.isEmpty()) {
            return COMPONENTS.get(0);
        }
        return null;
    }
}
