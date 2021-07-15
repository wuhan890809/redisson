/**
 * Copyright (c) 2013-2021 Nikita Koksharov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.redisson.api;

import org.redisson.client.RedisException;
import org.redisson.client.codec.Codec;

/**
 * Interface for using Redis pipeline feature.
 * <p>
 * All method invocations on objects got through this interface 
 * are batched to separate queue and could be executed later
 * with <code>execute()</code> or <code>executeAsync()</code> methods.
 *
 *
 * @author Nikita Koksharov
 *
 */
public interface RBatch {

    /**
     * Returns stream instance by <code>name</code>
     * 
     * @param <K> type of key
     * @param <V> type of value
     * @param name of stream
     * @return RStream object
     */
    <K, V> RStreamAsync<K, V> getStream(String name);
    
    /**
     * Returns stream instance by <code>name</code>
     * using provided <code>codec</code> for entries.
     * 
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of stream
     * @param codec - codec for entry
     * @return RStream object
     */
    <K, V> RStreamAsync<K, V> getStream(String name, Codec codec);
    
    /**
     * Returns geospatial items holder instance by <code>name</code>.
     * 
     * @param <V> type of object
     * @param name - name of object
     * @return Geo object
     */
    <V> RGeoAsync<V> getGeo(String name);

    /**
     * Returns geospatial items holder instance by <code>name</code>
     * using provided codec for geospatial members.
     *
     * @param <V> type of value
     * @param name - name of object
     * @param codec - codec for value
     * @return Geo object
     */
    <V> RGeoAsync<V> getGeo(String name, Codec codec);
    
    /**
     * Returns Set based MultiMap instance by name.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @return Multimap object
     */
    <K, V> RMultimapAsync<K, V> getSetMultimap(String name);

    /**
     * Returns Set based MultiMap instance by name
     * using provided codec for both map keys and values.
     * 
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @param codec - provided codec
     * @return Multimap object
     */
    <K, V> RMultimapAsync<K, V> getSetMultimap(String name, Codec codec);
    
    /**
     * Returns Set based Multimap instance by name.
     * Supports key-entry eviction with a given TTL value.
     * 
     * <p>If eviction is not required then it's better to use regular map {@link #getSetMultimap(String)}.</p>
     * 
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @return SetMultimapCache object
     */
    <K, V> RMultimapCacheAsync<K, V> getSetMultimapCache(String name);

    /**
     * Returns Set based Multimap instance by name
     * using provided codec for both map keys and values.
     * Supports key-entry eviction with a given TTL value.
     * 
     * <p>If eviction is not required then it's better to use regular map {@link #getSetMultimap(String, Codec)}.</p>
     * 
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @param codec - provided codec
     * @return SetMultimapCache object
     */
    <K, V> RMultimapCacheAsync<K, V> getSetMultimapCache(String name, Codec codec);
    
    /**
     * Returns set-based cache instance by <code>name</code>.
     * Uses map (value_hash, value) under the hood for minimal memory consumption.
     * Supports value eviction with a given TTL value.
     *
     * <p>If eviction is not required then it's better to use regular map {@link #getSet(String, Codec)}.</p>
     *
     * @param <V> type of value
     * @param name - name of object
     * @return SetCache object
     */
    <V> RSetCacheAsync<V> getSetCache(String name);

    /**
     * Returns set-based cache instance by <code>name</code>
     * using provided <code>codec</code> for values.
     * Uses map (value_hash, value) under the hood for minimal memory consumption.
     * Supports value eviction with a given TTL value.
     *
     * <p>If eviction is not required then it's better to use regular map {@link #getSet(String, Codec)}.</p>
     *
     * @param <V> type of value
     * @param name - name of object
     * @param codec - codec for values
     * @return SetCache object
     */
    <V> RSetCacheAsync<V> getSetCache(String name, Codec codec);

    /**
     * Returns map-based cache instance by <code>name</code>
     * using provided <code>codec</code> for both cache keys and values.
     * Supports entry eviction with a given TTL value.
     *
     * <p>If eviction is not required then it's better to use regular map {@link #getMap(String, Codec)}.</p>
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @param codec - codec for keys and values
     * @return MapCache object
     */
    <K, V> RMapCacheAsync<K, V> getMapCache(String name, Codec codec);

    /**
     * Returns map-based cache instance by <code>name</code>.
     * Supports entry eviction with a given TTL value.
     *
     * <p>If eviction is not required then it's better to use regular map {@link #getMap(String)}.</p>
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @return MapCache object
     */
    <K, V> RMapCacheAsync<K, V> getMapCache(String name);

    /**
     * Returns object holder by <code>name</code>
     *
     * @param <V> type of object
     * @param name - name of object
     * @return Bucket object
     */
    <V> RBucketAsync<V> getBucket(String name);

    <V> RBucketAsync<V> getBucket(String name, Codec codec);

    /**
     * Returns HyperLogLog object
     *
     * @param <V> type of object
     * @param name - name of object
     * @return HyperLogLog object
     */
    <V> RHyperLogLogAsync<V> getHyperLogLog(String name);

    <V> RHyperLogLogAsync<V> getHyperLogLog(String name, Codec codec);

    /**
     * Returns list instance by name.
     *
     * @param <V> type of object
     * @param name - name of object
     * @return List object
     */
    <V> RListAsync<V> getList(String name);

    <V> RListAsync<V> getList(String name, Codec codec);

