package script.task;

import java.awt.Graphics;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

/**
 * Chop a tree based on the name of a GameObject "Tree"
 * 
 * @author Robby
 *
 */
public class OreTask extends Task<ClientContext> {

	private GameObject ore;
	private int id;
	private String name;
	private String task;

	public OreTask(ClientContext ctx, int id, String name, String task) {
		super(ctx);
		this.ore = null;
		this.id = id;
		this.name = name;
		this.task = task;
	}

	@Override
	public boolean activate() {
		return !ctx.objects.select().id(id).name(name).isEmpty() && ctx.players.local().animation() == -1;
	}

	@Override
	public void execute() {

		ore = ctx.objects.nearest().poll();

		if (ore.inViewport()) {
			ore.interact(task);
			Condition.sleep(Random.nextInt(500, 800));
		} else {
			ctx.movement.step(ore);
			ctx.camera.turnTo(ore);
		}

	}

	@Override
	public boolean draw() {
		return ore != null;
	}

	@Override
	public void paint(Graphics g1) {
		ore.draw(g1);
	}

}
