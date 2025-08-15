package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.relics.MagicDice;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import tags.CustomTags;

public class MagicPotion extends BaseCard{
    public static final String ID = makeID("MagicPotion");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            0
    );

    public MagicPotion() {
        super(ID, info);

        setExhaust(true);
        tags.add(CustomTags.MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        MagicDice dice = (MagicDice) p.getRelic(MagicDice.ID);
        MagicDice.CheckResult res = dice.magicCheck();
        if (res == MagicDice.CheckResult.SUCCESS) {
            addToBot(new ObtainPotionAction(AbstractDungeon.returnRandomPotion(true)));
        } else {
            if (upgraded) {
                addToBot(new MakeTempCardInHandAction(new Bb()));
            } else {
                addToBot(new MakeTempCardInHandAction(new Slimed()));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new MagicPotion();
    }
}
