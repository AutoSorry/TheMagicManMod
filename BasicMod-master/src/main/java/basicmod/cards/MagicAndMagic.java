package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.relics.MagicDice;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MagicAndMagic extends BaseCard{
    public static final String ID = makeID("MagicAndMagic");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 9;
    private static final int UPG_DAMAGE = 3;

    public MagicAndMagic() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        MagicDice dice = (MagicDice) p.getRelic(MagicDice.ID);
        MagicDice.CheckResult res = dice.magicCheck();
        int count = 1;
        while (res == MagicDice.CheckResult.CRITICAL_SUCCESS || res == MagicDice.CheckResult.SUCCESS) {
            if (res == MagicDice.CheckResult.SUCCESS) {
                addToBot(new DamageAction(m, new DamageInfo(p, damage * count, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
            } else {
                addToBot(new DamageAction(m, new DamageInfo(p, damage * count * 2, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
            }
            res = dice.magicCheck();
            if (++count == 3) {
                break;
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new MagicAndMagic();
    }
}
