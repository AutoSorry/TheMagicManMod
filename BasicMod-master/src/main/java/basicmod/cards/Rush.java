package basicmod.cards;

import basicmod.powers.Tired;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Rush extends BaseCard{
    public static final String ID = makeID("Rush");
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0
    );

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public Rush() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
        setSelfRetain(true);
        setExhaust(true);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, magicNumber));
        addToBot(new ApplyPowerAction(p, p, new Tired(p, 1)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Rush();
    }
}
