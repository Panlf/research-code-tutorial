package com.plf.disruptor.factory;

import com.lmax.disruptor.EventFactory;
import com.plf.disruptor.model.MessageModel;

/**
 * @author panlf
 * @date 2023/1/19
 */
public class MessageEventFactory implements EventFactory<MessageModel> {
    @Override
    public MessageModel newInstance() {
        return new MessageModel();
    }
}
