package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.relics.MagicDice;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import tags.CustomTags;

public class MilkDragonCry extends BaseCard{
    public static final String ID = makeID("MilkDragonCry");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );

    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 2;

    public MilkDragonCry() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);
        tags.add(CustomTags.MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        MagicDice dice = (MagicDice) p.getRelic(MagicDice.ID);
        MagicDice.CheckResult res = dice.magicCheck();
        if (res == MagicDice.CheckResult.CRITICAL_SUCCESS) {
            addToBot(new GainBlockAction(p, p, block * 2));
        } else if (res == MagicDice.CheckResult.SUCCESS) {
            addToBot(new GainBlockAction(p, p, block));
        } else {
            addToBot(new GainBlockAction(p, p, block / 2));
        }
    }

    @Override
    public void tookDamage() {
        addToBot(new DiscardToHandAction(this));
    }

    @Override
    public AbstractCard makeCopy() {
        return new MilkDragonCry();
    }
}
