package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AreYouHitting extends BaseCard{
    public static final String ID = makeID("AreYouHitting");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ENEMY,
            2
    );

    public AreYouHitting() {
        super(ID, info);
        
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractCard c = new ImHittingHard();
        if (upgraded) {
            c.upgrade();
            addToBot(new ApplyPowerAction(m, p, new StunMonsterPower(m, 1)));
            addToBot(new MakeTempCardInHandAction(c, 1));
        } else {
            if (m.getIntentBaseDmg() >= 0) {
                addToBot(new ApplyPowerAction(m, p, new StunMonsterPower(m, 1)));
                addToBot(new MakeTempCardInHandAction(c, 1));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new AreYouHitting();
    }
}
