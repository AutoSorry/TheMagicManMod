package basicmod.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static basicmod.BasicMod.makeID;

public class WoodiePower extends BasePower{
    public static final String POWER_ID = makeID("WoodiePower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public WoodiePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (card.hasTag(AbstractCard.CardTags.STARTER_STRIKE) || card.hasTag(AbstractCard.CardTags.STARTER_DEFEND)) {
            card.costForTurn = 0;
        }
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.hasTag(AbstractCard.CardTags.STARTER_STRIKE) || card.hasTag(AbstractCard.CardTags.STARTER_DEFEND)) {
            addToBot(new DrawCardAction(AbstractDungeon.player, 1));
        }
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, this, 1));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
