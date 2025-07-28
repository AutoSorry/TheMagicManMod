package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.NinetyEightPower;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class NinetyEight extends BaseCard{
    public static final String ID = makeID("NinetyEight");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            0
    );

    private static final int MAGIC = 1;

    public NinetyEight() {
        super(ID, info);

        setMagic(MAGIC);
        setInnate(false, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new NinetyEightPower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new NinetyEight();
    }
}
