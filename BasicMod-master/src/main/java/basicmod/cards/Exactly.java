package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.Magics;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Exactly extends BaseCard{
    public static final String ID = makeID("Exactly");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            1
    );

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public Exactly() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.getPower(Magics.POWER_ID).amount - magicNumber > 0) {
            addToBot(new ReducePowerAction(p, p, Magics.POWER_ID, magicNumber));
        }
        addToBot(new DrawCardAction(p, 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Exactly();
    }
}
