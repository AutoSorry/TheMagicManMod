package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.WoodiePower;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class WoodieMaster extends BaseCard{
    public static final String ID = makeID("WoodieMaster");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );

    public WoodieMaster() {
        super(ID, info);

        setMagic(2, 1);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, magicNumber)));
        if (!p.hasPower(WoodiePower.POWER_ID)) {
            addToBot(new ApplyPowerAction(p, p, new WoodiePower(p, magicNumber)));
        }
        for (AbstractCard c : p.hand.group) {
            if (c.hasTag(CardTags.STARTER_STRIKE) || c.hasTag(CardTags.STARTER_DEFEND)) {
                c.costForTurn = 0;
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new WoodieMaster();
    }
}
