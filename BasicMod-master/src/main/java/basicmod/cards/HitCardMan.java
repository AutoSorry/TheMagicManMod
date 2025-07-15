package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.HitCard;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HitCardMan extends BaseCard{
    public static final String ID = makeID("HitCardMan");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public HitCardMan() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HitCard(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new HitCardMan();
    }
}
