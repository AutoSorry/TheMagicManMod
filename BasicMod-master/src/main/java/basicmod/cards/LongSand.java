package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LongSand extends BaseCard{
    public static final String ID = makeID("LongSand");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 5;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public LongSand() {
        this(0);
    }

    public LongSand(int upgrades) {
        super(ID, info);

        setDamage(DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);
        timesUpgraded = upgrades;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
        }
    }

    public void upgrade() {
        ++timesUpgraded;
        baseMagicNumber = baseMagicNumber + magicUpgrade;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
    }

    public boolean canUpgrade() {
        return true;
    }

    @Override
    public AbstractCard makeCopy() {
        return new LongSand(timesUpgraded);
    }
}
