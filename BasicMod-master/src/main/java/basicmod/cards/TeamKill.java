package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.Magics;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class TeamKill extends BaseCard{
    public static final String ID = makeID("TeamKill");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );

    private static final int DAMAGE = 3;
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;

    public TeamKill() {
        super(ID, info);

        setDamage(DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(p, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new ApplyPowerAction(p, p, new Magics(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new VigorPower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new TeamKill();
    }
}
