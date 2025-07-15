package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.ExhaustToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WordForWord extends BaseCard{
    public static final String ID = makeID("WordForWord");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public WordForWord() {
        super(ID, info);

        setCostUpgrade(0);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = p.hand.size() - 1;
        addToBot(new ExhaustAction(p, p, count, false));
        for (int i = 0; i < count; i++) {
            AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat();
            addToBot(new MakeTempCardInHandAction(c));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new WordForWord();
    }
}
