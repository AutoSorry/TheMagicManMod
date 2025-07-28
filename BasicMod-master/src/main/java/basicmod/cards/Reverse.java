package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Reverse extends BaseCard{
    public static final String ID = makeID("Reverse");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );

    public Reverse() {
        super(ID, info);

        setSelfRetain(false, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = p.hand.size() - 1;
        addToBot(new PutOnDeckAction(p, p, count, true));
        addToBot(new BetterDiscardPileToHandAction(count, false));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Reverse();
    }
}
