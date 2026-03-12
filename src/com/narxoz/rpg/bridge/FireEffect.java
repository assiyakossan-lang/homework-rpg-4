package com.narxoz.rpg.bridge;

public class FireEffect implements EffectImplementor {

    @Override
    public String getEffectName() {
        return "Fire";
    }

    @Override
    public int applyEffect(int basePower) {
        return (int) Math.round(basePower * 1.2);
    }
}
