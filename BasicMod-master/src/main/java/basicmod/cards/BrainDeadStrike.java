package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.relics.MagicDice;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import tags.CustomTags;

public class BrainDeadStrike extends BaseCard{
    public static final String ID = makeID("BrainDeadStrike");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ALL_ENEMY,
            -1
    );

    private static final int DAMAGE = 6;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public BrainDeadStrike() {
        super(ID, info);

        setDamage(DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);

        tags.add(CardTags.STRIKE);
        tags.add(CustomTags.MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        MagicDice dice = (MagicDice) p.getRelic(MagicDice.ID);
        MagicDice.CheckResult res = dice.magicCheck();
        int effect = EnergyPanel.totalCount;
        if (energyOnUse != -1) {
            effect = energyOnUse;
        }
        if (p.hasRelic("Chemical X")) {
            effect += 2;
            p.getRelic("Chemical X").flash();
        }
        effect += magicNumber;
        if (res == MagicDice.CheckResult.CRITICAL_SUCCESS) {
            for (AbstractMonster monster: AbstractDungeon.getMonsters().monsters) {
                addToBot(new DamageAction(monster, new DamageInfo(p, damage * effect * 2, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
            }
        } else if (res == MagicDice.CheckResult.SUCCESS) {
            for (AbstractMonster monster: AbstractDungeon.getMonsters().monsters) {
                addToBot(new DamageAction(monster, new DamageInfo(p, damage * effect, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
            }
        }
        if (!freeToPlayOnce) {
            p.energy.use(EnergyPanel.totalCount);
        }
        int count = p.hand.size();
        addToBot(new DiscardAction(p, p, count, false));
    }

    @Override
    public AbstractCard makeCopy() {
        return new BrainDeadStrike();
    }
}
