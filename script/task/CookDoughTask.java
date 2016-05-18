package script.task;

import java.awt.Graphics;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Item;

public class CookDoughTask extends Task<ClientContext> {

	public CookDoughTask(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return !ctx.inventory.select().name("Bread dough").isEmpty() 
				&& ctx.players.local().animation() == -1 
				&& !ctx.objects.select().name("Range").isEmpty();
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
		GameObject range = ctx.objects.nearest().poll();
		Item dough = ctx.inventory.select().name("Bread dough").poll();
		if(range.inViewport()) {
			dough.click();
	        Condition.sleep(Random.nextInt(100, 300));
			range.click();
	        Condition.sleep(Random.nextInt(200, 500));
		} else {
			ctx.movement.step(range);
			ctx.camera.turnTo(range);
		}
	}

}