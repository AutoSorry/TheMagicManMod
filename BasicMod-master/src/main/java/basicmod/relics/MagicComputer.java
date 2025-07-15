package basicmod.relics;

import basicmod.character.MyCharacter;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static basicmod.BasicMod.makeID;

public class MagicComputer extends BaseRelic{
    private static final String NAME = "MagicComputer";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.SOLID;

    public MagicComputer() {
        super(ID, NAME, MyCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atBattleStart() {
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    @Override
    public void atTurnStartPostDraw() {
        flash();
        AbstractPlayer p = AbstractDungeon.player;
        MagicDice dice = (MagicDice) p.getRelic(MagicDice.ID);
        MagicDice.CheckResult res = dice.magicCheck();
        if (res == MagicDice.CheckResult.CRITICAL_SUCCESS) {
            addToBot(new GainEnergyAction(2));
        } else if (res == MagicDice.CheckResult.SUCCESS) {
            addToBot(new GainEnergyAction(1));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
