package basicmod.relics;

import basicmod.character.MyCharacter;
import basicmod.powers.HappyPower;
import basicmod.powers.Magics;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static basicmod.BasicMod.makeID;

public class MiddlePart extends BaseRelic{
    private static final String NAME = "MiddlePart";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.COMMON;
    private static final LandingSound SOUND = LandingSound.SOLID;

    public MiddlePart() {
        super(ID, NAME, MyCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atBattleStart() {
        flash();
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new RelicAboveCreatureAction(p, this));
        addToBot(new ApplyPowerAction(p, p, new Magics(p, 3)));
        addToBot(new ApplyPowerAction(p, p, new HappyPower(p, 3)));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
