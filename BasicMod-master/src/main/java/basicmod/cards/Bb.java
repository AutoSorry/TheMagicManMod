package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import tags.CustomTags;

public class Bb extends BaseCard{
    public static final String ID = makeID("Bb");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            0
    );

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public Bb() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
        setDamage(11);
        isMultiDamage = true;
        setEthereal(true);
        setExhaust(true);
        tags.add(CustomTags.EMOJI);
    }

    @Override
    public void triggerOnManualDiscard() {
        addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, multiDamage, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.POISON));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Bb();
    }
}
