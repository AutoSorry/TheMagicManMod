package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.Magics;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class LionAndTerritory extends BaseCard{
    public static final String ID = makeID("LionAndTerritory");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            1
    );

    private static final int DAMAGE = 7;
    private static final int BLOCK = 7;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public LionAndTerritory() {
        super(ID, info);

        setDamage(DAMAGE);
        setBlock(BLOCK);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            for (AbstractMonster mon: AbstractDungeon.getMonsters().monsters) {
                addToBot(new DamageAction(mon, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
                if (mon.getIntentBaseDmg() >= 0) {
                    addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, -1)));
                    addToBot(new GainBlockAction(p, block));
                }
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new LionAndTerritory();
    }
}
