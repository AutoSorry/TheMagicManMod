package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.EndTurnAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class NotToSay extends BaseCard{
    public static final String ID = makeID("NotToSay");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;

    public NotToSay() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);
        setMagic(3);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new GainBlockAction(p, p, block));
        }
        addToBot(new PressEndTurnButtonAction());
    }

    @Override
    public AbstractCard makeCopy() {
        return new NotToSay();
    }
}
