package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.Magics;
import basicmod.powers.NextAlwaysCriticalSuccess;
import basicmod.powers.NextAlwaysSuccess;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class IAmMagicMan extends BaseCard{
    public static final String ID = makeID("IAmMagicMan");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            0
    );

    public IAmMagicMan() {
        super(ID, info);

        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new Magics(p, 5)));
        if(upgraded) {
            addToBot(new ApplyPowerAction(p, p, new NextAlwaysCriticalSuccess(p, 1)));
        } else {
            addToBot(new ApplyPowerAction(p, p, new NextAlwaysSuccess(p, 1)));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new IAmMagicMan();
    }
}
