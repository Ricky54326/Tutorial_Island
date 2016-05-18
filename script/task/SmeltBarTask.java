package script.task;

import java.awt.Graphics;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Item;

public class SmeltBarTask extends Task<ClientContext> {

	public SmeltBarTask(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return !ctx.objects.select().name("Furnace").isEmpty();
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
		GameObject furnace = ctx.objects.nearest().poll();
		Item tin = ctx.inventory.select().name("Tin ore").poll();

		if(furnace.inViewport()) {
			tin.click();
	        Condition.sleep(Random.nextInt(100, 300));
			furnace.click();
	        Condition.sleep(Random.nextInt(100, 300));
		} else {
			ctx.movement.step(furnace);
			ctx.camera.turnTo(furnace);
		}
	}

}
