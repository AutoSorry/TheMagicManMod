package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import tags.CustomTags;

public class Bx extends BaseCard{
    public static final String ID = makeID("Bx");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    private static final int MAGIC = 5;
    private static final int UPG_MAGIC = 3;

    public Bx() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
        setEthereal(true);
        tags.add(CustomTags.EMOJI);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AddTemporaryHPAction(p, p, magicNumber));
        addToBot(new DrawCardAction(p, 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Bx();
    }
}
