package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.HitPower;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HitMagic extends BaseCard{
    public static final String ID = makeID("HitMagic");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public HitMagic() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HitPower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new HitMagic();
    }
}
