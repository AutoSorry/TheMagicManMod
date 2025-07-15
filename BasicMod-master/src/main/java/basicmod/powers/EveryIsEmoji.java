package basicmod.powers;

import basicmod.cards.*;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import static basicmod.BasicMod.makeID;

public class EveryIsEmoji extends BasePower{
    public static final String POWER_ID = makeID("EveryIsEmoji");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public EveryIsEmoji(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new DrawCardAction(amount));
    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new ExhaustAction(amount, false, false, false));
        AbstractCard[] list = new AbstractCard[]{new Bb(), new Bx(), new Yx(), new Wul(), new BangBangT(), new Azgc()};
        for (int i = 0; i < amount; i++) {
            addToBot(new MakeTempCardInHandAction(list[AbstractDungeon.cardRandomRng.random(list.length - 1)].makeCopy()));
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2] + amount + DESCRIPTIONS[5];
        } else {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[3] + amount + DESCRIPTIONS[4] + amount + DESCRIPTIONS[5];
        }
    }
}
