package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.relics.MagicDice;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import tags.CustomTags;

public class CheckCheck extends BaseCard{
    public static final String ID = makeID("CheckCheck");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            0
    );

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public CheckCheck() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
        tags.add(CustomTags.MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        MagicDice dice = (MagicDice) p.getRelic(MagicDice.ID);
        MagicDice.CheckResult res = dice.magicCheck();
        if (res == MagicDice.CheckResult.CRITICAL_SUCCESS) {
            addToBot(new GainEnergyAction(magicNumber * 2));
        } else if (res == MagicDice.CheckResult.SUCCESS) {
            addToBot(new GainEnergyAction(magicNumber));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CheckCheck();
    }
}
