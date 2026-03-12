package com.narxoz.rpg.composite;

import java.util.Collections;
import java.util.List;

public abstract class UnitLeaf implements CombatNode {
    private final String name;
    private final int attackPower;
    private int health;

    public UnitLeaf(String name, int attackPower, int health) {
        this.name = name;
        this.attackPower = attackPower;
        this.health = health;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public int getAttackPower() {
        return isAlive() ? attackPower : 0;
    }

    @Override
    public void takeDamage(int amount) {
        if (!isAlive()) return;
        if (amount < 0) amount = 0;
        health = Math.max(0, health - amount);
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public void printTree(String indent) {
        System.out.println(indent + "- " + name +
                " [HP=" + health + ", ATK=" + attackPower + ", alive=" + isAlive() + "]");
    }

    @Override
    public List<CombatNode> getChildren() {
        return Collections.emptyList();
    }
}