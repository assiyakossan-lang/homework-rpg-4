package com.narxoz.rpg.bridge;

public class IceEffect implements EffectImplementor {

    @Override
    public String getEffectName() {
        return "Ice";
    }

    @Override
    public int applyEffect(int basePower) {
        return (int) Math.round(basePower * 1.1);
    }
}
