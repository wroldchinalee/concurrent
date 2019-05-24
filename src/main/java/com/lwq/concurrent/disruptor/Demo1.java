package com.lwq.concurrent.disruptor;

import com.lmax.disruptor.*;

import java.util.UUID;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2019/5/22.
 */
public class Demo1 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int BUFFER_SIZE = 1024;
        int THREAD_NUMBERS = 4;
        final RingBuffer<TradeTransaction> ringBuffer = RingBuffer.createSingleProducer(new EventFactory<TradeTransaction>() {
            public TradeTransaction newInstance() {
                return new TradeTransaction();
            }
        }, BUFFER_SIZE, new YieldingWaitStrategy());

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUMBERS);
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
        // 创建消息处理器
        BatchEventProcessor<TradeTransaction> processor = new BatchEventProcessor<TradeTransaction>(
                ringBuffer, sequenceBarrier, new TradeTransactionInDBHandler());
        // 这一步的目的是让RingBuffer跟踪消费者的状态，如果只有一个消费者可以忽略
        executorService.submit(processor);

        Future<?> future = executorService.submit(new Callable<Void>() {
            public Void call() throws Exception {
                long seq;
                for (int i = 0; i < 1000; i++) {
                    seq = ringBuffer.next();
                    TradeTransaction tradeTransaction = ringBuffer.get(seq);
                    String id = UUID.randomUUID().toString().replaceAll("-", "");
                    tradeTransaction.setId(id);
                    tradeTransaction.setPrice(Math.random() * 9999);
                    ringBuffer.publish(seq);
                    System.out.println(Thread.currentThread() + "生产数据:" + tradeTransaction.getId());
                }
                return null;
            }
        });
        future.get();//等待生产者结束
        Thread.sleep(1000);
        processor.halt();//通知事件处理器可以结束了
        executorService.shutdown();//中止线程
    }
}
