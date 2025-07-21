package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.LifeFlavorPower;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LifeFlavor extends BaseCard{
    public static final String ID = makeID("LifeFlavor");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    private static final int MAGIC = 5;
    private static final int UPG_MAGIC = 2;

    public LifeFlavor() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new LifeFlavorPower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new LifeFlavor();
    }
}
