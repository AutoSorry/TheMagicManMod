package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.AlarmismPower;
import basicmod.powers.AlarmismUp;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Alarmism extends BaseCard{
    public static final String ID = makeID("Alarmism");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );

    public Alarmism() {
        super(ID, info);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            addToBot(new ApplyPowerAction(p, p, new AlarmismUp(p, 1)));
        } else {
            addToBot(new ApplyPowerAction(p, p, new AlarmismPower(p, 1)));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Alarmism();
    }
}
