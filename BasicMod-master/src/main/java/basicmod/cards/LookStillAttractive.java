package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.HappyPower;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class LookStillAttractive extends BaseCard{
    public static final String ID = makeID("LookStillAttractive");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF_AND_ENEMY,
            1
    );

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public LookStillAttractive() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
        setExhaust(true);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HappyPower(p, magicNumber)));
        if (m.getIntentBaseDmg() >= 0) {
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
        } else {
            addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, magicNumber)));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new LookStillAttractive();
    }
}
