package basicmod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static basicmod.BasicMod.makeID;

public class SteamDelay extends BasePower{
    public static final String POWER_ID = makeID("SteamDelay");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private final int damage;

    public SteamDelay(AbstractCreature owner, int damage, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.damage = damage;
    }

    @Override
    public void atStartOfTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        if (amount > 1) {
            addToBot(new ReducePowerAction(p, p, this, 1));
        } else {
            addToBot(new RemoveSpecificPowerAction(p, p, this));
            addToBot(new DamageAllEnemiesAction(p, damage, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + damage + DESCRIPTIONS[1] + amount;
        if (amount == 1) {
            description += DESCRIPTIONS[2];
        } else {
            description += DESCRIPTIONS[3];
        }
    }
}
