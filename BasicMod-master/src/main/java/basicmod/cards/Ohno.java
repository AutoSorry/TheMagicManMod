package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.SadPower;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Ohno extends BaseCard{
    public static final String ID = makeID("Ohno");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.ALL,
            1
    );

    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 3;

    public Ohno() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        for (AbstractMonster mon: AbstractDungeon.getMonsters().monsters) {
            addToBot(new ApplyPowerAction(mon, p, new SadPower(mon, 2)));
        }
        addToBot(new ApplyPowerAction(p, p, new SadPower(p, 2)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Ohno();
    }
}
