package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import tags.CustomTags;

public class BangBangT extends BaseCard{
    public static final String ID = makeID("BangBangT");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            -2
    );

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public BangBangT() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
        setEthereal(true);
        tags.add(CustomTags.EMOJI);
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new DiscardAction(p, p, 1, true));
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public AbstractCard makeCopy() {
        return new BangBangT();
    }
}
