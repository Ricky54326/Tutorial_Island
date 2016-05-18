package script.task;

import java.awt.Graphics;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

/**
 * Chop a tree based on the name of a GameObject "Tree"
 * 
 * @author Robby
 *
 */
public class ChopTask extends Task<ClientContext> {

	private GameObject tree;

	public ChopTask(ClientContext ctx) {
		super(ctx);
		this.tree = null;
	}

	@Override
	public boolean activate() {
		return !ctx.objects.select().name("Tree").isEmpty() && ctx.players.local().animation() == -1;
	}

	@Override
	public void execute() {

		tree = ctx.objects.nearest(new Tile(3105, 3095, 0)).poll();

		if (tree.inViewport()) {
			tree.interact("Chop down");
			Condition.sleep(Random.nextInt(500, 800));
		} else {
			ctx.movement.step(tree);
			ctx.camera.turnTo(tree);
		}

	}

	@Override
	public boolean draw() {
		return tree != null;
	}

	@Override
	public void paint(Graphics g1) {
		tree.draw(g1);
	}

}
