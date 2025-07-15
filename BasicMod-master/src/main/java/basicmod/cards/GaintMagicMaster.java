package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.GaintMagic;
import basicmod.powers.SadPower;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GaintMagicMaster extends BaseCard{
    public static final String ID = makeID("GaintMagicMaster");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );

    private static final int MAGIC = 8;
    private static final int UPG_MAGIC = 4;

    public GaintMagicMaster() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new GaintMagic(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new SadPower(p, 5)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new GaintMagicMaster();
    }
}
