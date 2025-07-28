package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.badlogic.gdx.math.MathUtils.random;

public class MagicFriend extends BaseCard{
    public static final String ID = makeID("MagicFriend");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 3;

    public MagicFriend() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);
        setExhaust(true);

        cardsToPreview = new BangBangT();

    }

    @Override
    public void upgrade() {
        super.upgrade();
        if (!upgraded) {
            cardsToPreview.upgrade();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        AbstractCard c = new BangBangT();
        if (upgraded) {
            c.upgrade();
        }
        addToBot(new MakeTempCardInDrawPileAction(c, 1, true, true));
        AbstractCard c1 = this.makeCopy();
        if (upgraded) {
            c1.upgrade();
        }
        addToBot(new MakeTempCardInDiscardAction(c1, 2));
    }

    @Override
    public AbstractCard makeCopy() {
        return new MagicFriend();
    }
}
