package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.Magics;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SoHandsomeBro extends BaseCard{
    public static final String ID = makeID("SoHandsomeBro");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );

    private static final int BLOCK = 16;

    public SoHandsomeBro() {
        super(ID, info);

        setBlock(BLOCK);
        setSelfRetain(false, true);
        setExhaust(true);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else {
            return p.getPower(Magics.POWER_ID).amount >= 16;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new SoHandsomeBro();
    }
}
