package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.Plus1;
import basicmod.powers.Plus1Up;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PlusOne extends BaseCard{
    public static final String ID = makeID("PlusOne");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public PlusOne() {
        super(ID, info);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            addToBot(new ApplyPowerAction(p, p, new Plus1Up(p, 1)));
        } else {
            addToBot(new ApplyPowerAction(p, p, new Plus1(p, 1)));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new PlusOne();
    }
}
