package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.CombinePower;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConfusionPower;

public class Combination extends BaseCard{
    public static final String ID = makeID("Combination");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public Combination() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ConfusionPower(p)));
        addToBot(new ApplyPowerAction(p, p, new CombinePower(p, 1)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Combination();
    }
}
