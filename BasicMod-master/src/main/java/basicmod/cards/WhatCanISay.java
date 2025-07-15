package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class WhatCanISay extends BaseCard{
    public static final String ID = makeID("WhatCanISay");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );

    public WhatCanISay() {
        super(ID, info);

        setExhaust(true, false);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = p.hand.size() - 1;
        addToBot(new DiscardAction(p, p, count, false));
        addToBot(new DrawCardAction(p, 10));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else {
            return EnergyPanel.getCurrentEnergy() == 0;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new WhatCanISay();
    }
}
