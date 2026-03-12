package com.narxoz.rpg.bridge;

public class ShadowEffect implements EffectImplementor {

    @Override
    public String getEffectName() {
        return "Shadow";
    }

    @Override
    public int applyEffect(int basePower) {
        return (int) Math.round(basePower * 1.3);
    }
}
