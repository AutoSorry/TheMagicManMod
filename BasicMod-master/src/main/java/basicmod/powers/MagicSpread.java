package basicmod.powers;

import basicmod.relics.MagicDice;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static basicmod.BasicMod.makeID;

public class MagicSpread extends BasePower{
    public static final String POWER_ID = makeID("MagicSpread");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public MagicSpread(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfRound() {
        for(AbstractMonster m: AbstractDungeon.getMonsters().monsters) {
            if (m.hasPower(POWER_ID)) {
                AbstractPlayer p = AbstractDungeon.player;
                MagicDice dice = (MagicDice) p.getRelic(MagicDice.ID);
                MagicDice.CheckResult res = dice.savingCheck(m);
                if (res == MagicDice.CheckResult.FAILURE) {
                    addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -amount)));
                }
            }
        }
    }
}
