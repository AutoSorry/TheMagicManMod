package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.EveryIsEmoji;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EverythingIsEmoji extends BaseCard{
    public static final String ID = makeID("EverythingIsEmoji");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );

    public EverythingIsEmoji() {
        super(ID, info);

        setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new EveryIsEmoji(p, 1)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new EverythingIsEmoji();
    }
}
