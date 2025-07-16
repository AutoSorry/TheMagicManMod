package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Man extends BaseCard{
    public static final String ID = makeID("Man");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );

    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;

    public Man() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        AbstractCard c = this.makeCopy();
        if (upgraded) {
            c.upgrade();
        }
        addToBot(new MakeTempCardInDrawPileAction(c, 1, true, true));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Man();
    }
}
