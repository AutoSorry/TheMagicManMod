package basicmod.powers;

import basicmod.cards.ThisBombBro;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static basicmod.BasicMod.makeID;

public class BombPower extends BasePower{
    public static final String POWER_ID = makeID("BombPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public BombPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new MakeTempCardInHandAction(new ThisBombBro()));
        addToBot(new ReducePowerAction(owner, owner, this, 1));
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
