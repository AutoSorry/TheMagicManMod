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
            1
    );

    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 3;

    public HitMagic() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new ApplyPowerAction(p, p, new HitPower(p, 1)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new HitMagic();
    }
}
