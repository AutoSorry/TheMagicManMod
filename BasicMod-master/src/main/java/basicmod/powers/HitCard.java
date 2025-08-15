package basicmod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static basicmod.BasicMod.makeID;
import static com.badlogic.gdx.math.MathUtils.random;

public class HitCard extends BasePower{
    public static final String POWER_ID = makeID("HitCard");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public HitCard(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        int r = random(0, 2);
        switch (r) {
            case 0: {
                addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, amount)));
                break;
            } case 1: {
                addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, amount)));
                break;
            } case 2: {
                addToBot(new ApplyPowerAction(p, p, new HappyPower(p, amount)));
                break;
            }
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
