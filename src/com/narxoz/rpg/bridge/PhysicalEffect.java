package com.narxoz.rpg.bridge;

public class PhysicalEffect implements EffectImplementor {

    @Override
    public String getEffectName() {
        return "Physical";
    }

    @Override
    public int applyEffect(int basePower) {
        return basePower;
    }
}
