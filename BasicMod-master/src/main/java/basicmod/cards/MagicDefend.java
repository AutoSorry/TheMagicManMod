package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.Magics;
import basicmod.relics.MagicDice;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import tags.CustomTags;

public class MagicDefend extends BaseCard{
    public static final String ID = makeID("MagicDefend");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            1
    );

    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;

    public MagicDefend() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);
        tags.add(CustomTags.MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        MagicDice dice = (MagicDice) p.getRelic(MagicDice.ID);
        MagicDice.CheckResult res = dice.magicCheck();
        int magics = p.getPower(Magics.POWER_ID).amount;
        if (res == MagicDice.CheckResult.CRITICAL_SUCCESS) {
            addToBot(new GainBlockAction(p, p, (block + magics) * 2));
        } else if (res == MagicDice.CheckResult.SUCCESS) {
            addToBot(new GainBlockAction(p, p, block + magics));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new MagicDefend();
    }
}
