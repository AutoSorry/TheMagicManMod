package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Roller extends BaseCard{
    public static final String ID = makeID("Roller");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public Roller() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
        cardsToPreview = new VoidCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, magicNumber));
        addToBot(new MakeTempCardInDiscardAction(new VoidCard(), 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Roller();
    }
}
