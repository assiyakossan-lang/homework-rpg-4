package com.narxoz.rpg.bridge;

import com.narxoz.rpg.composite.CombatNode;

public abstract class Skill {
    private final String name;
    protected final int basePower;
    protected final EffectImplementor effect;

    public Skill(String name, int basePower, EffectImplementor effect) {
        this.name = name;
        this.basePower = basePower;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public int getBasePower() {
        return basePower;
    }

    public EffectImplementor getEffect() {
        return effect;
    }

    public int getFinalPower() {
        return effect.applyEffect(basePower);
    }

    public String getEffectName() {
        return effect.getEffectName();
    }

    public abstract void use(CombatNode target);

    @Override
    public String toString() {
        return name + " [" + getEffectName() + ", power=" + getFinalPower() + "]";
    }
}
