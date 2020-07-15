package com.lxk.blockqueue;

import com.google.common.collect.Queues;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * 阻塞队列测试
 * ArrayBlockingQueue
 * LinkedBlockingQueue
 * PriorityBlockingQueue
 * DelayQueue
 * SynchronousQueue
 * LinkedTransferQueue
 * LinkedBlockingDeque
 *
 * @author LiXuekai on 2020/6/15
 */
public class BlockingQueueTest {

    @Test
    public void arrayBlockingQueue() {
        ArrayBlockingQueue queue = Queues.newArrayBlockingQueue(10);
    }

    @Test
    public void LinkedBlockingQueue() {
        LinkedBlockingQueue linkedBlockingQueue = Queues.newLinkedBlockingQueue();
    }

    @Test
    public void PriorityBlockingQueue() {
        PriorityBlockingQueue priorityBlockingQueue = Queues.newPriorityBlockingQueue();

    }

    @Test
    public void DelayQueue() {
        DelayQueue delayQueue = new DelayQueue();
    }

    @Test
    public void SynchronousQueue() {
        SynchronousQueue synchronousQueue = Queues.newSynchronousQueue();
    }

    @Test
    public void LinkedTransferQueue() {
        LinkedTransferQueue linkedTransferQueue = new LinkedTransferQueue();
    }

    @Test
    public void LinkedBlockingDeque() {
        LinkedBlockingDeque linkedBlockingDeque = Queues.newLinkedBlockingDeque();
    }

}
