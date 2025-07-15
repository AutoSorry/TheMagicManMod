package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.WulPower;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import tags.CustomTags;

public class Wul extends BaseCard{
    public static final String ID = makeID("Wul");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public Wul() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
        setEthereal(true);
        tags.add(CustomTags.EMOJI);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new WulPower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Wul();
    }
}
