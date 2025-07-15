package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.DetectMagic;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MagicDetection extends BaseCard{
    public static final String ID = makeID("MagicDetection");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );

    private static final int MAGIC = 5;
    private static final int UPG_MAGIC = 2;

    public MagicDetection() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DetectMagic(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new MagicDetection();
    }
}
