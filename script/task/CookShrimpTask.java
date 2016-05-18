package script.task;

import java.awt.Graphics;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Item;

public class CookShrimpTask extends Task<ClientContext> {

	public CookShrimpTask(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return !ctx.inventory.select().name("Raw shrimps").isEmpty() && !ctx.objects.select().name("Fire").isEmpty();
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
		GameObject fire = ctx.objects.nearest().poll();
		Item shrimp = ctx.inventory.select().name("Raw shrimps").poll();
		if(fire.inViewport()) {
			shrimp.click();
	        Condition.sleep(Random.nextInt(100, 300));
			fire.interact("Use");
		} else {
			ctx.movement.step(fire);
			ctx.camera.turnTo(fire);
		}
	}

}
