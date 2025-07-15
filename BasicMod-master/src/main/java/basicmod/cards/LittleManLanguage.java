package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LittleManLanguage extends BaseCard{
    public static final String ID = makeID("LittleManLanguage");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public LittleManLanguage() {
        super(ID, info);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractCard c : p.discardPile.group) {
            if (c.costForTurn < 1 && upgraded) {
                continue;
            }
            c.costForTurn = 1;
        }
        for (AbstractCard c : p.drawPile.group) {
            if (c.costForTurn < 1 && upgraded) {
                continue;
            }
            c.costForTurn = 1;
        }
        for (AbstractCard c : p.hand.group) {
            if (c.costForTurn < 1 && upgraded) {
                continue;
            }
            c.costForTurn = 1;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new LittleManLanguage();
    }
}
