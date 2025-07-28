package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.Magics;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Holiday extends BaseCard{
    public static final String ID = makeID("Holiday");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    private static final int BLOCK = 11;
    private static final int UPG_BLOCK = 4;

    public Holiday() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);

        cardsToPreview = new Excuse();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new MakeTempCardInHandAction(new Excuse()));
        addToBot(new ReducePowerAction(p, p, Magics.POWER_ID, p.getPower(Magics.POWER_ID).amount - 10));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Holiday();
    }
}