    /**
     * Returns List based MultiMap instance by name.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @return ListMultimap object
     */
    <K, V> RMultimapAsync<K, V> getListMultimap(String name);

    /**
     * Returns List based MultiMap instance by name
     * using provided codec for both map keys and values.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @param codec - codec for keys and values
     * @return ListMultimap object
     */
    <K, V> RMultimapAsync<K, V> getListMultimap(String name, Codec codec);
    
    /**
     * Returns List based Multimap instance by name.
     * Supports key-entry eviction with a given TTL value.
     * 
     * <p>If eviction is not required then it's better to use regular map {@link #getSetMultimap(String)}.</p>
     * 
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @return ListMultimapCache object
     */
    <K, V> RMultimapAsync<K, V> getListMultimapCache(String name);
    
    /**
     * Returns List based Multimap instance by name
     * using provided codec for both map keys and values.
     * Supports key-entry eviction with a given TTL value.
     * 
     * <p>If eviction is not required then it's better to use regular map {@link #getSetMultimap(String, Codec)}.</p>
     * 
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @param codec - codec for keys and values
     * @return ListMultimapCache object
     */
    <K, V> RMultimapAsync<K, V> getListMultimapCache(String name, Codec codec);
    
    /**
     * Returns map instance by name.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @return Map object
     */
    <K, V> RMapAsync<K, V> getMap(String name);

    <K, V> RMapAsync<K, V> getMap(String name, Codec codec);

    /**
     * Returns set instance by name.
     *
     * @param <V> type of value
     * @param name - name of object
     * @return Set object
     */
    <V> RSetAsync<V> getSet(String name);

    <V> RSetAsync<V> getSet(String name, Codec codec);

    /**
     * Returns topic instance by name.
     *
     * @param name - name of object
     * @return Topic object
     */
    RTopicAsync getTopic(String name);

    RTopicAsync getTopic(String name, Codec codec);

    /**
     * Returns queue instance by name.
     *
     * @param <V> type of value
     * @param name - name of object
     * @return Queue object
     */
    <V> RQueueAsync<V> getQueue(String name);

    <V> RQueueAsync<V> getQueue(String name, Codec codec);

    /**
     * Returns blocking queue instance by name.
     *
     * @param <V> type of value
     * @param name - name of object
     * @return BlockingQueue object
     */
    <V> RBlockingQueueAsync<V> getBlockingQueue(String name);

    <V> RBlockingQueueAsync<V> getBlockingQueue(String name, Codec codec);

    /**
     * Returns deque instance by name.
     *
     * @param <V> type of value
     * @param name - name of object
     * @return Deque object
     */
    <V> RDequeAsync<V> getDeque(String name);

    <V> RDequeAsync<V> getDeque(String name, Codec codec);

    /**
     * Returns blocking deque instance by name.
     * 
     * @param <V> type of value
     * @param name - name of object
     * @return BlockingDeque object
     */
    <V> RBlockingDequeAsync<V> getBlockingDeque(String name);

    <V> RBlockingDequeAsync<V> getBlockingDeque(String name, Codec codec);

    /**
     * Returns atomicLong instance by name.
     *
     * @param name - name of object
     * @return AtomicLong object
     */
    RAtomicLongAsync getAtomicLong(String name);

    /**
     * Returns atomicDouble instance by name.
     *
     * @param name - name of object
     * @return AtomicDouble object
     */
    RAtomicDoubleAsync getAtomicDouble(String name);

    /**
     * Returns Redis Sorted Set instance by name
     * 
     * @param <V> type of value
     * @param name - name of object
     * @return ScoredSortedSet object
     */
    <V> RScoredSortedSetAsync<V> getScoredSortedSet(String name);

    <V> RScoredSortedSetAsync<V> getScoredSortedSet(String name, Codec codec);

    /**
     * Returns String based Redis Sorted Set instance by name
     * All elements are inserted with the same score during addition,
     * in order to force lexicographical ordering
     *
     * @param name - name of object
     * @return LexSortedSet object
     */
    RLexSortedSetAsync getLexSortedSet(String name);

    /**
     * Returns bitSet instance by name.
     *
     * @param name - name of object
     * @return BitSet object
     */
    RBitSetAsync getBitSet(String name);

    /**
     * Returns script operations object
     *
     * @return Script object
     */
    RScriptAsync getScript();

    /**
     * Returns script operations object using provided codec.
     * 
     * @param codec - codec for params and result
     * @return Script object
     */
    RScript getScript(Codec codec);
    
    /**
     * Returns keys operations.
     * Each of Redis/Redisson object associated with own key
     *
     * @return Keys object
     */
    RKeysAsync getKeys();

    /**
     * Executes all operations accumulated during async methods invocations.
     * <p>
     * If cluster configuration used then operations are grouped by slot ids
     * and may be executed on different servers. Thus command execution order could be changed
     *
     * @return List with result object for each command
     * @throws RedisException in case of any error
     *
     */
    BatchResult<?> execute() throws RedisException;

    /**
     * Executes all operations accumulated during async methods invocations asynchronously.
     * <p>
     * In cluster configurations operations grouped by slot ids
     * so may be executed on different servers. Thus command execution order could be changed
     *
     * @return List with result object for each command
     */
    RFuture<BatchResult<?>> executeAsync();

    /**
     * Discard batched commands and release allocated buffers used for parameters encoding.
     */
    void discard();

    /**
     * Discard batched commands and release allocated buffers used for parameters encoding.
     *
     * @return void
     */
    RFuture<Void> discardAsync();


}
