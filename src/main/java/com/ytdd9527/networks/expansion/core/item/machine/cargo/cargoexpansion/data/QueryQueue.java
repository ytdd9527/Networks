package com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.data;

import com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.objects.QueuedTask;
import io.github.sefiraat.networks.Networks;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class QueryQueue {

    private final BlockingQueue<QueuedTask> updateTasks;
    private final BlockingQueue<QueuedTask> queryTasks;
    private boolean threadStarted;

    public QueryQueue() {
        // Creat database query processing thread
        updateTasks = new LinkedBlockingDeque<>();
        queryTasks = new LinkedBlockingDeque<>();

        threadStarted = false;
    }

    public synchronized void scheduleUpdate(QueuedTask task) {
        if (!updateTasks.offer(task)) {
            throw new IllegalStateException("无法将任务添加到队列，请尝试重启服务器！");
        }
    }

    public synchronized void scheduleQuery(QueuedTask task) {
        if (!queryTasks.offer(task)) {
            throw new IllegalStateException("无法将任务添加到队列，请尝试重启服务器！");
        }
    }

    public void startThread() {
        if(!threadStarted) {
            getProcessor(queryTasks).runTaskAsynchronously(Networks.getInstance());
            getProcessor(updateTasks).runTaskAsynchronously(Networks.getInstance());
            threadStarted = true;
        }
    }

    public int getTaskAmount() {
        return updateTasks.size() + queryTasks.size();
    }

    public boolean isAllDone() {
        return !threadStarted || getTaskAmount() == 0;
    }

    public void scheduleAbort() {
        QueuedTask abortTask = new QueuedTask() {
            @Override
            public boolean execute() {
                return true;
            }

            @Override
            public boolean callback() {
                return true;
            }
        };
        queryTasks.offer(abortTask);
        updateTasks.offer(abortTask);
    }

    private BukkitRunnable getProcessor(BlockingQueue<QueuedTask> queue) {
        return new BukkitRunnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        QueuedTask task = queue.take();
                        if(task.execute() && task.callback()) {
                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

}
