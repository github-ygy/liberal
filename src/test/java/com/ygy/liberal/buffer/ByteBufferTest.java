package com.ygy.liberal.buffer;

import org.junit.Assert;
import org.junit.Test;

import java.nio.ByteBuffer;

/**
 *  byte buffer api 学习
 *
 * @author guoyao
 * @date 2019-05-16
 */
public class ByteBufferTest {

    /**
     *  成员变量意义:
     * Capacity :容量，即可以容纳的最大数据量；在缓冲区创建时被设定并且不能改变
     * Limit : 表示缓冲区的当前终点，不能对缓冲区超过极限的位置进行读写操作。且极限是可以修改的
     * Position : 位置，下一个要被读或写的元素的索引，每次读写缓冲区数据时都会改变改值，为下次读写作准备
     * Mark : 标记，调用mark()来设置mark=position，再调用reset()可以让position恢复到标记的位置
     */

    private static final int BUFFER_LENGTH = 1024;

    @Test
    public void testFlip() {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_LENGTH);
        //position = 0 limit = position 常用于写后的读,只能读指定的数据
        buffer.putInt(1); //4个字节
        buffer.putLong(2); //8个字节
        Assert.assertTrue(buffer.limit() == BUFFER_LENGTH);
        Assert.assertTrue(buffer.position() == (4 + 8));
        buffer.flip();
        Assert.assertTrue(buffer.limit() == (4 + 8));
        Assert.assertTrue(buffer.position() == 0);
        Assert.assertTrue(buffer.getInt() == 1);
        Assert.assertTrue(buffer.getLong() == 2);
        Assert.assertTrue(buffer.position() == (4 + 8));
    }

    @Test
    public void testRemaining() {
        // remaining = limit - position
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_LENGTH);
        Assert.assertTrue(buffer.remaining() == BUFFER_LENGTH);
        buffer.limit(100);
        buffer.position(10);
        Assert.assertTrue(buffer.remaining() == (100 -10));
    }

    @Test
    public void testMarkAndReset() {
        //mark标记的position reset后可以还原到mark标记的位置
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_LENGTH);
        buffer.position(10);
        Assert.assertTrue(buffer.position() == 10);
        buffer.mark();
        buffer.position(100);
        Assert.assertTrue(buffer.position() == 100);
        buffer.reset();
        Assert.assertTrue(buffer.position() == 10);
    }

    /**
     *  堆内存分配与直接内存分配
     *
     *  堆内存分配需要从系统内存复制到java内存中,操作大数据时比较耗性能
     *  直接内存分配时较慢,减少内存复制的过程,操作比较快
     */
    @Test
    public void testBuildByteBuffer() {
        //从堆内存初始化数据,分配时间短(需要从系统内存中复制到java内存,大数据操作时性能不及直接内存)
        ByteBuffer allocateHeap = ByteBuffer.allocate(BUFFER_LENGTH);
        Assert.assertEquals(allocateHeap.capacity(), BUFFER_LENGTH);
        Assert.assertEquals(allocateHeap.limit(), BUFFER_LENGTH);
        Assert.assertEquals(allocateHeap.position(), 0);

        //从直接内存中初始化数据,分配时间长,
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(BUFFER_LENGTH);
        Assert.assertEquals(allocateDirect.capacity(), BUFFER_LENGTH);
        Assert.assertEquals(allocateDirect.limit(), BUFFER_LENGTH);
        Assert.assertEquals(allocateDirect.position(), 0);

        byte[] buffer = new byte [BUFFER_LENGTH];
        //包装数据成为buffer,会相互影响
        ByteBuffer wrapBuffer = ByteBuffer.wrap(buffer);
        Assert.assertEquals(wrapBuffer.capacity(), buffer.length);
        Assert.assertEquals(wrapBuffer.limit(), buffer.length);
        Assert.assertEquals(wrapBuffer.position(), 0);

        //包装指定长度的数据
        ByteBuffer wrapOffsetBuffer = ByteBuffer.wrap(buffer, buffer.length >> 1, buffer.length >> 1);
        Assert.assertEquals(wrapOffsetBuffer.capacity(), buffer.length);
        Assert.assertEquals(wrapOffsetBuffer.limit(), buffer.length);
        Assert.assertEquals(wrapOffsetBuffer.position(), buffer.length >> 1);
    }
}
