package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FakeNineChampion extends BaseCard{
    public static final String ID = makeID("FakeNineChampion");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public FakeNineChampion() {
        super(ID, info);

        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 9; i++) {
            AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat();
            if (upgraded) {
                c.upgrade();
            }
            addToBot(new MakeTempCardInDrawPileAction(c, 1, true, true, true));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new FakeNineChampion();
    }
}
