package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.SteamDelay;
import basicmod.relics.MagicDice;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import tags.CustomTags;

public class SteamStart extends BaseCard{
    public static final String ID = makeID("SteamStart");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            3
    );

    private static final int DAMAGE = 32;
    private static final int UPG_DAMAGE = 10;

    public SteamStart() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setInnate(true);
        setExhaust(true);
        setEthereal(true);
        tags.add(CustomTags.MAGIC);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        MagicDice dice = (MagicDice) p.getRelic(MagicDice.ID);
        MagicDice.CheckResult res = dice.magicCheck();
        if (res == MagicDice.CheckResult.CRITICAL_SUCCESS) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage * 2, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        } else if (res == MagicDice.CheckResult.SUCCESS) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        } else {
            addToBot(new ApplyPowerAction(p, p, new SteamDelay(p, damage, 7)));
        }

    }

    @Override
    public AbstractCard makeCopy() {
        return new SteamStart();
    }
}
