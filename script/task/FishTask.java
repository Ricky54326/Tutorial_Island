package script.task;

import java.awt.Graphics;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;

public class FishTask extends Task<ClientContext> {
	
	private Npc fishSpot;

	public FishTask(ClientContext ctx) {
		super(ctx);
		this.fishSpot = null;
	}

	@Override
	public boolean activate() {
		return ctx.inventory.select().count() < 28 
				&& !ctx.npcs.select().name("Fishing spot").isEmpty() 
				&& ctx.players.local().animation() == -1;
	}

	@Override
	public void execute() {

		fishSpot = ctx.npcs.nearest().poll();

		if(fishSpot.inViewport()) {
			fishSpot.interact("Net");
            Condition.sleep(Random.nextInt(500, 800));
		} else {
			ctx.movement.step(fishSpot);
			ctx.camera.turnTo(fishSpot);
		}
		
	}

	@Override
	public boolean draw() {
		return fishSpot != null;
	}

	@Override
	public void paint(Graphics g1) {
		fishSpot.draw(g1);
	}
}
