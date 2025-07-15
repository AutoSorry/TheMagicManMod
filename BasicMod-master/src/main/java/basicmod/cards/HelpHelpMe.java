package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.relics.MagicDice;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HelpHelpMe extends BaseCard{
    public static final String ID = makeID("HelpHelpMe");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );

    private static final int MAGIC = 6;
    private static final int UPG_MAGIC = 3;

    public HelpHelpMe() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
        setExhaust(true);
        tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        MagicDice dice = new MagicDice();
        MagicDice.CheckResult res = dice.savingCheck(m);
        if (res == MagicDice.CheckResult.FAILURE) {
            addToBot(new HealAction(p, m, magicNumber));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new HelpHelpMe();
    }
}
