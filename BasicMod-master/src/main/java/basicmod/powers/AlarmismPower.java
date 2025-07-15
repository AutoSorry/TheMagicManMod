package basicmod.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static basicmod.BasicMod.makeID;

public class AlarmismPower extends BasePower{
    public static final String POWER_ID = makeID("AlarmismPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public AlarmismPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atEndOfRound() {
        addToBot(new ReducePowerAction(owner, owner, this, 1));
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        addToBot(new GainBlockAction(info.owner, owner, damageAmount));
        return 0;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount;
        if (amount == 1) {
            description += DESCRIPTIONS[1];
        } else {
            description += DESCRIPTIONS[2];
        }
        description += DESCRIPTIONS[3];
    }
}
