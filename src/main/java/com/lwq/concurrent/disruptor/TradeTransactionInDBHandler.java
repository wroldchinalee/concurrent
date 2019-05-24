package com.lwq.concurrent.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

import java.util.UUID;

/**
 * Created by Administrator on 2019/5/22.
 */
public class TradeTransactionInDBHandler implements EventHandler<TradeTransaction>, WorkHandler<TradeTransaction> {
    public void onEvent(TradeTransaction event) throws Exception {
//        event.setId(UUID.randomUUID().toString());
        System.out.println(Thread.currentThread() + "消费数据:" + event.getId());
    }

    public void handle(TradeTransaction event) {

    }

    public void onEvent(TradeTransaction event, long sequence, boolean endOfBatch) throws Exception {
        this.onEvent(event);
    }
}
