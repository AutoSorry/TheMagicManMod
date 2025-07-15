package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.relics.MagicDice;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GrassHead extends BaseCard{
    public static final String ID = makeID("GrassHead");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.ALL_ENEMY,
            1
    );

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public GrassHead() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        MagicDice dice = new MagicDice();
        for (AbstractMonster mon: AbstractDungeon.getMonsters().monsters) {
            for (int i = 0; i < magicNumber; i++) {
                MagicDice.CheckResult res = dice.savingCheck(mon);
                if (res == MagicDice.CheckResult.FAILURE) {
                    addToBot(new LoseHPAction(mon, p, 6));
                }
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new GrassHead();
    }
}
