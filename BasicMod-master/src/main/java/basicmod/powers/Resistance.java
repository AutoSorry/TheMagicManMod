package basicmod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static basicmod.BasicMod.makeID;

public class Resistance extends BasePower{
    public static final String POWER_ID = makeID("Resistance");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public Resistance(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
        return damage / 2;
    }

    @Override
    public void atEndOfRound() {
        addToBot(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, this, 1));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount;
        if (amount == 1) {
            description = description + DESCRIPTIONS[1];
        } else {
            description = description + DESCRIPTIONS[2];
        }
    }
}
