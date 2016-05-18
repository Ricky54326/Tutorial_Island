package script.task;

import java.awt.Graphics;

import org.powerbot.script.ClientAccessor;
import org.powerbot.script.ClientContext;

@SuppressWarnings("rawtypes")
public abstract class Task<C extends ClientContext> extends ClientAccessor<C> {
		
    public Task(C ctx) {
		super(ctx);
	}
    
	public abstract boolean activate();
    public abstract void execute();
	public abstract boolean draw();
	public abstract void paint(Graphics g1);
}
