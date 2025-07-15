package basicmod.relics;

import basicmod.character.MyCharacter;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static basicmod.BasicMod.makeID;

public class MiaowtainTop extends BaseRelic{
    private static final String NAME = "MiaowtainTop";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.SHOP;
    private static final LandingSound SOUND = LandingSound.SOLID;

    public MiaowtainTop() {
        super(ID, NAME, MyCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atBattleStart() {
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
