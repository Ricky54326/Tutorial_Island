package script.task;

import java.awt.Graphics;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Magic.Spell;
import org.powerbot.script.rt4.Npc;
import org.powerbot.script.rt4.Game.Tab;

public class CastWindTask extends Task<ClientContext> {

	public CastWindTask(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return !ctx.npcs.select().name("Chicken").isEmpty();
	}

	@Override
	public boolean draw() {
		return false;
	}

	@Override
	public void paint(Graphics g1) {		
	}

	@Override
	public void execute() {
		ctx.game.tab(Tab.MAGIC);
		Npc chicken = ctx.npcs.nearest().poll();

		if(chicken.inViewport()) {
			ctx.magic.cast(Spell.WIND_STRIKE);
	        Condition.sleep(Random.nextInt(100, 300));
			chicken.click();
		} else {
			ctx.movement.step(chicken);
			ctx.camera.turnTo(chicken);
		}
	}

}