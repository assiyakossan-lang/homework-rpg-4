package com.narxoz.rpg;

import com.narxoz.rpg.battle.RaidEngine;
import com.narxoz.rpg.battle.RaidResult;
import com.narxoz.rpg.bridge.AreaSkill;
import com.narxoz.rpg.bridge.FireEffect;
import com.narxoz.rpg.bridge.IceEffect;
import com.narxoz.rpg.bridge.PhysicalEffect;
import com.narxoz.rpg.bridge.ShadowEffect;
import com.narxoz.rpg.bridge.SingleTargetSkill;
import com.narxoz.rpg.bridge.Skill;
import com.narxoz.rpg.composite.EnemyUnit;
import com.narxoz.rpg.composite.HeroUnit;
import com.narxoz.rpg.composite.PartyComposite;
import com.narxoz.rpg.composite.RaidGroup;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== BRIDGE DEMO =====");

        Skill slashPhysical = new SingleTargetSkill("Slash", 20, new PhysicalEffect());
        Skill slashFire = new SingleTargetSkill("Slash", 20, new FireEffect());
        Skill novaFire = new AreaSkill("Nova", 15, new FireEffect());
        Skill novaIce = new AreaSkill("Nova", 15, new IceEffect());

        System.out.println("Same skill with different effects:");
        System.out.println(slashPhysical);
        System.out.println(slashFire);

        System.out.println();
        System.out.println("Same effect with different skills:");
        System.out.println(slashFire);
        System.out.println(novaFire);

        System.out.println();
        System.out.println("Another combination:");
        System.out.println(novaIce);

        System.out.println();
        System.out.println("===== COMPOSITE DEMO =====");

        HeroUnit knight = new HeroUnit("Knight", 18, 100);
        HeroUnit mage = new HeroUnit("Mage", 14, 70);
        HeroUnit healer = new HeroUnit("Healer", 8, 60);

        EnemyUnit goblin = new EnemyUnit("Goblin", 10, 40);
        EnemyUnit orc = new EnemyUnit("Orc", 16, 80);
        EnemyUnit necromancer = new EnemyUnit("Necromancer", 20, 90);
        EnemyUnit skeleton = new EnemyUnit("Skeleton", 9, 35);

        PartyComposite heroesParty = new PartyComposite("Heroes Party");
        heroesParty.add(knight);
        heroesParty.add(mage);
        heroesParty.add(healer);

        PartyComposite undeadParty = new PartyComposite("Undead Party");
        undeadParty.add(necromancer);
        undeadParty.add(skeleton);

        PartyComposite frontLine = new PartyComposite("Front Line");
        frontLine.add(goblin);
        frontLine.add(orc);

        RaidGroup monstersRaid = new RaidGroup("Monsters Raid");
        monstersRaid.add(frontLine);
        monstersRaid.add(undeadParty);

        System.out.println("Heroes tree:");
        heroesParty.printTree("");

        System.out.println();
        System.out.println("Monsters tree:");
        monstersRaid.printTree("");

        System.out.println();
        System.out.println("===== RAID SIMULATION =====");

        Skill heroesSkill = new AreaSkill("Blizzard", 12, new IceEffect());
        Skill monstersSkill = new SingleTargetSkill("Dark Strike", 18, new ShadowEffect());

        RaidEngine engine = new RaidEngine().setRandomSeed(42);
        RaidResult result = engine.runRaid(heroesParty, heroesSkill, monstersRaid, monstersSkill);

        for (String line : result.getLog()) {
            System.out.println(line);
        }

        System.out.println();
        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());

        System.out.println();
        System.out.println("===== FINAL STATE =====");

        System.out.println("Heroes tree after battle:");
        heroesParty.printTree("");

        System.out.println();
        System.out.println("Monsters tree after battle:");
        monstersRaid.printTree("");
    }
}