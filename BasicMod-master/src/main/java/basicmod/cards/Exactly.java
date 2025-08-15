package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.Magics;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static java.lang.Math.min;

public class Exactly extends BaseCard{
    public static final String ID = makeID("Exactly");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            0
    );

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public Exactly() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amt = p.getPower(Magics.POWER_ID).amount;
        if (amt - magicNumber < 1) {
            addToBot(new GainEnergyAction(1));
            addToBot(new DrawCardAction(p, 1));
        }
        addToBot(new ReducePowerAction(p, p, Magics.POWER_ID, min(amt - 1, magicNumber)));
        addToBot(new DrawCardAction(p, 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Exactly();
    }
}
