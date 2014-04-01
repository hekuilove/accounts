package org.quinn.accounts.task;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class QueueTask {

	private final Queue<Thread> queues = new LinkedBlockingDeque<Thread>();

	private boolean isStarted = false;

	private QueueTask() {

	}

	private static QueueTask instance = null;

	public synchronized static QueueTask getInstance() {
		if (instance == null)
			instance = new QueueTask();
		return instance;
	}

	private synchronized void start() {
		if (!this.isStarted) {
			this.isStarted = true;
			while (true) {
				Thread t = this.queues.poll();
				if (t != null)
					t.start();
				else {
					this.stop();
					break;
				}
			}
		}
	}

	private void stop() {
		if (!this.isEmptyQueue())
			this.isStarted = false;
	}

	private boolean isEmptyQueue() {
		synchronized (instance) {
			return this.queues.size() == 0;
		}
	}

	/**
	 * 添加线程任务到队列
	 * @param t
	 */
	public synchronized void add(Thread t) {
		this.queues.add(t);
		if (!isStarted)
			this.start();
	}

}
