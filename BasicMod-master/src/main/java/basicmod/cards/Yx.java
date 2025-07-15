package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import tags.CustomTags;

public class Yx extends BaseCard{
    public static final String ID = makeID("Yx");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    private static final int MAGIC = 8;
    private static final int UPG_MAGIC = 3;

    public Yx() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
        setEthereal(true);
        tags.add(CustomTags.EMOJI);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseHPAction(m, p, magicNumber));
        addToBot(new DrawCardAction(p, 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Yx();
    }
}
